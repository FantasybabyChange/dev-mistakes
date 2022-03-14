package com.fantasybaby.dee.code.precision;

import java.math.BigInteger;

/**
 * @author fanta
 * @Description
 * @create 2021-01-23 21:17
 */
public class ValueOverflow {
    public void overThrowException() {
        try {
            long l = Long.MAX_VALUE;
            System.out.println(Math.addExact(l, 1));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void useBigInteger() {
        BigInteger i = new BigInteger(String.valueOf(Long.MAX_VALUE));
        System.out.println(i.add(BigInteger.ONE).toString());
        try {
            long l = i.add(BigInteger.ONE).longValueExact();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ValueOverflow vo = new ValueOverflow();
        vo.useBigInteger();
    }
}
