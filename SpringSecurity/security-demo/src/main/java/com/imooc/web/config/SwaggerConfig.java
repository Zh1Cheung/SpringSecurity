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
 *配置Swagger 在地址栏访问http://localhost:8080/swagger-ui.html就可以进入自定义的Swagger文档界面
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 3:43
 */


/**
 * 我们写一个Swagger的配置类，添加上@Configuration注解方便被Spring Boot配置，
 * 添加@EnableSwagger2注解启动Swagger文档构建能力。
 *
 *
 *createRestfulApiDocs方法创建Docket的Bean之后，apiInfo()用来创建该API的基本信息（这些基本信息会展现在文档页面中）。
 *
 * select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，本例采用指定扫描的包路径来定义，
 * Swagger会扫描该包下所有Controller定义的API，并产生文档内容（除了被@ApiIgnore指定的请求）。
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
                .title("自定义RESTful API文档")
                .description("更多内容请关注博客：Zh1Cheung.com")
                .termsOfServiceUrl("Zh1Cheung.com")
                .contact(new Contact("Lemon", "Zh1Cheung.com", "945503088@qq.com"))
                .version("1.0.0")
                .build();
    }
}