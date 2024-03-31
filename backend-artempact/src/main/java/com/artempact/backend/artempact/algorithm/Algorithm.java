package com.artempact.backend.artempact.algorithm;

import lombok.*;
import org.springframework.stereotype.Service;

@Service
public class Algorithm {

    @Getter
    @AllArgsConstructor
    public static class DistanceValidationResult {
        private final double distance;
        private final boolean isValid;
    }

    public DistanceValidationResult isValid(Double maxDistance, Double x1, Double y1, Double x2, Double y2) {
        double calculatedDistance = distanceEuclidean(x1, y1, x2, y2);
        return new DistanceValidationResult(calculatedDistance, calculatedDistance <= maxDistance);
    }

    private double distanceEuclidean(Double x1, Double y1, Double x2, Double y2) {
        // Calcolo della differenza tra le coordinate
        double deltaX = x2 - x1;
        double deltaY = y2 - y1;

        // Calcolo della distanza euclidea
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
