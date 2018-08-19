package com.imooc.handler;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 添加自定义异常类，指定json序列化方式
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/15 1:12
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {
    public CustomOauthException(String msg) {
        super(msg);
    }
}
