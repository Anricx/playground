package com.github.anricx.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.media.*;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@OpenAPIDefinition(info = @Info(title = "My API", version = "1.0.0", description = "My API REST API",
        contact = @Contact(url = "https://github.com/Anricx", name = "Developer", email = "joe.dengtao@gmail.com")
    )
)
@Configuration
public class SpringConfig implements OpenApiCustomiser {

    @Override
    public void customise(OpenAPI api) {
        // Add Production Server
        api.addServersItem(new Server()
                .url("https://api.github.com")
                .description("Production API Server"));

        // Define Tags..
        api.addTagsItem(new Tag()
                .name("User API")
                .description("This is User API"));

        // Create Request Parameters
        List<Parameter> parameters = Arrays.asList(
                new Parameter().in(String.valueOf(ParameterIn.QUERY)).required(true).name("username").description("Input UserName").example("jack"),
                new Parameter().in(String.valueOf(ParameterIn.QUERY)).required(true).name("password").description("Input Password").example("secret")
        );

        // Create Response...
        Map<String, Schema> replyModel = new HashMap<>();
        replyModel.put("code", new StringSchema().title("Status Code").example("200"));
        replyModel.put("message", new StringSchema().title("Status Message").example("OK"));
        replyModel.put("access_token", new StringSchema().title("Access Token"));

        Schema replySchema = new ObjectSchema()
                .name("UserLoginReply")
                .title("UserLoginReply")
                .description("User Login Reply")
                .properties(replyModel);

        MediaType replyMedia = new MediaType().schema(replySchema);

        ApiResponses responses = new ApiResponses()
                .addApiResponse("200", new ApiResponse().description("Access Granted")
                        .content(new Content().addMediaType("application/json", replyMedia)))
                .addApiResponse("500", new ApiResponse().description("Internal Server Error"));

        // Create Operation
        Operation operation = new Operation().operationId("/user/login")
                .addTagsItem("User API")
                .parameters(parameters)
                .responses(responses);

        // Register Path
        api.path("/user/login", new PathItem().post(operation));

    }
}
