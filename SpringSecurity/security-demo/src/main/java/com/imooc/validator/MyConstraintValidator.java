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

 * У�����֤�Ϸ��Ե�ʵ����MyConstraintValidatorʵ���˽ӿ�ConstraintValidator��
 * �����ĵ�һ��������ָΪ�ĸ�ע���ṩУ����񣬵ڶ���������ָ��ҪУ���ֵ�����ͣ�
 *
 *  ����Ҫʵ��������������һ���ǳ�ʼ���������ڶ�����У����߼�������
 *  ������У�鷽��֮ǰ��������г�ʼ���������ڳ�ʼ�������г�ʼһЩ���ݣ�
 *  �����ȡ�û��Զ���message���ݣ�
 *  �ڶ��������ĵ�һ����������Ҫ��У���ֵ���ڶ���������У��������Ļ���
 */
//��дע��У���߼��ࣺ
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

	/*
	һ��Ŀ��������У�������У���߼���ȡ��Ϊһ��Service����
	��ͨ��Spring��DIע�뵽���У�����У�
	��Ҫע����ǣ����У�����ϲ�����Ҫ���Spring��Component��ע�⣬
	Spring�����Զ���У���߼�������ʵ������ע�뵽������С�
	�������ע����HelloServiceʵ�������
	 */
	@Autowired
	private HelloService helloService;
	/**
	 * ��ʼ������
	 * @param constraintAnnotation �Զ����У��ע��
	 */
	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		System.out.println("my validator init");
	}



	/**
	 * �����У���߼�����
	 *
	 * @param value   ��ҪУ���ֵ����ǰ�˴��ݹ���
	 * @param context У������У�黷��
	 * @return ͨ��У�鷵��true�����򷵻�false
	 */
	@Override
	public boolean isValid(Object value,
						   ConstraintValidatorContext context) {
		helloService.greeting("tom");
		System.out.println(value);
		return true;
	}

}
