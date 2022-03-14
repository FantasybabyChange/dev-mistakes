package com.fantasybaby.dee.code.list;

import java.util.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author fanta
 * @Description
 * @create 2021-01-24 18:45
 */
public class SubListOOM {
    private static List<List<Integer>> data = new ArrayList<>();

    /**
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     */
    public void oom() {
        for (int i = 0; i < 10000; i++) {
            List<Integer> rawList = IntStream.rangeClosed(1, 100000).boxed().collect(Collectors.toList());
            data.add(rawList.subList(0, 1));
        }
    }

    public void deleteAndPutWrong() {
        List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
//        List<Integer> subList = list.subList(1, 4);
        //方式一：
        List<Integer> subList = new ArrayList<>(list.subList(1, 4));

        //方式二：
//        List<Integer> subList = list.stream().skip(1).limit(3).collect(Collectors.toList());
        System.out.println(subList);
        //删掉后 会影响原来的list
        subList.remove(1);
        System.out.println(list);
        list.add(0);
        try {
            subList.forEach(System.out::println);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SubListOOM sl = new SubListOOM();
//        sl.oom();
        sl.deleteAndPutWrong();
    }
}



