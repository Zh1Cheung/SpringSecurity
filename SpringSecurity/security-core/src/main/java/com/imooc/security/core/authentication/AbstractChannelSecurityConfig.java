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
 * http.formLogin()ָ���ı���¼��ʽ��
 * loginPage("/authentication/require")�����˵�¼ҳ�棬���ｫURLָ����һ��Controller�����Controller���Ը����û�������ѡ�񴫵�JSON���ݻ��Ƿ���һ����¼ҳ�档
 * loginProcessingUrl("/authentication/form")�Ǹ�����UsernamePasswordAuthenticationFilterĬ�ϵĴ������¼��/login��API������ǰ�˵�form��ǩ��action�Ϳ���д/authentication/form�����ǹ̶���/login��
 * successHandler(lemonAuthenticationSuccessHandler)ָ���˵�¼�ɹ���Ĵ����߼���һ�㶼����ת���߷���һ��JSON���ݡ�
 * failureHandler(lemonAuthenticationFailureHandler)ָ���˵�¼ʧ�ܺ�Ĵ����߼���һ��������ת���߷���һ��JSON���ݡ�
 * antMatchers("/authentication/require", securityProperties.getBrowser().getLoginPage()).permitAll()��˼��ָ/authentication/require�͵�¼ҳ�������������֤Ȩ�ޡ�
 * csrf().disable()��ָ�رտ�վ����α��ķ�����������Ϊ��ǰ�ڿ������㣬�ر�����
 *
 *���û�����ϵͳ��RESTful API��ʱ�򣬵�һ�η��ʻ��鵱ǰ���ʵ��û���û��Ȩ�޷��ʣ�
 * ���û��Ȩ�ޣ��ͻ���뵽BrowserSecurityConfig��configure�����У��Ӷ����뵽/authentication/require��Controller�������ж��û��Ƿ��Ƿ���HTML��
 * ���������ת����½ҳ�棬���򷵻�һ��JSON������ʾ�û���¼��
 * ���ﻹ�Զ����������û���½�ɹ���ʧ�ܵĴ����߼�������/authentication/require�͵�¼ҳ���������������֤Ȩ�ޣ������ݽ���ѭ���С�
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
