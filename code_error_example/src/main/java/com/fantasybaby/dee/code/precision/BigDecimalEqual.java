package com.fantasybaby.dee.code.precision;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * BigDecimal 的 equals 方法的注释中说明了原因，
 * equals 比较的是 BigDecimal 的 value 和 scale，
 * 所以1和1.0 equal是false
 *
 * @author fanta
 * @Description
 * @create 2021-01-23 16:17
 */
public class BigDecimalEqual {
    public void notEqual() {
        System.out.println(new BigDecimal("1.0").equals(new BigDecimal("1")));
    }

    public void useCompare() {
        System.out.println(new BigDecimal("1.0").compareTo(new BigDecimal("1")) == 0);
    }

    public void compareSet(){
        Set<BigDecimal> hashSet2 = new HashSet<>();
        hashSet2.add(new BigDecimal("1.0").stripTrailingZeros());
        System.out.println(hashSet2.contains(new BigDecimal("1.000").stripTrailingZeros()));//返回true
    }
    public static void main(String[] args) {
        new BigDecimalEqual().notEqual();
        new BigDecimalEqual().useCompare();
        new BigDecimalEqual().compareSet();
    }
}
