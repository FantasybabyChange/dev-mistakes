package com.fantasybaby.dee.code.spring.beansingletonandorder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("beansingletonandorder")
public class BeanSingletonAndOrderController {

    @Autowired
    List<SayService> sayServiceList;
    @Autowired
    List<WrongSayService> wrongSayServiceList;
    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("test")
    public void test() {
        log.info("====================");
        sayServiceList.forEach(SayService::say);
    }

    @GetMapping("test-wrong")
    public void testWrong() {
        log.info("====================");
        wrongSayServiceList.forEach(WrongSayService::say);
    }

    @GetMapping("test2")
    public void test2() {
        log.info("====================");
        applicationContext.getBeansOfType(WrongSayService.class).values().forEach(WrongSayService::say);
    }
}
