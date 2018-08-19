/**
 * 
 */
package com.imooc.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * �����Զ���ĵ�¼ҳ�棬ͨ������Ĵ���������ļ��ж�ȡ��
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 15:15
 */
@ConfigurationProperties(prefix = "Security")
public class SecurityProperties {
	
	private BrowserProperties browser = new BrowserProperties();
	
	private ValidateCodeProperties code = new ValidateCodeProperties();
	
	private SocialProperties social = new SocialProperties();

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}

	public ValidateCodeProperties getCode() {
		return code;
	}

	public void setCode(ValidateCodeProperties code) {
		this.code = code;
	}

	public SocialProperties getSocial() {
		return social;
	}

	public void setSocial(SocialProperties social) {
		this.social = social;
	}
}

