package pl.coderslab.swagger;

import com.fasterxml.classmate.TypeResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import pl.coderslab.swagger.examples.UserLogin;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
@Slf4j
public class SwaggerLoginListingScanner implements ApiListingScannerPlugin {

    private final CachingOperationNameGenerator operationNames;

    public SwaggerLoginListingScanner(CachingOperationNameGenerator operationNames) {//<9>
        this.operationNames = operationNames;
    }

    @Override
    public List<ApiDescription> apply(DocumentationContext context) {
        return new ArrayList<>(
                Arrays.asList(
                        new ApiDescription(null, "/api/login", "login", Collections.singletonList(
                                new OperationBuilder(operationNames)
                                        .summary("login")
                                        .tags(Set.of("Login Controller"))
                                        .authorizations(new ArrayList<>())
                                        .position(1)
                                        .codegenMethodNameStem("loginPost")
                                        .method(HttpMethod.POST)
                                        .notes("User and admin login")
                                        .parameters(
                                                Arrays.asList(
                                                        new ParameterBuilder()
                                                                .description("Login Parameter")
                                                                .type(new TypeResolver().resolve(UserLogin.class))
                                                                .name("userLogin")
                                                                .parameterType("body")
                                                                .parameterAccess("access")
                                                                .required(true)
                                                                .modelRef(new ModelRef("UserLogin"))
                                                                .build()
                                                )
                                        ).responseMessages(responseMessages())
                                        .responseModel(new ModelRef(("UserToken")))
                                        .build()
                        ), false)));
    }


    private Set<ResponseMessage> responseMessages() { //<8>
        return Set.of(new ResponseMessageBuilder()
                        .code(200)
                        .message("Ok")
                        .responseModel(new ModelRef("UserToken"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(401)
                        .message("Unauthorized")
                        .build(),
                new ResponseMessageBuilder()
                        .code(403)
                        .message("Forbidden")
                        .build(),
                new ResponseMessageBuilder()
                        .code(404)
                        .message("Not found")
                        .build()
        );
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return DocumentationType.SWAGGER_2.equals(delimiter);
    }
}
