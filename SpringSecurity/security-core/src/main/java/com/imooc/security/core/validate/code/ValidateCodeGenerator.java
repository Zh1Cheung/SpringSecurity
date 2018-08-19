/**
 * 
 */
package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 生成图片验证码
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 15:45
 */
public interface ValidateCodeGenerator {
	//这里为什么会传入一个ServletWebRequest类型的参数，是因为这个有许多对请求中参数操作的方法，十分方便
	ValidateCode generate(ServletWebRequest request);

}
