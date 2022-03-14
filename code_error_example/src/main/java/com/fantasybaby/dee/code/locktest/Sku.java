package com.fantasybaby.dee.code.locktest;

import lombok.Builder;
import lombok.ToString;
import lombok.Data;
import lombok.Getter;

import java.util.concurrent.locks.ReentrantLock;
@Builder
@Data
public class Sku {
    private String name;
    private Integer remaining;
    @ToString.Exclude
    private ReentrantLock rl;

    public void reduce() {
        remaining--;
    }
}
