package com.fantasybaby.dee.code.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 确认线程池是否是复用
 */
@Slf4j
public class ThreadPoolPolicyReused {
    public void executeTask(ThreadPoolExecutor threadPool) {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            threadPool.execute(() -> {
                String payload = IntStream.rangeClosed(1, 1000000)
                        .mapToObj(__ -> "a")
                        .collect(Collectors.joining("")) + UUID.randomUUID().toString();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                log.debug(payload);
            });
        });
    }

    public static class ThreadPoolHelper {
        private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10, 50,
                2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000),
                new ThreadFactoryBuilder().setNameFormat("demo-threadpool-%d").build());

        public static ThreadPoolExecutor getThreadPool() {
            return (ThreadPoolExecutor) Executors.newCachedThreadPool();
        }

        public static ThreadPoolExecutor getRightThreadPool() {
            return threadPoolExecutor;
        }
    }
}
