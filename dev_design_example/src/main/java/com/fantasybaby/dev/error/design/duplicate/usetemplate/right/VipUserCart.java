package com.fantasybaby.dev.error.design.duplicate.usetemplate.right;

import com.fantasybaby.dev.error.design.duplicate.usetemplate.Db;
import com.fantasybaby.dev.error.design.duplicate.usetemplate.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created on 6/21/2021.
 *
 * @author Reid Liu
 */
@Service("VipUserCart")
public class VipUserCart extends NormalUserCart {
    @Override
    protected void processCouponPrice(long userId, Item item) {
        if (item.getQuantity() > 2) {
            item.setCouponPrice(item.getPrice()
                    .multiply(BigDecimal.valueOf(100 - Db.getUserCouponPercent(userId)).divide(new BigDecimal("100")))
                    .multiply(BigDecimal.valueOf(item.getQuantity() - 2)));
        } else {
            item.setCouponPrice(BigDecimal.ZERO);
        }
    }
}
