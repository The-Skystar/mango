package com.tss.mangocommon.feign;

import com.alibaba.fastjson.JSONObject;
import com.tss.mangocommon.exception.InternalException;
import com.tss.mangocommon.web.Result;
import com.tss.mangocommon.web.ResultCode;
import feign.*;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;

/**
 * @author YC01224 yangxiangjun
 * @description 自定义feign请求的异常处理
 * @date 2021/3/15 17:55
 * @since JDK 1.8
 */
@Configuration
public class FeignErrorDecoder implements ErrorDecoder {

    private static final Logger logger = LoggerFactory.getLogger(FeignErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        Request request = response.request();

        byte[] body = {};
        try {
            if (response.body() != null) {
                body = Util.toByteArray(response.body().asInputStream());
            }
        } catch (IOException ignored) { // NOPMD
        }

        String contentUTF8 = new String(body, Charset.forName("UTF-8"));
        logger.error("服务间调用错误（路径：{}）:\n" +
                "{}", request.url(), contentUTF8);

        //HTTP状态码在500以上的错误才是服务端报错
        if (response.status() >= 500) {
            Result result;
            try {
                result = JSONObject.parseObject(contentUTF8, Result.class);
                if (null != result) {
                    return new InternalException(result.getCode(), result.getMsg(), request.url());
                }
            } catch (Exception e) {
            }
        }

        return new InternalException(ResultCode.INTERNAL_ERROR.getCode(), ResultCode.INTERNAL_ERROR.getMsg(), request.url());
    }
}
