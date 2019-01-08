package com.fengluochuni.commons.scan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Date;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .apiInfo(getApiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.fengluochuni.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    protected ApiInfo getApiInfo(){
        return new ApiInfo(
                "SpringMVC脚手架项目",
                "SpringMVC脚手架项目",
                "0.0.1",
                "",
                new Contact("徐榕生", "", ""),
                "",
                "",
                new ArrayList<VendorExtension>()
        );
    }
}
