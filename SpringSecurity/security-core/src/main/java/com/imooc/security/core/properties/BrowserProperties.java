/**
 * 
 */
package com.imooc.security.core.properties;

/**
 *
 * securityProperties.getBrowser().getLoginPage()
 * �;������û�����ת���Զ���ĵ�¼ҳ�棬���Ǵ���Ŀ���Դ��ĵ�¼ҳ����
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 15:15
 */
public class BrowserProperties {
	
	private SessionProperties session = new SessionProperties();
	
	private String signUpUrl = "/imooc-signUp.html";
	
	private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
	
	private LoginResponseType loginType = LoginResponseType.JSON;

	//���һ���ֶ�rememberMeSeconds������ֶ�������������ס�ҡ���ʱ�����ޡ�
	// ����Ĭ�ϵ���Ч����һ��Сʱ��Ҳ����˵��һ��Сʱ���ظ���¼�����������û��������롣
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
