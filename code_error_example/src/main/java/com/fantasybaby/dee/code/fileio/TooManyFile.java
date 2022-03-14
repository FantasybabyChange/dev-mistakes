package com.fantasybaby.dee.code.fileio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

/**
 * 文件过大 oom的问题
 * Created on 4/9/2021.
 *
 * @author Reid Liu
 */
@Slf4j
public class TooManyFile {
    public static void writeSimpleFile() throws IOException {
        Files.write(Paths.get("demo.txt"),
                IntStream.rangeClosed(1, 2000000).mapToObj(i -> UUID.randomUUID().toString()).collect(Collectors.toList())
                , UTF_8, CREATE, TRUNCATE_EXISTING);
    }

    private static void readFileLimit() throws IOException {
        //输出文件大小
        log.info("file size:{}", Files.size(Paths.get("demo.txt")));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("read 200000 lines");
        //使用Files.lines方法读取20万行数据
        log.info("lines {}", Files.lines(Paths.get("demo.txt")).limit(200000).collect(Collectors.toList()).size());
        stopWatch.stop();
        stopWatch.start("read 2000000 lines");
        //使用Files.lines方法读取200万行数据
        log.info("lines {}", Files.lines(Paths.get("demo.txt")).limit(2000000).collect(Collectors.toList()).size());
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        AtomicLong atomicLong = new AtomicLong();
        //使用Files.lines方法统计文件总行数
        Files.lines(Paths.get("demo.txt")).forEach(line -> atomicLong.incrementAndGet());
        log.info("total lines {}", atomicLong.get());
    }

    /**
     * too many file
     */
    public static void readMultiple() {
        LongAdder longAdder = new LongAdder();
        IntStream.rangeClosed(1, 1000000).forEach(i -> {
            try {
                Files.lines(Paths.get("demo.txt")).forEach(line -> longAdder.increment());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        log.info("total : {}", longAdder.longValue());
    }

    /**
     * 使用try catch 来释放资源
     */
    public static void releaseMultiple() {

        LongAdder longAdder = new LongAdder();
        IntStream.rangeClosed(1, 1000000).forEach(i -> {
            try (Stream<String> lines = Files.lines(Paths.get("demo.txt"))) {
                lines.forEach(line -> longAdder.increment());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        log.info("total : {}", longAdder.longValue());
    }

    /**
     * read all line
     * 会OOM
     *
     * @throws IOException
     */
    private static void readErrorAllLines() throws IOException {
        log.info("result: {}", Files.readAllLines(Paths.get("demo.txt")));
    }

    public static void main(String[] args) throws IOException {
        writeSimpleFile();
        readMultiple();
        readErrorAllLines();
        readFileLimit();

//        releaseMultiple();
    }
}
