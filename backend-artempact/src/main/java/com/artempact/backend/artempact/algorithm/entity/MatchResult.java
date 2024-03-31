package com.artempact.backend.artempact.algorithm.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// Classe interna per incapsulare i risultati del match
@Getter
@Setter
@RequiredArgsConstructor
public class MatchResult {
    private final String myCardName;
    private final String compatibilityCardName;
    private final Short distance;
    private final Object myCard;
    private final Object compatibilityCard;
}
