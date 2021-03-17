package com.tss.mangoauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tss.mangoauth.constant.MessageConstant;
import com.tss.mangoauth.domain.SecurityUser;
import com.tss.mangoauth.domain.UserDTO;
import com.tss.mangoauth.entity.RoleEntity;
import com.tss.mangoauth.entity.UserEntity;
import com.tss.mangoauth.mapper.RoleMapper;
import com.tss.mangoauth.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户管理业务类
 * Created by yangxiangjun on 2020/6/19.
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询该用户
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getUserName,username);
        UserEntity findUser = userMapper.selectOne(wrapper);
//        List<UserDTO> findUser = userList.stream().filter(item -> item.getUsername().equals(username)).collect(Collectors.toList());
        if (Objects.isNull(findUser)) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(findUser,userDTO);
        userDTO.setPassword(passwordEncoder.encode(findUser.getPassword()));

        //获取用户角色
        List<RoleEntity> roles = roleMapper.queryRoles(findUser.getUserId());
        if (!CollectionUtils.isEmpty(roles)) {
            userDTO.setRoles(roles.stream().map(roleEntity -> roleEntity.getRoleCode()).collect(Collectors.toList()));
        }

        SecurityUser securityUser = new SecurityUser(userDTO);
        if (!securityUser.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;
    }

}
