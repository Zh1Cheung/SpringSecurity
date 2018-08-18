/**
 * 
 */
package com.imooc.security.browser;

import com.imooc.security.core.authentication.AbstractChannelSecurityConfig;
import com.imooc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.core.properties.SecurityConstants;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * �������ȫ��֤��������
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 14:57
 */


@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

	@Autowired
	private  SecurityProperties securityProperties;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	@Autowired
	private SpringSocialConfigurer imoocSocialSecurityConfig;
	
	@Autowired
	private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
	
	@Autowired
	private InvalidSessionStrategy invalidSessionStrategy;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		applyPasswordAuthenticationConfig(http);

		//���������addFilterBefore����
		http.apply(validateCodeSecurityConfig)// ��֤������
				.and()


			.apply(smsCodeAuthenticationSecurityConfig)
				.and()
			.apply(imoocSocialSecurityConfig)//�罻��¼
				.and()
			.rememberMe()
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
				.userDetailsService(userDetailsService)
				.and()
				/**
				 * ע������Ĵ��룬����ע����DataSource��UserDetailsService������UserDetailsService��û��ʹ�ù�����ע�룬�����ֶ�ע�룬
				 * ������ΪUserDetailsService��ʵ������ע����PasswordEncoder��Bean��������������ע���ѭ��Ӧ�����⡣
				 */



				.sessionManagement()
				.invalidSessionStrategy(invalidSessionStrategy)
				.maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
				.maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
				.expiredSessionStrategy(sessionInformationExpiredStrategy)
				.and()
				.and()
				.logout()
				.logoutUrl("/signOut")//�Զ����˳��ĵ�ַ
				.logoutSuccessUrl("/register")//�˳�֮����ת��ע��ҳ��
				.deleteCookies("JSESSIONID")//ɾ����ǰ��JSESSIONID
				.and()
			.authorizeRequests()
				.antMatchers(
						SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,	//��������URL��Ҫ��֤����ת��URL
					SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,//��������Զ���ĵ�¼URL
					securityProperties.getBrowser().getLoginPage(),
					SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
					securityProperties.getBrowser().getSignUpUrl(),
					securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".json",
					securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".html",
					"/user/regist")
					.permitAll()//���ϵ����󶼲���Ҫ��֤
				.anyRequest()
				.authenticated()
				.and()
			.csrf().disable();//�ر�csrd����
		
	}

	//��������İ�ȫ������BrowserSecurityConfig�����һ��Bean��
	//���Bean����persistenTokenRepository�����������Bean�ͻ�������ˡ���ס�ҡ����ܵĿ�����Ȼ���ڽ����Bean���õ�configure�����м���

	/**
	 * ����Ĵ���tokenRepository.setCreateTableOnStartup(true);���Զ�����Token�浽���ݿ�ʱ������Ҫ�ı�
	 * ���д���ֻ������һ�Σ���������������ݿ⣬����ɾ�����д��룬���򽫱���
	 * ��ʵ����鿴JdbcTokenRepositoryImpl���е�һ�������ֶ�CREATE_TABLE_SQL��
	 * ����ֶ��������˽����һ��SQL��䣬�����ֶ��������SQL��佨����ô����ȫ����ҪtokenRepository.setCreateTableOnStartup(true)
	 */
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}



	/**
	 * ���������Bean�Ժ󣬴�ǰ�˴��ݹ��������뽫������
	 *
	 * @return PasswordEncoderʵ�������
	 */


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
