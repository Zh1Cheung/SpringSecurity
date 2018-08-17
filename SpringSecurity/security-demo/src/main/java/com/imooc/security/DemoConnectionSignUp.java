/**
 * 
 */
package com.imooc.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

/**
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/15 0:20
 */
//@Component
public class DemoConnectionSignUp implements ConnectionSignUp {

	/* (non-Javadoc)
	 * @see org.springframework.social.connect.ConnectionSignUp#execute(org.springframework.social.connect.Connection)
	 */
	@Override
	public String execute(Connection<?> connection) {
		//根据社交用户信息默认创建用户并返回用户唯一标识
		return connection.getDisplayName();
	}

}
