package com.sea.be.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String CONTROLLER_PATH = "com.sea.be.demo.Controller";
    private static final String API_PATH = "/api/v1/*";
    private static final String SWAGGER_TITLE= "Item Management";

    @Bean
    ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(SWAGGER_TITLE).build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(CONTROLLER_PATH))
                .paths(PathSelectors.ant(API_PATH))
                .build()
                .apiInfo(apiInfo());
    }
}

