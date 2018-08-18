/**
 * 
 */
package com.imooc.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.core.properties.LoginResponseType;
import com.imooc.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义用户登录成功处理
 *
 * 用户登录成功后，Spring Security的默认处理方式是跳转到原来的链接上，这也是企业级开发的常见方式，
 * 但是有时候采用的是AJAX方式发送的请求，往往需要返回JSON数据
 *
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 15:20
 */


/**
 * SavedRequestAwareAuthenticationSuccessHandler是Spring Security默认的成功处理器，默认是跳转。
 * 这里将认证信息作为JSON数据进行了返回，也可以返回其他数据，这个是根据业务需求来定的，
 * 同样，这里也是配置了用户的自定义的登录类型，要么是跳转，要么是JSON，
 * securityProperties.getBrowser().getLoginType()决定了登录的类型，默认是JSON，如果需要跳转，也是需要在YAML配置文件中进行配置的。
 *
 *
 *
 */
@Component("imoocAuthenticationSuccessHandler")
public class ImoocAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SecurityProperties securityProperties;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		logger.info("登录成功");

		if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(authentication));
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}

	}

}
