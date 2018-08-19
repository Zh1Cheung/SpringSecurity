/**
 * 
 */
package com.imooc.security.core.authentication.mobile;

import com.imooc.security.core.properties.SecurityConstants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SmsCodeAuthenticationFilter对应用户名密码登录的UsernamePasswordAuthenticationFilter同样继承AbstractAuthenticationProcessingFilter
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 16:26
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	//request中必须含有mobile参数
	private String mobileParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
	private boolean postOnly = true;


	public SmsCodeAuthenticationFilter() {
		//处理的手机验证码登录请求处理url
		super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, "POST"));
	}


	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		//判断是是不是post请求
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		//从请求中获取手机号码
		String mobile = obtainMobile(request);

		if (mobile == null) {
			mobile = "";
		}

		mobile = mobile.trim();

		////创建SmsCodeAuthenticationToken(未认证)
		SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);

		//设置用户信息
		setDetails(request, authRequest);

		//返回Authentication实例
		return this.getAuthenticationManager().authenticate(authRequest);
	}


	/**
	 * 获取手机号
	 */
	protected String obtainMobile(HttpServletRequest request) {
		return request.getParameter(mobileParameter);
	}


	protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}


	public void setMobileParameter(String usernameParameter) {
		Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
		this.mobileParameter = usernameParameter;
	}



	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public final String getMobileParameter() {
		return mobileParameter;
	}

}
