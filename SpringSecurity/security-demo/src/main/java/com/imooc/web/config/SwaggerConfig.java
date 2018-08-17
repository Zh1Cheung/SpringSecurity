package com.imooc.web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *����Swagger �ڵ�ַ������http://localhost:8080/swagger-ui.html�Ϳ��Խ����Զ����Swagger�ĵ�����
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 3:43
 */


/**
 * ����дһ��Swagger�������࣬�����@Configurationע�ⷽ�㱻Spring Boot���ã�
 * ���@EnableSwagger2ע������Swagger�ĵ�����������
 *
 *
 *createRestfulApiDocs��������Docket��Bean֮��apiInfo()����������API�Ļ�����Ϣ����Щ������Ϣ��չ�����ĵ�ҳ���У���
 *
 * select()��������һ��ApiSelectorBuilderʵ������������Щ�ӿڱ�¶��Swagger��չ�֣���������ָ��ɨ��İ�·�������壬
 * Swagger��ɨ��ð�������Controller�����API���������ĵ����ݣ����˱�@ApiIgnoreָ�������󣩡�
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestfulApiDocs() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.imooc.web.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("�Զ���RESTful API�ĵ�")
                .description("�����������ע���ͣ�Zh1Cheung.com")
                .termsOfServiceUrl("Zh1Cheung.com")
                .contact(new Contact("Lemon", "Zh1Cheung.com", "945503088@qq.com"))
                .version("1.0.0")
                .build();
    }
}