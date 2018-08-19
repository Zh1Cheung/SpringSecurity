package com.imooc.permit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;

/**
 * 添加PermitAllSecurityConfig配置用于配置PermitAuthenticationFilter
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/15 1:48
 */
@Component("permitAllSecurityConfig")
public class PermitAllSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {

    @Autowired
    private Filter permitAuthenticationFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(permitAuthenticationFilter, OAuth2AuthenticationProcessingFilter.class);
    }
}
