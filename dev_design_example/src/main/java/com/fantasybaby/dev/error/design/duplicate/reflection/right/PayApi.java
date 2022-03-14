package com.fantasybaby.dev.error.design.duplicate.reflection.right;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created on 6/23/2021.
 *
 * @author Reid Liu
 */
@BankApi(url = "/bank/pay", desc = "支付接口")
@Data
public class PayApi extends AbstractApi {
    @BankApiField(order = 1, type = "N", length = 20)
    private long userId;
    @BankApiField(order = 2, type = "M", length = 10)
    private BigDecimal amount;
}
