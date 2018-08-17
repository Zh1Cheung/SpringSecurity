/**
 * 
 */
package com.imooc.web.config;

import com.imooc.web.filter.TimeFilter;
import com.imooc.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * ����������Ϊ����������������ע��
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 2:00
 */


/**
 * ʹ�õ��໹�������ͬһ���ֻ࣬������β���Ҫ@Componentע�⣬
 * ��ʱ��������Ҫ�Լ�дһ�������࣬��������ע�ᵽSpring�����С�
 * �Ƽ�ʹ�����ַ�ʽ��
 * ��Ϊ���ַ�ʽ���ǿ����Լ�������Ҫ���ص�API�������һ�ַ�ʽ���������е�API��
 */


/**
 * ʹ��������Interceptor��������Config
 * �����������Ҫ�̳�WebMvcConfigurerAdapter������д����������ķ���addInterceptors��
 * ���Զ�����������ӵ�Ӧ���С���ʱ������������Ч�ˡ�
 *
 *
 */


@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@SuppressWarnings("unused")
	@Autowired
	private TimeInterceptor timeInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(timeInterceptor);
	}



//ʹ�ù�����Filter��������Config������������Ϊ����������������ע�ᣩ
//	@Bean
	public FilterRegistrationBean timeFilter() {
		
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		
		TimeFilter timeFilter = new TimeFilter();
		registrationBean.setFilter(timeFilter);
		
		List<String> urls = new ArrayList<>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);
		
		return registrationBean;
		
	}

}
