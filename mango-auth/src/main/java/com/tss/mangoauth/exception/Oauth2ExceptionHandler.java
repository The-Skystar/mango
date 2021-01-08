package com.tss.mangoauth.exception;

import com.tss.mangoauth.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局处理Oauth2抛出的异常
 * Created by yangxiangjun on 2020/7/17.
 */
@ControllerAdvice
@Slf4j
public class Oauth2ExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = OAuth2Exception.class)
    public CommonResult handleOauth2(OAuth2Exception e) {
        log.error(e.getMessage(),e);
        return CommonResult.failed(e.getMessage());
    }
}
