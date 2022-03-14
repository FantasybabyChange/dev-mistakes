package com.fantasybaby.dee.code.precision;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * 2021/1/17
 *
 * @authorfantasybaby
 **/
@Slf4j
public class DoublePrecision {
    /**
     * 计算机是以二进制存储数值的
     * 和想象不匹配
     * https://en.wikipedia.org/wiki/IEEE_754
     */
    public void notMatchMind() {
        System.out.println(0.1 + 0.2);
        System.out.println(1.0 - 0.8);
        System.out.println(4.015 * 100);
        System.out.println(123.3 / 100);

        double amount1 = 2.15;
        double amount2 = 1.10;
        if (amount1 - amount2 == 1.05) {
            System.out.println("OK");
        }
    }

    /**
     * 使用 BigDecimal 表示和计算浮点数，且务必使用字符串的构造方法来初始化 BigDecimal
     */
    public void useBigDecimal() {
        System.out.println(new BigDecimal(0.1).add(new BigDecimal(0.2)));
        System.out.println(new BigDecimal(1.0).subtract(new BigDecimal(0.8)));
        System.out.println(new BigDecimal(4.015).multiply(new BigDecimal(100)));
        System.out.println(new BigDecimal(123.3).divide(new BigDecimal(100)));
    }

    /**
     * 使用 BigDecimal 表示和计算浮点数，且务必使用字符串的构造方法来初始化 BigDecimal
     */
    public void useBigDecimalStr() {
        System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));
        System.out.println(new BigDecimal("1.0").subtract(new BigDecimal("0.8")));
        System.out.println(new BigDecimal("4.015").multiply(new BigDecimal("100")));
        System.out.println(new BigDecimal("123.3").divide(new BigDecimal("100")));
    }

    /**
     * BigDecimal 有 scale 和 precision 的概念
     */
    public void useDoubleNotStr() {
        System.out.println(new BigDecimal("4.015").multiply(new BigDecimal(Double.toString(100))));
        System.out.println(new BigDecimal("4.015").divide(new BigDecimal(Double.toString(100))));

    }

    /**
     * scale 代表小数点右边的位数  precision代表
     */
    private  void testScale() {
        BigDecimal bigDecimal1 = new BigDecimal("100");
        BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(100d));
        BigDecimal bigDecimal3 = new BigDecimal(String.valueOf(100));
        BigDecimal bigDecimal4 = BigDecimal.valueOf(100d);
        BigDecimal bigDecimal5 = new BigDecimal(Double.toString(100));
        //scale 0 precision 3 result 401.500
        print(bigDecimal1);
        //scale 1 precision 4 result 401.5000
        print(bigDecimal2);
        //scale 0 precision 3 result 401.500
        print(bigDecimal3);
        //scale 1 precision 4 result 401.5000
        print(bigDecimal4);
        //scale 1 precision 4 result 401.5000
        print(bigDecimal5);
    }

    private  void print(BigDecimal bigDecimal) {
        log.info("scale {} precision {} result {}", bigDecimal.scale(), bigDecimal.precision(), bigDecimal.multiply(new BigDecimal("4.015")));
    }


    public  static void main(String[] args) {
        DoublePrecision doublePrecision = new DoublePrecision();
//        doublePrecision.notMatchMind();
//        doublePrecision.useBigDecimal();
//        doublePrecision.useBigDecimalStr();
//        doublePrecision.useDoubleNotStr();
        doublePrecision.testScale();
    }
}
