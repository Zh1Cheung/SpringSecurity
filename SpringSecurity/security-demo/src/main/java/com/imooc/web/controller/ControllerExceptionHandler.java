/**
 * 
 */
package com.imooc.web.controller;

import com.imooc.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 *自定义错误处理逻辑
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 0:57
 */

/**
 * 有时候我们前端不需要这么多的信息，只需要部分信息，
 * 这个时候就需要自定义异常处理了，而不是采用Spring Boot的默认处理方式了，
 * 在这里，我们可以写一个异常处理类，专门用来处理自定义异常。
 *
 * 当一个类加上了@ControllerAdvice注解，
 * 那么这个类就具备了处理其他Controller异常的能力，具体的处理方式还是通过方法来进行的。
 * 上面的方法就是专门来处理UserNotExistException这个异常的，
 * @param @ExceptionHandler就是指定了需要被处理的异常，
 * @param @ResponseStatus指定状态码，最后将处理后的数据返回。
 * 定义好这个类之后，当代码中抛出了UserNotExistException异常的时候，都会转到这个方法中进行处理。
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(UserNotExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> handleUserNotExistException(UserNotExistException ex) {
		Map<String, Object> result = new HashMap<>();
		result.put("id", ex.getId());
		result.put("message", ex.getMessage());
		return result;
	}

}
