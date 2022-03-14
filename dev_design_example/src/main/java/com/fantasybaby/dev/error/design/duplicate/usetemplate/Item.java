package com.fantasybaby.dev.error.design.duplicate.usetemplate;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created on 6/21/2021.
 *
 * @author Reid Liu
 */
@Data
public class Item {
    //商品ID
    private long id;
    //商品数量
    private int quantity;
    //商品单价
    private BigDecimal price;
    //商品优惠
    private BigDecimal couponPrice;
    //商品运费
    private BigDecimal deliveryPrice;
}
