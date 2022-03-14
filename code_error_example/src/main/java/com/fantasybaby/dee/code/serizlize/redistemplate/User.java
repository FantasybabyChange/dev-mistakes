package com.fantasybaby.dee.code.serizlize.redistemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
class User implements Serializable {
    private String name;
    private int age;
}
