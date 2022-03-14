package com.fantasybaby.dev.error.design.duplicate.usetemplate;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

/**
 * Created on 6/21/2021.
 *
 * @author Reid Liu
 */
@Service
public class Db {
    private static final Map<Long, BigDecimal> SKU_PRICE = Maps.newHashMap();
    private static final Map<Long, Integer> COUPON_PRECENT = Maps.newHashMap();
    private static final Map<Long, String> USER_CATEGORY = Maps.newHashMap();

    private static Map<Long, Integer> ITEMS = Maps.newHashMap();
    @PostConstruct
    public void init(){
        USER_CATEGORY.put(1L,"Normal");
        USER_CATEGORY.put(2L,"Vip");
        USER_CATEGORY.put(3L,"Inner");

        SKU_PRICE.put(1L,new BigDecimal("10"));
        SKU_PRICE.put(2L,new BigDecimal("20"));

        ITEMS.put(1L,2);
        ITEMS.put(2L,4);
    }

    public static BigDecimal getItemPrice(Long key) {
        return Optional.ofNullable(SKU_PRICE.get(key)).orElse(new BigDecimal(0));
    }

    public static int getUserCouponPercent(Long key) {
        return Optional.ofNullable(COUPON_PRECENT.get(key)).orElse(90);
    }
    public static String getUserCategory(Long key){
        return USER_CATEGORY.get(key);
    }
    public static Map<Long,Integer> getAllItems(){
        return ITEMS;
    }
}
