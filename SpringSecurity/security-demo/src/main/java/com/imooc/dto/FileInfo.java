/**
 * 
 */
package com.imooc.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * ��ʵ��������ĵ�˵��
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 4:12
 */
public class FileInfo {

	public FileInfo(String path){
		this.path = path;
	}

	//@ApiModelProperty������������������
	@ApiModelProperty(value = "�ļ��ϴ�����ļ�·��")
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
