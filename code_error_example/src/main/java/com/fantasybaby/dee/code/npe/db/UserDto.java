package com.fantasybaby.dee.code.npe.db;


import lombok.Data;

import java.util.Optional;

/**
 * @author Reid.Liu
 */
@Data
public class UserDto {
    private Long id;
    private Optional<String> name;
    private Optional<Integer> age;
}
