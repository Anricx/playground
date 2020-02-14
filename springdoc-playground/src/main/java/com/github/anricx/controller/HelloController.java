package com.github.anricx.controller;

import com.github.anricx.model.Goods;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Hello API", description = "This is Hello API")
@RestController
public class HelloController {

    @Operation(
            summary = "Greeting Operation",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK")
            })
    @GetMapping(path = "greeting")
    public String greeting(@Parameter(description = "Input Your Name") @RequestParam("name") String name) {
        return "Hello " + name;
    }

    @Operation(summary = "Greeting Operation")
    @GetMapping(path = "want")
    public Goods want(@RequestParam("name") String name) {
        return Goods.builder().name(name).build();
    }

}
