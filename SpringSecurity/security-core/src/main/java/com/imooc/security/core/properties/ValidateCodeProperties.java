/**
 * 
 */
package com.imooc.security.core.properties;

/**
 * 	Ϊ�˱��ֺ�֮ǰ��������Ļ������ñ���һ�£������װһ������
 *	�ٽ�������װ��SecurityProperties����
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
