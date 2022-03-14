package com.fantasybaby.dee.code.precision;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 格式化带来的精度问题
 *
 * @author fanta
 * @Description
 * @create 2021-01-23 15:07
 */
public class FormatPrecision {
    /**
     * double 和 float 的 3.35
     * 其实相当于 3.350xxx 和 3.349xxx：
     */
    public void formatFloat() {
        double num1 = 3.35;
        float num2 = 3.35f;
        System.out.println(String.format("%.1f", num1));//四舍五入
        System.out.println(String.format("%.1f", num2));
    }

    /**
     * 同时会出现精度问题
     */
    public void formatByDecimal() {
        double num1 = 3.35;
        float num2 = 3.35f;
        DecimalFormat format = new DecimalFormat("#.##");
        format.setRoundingMode(RoundingMode.DOWN);
        System.out.println(format.format(num1));
        format.setRoundingMode(RoundingMode.DOWN);
        System.out.println(format.format(num2));
    }

    /**
     * 浮点数的字符串格式化也要通过 BigDecimal 进行。
     */
    public void formatByBigDecimal() {
        BigDecimal num1 = new BigDecimal("3.35");
        BigDecimal num2 = num1.setScale(1, BigDecimal.ROUND_DOWN);
        System.out.println(num2);
        BigDecimal num3 = num1.setScale(1, BigDecimal.ROUND_HALF_UP);
        System.out.println(num3);
    }

    public static void main(String[] args) {
        FormatPrecision format = new FormatPrecision();
        format.formatFloat();
        format.formatByDecimal();
        format.formatByBigDecimal();
    }
}
