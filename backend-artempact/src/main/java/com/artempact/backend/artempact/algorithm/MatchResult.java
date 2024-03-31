package com.artempact.backend.artempact.algorithm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Service
public class MatchResult {
    private String myCardName;
    private String compatibilityCardName;
    private double distance;
    private Object myCard;
    private Object compatibilityCard;
}
