/**
 * 
 */
package com.imooc.security.core.properties;

/**
 *
 * securityProperties.getBrowser().getLoginPage()
 * 就决定了用户是跳转到自定义的登录页面，还是此项目中自带的登录页面中
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 15:15
 */
public class BrowserProperties {
	
	private SessionProperties session = new SessionProperties();
	
	private String signUpUrl = "/imooc-signUp.html";
	
	private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
	
	private LoginResponseType loginType = LoginResponseType.JSON;

	//添加一个字段rememberMeSeconds，这个字段用来描述“记住我”的时间期限。
	// 这里默认的有效期是一个小时，也就是说在一个小时内重复登录，无需输入用户名和密码。
	private int rememberMeSeconds = 3600;

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginResponseType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginResponseType loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}

	public String getSignUpUrl() {
		return signUpUrl;
	}

	public void setSignUpUrl(String signUpUrl) {
		this.signUpUrl = signUpUrl;
	}

	public SessionProperties getSession() {
		return session;
	}

	public void setSession(SessionProperties session) {
		this.session = session;
	}
	
}
