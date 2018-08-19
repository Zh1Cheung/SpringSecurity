/**
 * 
 */
package com.imooc.security.core.validate.code;

import com.imooc.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 编写图形验证码生成接口，具体实现在 AbstractValidateCodeProcessor（）方法
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 15:50
 */


@RestController
public class ValidateCodeController {

	@Autowired
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;

	/**
	 * 创建验证码，根据验证码类型不同，调用不同的 {@link ValidateCodeProcessor}接口实现
	 *
	 *
	 *   第一步：根据请求生成一个图形验证码对象: mageCodeGenerator.generate()
	 *   第二步：将图形验证码对象存到session中,第一个参数可以从传入的请求中获取session: sessionStrategy.setAttribute()
	 *   第三步：将生成的图片写到接口的响应中: ImageIO.write()
	 *
	 *   这里使用imageCodeGenerator对象的generate方法生成了图形验证码，并将验证码存入到了session中，最后将图片写回到输出流中。
	 */
	@GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
	public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
			throws Exception {
		validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
	}

}
