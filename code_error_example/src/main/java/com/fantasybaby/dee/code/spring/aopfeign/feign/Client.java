package com.fantasybaby.dee.code.spring.aopfeign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "client")
public interface Client {
    @GetMapping("/feignaop/server")
    String api();

    @GetMapping("/feignaop/server1")
    String api2();
}
