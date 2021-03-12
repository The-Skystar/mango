package com.tss.mangoauth.mapper;

import com.tss.mangoauth.domain.UrlAccessBO;
import com.tss.mangoauth.entity.UrlRoute;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * API接口表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2021-03-08
 */
@Repository
public interface UrlRouteMapper extends BaseMapper<UrlRoute> {
    List<UrlAccessBO> getUrlAccess();
}
