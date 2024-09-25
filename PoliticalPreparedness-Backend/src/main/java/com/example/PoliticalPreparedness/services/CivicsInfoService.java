package com.example.PoliticalPreparedness.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CivicsInfoService {

    @Value("${google.api.key}") // Injecting the Google API key from application properties file
    private String googleApiKey;

    private final WebClient webClient;

    public CivicsInfoService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://www.googleapis.com/civicinfo/v2").build();
    }

    // Mono is like a Promise that will eventually get us what we want. Allows for async operation.
    public Mono<String> getRepresentatives(String address) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/representatives")
                        .queryParam("key", googleApiKey)
                        .queryParam("address", address)
                        .build())
                .retrieve()
                .bodyToMono(String.class); // Whatever we get from the response, convert it to an instance of the specific class
    }
}
