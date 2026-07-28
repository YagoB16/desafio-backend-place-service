package br.com.yagobarbosa.placeservice.api;

import org.springframework.data.relational.core.mapping.Column;

import jakarta.validation.constraints.NotBlank;

public record PlaceRequest(
    @NotBlank @Column("name") String name,
    @NotBlank @Column("state") String state
) {
}
