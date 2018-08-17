/**
 * 
 */
package com.imooc.validator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.validation.BindingResult;

/**
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/15 0:20
 */
//@Aspect
//@Component
public class ValidateAspect {
	
	@Around("execution(* com.imooc.web.controller.UserController.*(..))")
	public Object handleValidateResult(ProceedingJoinPoint pjp) throws Throwable {
		
		System.out.println("进入切片");
		
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			if(arg instanceof BindingResult) {
				BindingResult errors = (BindingResult)arg;
				if (errors.hasErrors()) {
					throw new ValidateException(errors.getAllErrors());
				}
			}
		}
		
		Object result = pjp.proceed();
		
		return result;
	}

}