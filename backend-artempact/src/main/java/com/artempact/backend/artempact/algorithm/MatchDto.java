package com.artempact.backend.artempact.algorithm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchDto {
    private Long creativeCardId;
    private Long businessCardId;
    private Boolean isMatch;
}
