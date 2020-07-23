package com.infy.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.infy.controller"))
                .paths(or(regex("/Vehicle/insert/vehicle"), regex("/Vehicle/getAll"),
                		regex("/Vehicle/modelid.*"), regex("/Vehicle/view.*")
                		, regex("/Vehicle/vehicle"), regex("/Vehicle/getByColour.*"), 
                		regex("/Vehicle/getByPrice.*")))
                .build()
                .apiInfo(metaData());
    }
	
	private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo("", "", "", "", "", "", "");
        return apiInfo;
    }

	

}