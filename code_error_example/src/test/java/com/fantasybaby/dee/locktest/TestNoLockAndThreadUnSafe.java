package com.fantasybaby.dee.locktest;

import com.fantasybaby.dee.code.locktest.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
public class TestNoLockAndThreadUnSafe {
    NoLockAndThreadUnSafe lt;
    LockNotInSameLayer lsl;
    LockingGranularity lg;
    DeadLockUnsafe del;

    @Before
    public void init() {
        lt = new NoLockAndThreadUnSafe();
        lsl = new LockNotInSameLayer();
        lg = new LockingGranularity();
        del = new DeadLockUnsafe();
    }

    @Test
    public void unsafeTest() {
        Thread th = new Thread(() -> lt.add());
        Thread th0 = new Thread(() -> lt.compare());
        th.start();
        th0.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void notInSameLayer() {
        LockNotInSameLayer.reset();
        IntStream.rangeClosed(0, 100000).parallel().forEach(i -> {
            new LockNotInSameLayer().wrong();
        });
        log.info(LockNotInSameLayer.getCounter() + "");
    }

    @Test
    public void notInSameLayerRight() {
        LockNotInSameLayer.reset();
        IntStream.rangeClosed(0, 100000).parallel().forEach(i -> {
            new LockNotInSameLayer().right();
        });
        log.info(LockNotInSameLayer.getCounter() + "");
    }

    @Test
    public void lockGranularity() {
        lg.wrong();

        lg.right();
    }

    @Test
    public void deadlockWrong() {
        long begin = System.currentTimeMillis();
        //并发进行100次下单操作，统计成功次数
        long success = IntStream.rangeClosed(1, 100).parallel()
                .mapToObj(i -> {
                    List<Sku> cart = del.createCart();
                    return del.createOrder(cart);
                })
                .filter(result -> result)
                .count();
        del.getSkus().entrySet().stream().map(kv->kv.getValue());
        log.info("success:{} totalRemaining:{} took:{}ms items:{}",
                success,
                del.getSkus().entrySet().stream().map(item -> item.getValue().getRemaining()).reduce(0, Integer::sum),
                System.currentTimeMillis() - begin, del.getSkus());

    }
    @Test
    public void deadlockRight() {
        long begin = System.currentTimeMillis();
        //并发进行100次下单操作，统计成功次数
        long success = IntStream.rangeClosed(1, 100).parallel()
                .mapToObj(i -> {
                    List<Sku> cart = del.createCart();
                    cart.sort(Comparator.comparing(Sku::getName));
                    return del.createOrder(cart);
                })
                .filter(result -> result)
                .count();
        del.getSkus().entrySet().stream().map(kv->kv.getValue());
        log.info("success:{} totalRemaining:{} took:{}ms items:{}",
                success,
                del.getSkus().entrySet().stream().map(item -> item.getValue().getRemaining()).reduce(0, Integer::sum),
                System.currentTimeMillis() - begin, del.getSkus());

    }
}
