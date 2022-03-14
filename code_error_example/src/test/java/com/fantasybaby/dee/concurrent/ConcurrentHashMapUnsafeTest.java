package com.fantasybaby.dee.concurrent;

import com.fantasybaby.dee.code.concurrent.ConcurrentHashMapUnsafe;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

import java.util.Map;
@Slf4j
public class ConcurrentHashMapUnsafeTest {
    ConcurrentHashMapUnsafe map;
    @Before
    public void init(){
        map = new ConcurrentHashMapUnsafe();
    }
    @Test
    public void testWrong(){
        try {
            map.unsafePut();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testSafe(){
        try {
            map.safePut();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testLockAdd(){
        try {
            Map<String, Long> stringLongMap = map.useLockToAdd();
            System.out.println(stringLongMap.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testUnlockAdd(){
        try {
            Map<String, Long> stringLongMap = map.casToAdd();
            System.out.println(stringLongMap.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPerformance() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("normaluse");
        Map<String, Long> normaluse = map.useLockToAdd();
        stopWatch.stop();
        //校验元素数量
        Assert.isTrue(normaluse.size() == ConcurrentHashMapUnsafe.ITEM_COUNT, "normaluse size error");
        //校验累计总数
        Assert.isTrue(normaluse.entrySet().stream()
                        .mapToLong(item -> item.getValue()).reduce(0, Long::sum) == ConcurrentHashMapUnsafe.LOOP_COUNT
                , "normaluse count error");
        stopWatch.start("gooduse");
        Map<String, Long> gooduse = map.casToAdd();
        stopWatch.stop();
        Assert.isTrue(gooduse.size() == ConcurrentHashMapUnsafe.ITEM_COUNT, "gooduse size error");
        Assert.isTrue(gooduse.entrySet().stream()
                        .mapToLong(item -> item.getValue())
                        .reduce(0, Long::sum) == ConcurrentHashMapUnsafe.LOOP_COUNT
                , "gooduse count error");
        log.info(stopWatch.prettyPrint());
    }
}
