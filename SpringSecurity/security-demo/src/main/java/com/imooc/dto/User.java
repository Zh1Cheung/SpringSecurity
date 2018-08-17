/**
 * 
 */
package com.imooc.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.validator.MyConstraint;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/12 15:50
 *
 */
/*
@JsonView的使用步骤
	1使用接口来声明多个视图
	2在值对象的get方法上指定视图
	3在Controller方法上指定视图
使用@JsonView注解将两个视图绑定到对应的字段的get方法上面
 */
public class User {
	//简单视图
	public interface UserSimpleView {};
	//详情视图
	public interface UserDetailView extends UserSimpleView {};

	private String id;
	
	@MyConstraint(message = "这是一个测试")
	@ApiModelProperty(value = "用户名")
	private String username;
	
	@NotBlank(message = "密码不能为空")
	private String password;
	
	@Past(message = "生日必须是过去的时间")
	private Date birthday;

	@JsonView(UserSimpleView.class)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonView(UserDetailView.class)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonView(UserSimpleView.class)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@JsonView(UserSimpleView.class)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
