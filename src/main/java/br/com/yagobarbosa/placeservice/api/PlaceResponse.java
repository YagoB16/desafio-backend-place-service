package br.com.yagobarbosa.placeservice.api;

import java.time.LocalDateTime;

import org.springframework.data.relational.core.mapping.Column;

public record PlaceResponse(
    @Column("id") Long id,
    
    @Column("name") String name,

    @Column("slug") String slug,

    @Column("state") String state,

    @Column("created_at") LocalDateTime createdAt,

    @Column("updated_at") LocalDateTime updatedAt) {
}
