package com.tss.mangocommon.web;

import org.springframework.http.HttpStatus;

/**
 * @author YC01224 yangxiangjun
 * @description
 * @date 2021/3/15 10:23
 * @since JDK 1.8
 */
public class ResultCode {
    private String code;
    private String msg;

    public static final ResultCode SUCCESS = new ResultCode(HttpStatus.OK);
    public static final ResultCode FAIL = new ResultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),"系统异常,请联系技术服务！");
    public static final ResultCode INTERNAL_ERROR = new ResultCode("1001","服务调用异常，请稍后重试！");

    public ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultCode(String code) {
        this.code = code;
        this.msg = "";
    }

    public ResultCode(HttpStatus httpStatus) {
        this.code = String.valueOf(httpStatus.value());
        this.msg = httpStatus.getReasonPhrase();
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
