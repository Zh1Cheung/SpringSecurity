/**
 * 
 */
package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * ����ͼƬ��֤��
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 15:45
 */
public interface ValidateCodeGenerator {
	//����Ϊʲô�ᴫ��һ��ServletWebRequest���͵Ĳ���������Ϊ��������������в��������ķ�����ʮ�ַ���
	ValidateCode generate(ServletWebRequest request);

}
