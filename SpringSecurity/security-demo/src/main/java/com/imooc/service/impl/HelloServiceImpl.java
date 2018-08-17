/**
 * 
 */
package com.imooc.service.impl;

import org.springframework.stereotype.Service;

import com.imooc.service.HelloService;

/**
 * 编写注解校验逻辑实现类：
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/12 23:45
 */
@Service
public class HelloServiceImpl implements HelloService {

	/* (non-Javadoc)
	 * @see com.imooc.service.HelloService#greeting(java.lang.String)
	 */
	@Override
	public void greeting(String name) {
		System.out.println("greeting");
    }

}
