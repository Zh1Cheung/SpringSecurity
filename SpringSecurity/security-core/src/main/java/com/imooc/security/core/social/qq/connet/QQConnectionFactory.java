/**
 * 
 */
package com.imooc.security.core.social.qq.connet;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import com.imooc.security.core.social.qq.api.QQ;

/**
 * QQConnectionFactory连接服务提供商的工厂类
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/14 15:21
 */

public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}

}
