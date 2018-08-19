/**
 * 
 */
package com.imooc.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.image.ImageCodeGenerator;
import com.imooc.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.imooc.security.core.validate.code.sms.SmsCodeSender;

/**
 *  ������֤����������
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 15:49
 */
@Configuration
public class ValidateCodeBeanConfig {
	
	@Autowired
	private SecurityProperties securityProperties;

	/**
	 *��ʵ������ú���ImageCodeGenerator����ʹ��@Componentע��Ч����һ�µģ����ᱻ���ΪSpring��Bean��
	 * ��������������õĹ�����ʹ����һ��������@ConditionalOnMissingBean(name = "imageCodeGenerator")��
	 * Ҳ����˵�����Ļ��������û������ΪimageCodeGenerator��Spring Bean�Ļ���
	 * ��ô��������ĿĬ�ϵ�Bean�����򽫲��������Bean��
	 * ��Ҳ����˵������û��Զ�����һ����ʵ����ValidateCodeGenerator�ӿڣ�
	 * ����ʵ�������Spring������Bean������ΪimageCodeGenerator����ô��ʹ���û���ʵ����������ͼ����֤�롣
	 * ��������һ�������������ͼ����֤��ĺ�������
	 */
	@Bean
	@ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
	public ValidateCodeGenerator imageValidateCodeGenerator() {
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator(); 
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}

}
