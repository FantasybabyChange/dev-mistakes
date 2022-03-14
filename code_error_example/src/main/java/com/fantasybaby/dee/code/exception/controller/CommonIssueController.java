package com.fantasybaby.dee.code.exception.controller;

import com.fantasybaby.dee.code.exception.common.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author fanta
 * @Description
 * @create 2021-02-22 21:44
 */
@Slf4j

@RestController
@RequestMapping("exception")
public class CommonIssueController {
    @GetMapping("exception")
    public void exception(@RequestParam("business") boolean b) {
        if (b) {
            throw new BusinessException("订单不存在", 2001);
        }
        throw new RuntimeException("系统错误");
    }

    @GetMapping("wrong1")
    public void wrong1() {
        try {
            readFile();
        } catch (IOException e) {
            throw new RuntimeException("系统忙请稍后再试");
        }
    }

    /**
     * 保留记录异常信息
     */
    @GetMapping("wrong2")
    public void wrong2() {
        try {
            readFile();
        } catch (IOException e) {
            log.error("文件读取错误, {}", e.getMessage());
            throw new RuntimeException("系统忙请稍后再试");
        }
    }

    /**
     * 不要抛出异常不带信息
     * @param orderId
     */
    @GetMapping("wrong3")
    public void wrong3(@RequestParam("orderId") String orderId) {
        try {
            readFile();
        } catch (Exception e) {
            log.error("文件读取错误", e);
            throw new RuntimeException();
        }
    }

    @GetMapping("right1")
    public void right1() {
        try {
            readFile();
        } catch (IOException e) {
            log.error("文件读取错误", e);
            throw new RuntimeException("系统忙请稍后再试");
        }
    }

    @GetMapping("right2")
    public void right2() {
        try {
            readFile();
        } catch (IOException e) {
            throw new RuntimeException("系统忙请稍后再试", e);
        }
    }


    private void readFile() throws IOException {
        Files.readAllLines(Paths.get("a_file"));
    }
}
