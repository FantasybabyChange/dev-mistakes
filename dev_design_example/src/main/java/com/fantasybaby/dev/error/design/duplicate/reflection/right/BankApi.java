package com.fantasybaby.dev.error.design.duplicate.reflection.right;

import java.lang.annotation.*;

/**
 * 包含接口 URL 地址和接口说明
 * Created on 6/23/2021.
 *
 * @author Reid Liu
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
public @interface BankApi {
    String desc() default "";
    String url() default "";
}
