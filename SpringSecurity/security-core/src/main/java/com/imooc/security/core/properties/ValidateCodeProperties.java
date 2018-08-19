/**
 * 
 */
package com.imooc.security.core.properties;

/**
 * 	为了保持和之前的浏览器的基本设置保持一致，这里包装一层配置
 *	再将这个类包装到SecurityProperties类中
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 15:43
 */
public class ValidateCodeProperties {
	
	private ImageCodeProperties image = new ImageCodeProperties();
	
	private SmsCodeProperties sms = new SmsCodeProperties();

	public ImageCodeProperties getImage() {
		return image; 
	}

	public void setImage(ImageCodeProperties image) {
		this.image = image;
	}

	public SmsCodeProperties getSms() {
		return sms;
	}

	public void setSms(SmsCodeProperties sms) {
		this.sms = sms;
	}
	
}
