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
 *�Զ���������߼�
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 0:57
 */

/**
 * ��ʱ������ǰ�˲���Ҫ��ô�����Ϣ��ֻ��Ҫ������Ϣ��
 * ���ʱ�����Ҫ�Զ����쳣�����ˣ������ǲ���Spring Boot��Ĭ�ϴ���ʽ�ˣ�
 * ��������ǿ���дһ���쳣�����࣬ר�����������Զ����쳣��
 *
 * ��һ���������@ControllerAdviceע�⣬
 * ��ô�����;߱��˴�������Controller�쳣������������Ĵ���ʽ����ͨ�����������еġ�
 * ����ķ�������ר��������UserNotExistException����쳣�ģ�
 * @param @ExceptionHandler����ָ������Ҫ��������쳣��
 * @param @ResponseStatusָ��״̬�룬��󽫴��������ݷ��ء�
 * ����������֮�󣬵��������׳���UserNotExistException�쳣��ʱ�򣬶���ת����������н��д���
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
