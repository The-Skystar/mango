package com.tss.mangoservicea.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by yangxiangjun on 2020/6/19.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private int age;
    private Boolean bald;
    private List<String> roles;
}
