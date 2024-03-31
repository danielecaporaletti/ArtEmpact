package com.artempact.backend.artempact.algorithm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CompatibilityCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "fromCardIdName", nullable = false)
    private String fromCardIdName;
    @Column(name = "fromCardId", nullable = false)
    private Long fromCardId;
    @Column(name = "toCardIdName", nullable = false)
    private String toCardIdName;
    @Column(name = "toCardId", nullable = false)
    private Long toCardId;
    @Column(name = "isCompatible", nullable = false, columnDefinition = "BOOL")
    private Boolean isCompatible;
    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

}
