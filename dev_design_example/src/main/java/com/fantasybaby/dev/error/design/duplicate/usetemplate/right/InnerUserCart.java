package com.fantasybaby.dev.error.design.duplicate.usetemplate.right;

import com.fantasybaby.dev.error.design.duplicate.usetemplate.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created on 6/21/2021.
 *
 * @author Reid Liu
 */
@Service(value = "InnerUserCart")
public class InnerUserCart extends AbstractCart {
    @Override
    protected void processCouponPrice(long userId, Item item) {
        item.setCouponPrice(new BigDecimal("0"));
    }

    @Override
    protected void processDeliveryPrice(long userId, Item item) {
        item.setDeliveryPrice(new BigDecimal("0"));
    }
}
