/**
 * 
 */
package com.imooc.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 *
 * 使用拦截器Interceptor进行拦截
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 2:10
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */

	/**
	 *
	 preHandle方法的第三个参数是具体的API处理方法的Method对象，
	 我们可以将其强转为HandlerMethod，然后就可以获取该Method的一些属性，
	 比如方法名，方法所在类的类名等信息。preHandle是当访问API之前，都要进入这个方法，由这个方法进行一些逻辑处理，如果处理完结果返回true，
	 那么将继续进入到具体的API中，否则将就地结束访问，逻辑不会进入API方法中。
	 */

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle");
		
		System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
		System.out.println(((HandlerMethod)handler).getMethod().getName());
		
		request.setAttribute("startTime", new Date().getTime());
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */


	/**
	 *
	 postHandle方法是在API方法访问完成之后立即进入的方法，可以处理一些逻辑，
	 比如将API中的数据封装到ModelAndView中，如果前面的preHandle方法返回false，将不会执行该方法，
	 如果API方法发生了异常，也将不会调用此方法。
	 */


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
		Long start = (Long) request.getAttribute("startTime");
		System.out.println("time interceptor 耗时:"+ (new Date().getTime() - start));

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */

	/**
	 *
	 afterCompletion方法的调用只要preHandle方法通过之后就会调用它，不论API方法是否出现了异常。
	 如果出现了异常，将被封装到Exception对象中。
	 */


	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion");
		Long start = (Long) request.getAttribute("startTime");
		System.out.println("time interceptor 耗时:"+ (new Date().getTime() - start));
		System.out.println("ex is "+ex);

	}

}
