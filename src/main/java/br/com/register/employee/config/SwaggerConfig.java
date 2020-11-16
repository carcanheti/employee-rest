package br.com.register.employee.config;

import com.fasterxml.classmate.TypeResolver;

import com.google.common.collect.Lists;

import br.com.register.employee.models.ErrorResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

import static io.swagger.models.auth.In.HEADER;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {
	
	
	private static final String MSG_ERROR = "Error";
	private static final String MSG_SERVER_ERROR = "Internal Server Error";
	private static final String MSG_BAD_REQUEST = "Bad Request";
	private static final String MSG_OK = "OK";
	private static final String MSG_NO_CONTENT = "No Content";
	private static final String JWT = "jwt";

    @Bean
    public Docket apiDocket() {
        var typeResolver = new TypeResolver();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.register.employee.controllers"))
                .paths(PathSelectors.any())
                .build()
                .forCodeGeneration(true)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
                .globalResponseMessage(RequestMethod.PUT, globalPutResponseMessages())
                .apiInfo(apiInfo())
                .additionalModels(typeResolver.resolve(ErrorResponse.class))
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()))
                .tags(new Tag("Cargo", ""), 
                  	  new Tag("Funcionário", ""),
                  	  new Tag("Departamento", "")
                  	 );
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private List<ResponseMessage> globalGetResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.OK.value())
                        .message(MSG_OK)
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(MSG_BAD_REQUEST)
                        .responseModel(new ModelRef(MSG_ERROR))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(MSG_SERVER_ERROR)
                        .responseModel(new ModelRef(MSG_ERROR))
                        .build()
        );
    }
    

    private List<ResponseMessage> globalPutResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.NO_CONTENT.value())
                        .message(MSG_NO_CONTENT)
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(MSG_BAD_REQUEST)
                        .responseModel(new ModelRef(MSG_ERROR))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(MSG_SERVER_ERROR)
                        .responseModel(new ModelRef(MSG_ERROR))
                        .build()
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Cadastro de Funcionários")
                .description("API para cadastro de funcionários")
                .version("1.0.0")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .contact(new Contact("Cadastro S/A", "http://www.cadastro.com.br", "cadastro@yahoo.com.br"))
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey(JWT, AUTHORIZATION, HEADER.name());
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(authDefinition())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> authDefinition() {
        final var scopes = new AuthorizationScope[0];
        return Lists.newArrayList(
                SecurityReference.builder()
                        .reference(JWT)
                        .scopes(scopes)
                        .build()
        );
    }
}
