package com.fantasybaby.dev.error.design.nosqluse.influxdbvsmysql;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RestController
@Slf4j
@RequestMapping("influxdbvsmysql")
public class PerformanceController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("mysql")
    public void mysql() {
        long begin = System.currentTimeMillis();
        Object result = jdbcTemplate.queryForList("SELECT date_format(time,'%Y%m%d%H'),max(value),min(value),avg(value) FROM m WHERE time>now()- INTERVAL 60 DAY GROUP BY date_format(time,'%Y%m%d%H')");
        log.info("took {} ms result {}", System.currentTimeMillis() - begin, result);
    }

    @GetMapping("influxdb")
    public void influxdb() {
     /*   long begin = System.currentTimeMillis();
        // You can generate an API token from the "API Tokens Tab" in the UI
        String token = "J5Tb5gSCMEhQ6pgH_f-dtwOpUJna92bpNqWCY2hjir8P_4yzQxaHwijr2p6cs1ntiZ2lHCPmWzdi_HyWmggMPA==";
        String bucket = "influxdb";
        String org = "fantasybaby";
        InfluxDBClient client = InfluxDBClientFactory.create("http://192.168.3.12:8086", token.toCharArray());
        client
        try (InfluxDB influxDB = InfluxDBFactory.connect("http://127.0.0.1:8086", "root", "root")) {
            influxDB.setDatabase("performance");
            Object result = influxDB.query(new Query("SELECT MEAN(value),MIN(value),MAX(value) FROM m WHERE time > now() - 60d GROUP BY TIME(1h)"));
            log.info("took {} ms result {}", System.currentTimeMillis() - begin, result);
        }*/

    }

    @GetMapping("influxdbwrong")
    public void influxdbwrong() {
      /*  OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient().newBuilder()
                .connectTimeout(1, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);
        try (InfluxDB influxDB = InfluxDBFactory.connect("http://127.0.0.1:8086", "root", "root", okHttpClientBuilder)) {
            influxDB.setDatabase("performance");
            IntStream.rangeClosed(1, 10000).forEach(i -> {
                Map<String, String> tags = new HashMap<>();
                IntStream.rangeClosed(1, 10).forEach(j -> tags.put("tagkey" + i, "tagvalue" + ThreadLocalRandom.current().nextInt(100000)));
                Point point = Point.measurement("bad")
                        .tag(tags)
                        .addField("value", ThreadLocalRandom.current().nextInt(10000))
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .build();
                influxDB.write(point);
            });
    }*/
    }
}
