/**
 * 
 */
package com.imooc.security.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.imooc.security.core.properties.SecurityProperties;

/**��
 * Ϊ��ʹ���SecurityProperties()��ȡ���õ�����Ч
 *
 *
 * ���ϴ����������˵�¼�Ļ������ܣ�
 * ���û����ʵ���HTML��ʱ�򣬾ͻ���ת����¼ҳ�棬
 * �����RESTful API��ʱ�򣬷���һ��JSON���ݣ�
 * ǰ�˿��Ը���JSON��������ʾ�û���¼��
 * �����û��Զ�����棬������application.yml����
 *
 *
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 15:17
        */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
