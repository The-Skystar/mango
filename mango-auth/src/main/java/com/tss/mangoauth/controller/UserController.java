package com.tss.mangoauth.controller;

import com.tss.mangoauth.domain.UserDTO;
import com.tss.mangoauth.mapper.UserMapper;
import com.tss.mangocommon.web.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 
 * @since 2021-03-08
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/test")
    public Result<UserDTO> test(){
        userMapper.query(new Date());
        return null;
    }
}
