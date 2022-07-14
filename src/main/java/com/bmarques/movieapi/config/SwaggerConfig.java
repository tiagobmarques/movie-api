package com.bmarques.movieapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bmarques.movieapi"))
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, responseMessageForPost())
                .apiInfo(apiInfo());
    }

    private List<ResponseMessage> responseMessageForPost() {
        return new ArrayList<>() {{
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("Erro interno da aplicação")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(403)
                    .message("Forbidden!")
                    .build());
        }};
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Movie - Golden Raspberry Awards")
                .description("This is only a api to return the best producer in the world.")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("Tiago Marques", "www.linkedin.com/in/tiago-bitencourt-marques-2996591a2", "tiagobm564@gmail.com"))
                .build();
    }
}