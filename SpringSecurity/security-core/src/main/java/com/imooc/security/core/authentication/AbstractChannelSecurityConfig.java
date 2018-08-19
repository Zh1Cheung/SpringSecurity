/**
 * 
 */
package com.imooc.security.core.authentication;

import com.imooc.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author : Zh1Cheung 945503088@qq.com
 *
 *  @date : 2018/8/13 14:59
 *
 *
 * http.formLogin()指定的表单登录方式。
 * loginPage("/authentication/require")设置了登录页面，这里将URL指向了一个Controller，这个Controller可以根据用户的设置选择传递JSON数据还是返回一个登录页面。
 * loginProcessingUrl("/authentication/form")是更改了UsernamePasswordAuthenticationFilter默认的处理表单登录的/login的API，现在前端的form标签的action就可以写/authentication/form而不是固定的/login了
 * successHandler(lemonAuthenticationSuccessHandler)指定了登录成功后的处理逻辑，一般都是跳转或者返回一个JSON数据。
 * failureHandler(lemonAuthenticationFailureHandler)指定了登录失败后的处理逻辑，一般是是跳转或者返回一个JSON数据。
 * antMatchers("/authentication/require", securityProperties.getBrowser().getLoginPage()).permitAll()意思是指/authentication/require和登录页面的请求无需验证权限。
 * csrf().disable()是指关闭跨站请求伪造的防护，这里是为了前期开发方便，关闭它。
 *
 *当用户访问系统的RESTful API的时候，第一次访问会检查当前访问的用户有没有权限访问，
 * 如果没有权限，就会进入到BrowserSecurityConfig的configure方法中，从而进入到/authentication/require的Controller方法中判断用户是否是访问HTML，
 * 如果是则跳转到登陆页面，否则返回一段JSON数据提示用户登录。
 * 这里还自定义配置了用户登陆成功和失败的处理逻辑，对于/authentication/require和登录页面的请求则无需验证权限，否则将陷进死循环中。
 */



public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	protected AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
	
	@Autowired
	protected AuthenticationFailureHandler imoocAuthenticationFailureHandler;

	protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
		http.formLogin()
			.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
			.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
			.successHandler(imoocAuthenticationSuccessHandler)
			.failureHandler(imoocAuthenticationFailureHandler);
	}
	
}
