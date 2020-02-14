package com.github.anricx.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

@Schema(title = "Goods Entity")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Goods {

    @Schema(title = "Goods Name", description = "This is Goods Name", example = "Apple", required = true)
    private String name;

    @Positive
    @NonNull
    @Max(10)
    private Integer price;

}
