package com.fantasybaby.dev.error.design.duplicate.usetemplate.right;

import com.fantasybaby.dev.error.design.duplicate.usetemplate.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created on 6/21/2021.
 *
 * @author Reid Liu
 */
@Service("NormalUserCart")
public class NormalUserCart extends AbstractCart {
    @Override
    protected void processCouponPrice(long userId, Item item) {
        item.setCouponPrice(BigDecimal.ZERO);
    }

    @Override
    protected void processDeliveryPrice(long userId, Item item) {
        item.setDeliveryPrice(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())).multiply(new BigDecimal("0.1")));
    }
}
