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
 *  生成验证码具体的配置
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 15:49
 */
@Configuration
public class ValidateCodeBeanConfig {
	
	@Autowired
	private SecurityProperties securityProperties;

	/**
	 *其实这个配置和在ImageCodeGenerator类上使用@Component注解效果是一致的，都会被标记为Spring的Bean，
	 * 但是在这里，在配置的过程中使用了一个条件：@ConditionalOnMissingBean(name = "imageCodeGenerator")，
	 * 也就是说上下文环境中如果没有名称为imageCodeGenerator的Spring Bean的话，
	 * 那么就配置项目默认的Bean，否则将不配置这个Bean，
	 * 这也就是说，如果用户自定义了一个类实现了ValidateCodeGenerator接口，
	 * 并且实现类的在Spring容器中Bean的名字为imageCodeGenerator，那么将使用用户的实现类来生成图形验证码。
	 * 到现在这一步，基本完成了图形验证码的核心需求。
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
