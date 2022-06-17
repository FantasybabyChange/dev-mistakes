package com.fantasybaby.dev.error.other.jdk8.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Long productId;
    private String productName;
    private Double productPrice;
    private Integer productQuantity;
}
