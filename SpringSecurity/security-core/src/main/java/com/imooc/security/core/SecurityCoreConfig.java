/**
 * 
 */
package com.imooc.security.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.imooc.security.core.properties.SecurityProperties;

/**的
 * 为了使这个SecurityProperties()读取配置的类生效
 *
 *
 * 以上代码基本完成了登录的基本功能，
 * 当用户访问的是HTML的时候，就会跳转到登录页面，
 * 如果是RESTful API的时候，返回一段JSON数据，
 * 前端可以根据JSON数据来提示用户登录。
 * 至于用户自定义界面，可以在application.yml配置
 *
 *
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 15:17
        */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
