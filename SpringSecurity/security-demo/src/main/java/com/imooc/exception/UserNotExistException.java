/**
 * 
 */
package com.imooc.exception;

/**
 *自定义服务异常处理类
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 0:49
 */
public class UserNotExistException extends RuntimeException {


	private static final long serialVersionUID = -6112780192479692859L;
	
	private Integer id;
	
	public UserNotExistException(Integer id) {
		super("user not exist");
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
