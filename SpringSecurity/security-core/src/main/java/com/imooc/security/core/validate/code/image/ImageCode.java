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
 * BufferedImage���͵�����
 * String���͵���֤��
 * LocalDateTime���͵�ʱ�����
 * �ڶ����вι��췽�������һ������ָ������֤��Ĺ���ʱ��
 * ������жϷ�����LocalDateTime.now().isAfter(expireTime)�������жϵġ�
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
