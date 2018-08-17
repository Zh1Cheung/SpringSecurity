/**
 * 
 */
package com.imooc.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/12 23:21
 *
 */

/*
message、groups、payload三个属性是必须的，可以参考@NotNull等注解；
@Target注解是指定当前自定义注解可以使用在哪些地方，
		这里仅仅让他可以使用在方法上和属性上；
@Retention指定当前注解保留到运行时；
@Constraint指定了当前注解使用哪个类来进行校验。
 */
//编写注解 自定义身份证号码校验注解
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {
	
	String message();

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
