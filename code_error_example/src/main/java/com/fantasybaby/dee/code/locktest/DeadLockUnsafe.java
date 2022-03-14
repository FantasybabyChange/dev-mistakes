package com.fantasybaby.dee.code.locktest;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * 造成死锁的场景
 *
 * @author fantasybaby
 * @date 2020/10/16
 */
@Slf4j
public class DeadLockUnsafe {
    @Getter
    private Map<String, Sku> skus;

    public DeadLockUnsafe() {
        skus = IntStream.rangeClosed(0, 10).parallel().mapToObj(i -> Sku.builder().name("item" + i).
                remaining(1000).rl(new ReentrantLock()).build())
                .collect(Collectors.toConcurrentMap(Sku::getName, Function.identity()));
    }


    public List<Sku> createCart() {
        return IntStream.rangeClosed(1, 3)
                .mapToObj(i -> "item" + ThreadLocalRandom.current().nextInt(skus.size()))
                .map(name -> skus.get(name)).collect(Collectors.toList());
    }

    public boolean createOrder(List<Sku> skus) {
        List<ReentrantLock> rls = Lists.newArrayList();
        for (Sku sku : skus) {
            try {
                if (sku.getRl().tryLock(10, TimeUnit.SECONDS)) {
                    rls.add(sku.getRl());
                } else {
                    rls.forEach(ReentrantLock::unlock);
                    return false;
                }
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }
        try {
            /**
             * 获取锁 减少
             */
            skus.forEach(sku -> sku.reduce());
        } finally {
            rls.forEach(ReentrantLock::unlock);
        }
        return true;
    }
}
