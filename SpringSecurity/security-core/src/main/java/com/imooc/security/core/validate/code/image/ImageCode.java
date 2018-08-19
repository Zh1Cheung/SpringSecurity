/**
 * 
 */
package com.imooc.security.core.validate.code.image;

import com.imooc.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;


/**
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 15:40
 *
 *
 * BufferedImage类型的属性
 * String类型的验证码
 * LocalDateTime类型的时间参数
 * 第二个有参构造方法的最后一个参数指定了验证码的过期时间
 * 具体的判断方法由LocalDateTime.now().isAfter(expireTime)来进行判断的。
 */
public class ImageCode extends ValidateCode {
	
	private BufferedImage image; 
	
	public ImageCode(BufferedImage image, String code, int expireIn){
		super(code, expireIn);
		this.image = image;
	}
	
	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
		super(code, expireTime);
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
