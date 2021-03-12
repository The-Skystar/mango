package com.tss.mangoauth.service.impl;

import com.tss.mangoauth.constant.RedisConstant;
import com.tss.mangoauth.entity.UrlAccess;
import com.tss.mangoauth.mapper.UrlAccessMapper;
import com.tss.mangoauth.service.UrlAccessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * <p>
 * 接口权限表 服务实现类
 * </p>
 *
 * @author 
 * @since 2021-03-08
 */
@Service
public class UrlAccessServiceImpl extends ServiceImpl<UrlAccessMapper, UrlAccess> implements UrlAccessService {


}
