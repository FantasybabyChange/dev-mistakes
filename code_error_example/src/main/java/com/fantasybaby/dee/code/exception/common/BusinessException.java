package com.fantasybaby.dee.code.exception.common;

/**
 * @author fanta
 * @Description
 * @create 2021-02-22 21:42
 */
public class BusinessException extends RuntimeException {
    private int code;

    public BusinessException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
