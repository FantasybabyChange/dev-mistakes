package com.fantasybaby.dee.code.spring.aopfeign;

import com.fantasybaby.dee.code.spring.aopfeign.feign.Client;
import com.fantasybaby.dee.code.spring.aopfeign.feign.ClientWithUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("feignaop")
@RestController
public class FeignAopConntroller {

    @Autowired
    private Client client;

    @Autowired
    private ClientWithUrl clientWithUrl;

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("client")
    public String client() {
        return client.api();
    }
    @GetMapping("client1")
    public String client1() {
        return client.api2();
    }

    @GetMapping("clientWithUrl")
    public String clientWithUrl() {
        return clientWithUrl.api();
    }

    @GetMapping("server")
    public String server() {
        return "OK";
    }
    @GetMapping("server1")
    public String server1() {
        return "hehe";
    }
}
