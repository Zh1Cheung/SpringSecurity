/**
 * 
 */
package com.imooc.security.core.social.weixin.api;

/**
 * 定义返回用户信息接口
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/14 15:51
 */


/**
 * 相对于QQ的getUserInfo微信多了一个参数openId
 * OAuth2.0的认证流程示意图第五步时，微信的openid 同access_token一起返回。
 * 而Spring Social获取access_token的类AccessGrant.java中没有openid。因此我们自己需要扩展一下Spring Social获取令牌的类（AccessGrant.java）
 */
public interface Weixin {

	/* (non-Javadoc)
	 * @see com.ymt.pz365.framework.security.social.api.SocialUserProfileService#getUserProfile(java.lang.String)
	 */
	WeixinUserInfo getUserInfo(String openId);
	
}
