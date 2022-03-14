package com.fantasybaby.dee.code.equals;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author fantasybaby
 * @Description
 * @create 2021-01-14 22:59
 */
@Data
@Slf4j
public class Person {
    @EqualsAndHashCode.Exclude
    private String name;
    private String identity;

    public Person(String name, String identity) {
        this.name = name;
        this.identity = identity;
    }

    public static void main(String[] args) {
        Person person1 = new Person("zhuye", "001");
        Person person2 = new Person("Joseph", "001");
        log.info("person1.equals(person2) ? {}", person1.equals(person2));
    }
}
