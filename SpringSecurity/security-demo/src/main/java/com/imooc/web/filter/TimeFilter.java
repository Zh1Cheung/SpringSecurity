/**
 * 
 */
package com.imooc.web.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * 将拦截器标注为Spring的Bean
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 1:57
 */
//@Component

/**
 * 启动Spring Boot应用的时候，上面的拦截器就会起作用，
 * 当访问每一个服务的时候，都会进入这个拦截器中。
 * 初始化方法init和销毁方法destroy只会调用一次，
 * 分别是应用启动时候调用init方法，应用关闭时候调用destroy方法。而doFilter方法则在每次都会调用。
 */
public class TimeFilter implements Filter {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		System.out.println("time filter destroy");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("time filter start");
		long start = new Date().getTime();
		chain.doFilter(request, response);
		System.out.println("time filter 耗时:"+ (new Date().getTime() - start));
		System.out.println("time filter finish");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("time filter init");
	}

}
