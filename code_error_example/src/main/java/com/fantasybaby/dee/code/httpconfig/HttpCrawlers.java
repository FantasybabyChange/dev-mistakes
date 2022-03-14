package com.fantasybaby.dee.code.httpconfig;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * 爬虫使用不当
 *
 * @author fantasybaby
 */
@Slf4j
public class HttpCrawlers {
    static CloseableHttpClient httpClient1;
    static CloseableHttpClient httpClient2;

    static {
        httpClient1 = HttpClients.custom().setConnectionManager(new PoolingHttpClientConnectionManager()).build();
        httpClient2 = HttpClients.custom().setMaxConnTotal(50).setMaxConnPerRoute(20).build();
    }

    private int sendRequest(int count, Supplier<CloseableHttpClient> client) throws InterruptedException {
        //用于计数发送的请求个数
        AtomicInteger atomicInteger = new AtomicInteger();
        //使用HttpClient从server接口查询数据的任务提交到线程池并行处理
        ExecutorService threadPool = Executors.newCachedThreadPool();
        long begin = System.currentTimeMillis();
        IntStream.rangeClosed(1, count).forEach(i -> {
            threadPool.execute(() -> {
                try (CloseableHttpResponse response = client.get().execute(new HttpGet("http://127.0.0.1:7888/slow/server"))) {
                    atomicInteger.addAndGet(Integer.parseInt(EntityUtils.toString(response.getEntity())));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        });
        //等到count个任务全部执行完毕
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
        log.info("发送 {} 次请求，耗时 {} ms", atomicInteger.get(), System.currentTimeMillis() - begin);
        return atomicInteger.get();
    }

    /**
     * 使用PoolingHttpClientConnectionManager 10个请求是5s
     *
     * @param count
     * @return
     * @throws InterruptedException
     */
    public int wrong(int count) throws InterruptedException {
        return sendRequest(count, () -> httpClient1);
    }

    public int right(int count) throws InterruptedException {
        return sendRequest(count, () -> httpClient2);
    }

    public static void main(String[] args) {
        try {
            new HttpCrawlers().wrong(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            new HttpCrawlers().right(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
