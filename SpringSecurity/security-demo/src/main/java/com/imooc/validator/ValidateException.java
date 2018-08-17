/**
 * 
 */
package com.imooc.validator;

import org.springframework.validation.ObjectError;

import java.util.List;

/**
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/15 0:20
 */
public class ValidateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7207451175263593487L;
	
	private List<ObjectError> errors;
	
	public ValidateException(List<ObjectError> errors) {
		this.errors = errors;
	}

	public List<ObjectError> getErrors() {
		return errors;
	}

	public void setErrors(List<ObjectError> errors) {
		this.errors = errors;
	}

}
