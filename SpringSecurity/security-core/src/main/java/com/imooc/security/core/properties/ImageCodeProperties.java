/**
 * 
 */
package com.imooc.security.core.properties;

/**
 * 图形验证码的默认配置
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 15:42
 */
public class ImageCodeProperties extends SmsCodeProperties {
	
	public ImageCodeProperties() {
		setLength(4);
	}
	 
	private int width = 67;
	private int height = 23;
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

}
