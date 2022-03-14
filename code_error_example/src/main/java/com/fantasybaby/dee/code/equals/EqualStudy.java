package com.fantasybaby.dee.code.equals;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 如何让系统知道自己是自己
 * @author fantasybaby
 * @Description
 * @create 2021-01-10 17:56
 */
@Slf4j
public class EqualStudy {
    List<String> list = new ArrayList<>();

    /**
     * 使用intern 如果不设置字符串常量池大小 性能很差
     * @param size
     * @return
     */
    public int internPerformance(int size) {
        //-XX:+PrintStringTableStatistics
        //-XX:StringTableSize=10000000
        long begin = System.currentTimeMillis();
        list = IntStream.rangeClosed(1, size)
                .mapToObj(i-> String.valueOf(i).intern())
                .collect(Collectors.toList());
        log.info("size:{} took:{}", size, System.currentTimeMillis() - begin);
        return list.size();
    }
    public int mapPerformance(int size) {
        //-XX:+PrintStringTableStatistics
        //-XX:StringTableSize=10000000
        long begin = System.currentTimeMillis();
        list = IntStream.rangeClosed(1, size)
                .mapToObj(i-> String.valueOf(i))
                .collect(Collectors.toList());
        log.info("size:{} took:{}", size, System.currentTimeMillis() - begin);
        return list.size();
    }

    public static void main(String[] args) {
        new EqualStudy().internPerformance(10000000);
//        new EqualStudy().mapPerformance(10000000);
    }
}
