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
 * 将拦截器作为第三方拦截器进行注册
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 2:00
 */


/**
 * 使用的类还是上面的同一个类，只不过这次不需要@Component注解，
 * 这时候我们需要自己写一个配置类，将过滤器注册到Spring容器中。
 * 推荐使用这种方式，
 * 因为这种方式我们可以自己设置需要拦截的API，否则第一种方式是拦截所有的API。
 */


/**
 * 使用拦截器Interceptor进行拦截Config
 * 这个配置类需要继承WebMvcConfigurerAdapter，并重写添加拦截器的方法addInterceptors，
 * 将自定义拦截器添加到应用中。这时候拦截器就生效了。
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



//使用过滤器Filter进行拦截Config（将拦截器作为第三方拦截器进行注册）
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
