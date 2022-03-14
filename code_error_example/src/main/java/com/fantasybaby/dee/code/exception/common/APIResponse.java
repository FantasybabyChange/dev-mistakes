package com.fantasybaby.dee.code.exception.common;

/**
 * @author fanta
 * @Description
 * @create 2021-02-22 21:41
 */

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class APIResponse<T> {
    private boolean success;
    private T data;
    private int code;
    private String message;
}