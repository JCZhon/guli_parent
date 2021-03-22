package com.zjh.servicebase;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2配置类
 */
@Configuration
@EnableSwagger2 //swagger注解
public class SwaggerConfig {

    @Bean
    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))//not-表示在接口中如果有这两个字符，这两个字符不会显示
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();

    }

    //文档信息
    private ApiInfo webApiInfo() {

        return new ApiInfoBuilder()
                .title("在线教育项目练习API文档")
                .description("本文档描述了在线教育项目练习时的微服务接口定义")
                .version("1.0")
                .contact(new Contact("java", "http://atguigu.com", "1427387068@qq.com"))
                .build();
    }
}
