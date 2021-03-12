package com.tss.mangoauth.service.impl;

import com.tss.mangoauth.entity.UserEntity;
import com.tss.mangoauth.mapper.UserMapper;
import com.tss.mangoauth.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 
 * @since 2021-03-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

}
