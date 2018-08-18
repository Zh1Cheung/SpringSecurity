/**
 * 
 */
package com.imooc.security.browser.support;

/**
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/14 16:46
 */
public class SimpleResponse {
	
	public SimpleResponse(Object content){
		this.content = content;
	}
	
	private Object content;

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
}
