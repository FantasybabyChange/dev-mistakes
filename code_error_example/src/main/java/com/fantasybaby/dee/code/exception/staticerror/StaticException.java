package com.fantasybaby.dee.code.exception.staticerror;

import com.fantasybaby.dee.code.exception.common.BusinessException;

/**
 * static exception
 * @author fantasybaby
 */
public class StaticException {
    /**
     * 使用静态变量有问题
     */
    public static BusinessException ORDEREXISTS = new BusinessException("订单已经存在", 3001);

    /**
     * right 抛出异常应该是new出来的
     * @return
     */
    public static BusinessException orderExists(){
        return new BusinessException("订单已经存在", 3001);
    }
}
