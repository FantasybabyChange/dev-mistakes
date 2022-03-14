package com.fantasybaby.dee.code.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RequestMapping("/slow/")
@RestController
@Slf4j
public class SlowController {
    @GetMapping("server")
    public int server() throws InterruptedException {
        log.info("in the server");
        TimeUnit.SECONDS.sleep(1);
        return 1;
    }
}
