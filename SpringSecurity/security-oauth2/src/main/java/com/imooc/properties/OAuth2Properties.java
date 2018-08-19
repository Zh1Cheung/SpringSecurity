package com.imooc.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/15 1:52
 */
@Data
@ConfigurationProperties(prefix = "merryyou.security.oauth2")
public class OAuth2Properties {

    private String jwtSigningKey = "merryyou";

    private OAuth2ClientProperties[] clients = {};
}
