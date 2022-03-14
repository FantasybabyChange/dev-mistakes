package com.fantasybaby.dee.code.serizlize.jsonignoreproperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRight {
    private String name;
}
