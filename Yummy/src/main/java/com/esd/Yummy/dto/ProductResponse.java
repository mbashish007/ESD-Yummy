package com.esd.Yummy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
public record ProductResponse (
    @JsonProperty("id")
    Long id,

    @JsonProperty("name")
    String name,

    @JsonProperty("price")
    Double price
) {}
