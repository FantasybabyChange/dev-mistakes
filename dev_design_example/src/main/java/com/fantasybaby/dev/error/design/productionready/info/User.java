package com.fantasybaby.dev.error.design.productionready.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long userId;
    private String userName;
}
