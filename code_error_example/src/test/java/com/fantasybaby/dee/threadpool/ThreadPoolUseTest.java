package com.fantasybaby.dee.threadpool;

import com.fantasybaby.dee.code.threadpool.ThreadPoolPolicyReused;
import org.junit.Test;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author fanta
 * @Description
 * @create 2020-10-21 22:58
 */
public class ThreadPoolUseTest {
    /**
     * 没有线程复用会创建很多新的线程
     */
    @Test
    public void testThreadPoolPolicyUseWrong() {
        ThreadPoolPolicyReused threadPoolPolicyReused = new ThreadPoolPolicyReused();
        IntStream.rangeClosed(0,100).forEach(i->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ThreadPoolExecutor threadPool = ThreadPoolPolicyReused.ThreadPoolHelper.getThreadPool();
            threadPoolPolicyReused.executeTask(threadPool);
        });
        while (true){

        }
    }

    /**
     * 使用静态线程防止线程池没有被复用
     */
    @Test
    public void testThreadPoolPolicyUseRight() {
        ThreadPoolPolicyReused threadPoolPolicyReused = new ThreadPoolPolicyReused();
        IntStream.rangeClosed(0,100).forEach(i->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ThreadPoolExecutor threadPool = ThreadPoolPolicyReused.ThreadPoolHelper.getRightThreadPool();
            threadPoolPolicyReused.executeTask(threadPool);
        });
    }
}
