package com.tss.mangoservicea.controller;

import com.tss.mangocommon.context.UserInfo;
import com.tss.mangocommon.context.UserInfoContext;
import com.tss.mangoservicea.manager.LoginUserHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取登录用户信息接口
 * Created by yangxiangjun on 2020/6/19.
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private LoginUserHolder loginUserHolder;

    @ApiOperation("根据用户名查询用户")
    @GetMapping("/currentUser")
    public UserInfo currentUser() {
        loginUserHolder.test();
        return UserInfoContext.getUserInfoContext();
    }

}
