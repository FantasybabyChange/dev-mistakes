package com.fantasybaby.dee.code.spring.aopfeign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "anotherClient", url = "http://localhost:7777")
public interface ClientWithUrl {
    @GetMapping("/feignaop/server")
    String api();
}
