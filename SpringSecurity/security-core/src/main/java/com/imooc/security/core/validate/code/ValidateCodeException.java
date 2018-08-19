/**
 * 
 */
package com.imooc.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/14 15:39
 */
public class ValidateCodeException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7285211528095468156L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
