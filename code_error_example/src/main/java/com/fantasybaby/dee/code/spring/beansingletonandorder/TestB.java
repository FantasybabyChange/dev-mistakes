package com.fantasybaby.dee.code.spring.beansingletonandorder;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestB {
//    @Autowired
    @Getter
    private TestA testA;
}
