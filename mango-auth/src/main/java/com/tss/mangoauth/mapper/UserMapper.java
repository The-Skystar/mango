package com.tss.mangoauth.mapper;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.tss.mangoauth.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2021-03-08
 */
@Repository
public interface UserMapper extends BaseMapper<UserEntity> {

    @Select("select * from sys_user where create_time < #{date}")
    List<UserEntity> query(@Param("date") Date date);
}
