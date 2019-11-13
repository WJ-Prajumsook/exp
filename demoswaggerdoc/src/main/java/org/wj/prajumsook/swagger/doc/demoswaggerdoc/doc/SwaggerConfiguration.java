package org.wj.prajumsook.swagger.doc.demoswaggerdoc.doc;

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

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.wj.prajumsook.swagger.doc.demoswaggerdoc"))
                .paths(PathSelectors.regex("/v1.*"))
                .build().apiInfo(apiEndPointInfo());
    }

    private ApiInfo apiEndPointInfo() {
        return new ApiInfoBuilder().title("My REST API docs")
                .description("My REST API documentation")
                .contact(new Contact("WJ Prajumsook", "www.wjprajumsook.com", "wj.prajumsook@prajumsook.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("0.0.1")
                .build();
    }
}
