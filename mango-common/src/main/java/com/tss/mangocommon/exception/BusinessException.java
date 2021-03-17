package com.tss.mangocommon.exception;

import com.tss.mangocommon.web.ResultCode;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;

/**
 * @author YC01224 yangxiangjun
 * @description 自定义业务异常
 * @date 2021/3/16 9:59
 * @since JDK 1.8
 */
public class BusinessException extends RuntimeException{

    private String code;
    private String msg;

    public BusinessException(String code,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    /**
     * 支持以{0}、{1}的占位符形式传参拼接字符串
     * @param code
     * @param msg
     * @param args
     */
    public BusinessException(String code,String msg, Object... args){
        super(MessageFormat.format(msg,args));
        this.code = code;
        this.msg = MessageFormat.format(msg,args);
    }

    /**
     * 可传递ResultCode对象构建异常
     * @param resultCode
     */
    public BusinessException(ResultCode resultCode){
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
