package com.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
		.apiInfo(apiInfo())
		.select()
		.apis(RequestHandlerSelectors.any())
		.paths(Predicates.not(PathSelectors.regex("/error.*")))//错误路径不监控
		.paths(PathSelectors.any())
		.build();
	}
	public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Swaggwe2接口文档")
                .contact(new Contact("JiaMin", "www.baidu.com", "test@qq.com"))
                .description("这是SWAGGER_2生成的接口文档")
                .version("v1.0")
                .build();
    }
}







