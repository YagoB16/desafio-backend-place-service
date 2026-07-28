package br.com.yagobarbosa.placeservice.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Table("place")
public record Place(
    @Id Long id,

    @Column("name")
    String name,

    @Column("slug")
    String slug,

    @Column("state")
    String state,

    @Column("created_at")
    @CreatedDate
    LocalDateTime createdAt,

    @Column("updated_at")
    @LastModifiedDate
    LocalDateTime updatedAt){


}
