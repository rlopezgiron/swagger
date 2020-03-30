package com.test.swagger.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

	@Bean
    public Docket publicSwaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("publicApi")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test.swagger.controller.publicApi"))
                //.paths(PathSelectors.any())        
                .paths(PathSelectors.regex("/public.*"))
                .build()
                .apiInfo(apiInfo()).globalResponseMessage(RequestMethod.GET, responseMessages ())
                .alternateTypeRules(alternateRule());
    }
	
	@Bean
    public Docket privateSwaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("privateApi")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test.swagger.controller.privateApi")) 
                .paths(PathSelectors.regex("/private.*"))
                .build()
                .apiInfo(apiInfo()).globalResponseMessage(RequestMethod.GET, responseMessages ())
                .alternateTypeRules(alternateRule());
    }
	 
	private AlternateTypeRule alternateRule () {
		TypeResolver typeResolver = new TypeResolver();
		AlternateTypeRule rule = AlternateTypeRules.newRule(
								typeResolver.resolve(DeferredResult.class,
				                typeResolver.resolve(ResponseEntity.class,WildcardType.class)), 
								typeResolver.resolve(WildcardType.class), Ordered.HIGHEST_PRECEDENCE);
		return rule;
	}
	 
	private List <ResponseMessage> responseMessages() {
		List <ResponseMessage> list = Arrays.asList(
				new ResponseMessageBuilder()   
			    	.code(500)
			    	.message("Unknown error")
			    	.responseModel(new ModelRef("string"))
			    	.build(),
			    new ResponseMessageBuilder() 
			      	.code(401)
			      	.message("Unauthorized Operation")
			      	.build(),
			    new ResponseMessageBuilder() 
			      	.code(403)
			      	.message("Forbidden  Operation")
			      	.build(),
			    new ResponseMessageBuilder() 
			      	.code(404)
			      	.message("Operation not found")
			      	.build());
		
		return list;
	}
	
	private ApiInfo apiInfo() {
		
        return new ApiInfoBuilder()
                .title("Spring Boot REST API")
                .description("\"Spring Boot REST API Example\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("Roberto Lopez Giron", "", "roberto.lopez.giron@gmail.com"))
                .build();
    }
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");    
   }
}
