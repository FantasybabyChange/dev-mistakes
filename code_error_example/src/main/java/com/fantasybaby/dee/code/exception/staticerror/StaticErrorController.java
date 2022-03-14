package com.fantasybaby.dee.code.exception.staticerror;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanta
 * @Description
 * @create 2021-03-01 22:03
 */
@RestController
@RequestMapping("static-error")
@Slf4j
public class StaticErrorController {
    /**
     * 使用静态异常,会导致异常抛出栈信息混乱
     */
    @GetMapping("static-wrong")
    public void staticWrong() {
        try {
            createOrderWrong();
        } catch (Exception ex) {
            log.error("createOrder got error", ex);
        }
        try {
            cancelOrderWrong();
        } catch (Exception ex) {
            log.error("cancelOrder got error", ex);
        }
    }

    /**
     * 不建议
     */
    private void createOrderWrong() {
        //31行有问题
        throw StaticException.ORDEREXISTS;
    }

    private void cancelOrderWrong() {
        //36行有问题
        throw StaticException.ORDEREXISTS;
    }
}
