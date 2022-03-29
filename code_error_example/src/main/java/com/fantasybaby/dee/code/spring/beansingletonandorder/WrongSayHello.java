package com.fantasybaby.dee.code.spring.beansingletonandorder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WrongSayHello extends WrongSayService {

    @Override
    public void say() {
        super.say();
        log.info("hello");
    }
}
