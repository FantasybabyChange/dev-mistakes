package com.fantasybaby.dee.code.equals;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author fanta
 * @Description
 * @create 2021-01-14 23:01
 */

@Data
@Slf4j
@EqualsAndHashCode(callSuper = true)
public class Employee extends Person {

    private String company;

    public Employee(String name, String identity, String company) {
        super(name, identity);
        this.company = company;
    }

    public static void main(String[] args) {
        Employee employee1 = new Employee("zhuye","001", "bkjk.com");
        Employee employee2 = new Employee("Joseph","002", "bkjk.com");
        log.info("employee1.equals(employee2) ? {}", employee1.equals(employee2));
    }
}