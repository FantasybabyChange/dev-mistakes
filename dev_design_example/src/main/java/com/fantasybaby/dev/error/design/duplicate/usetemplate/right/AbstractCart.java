package com.fantasybaby.dev.error.design.duplicate.usetemplate.right;

import com.fantasybaby.dev.error.design.duplicate.usetemplate.Cart;
import com.fantasybaby.dev.error.design.duplicate.usetemplate.Db;
import com.fantasybaby.dev.error.design.duplicate.usetemplate.Item;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created on 6/21/2021.
 *
 * @author Reid Liu
 */
public abstract class AbstractCart {
    /**
     * 处理购物车的大量重复逻辑在父类实现
     * @param userId
     * @param items
     * @return
     */
    public Cart process(long userId, Map<Long, Integer> items) {
        Cart cart = new Cart();
        List<Item> itemList = Lists.newArrayList();
        items.entrySet().stream().forEach(entry -> {
            Item item = new Item();
            item.setId(entry.getKey());
            item.setPrice(Db.getItemPrice(entry.getKey()));
            item.setQuantity(entry.getValue());
            itemList.add(item);
        });
        cart.setItems(itemList);
        //让子类处理每一个商品的优惠
        itemList.stream().forEach(item -> {
            processCouponPrice(userId, item);
            processDeliveryPrice(userId, item);
        });
        //计算商品总价
        cart.setTotalItemPrice(cart.getItems().stream().map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add));
        //计算总运费
        cart.setTotalDeliveryPrice(cart.getItems().stream().map(Item::getDeliveryPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        //计算总折扣
        cart.setTotalDiscount(cart.getItems().stream().map(Item::getCouponPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        //计算应付价格
        cart.setPayPrice(cart.getTotalItemPrice().add(cart.getTotalDeliveryPrice()).subtract(cart.getTotalDiscount()));
        return cart;
    }

    /**
     * 处理商品优惠的逻辑留给子类实现
     * @param userId
     * @param item
     */
    protected abstract void processCouponPrice(long userId, Item item);
    /**
     * 处理配送费的逻辑留给子类实现
     * @param userId
     * @param item
     */
    protected abstract void processDeliveryPrice(long userId, Item item);
}
