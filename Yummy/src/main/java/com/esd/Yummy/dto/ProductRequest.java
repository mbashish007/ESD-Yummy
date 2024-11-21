package com.esd.Yummy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProductRequest (
    @NotNull(message = "Product name should be present")
    @NotEmpty(message = "Product name should be present")
    @NotBlank(message = "Product name should be present")
    @JsonProperty("name")
    String name,



    @NotNull(message = "Password should be present")
    @Digits( fraction = 2, message = "The number must have up to 10 integer digits and 2 decimal places", integer = 10)
    @JsonProperty("price")
    Double price
) {}
