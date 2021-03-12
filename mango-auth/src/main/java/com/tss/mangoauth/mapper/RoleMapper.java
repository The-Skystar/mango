package com.tss.mangoauth.mapper;

import com.tss.mangoauth.entity.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2021-03-08
 */
@Repository
public interface RoleMapper extends BaseMapper<RoleEntity> {
    List<RoleEntity> queryRoles(@Param("userId") String userId);
}
