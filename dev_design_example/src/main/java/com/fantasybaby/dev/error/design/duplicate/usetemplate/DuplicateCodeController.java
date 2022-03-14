package com.fantasybaby.dev.error.design.duplicate.usetemplate;

import com.fantasybaby.dev.error.design.duplicate.usetemplate.right.AbstractCart;
import com.fantasybaby.dev.error.design.duplicate.usetemplate.wrong.InnerUserCart;
import com.fantasybaby.dev.error.design.duplicate.usetemplate.wrong.NormalUserCart;
import com.fantasybaby.dev.error.design.duplicate.usetemplate.wrong.VipUserCart;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Use Factory and template pattern
 * Created on 6/21/2021.
 *
 * @author Reid Liu
 */
@RequestMapping(value = "duplicate/")
@RestController
public class DuplicateCodeController {
    @Resource
    private ApplicationContext applicationContext;

    @GetMapping("wrong/{userId}")
    public Cart wrong(@PathVariable("userId") long userId) {
        Map<Long, Integer> items = Db.getAllItems();
        //根据用户ID获得用户类型
        String userCategory = Db.getUserCategory(userId);
        //普通用户处理逻辑
        if (CategoryEnum.Normal.name().equals(userCategory)) {
            NormalUserCart normalUserCart = new NormalUserCart();
            return normalUserCart.process(userId, items);
        }
        //VIP用户处理逻辑
        if (userCategory.equals(CategoryEnum.Vip.name())) {
            VipUserCart vipUserCart = new VipUserCart();
            return vipUserCart.process(userId, items);
        }
        //内部用户处理逻辑
        if (userCategory.equals(CategoryEnum.Inner.name())) {
            InnerUserCart internalUserCart = new InnerUserCart();
            return internalUserCart.process(userId, items);
        }

        return null;
    }

    @GetMapping("right/{userId}")
    public Cart right(@PathVariable("userId") long userId) {
        Map<Long, Integer> items = Db.getAllItems();
        String userCategory = Db.getUserCategory(userId);
        AbstractCart cart = (AbstractCart) applicationContext.getBean(userCategory + "UserCart");
        return cart.process(userId, items);
    }
}
