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
message��groups��payload���������Ǳ���ģ����Բο�@NotNull��ע�⣻
@Targetע����ָ����ǰ�Զ���ע�����ʹ������Щ�ط���
		���������������ʹ���ڷ����Ϻ������ϣ�
@Retentionָ����ǰע�Ᵽ��������ʱ��
@Constraintָ���˵�ǰע��ʹ���ĸ���������У�顣
 */
//��дע�� �Զ������֤����У��ע��
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {
	
	String message();

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
