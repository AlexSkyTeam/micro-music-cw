package com.example.id.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tokens")
@Getter
@Setter
public class TokenEntity {
    @Id
    private String token;
    @ManyToOne(optional = false)
    private UserEntity user;
    @Column(
            insertable = true,
            updatable = false,
            nullable = false,
            columnDefinition = "TIMESTAMPTZ NOT NULL"
    )
    private Instant expire;
    @Column(
            insertable = false,
            updatable = false,
            nullable = false,
            columnDefinition = "TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP"
    )
    private Instant created;
}
