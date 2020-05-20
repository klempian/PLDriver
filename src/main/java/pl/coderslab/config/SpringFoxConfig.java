package pl.coderslab.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.coderslab.swagger.examples.AdviceNew;
import pl.coderslab.swagger.examples.QuestionNew;
import pl.coderslab.swagger.examples.TrainingNew;
import pl.coderslab.swagger.examples.UserLogin;
import pl.coderslab.swagger.examples.UserToken;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@EnableSwagger2
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

    TypeResolver typeResolver = new TypeResolver();

    @Bean
    public Docket get() { return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .paths(PathSelectors.ant("/api/**"))
            .build().apiInfo(createApiInfo())
            .securitySchemes(Lists.newArrayList(apiKey()))
            .securityContexts(Lists.newArrayList(securityContext()))
            .additionalModels(typeResolver.resolve(UserLogin.class),
                    typeResolver.resolve(UserToken.class),
                    typeResolver.resolve(AdviceNew.class),
                    typeResolver.resolve(TrainingNew.class),
                    typeResolver.resolve(QuestionNew.class)
            );
    }

    @Bean
    SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forHttpMethods(t -> {
                    assert t != null;
                    return !t.matches("GET");
                })
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("JWT", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private ApiInfo createApiInfo() {
        return new ApiInfo("Driver advice API",
                "Driving lessons and trainings",
                "1.00",
                "https://github.com/klempian/PLDriver",
                new Contact("Ian Klemp",
                        "https://github.com/klempian/",
                        "klemp.ian@gmail.com"),
                "",
                "",
                Collections.emptyList()
                );
    }

}
