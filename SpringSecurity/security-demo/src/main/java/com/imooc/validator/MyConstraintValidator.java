/**
 * 
 */
package com.imooc.validator;

import com.imooc.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/12 23:31
 *

 * 校验身份证合法性的实体类MyConstraintValidator实现了接口ConstraintValidator，
 * 其后面的第一个泛型是指为哪个注解提供校验服务，第二个泛型是指需要校验的值的类型；
 *
 *  它需要实现两个方法，第一个是初始化方法，第二个是校验的逻辑方法，
 *  在启动校验方法之前，都会进行初始化，可以在初始化方法中初始一些数据，
 *  比如获取用户自定义message内容；
 *  第二个方法的第一个参数是需要被校验的值，第二个参数是校验的上下文环境
 */
//编写注解校验逻辑类：
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

	/*
	一般的开发过程中，往往将校验逻辑抽取成为一个Service服务，
	并通过Spring的DI注入到这个校验类中，
	需要注意的是，这个校验类上并不需要添加Spring的Component等注解，
	Spring可以自动将校验逻辑服务类实例对象注入到这个类中。
	在这里就注入了HelloService实现类对象。
	 */
	@Autowired
	private HelloService helloService;
	/**
	 * 初始化方法
	 * @param constraintAnnotation 自定义的校验注解
	 */
	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		System.out.println("my validator init");
	}



	/**
	 * 具体的校验逻辑方法
	 *
	 * @param value   需要校验的值，从前端传递过来
	 * @param context 校验器的校验环境
	 * @return 通过校验返回true，否则返回false
	 */
	@Override
	public boolean isValid(Object value,
						   ConstraintValidatorContext context) {
		helloService.greeting("tom");
		System.out.println(value);
		return true;
	}

}
