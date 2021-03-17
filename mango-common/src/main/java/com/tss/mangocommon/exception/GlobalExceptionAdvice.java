package com.tss.mangocommon.exception;

import com.tss.mangocommon.web.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 * @author YC01224 yangxiangjun
 * @description 全局异常处理
 * @date 2021/3/16 10:32
 * @since JDK 1.8
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    /**
     * 自定义的业务异常处理
     *
     * @param businessException
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleBusinessException(BusinessException businessException) {
        logger.error("业务处理错误：{}", businessException.getMsg(), businessException);
        return Result.fail(businessException.getCode(), businessException.getMsg());
    }

    /**
     * 对exception异常的处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleException(Exception exception) {
        logger.error("系统运行出错：{}", exception.getMessage(), exception);
        return Result.fail();
    }

    /**
     * 对error错误的处理，由于error是不可恢复的异常，可在发生error异常时做错误上报
     *
     * @param error
     * @return
     */
    @ExceptionHandler(Error.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleError(Error error) {
        logger.error("系统运行出错：{}", error.getMessage(), error);
        //系统出现严重的，且不可恢复的异常，此时可向管理平台报告错误
        return Result.fail();
    }

    /**
     * 服务间调用异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(InternalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleInternalException(InternalException exception) {
        logger.error("服务间调用错误（路径：{}）：{}", exception.getPath(), exception.getMsg(), exception);
        return Result.fail(exception.getCode(), exception.getMsg());
    }

    /**
     * 参数校验异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        logger.error("参数校验出错：{}", exception.getMessage(), exception);
        return Result.fail(exception.getMessage());
    }
}
