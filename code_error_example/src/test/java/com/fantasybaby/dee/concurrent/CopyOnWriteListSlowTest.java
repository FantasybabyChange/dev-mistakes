package com.fantasybaby.dee.concurrent;

import com.fantasybaby.dee.code.concurrent.CopyOnWriteListSlow;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 2020/10/13
 *
 * @authorfantasybaby
 **/
@Slf4j
public class CopyOnWriteListSlowTest {
    private CopyOnWriteListSlow cw;
    @Before
    public void init(){
        cw = new CopyOnWriteListSlow();
    }
    @Test
    public void testWrite(){
        cw.testWrite();
    }
    @Test
    public void testRead(){
        cw.testRead();
    }

    @Test
    public void testCode(){
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());

//        list.addAll(IntStream.rangeClosed(1, 1000000).boxed().collect(Collectors.toList()));
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }
    }
}
