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
 * 浏览器安全验证的配置类
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

		//这里调的是addFilterBefore方法
		http.apply(validateCodeSecurityConfig)// 验证码拦截
				.and()


			.apply(smsCodeAuthenticationSecurityConfig)
				.and()
			.apply(imoocSocialSecurityConfig)//社交登录
				.and()
			.rememberMe()
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
				.userDetailsService(userDetailsService)
				.and()
				/**
				 * 注意上面的代码，重新注入了DataSource和UserDetailsService，其中UserDetailsService并没有使用构造器注入，而是字段注入，
				 * 这是因为UserDetailsService的实现类中注入了PasswordEncoder的Bean，这就造成了依赖注入的循环应用问题。
				 */



				.sessionManagement()
				.invalidSessionStrategy(invalidSessionStrategy)
				.maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
				.maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
				.expiredSessionStrategy(sessionInformationExpiredStrategy)
				.and()
				.and()
				.logout()
				.logoutUrl("/signOut")//自定义退出的地址
				.logoutSuccessUrl("/register")//退出之后跳转到注册页面
				.deleteCookies("JSESSIONID")//删除当前的JSESSIONID
				.and()
			.authorizeRequests()
				.antMatchers(
						SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,	//如果请求的URL需要认证则跳转的URL
					SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,//处理表单中自定义的登录URL
					securityProperties.getBrowser().getLoginPage(),
					SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
					securityProperties.getBrowser().getSignUpUrl(),
					securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".json",
					securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".html",
					"/user/regist")
					.permitAll()//以上的请求都不需要认证
				.anyRequest()
				.authenticated()
				.and()
			.csrf().disable();//关闭csrd拦截
		
	}

	//在浏览器的安全配置类BrowserSecurityConfig中添加一个Bean，
	//这个Bean就是persistenTokenRepository，配置完这个Bean就基本完成了“记住我”功能的开发，然后在将这个Bean设置到configure方法中即可

	/**
	 * 上面的代码tokenRepository.setCreateTableOnStartup(true);是自动创建Token存到数据库时候所需要的表，
	 * 这行代码只能运行一次，如果重新启动数据库，必须删除这行代码，否则将报错
	 * 其实建议查看JdbcTokenRepositoryImpl类中的一个常量字段CREATE_TABLE_SQL，
	 * 这个字段是描述了建表的一个SQL语句，建议手动复制这个SQL语句建表，那么就完全不需要tokenRepository.setCreateTableOnStartup(true)
	 */
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}



	/**
	 * 配置了这个Bean以后，从前端传递过来的密码将被加密
	 *
	 * @return PasswordEncoder实现类对象
	 */


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
