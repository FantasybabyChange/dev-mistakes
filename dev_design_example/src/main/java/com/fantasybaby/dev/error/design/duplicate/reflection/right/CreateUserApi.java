package com.fantasybaby.dev.error.design.duplicate.reflection.right;

import lombok.Data;

/**
 * Created on 6/23/2021.
 *
 * @author Reid Liu
 */
@BankApi(url = "/bank/createUser", desc = "创建用户接口")
@Data
public class CreateUserApi extends AbstractApi {
    @BankApiField(order = 1, type = "S", length = 10)
    private String name;
    @BankApiField(order = 2, type = "S", length = 18)
    private String identity;
    /**
     * 注意这里的order需要按照Api表格中的顺序
     */
    @BankApiField(order = 4, type = "S", length = 11)
    private String mobile;
    @BankApiField(order = 3, type = "N", length = 5)
    private int age;
}
