package com.tss.mangocommon.exception;

import java.text.MessageFormat;

/**
 * @author YC01224 yangxiangjun
 * @description 内部服务异常
 * @date 2021/3/16 11:42
 * @since JDK 1.8
 */
public class InternalException extends RuntimeException{

    private String code;
    private String msg;
    private String path;

    public InternalException(String code,String msg,String path){
        super(msg);
        this.code = code;
        this.msg = msg;
        this.path = path;
    }

    /**
     * 支持以{0}、{1}的占位符形式传参拼接字符串
     * @param code
     * @param msg
     * @param args
     */
    public InternalException(String code,String msg,String path, Object... args){
        super(MessageFormat.format(msg,args));
        this.code = code;
        this.msg = MessageFormat.format(msg,args);
        this.path = path;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getPath() {
        return path;
    }
}
