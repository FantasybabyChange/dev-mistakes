package com.fantasybaby.dee.code.locktest;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
public class LockNotInSameLayer {
    @Getter
    private static int counter = 0;

    public static int reset() {
        counter = 0;
        return counter;
    }

    /**
     * 所在非静态方法 新的instance将会失效
     */
    public synchronized void wrong() {
        counter++;
    }
    static Object lock = new Object();
    /**
     * 所在非静态方法 新的instance将会失效
     */
    public void right() {
        synchronized (lock) {
            counter++;
        }
    }
}
