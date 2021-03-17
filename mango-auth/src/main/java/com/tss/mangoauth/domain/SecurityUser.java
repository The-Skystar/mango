package com.tss.mangoauth.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 登录用户信息
 * Created by yangxiangjun on 2020/6/19.
 */
@Data
public class SecurityUser implements UserDetails {

    /**
     * ID
     */
    private String userId;
    /**
     * ID
     */
    private String userCode;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户状态
     */
    private Integer status;
    /**
     * 权限数据
     */
    private Collection<SimpleGrantedAuthority> authorities;

    public SecurityUser() {

    }

    public SecurityUser(UserDTO userDTO) {
        this.setUserId(userDTO.getUserId());
        this.setUserCode(userDTO.getUserCode());
        this.setUsername(userDTO.getUserName());
        this.setPassword(userDTO.getPassword());
        this.setStatus(userDTO.getStatus());
        if (userDTO.getRoles() != null) {
            authorities = new ArrayList<>();
            userDTO.getRoles().forEach(item -> authorities.add(new SimpleGrantedAuthority(item)));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status == 1;
    }

}
