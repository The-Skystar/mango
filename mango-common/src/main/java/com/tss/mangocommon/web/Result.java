package com.tss.mangocommon.web;

import java.io.Serializable;

/**
 * @author YC01224 yangxiangjun
 * @description
 * @date 2021/3/15 9:47
 * @since JDK 1.8
 */
public class Result<T> implements Serializable {
    /**
     * 状态码
     */
    private String code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 分页查询总数据量
     */
    private Integer total;
    /**
     * 返回数据
     */
    private T data;

    public Result(String code, String msg, Integer total, T data) {
        this.code = code;
        this.msg = msg;
        this.total = total;
        this.data = data;
    }

    public static <T> Result<T> success(T data){
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), null, data);
    }

    public static <T> Result<T> success(T data, Integer total){
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), total, data);
    }

    public static <T> Result<T> fail(){
        return new Result(ResultCode.FAIL.getCode(), ResultCode.FAIL.getMsg(), null, null);
    }

    public static <T> Result<T> fail(String msg){
        return new Result(ResultCode.FAIL.getCode(), msg, null, null);
    }

    public static <T> Result<T> fail(String code, String msg){
        return new Result(code, msg, null, null);
    }

    public static <T> Result<T> fail(ResultCode resultCode){
        return new Result(resultCode.getCode(), resultCode.getMsg(), null, null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", total=" + total +
                ", data=" + data +
                '}';
    }
}
