package br.com.mbs.testmarcelo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.common.collect.ImmutableList;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig  {                                    
    @Bean
    public Docket api() { 
     
    	Docket docket =  new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("br.com.mbs.testmarcelo.api"))
        .paths(PathSelectors.any())
  
        .build()
        .useDefaultResponseMessages(false)
        .apiInfo(apiInfo());
        
        
        return docket;
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Teste do Marcelo")
                .description("Este é um teste de BackEnd")
                .version("1.0.0")                
                .contact(new Contact("Marcelo Soares", "", "mbsoares.infor@gmail.com"))
                .build();
    }
    
   
}