package com.artempact.backend.artempact.geoService;

import com.artempact.backend.artempact.repository.genericRepositoryNoJPA.GenericLocationRepository;
import com.artempact.backend.artempact.repository.genericRepositoryNoJPA.GenericProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Map;

@Service
public class AsyncGeoService {

    private final WebClient webClient;
    private final GeoUpdateService geoUpdateService;
    private static final Logger log = LoggerFactory.getLogger(AsyncGeoService.class);

    @Value("${url.microservice.version}")
    private String microserviceGeo;

    @Autowired
    public AsyncGeoService(WebClient.Builder webClientBuilder, GeoUpdateService geoUpdateService) {
        this.webClient = webClientBuilder.baseUrl(microserviceGeo).build();
        this.geoUpdateService = geoUpdateService;
    }

    @Async
    public void updateCoordinates(GenericProfileRepository repository, String profileId, String city, String province) {
        getCoordinates(city, province)
                .publishOn(Schedulers.boundedElastic())
                .doOnSuccess(coordinates -> geoUpdateService.updateProfileWithCoordinates(repository, profileId, coordinates))
                .doOnError(error -> logError(error, profileId))
                .onErrorResume(error -> Mono.empty()) // Continua in caso di errore, dopo averlo gestito
                .subscribe();
    }

    @Async
    public void updateCityTargetCoordinates(GenericLocationRepository repository, Long cityTargetId, String city, String province) {
        getCoordinates(city, province)
                .publishOn(Schedulers.boundedElastic())
                .doOnSuccess(coordinates -> geoUpdateService.updateCityTargetWithCoordinates(repository, cityTargetId, coordinates))
                .doOnError(error -> logError(error, Long.toString(cityTargetId)))
                .onErrorResume(error -> Mono.empty()) // Continua in caso di errore, dopo averlo gestito
                .subscribe();
    }

    private Mono<Map<String, Double>> getCoordinates(String city, String province) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/coordinates")
                        .queryParam("city", city)
                        .queryParam("province", province)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Double>>() {});
    }


    private void logError(Throwable error, String profileId) {
        log.error("Errore nel recupero delle coordinate per il profilo {}: {}", profileId, error.getMessage(), error);
    }
}
