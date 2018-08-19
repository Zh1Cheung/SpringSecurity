package com.imooc.server;

import com.imooc.config.AuthExceptionEntryPoint;
import com.imooc.permit.PermitAllSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 配置资源服务器
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/15 1:42
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     * 自定义登录成功处理器
     */
    @Autowired
    private AuthenticationSuccessHandler appLoginInSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler appLoginFailureHandler;

    @Autowired
    private PermitAllSecurityConfig permitAllSecurityConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        // @formatter:off
        http.formLogin()
                .successHandler(appLoginInSuccessHandler)//登录成功处理器
                .failureHandler(appLoginFailureHandler)
                .and()
                .exceptionHandling().authenticationEntryPoint(new AuthExceptionEntryPoint())
                .and()
                .apply(permitAllSecurityConfig)
                .and()
                .authorizeRequests()
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/forbidden").hasRole("ADMIN")
                .antMatchers("/permitAll").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

        // @formatter:ON
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(new AuthExceptionEntryPoint());
    }
}
