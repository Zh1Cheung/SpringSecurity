/**
 * 
 */
package com.imooc.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 给实体类添加文档说明
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 4:12
 */
public class FileInfo {

	public FileInfo(String path){
		this.path = path;
	}

	//@ApiModelProperty用来描述参数的意义
	@ApiModelProperty(value = "文件上传后的文件路径")
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
