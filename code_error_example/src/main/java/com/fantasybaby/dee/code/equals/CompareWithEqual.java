package com.fantasybaby.dee.code.equals;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author fanta
 * @Description
 * @create 2021-01-14 22:42
 */
@Slf4j
public class CompareWithEqual {
    public void wrong() {

        List<Student> list = new ArrayList<>();
        list.add(new Student(1, "zhang"));
        list.add(new Student(2, "wang"));
        Student student = new Student(2, "li");

        log.info("ArrayList.indexOf");
        int index1 = list.indexOf(student);
        Collections.sort(list);
        log.info("Collections.binarySearch");
        int index2 = Collections.binarySearch(list, student);

        log.info("index1 = " + index1);
        log.info("index2 = " + index2);
    }

    public static void main(String[] args) {
        new CompareWithEqual().wrong();
    }
}
