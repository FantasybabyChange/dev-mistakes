package com.fantasybaby.dee.code.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**自定义激进线程池
 *	 由于线程池在工作队列满了无法入队的情况下会扩容线程池，那么我们是否可以重写队列的 offer 方法，造成这个队列已满的假象呢？
 *   由于我们 Hack 了队列，在达到了最大线程后势必会触发拒绝策略，那么能否实现一个自定义的拒绝策略处理程序，这个时候再把任务真正插入队列呢
 * @author fanta
 * @Description
 * @create 2020-10-21 22:17
 */
@Slf4j
public class CustomerThreadPool {
    //这里开始是激进线程池的实现
    BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(5) {
        @Override
        public boolean offer(Runnable e) {
            //先返回false，造成队列满的假象，让线程池优先扩容
//            return super.offer(e);
            return false;
        }
    };
    ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            2, 5,
            5, TimeUnit.SECONDS,
            queue, new ThreadFactoryBuilder().setNameFormat("demo-threadpool-%d").build(), (r, executor) -> {
        try {
            //等出现拒绝后再加入队列
//            executor.getQueue().put(r);
            //如果希望队列满了阻塞线程而不是抛出异常，那么可以注释掉下面三行代码，修改为;
            if (!executor.getQueue().offer(r, 0, TimeUnit.SECONDS)) {
                throw new RejectedExecutionException("ThreadPool queue full, failed to offer " + r.toString());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    });

    public void monitorCustomerThreadPool(){
        ThreadMonitor.printStats(threadPool);
        //每秒提交一个任务，每个任务耗时10秒执行完成，一共提交20个任务

        //任务编号计数器
        AtomicInteger atomicInteger = new AtomicInteger();

        IntStream.rangeClosed(1, 20).forEach(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int id = atomicInteger.incrementAndGet();
            try {
                threadPool.submit(() -> {
                    log.info("{} started", id);
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                    }
                    log.info("{} finished", id);
                });
            } catch (Exception ex) {
                log.error("error submitting task {}", id, ex);
                atomicInteger.decrementAndGet();
            }
        });

        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
