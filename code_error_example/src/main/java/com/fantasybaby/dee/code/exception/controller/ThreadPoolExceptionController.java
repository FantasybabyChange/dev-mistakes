package com.fantasybaby.dee.code.exception.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author fanta
 * @Description
 * @create 2021-02-22 21:44
 */
@Slf4j

@RestController
@RequestMapping("thread-pool")
public class ThreadPoolExceptionController {
    static {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> log.error("Thread {} got exception", thread, throwable));
    }

    private String prefix = "test";
    ExecutorService threadPool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue(),
            new ThreadFactoryBuilder().setNameFormat(prefix + "%d").setUncaughtExceptionHandler((thread, throwable) -> log.error("Thread {} got exception", thread, throwable)).build());
    private int magicNum = 5;

    @GetMapping("execute")
    public void execute() throws InterruptedException {
        //提交10个任务到线程池处理，第5个任务会抛出运行时异常 线程会停止
        IntStream.rangeClosed(1, 10).forEach(i -> threadPool.execute(() -> {
            if (i == magicNum) {
                throw new RuntimeException("error");
            }
            log.info("I'm done : {}", i);
        }));
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }

    /**
     * 异常会被生吞
     *
     * @throws InterruptedException
     */
    @GetMapping("submit")
    public void submit() throws InterruptedException {
        //提交10个任务到线程池处理，第5个任务会抛出运行时异常 线程会停止
        IntStream.rangeClosed(1, 10).forEach(i -> threadPool.submit(() -> {
            if (i == magicNum) {
                throw new RuntimeException("error");
            }
            log.info("I'm done : {}", i);
        }));
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }

    /**
     * 通过future get触发异常
     */
    @GetMapping("submit-get")
    public void submitGet() {
        List<Future> tasks = IntStream.rangeClosed(1, 10).mapToObj(i -> threadPool.submit(() -> {
            if (i == magicNum) {
                throw new RuntimeException("error");
            }
            log.info("I'm done : {}", i);
        })).collect(Collectors.toList());

        tasks.forEach(task -> {
            try {
                task.get();
            } catch (Exception e) {
                log.error("Got exception", e);
            }
        });
    }
}
