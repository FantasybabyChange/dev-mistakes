package com.fantasybaby.dee.code.locktest;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

/**
 * 不在一个层的锁
 * @author fantasybaby
 */
@Slf4j
public class NoLockAndThreadUnSafe {
    volatile int a = 1;
    volatile int b = 1;

    /**
     * 加 synchronized也没用
     */
    public synchronized  void add() {
        log.info("add start");
        IntStream.rangeClosed(0,100000).forEach(i->{
            a++;
            b++;
        });
        log.info("add done");
    }

    /**
     * 都加锁有用
     */
    public synchronized  void compare() {
        log.info("compare start");
        IntStream.rangeClosed(0,100000).forEach(i->{
            //a始终等于b吗？
            if (a < b) {
                log.info("a:{},b:{},{}", a, b, a > b);
                //最后的a>b应该始终是false吗？
            }
        });
        log.info("compare done");
    }

}
