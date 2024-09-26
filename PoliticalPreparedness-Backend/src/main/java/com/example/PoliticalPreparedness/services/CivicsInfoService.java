package com.example.PoliticalPreparedness.services;

import com.example.PoliticalPreparedness.exceptions.UserNotFoundException;
import com.example.PoliticalPreparedness.models.Official;
import com.example.PoliticalPreparedness.models.User;
import com.example.PoliticalPreparedness.models.dtos.CivicApiResponse;
import com.example.PoliticalPreparedness.models.dtos.OfficialDto;
import com.example.PoliticalPreparedness.repositories.OfficialRepository;
import com.example.PoliticalPreparedness.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CivicsInfoService {

    @Value("${google.api.key}") // Injecting the Google API key from application properties file
    private String googleApiKey;

    private final WebClient webClient;
    private final UserRepository userRepository;
    private final OfficialRepository officialRepository;

    public CivicsInfoService(WebClient.Builder webClientBuilder, UserRepository userRepository, OfficialRepository officialRepository) {
        this.webClient = webClientBuilder.baseUrl("https://www.googleapis.com/civicinfo/v2").build();
        this.userRepository = userRepository;
        this.officialRepository = officialRepository;
    }

    // Mono is like a Promise that will eventually get us what we want. Allows for async operation.
    public Mono<List<Official>> getRepresentatives(String address, String email) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/representatives")
                        .queryParam("key", googleApiKey)
                        .queryParam("address", address)
                        .build())
                .retrieve()
                .bodyToMono(CivicApiResponse.class) // Whatever we get from the response, convert it to an instance of the specific class
                .doOnNext(response -> {
                    System.out.println("API Response: " + response); // Log the response
                })
                .flatMap(response -> {
                    // Check if officials is not null and not empty
                    if (response.getOfficials() != null && !response.getOfficials().isEmpty()) {
                        return saveOfficialsForUser(response.getOfficials(), email);
                    } else {
                        // Log or handle the case where officials is empty or null
                        return Mono.empty();
                    }
                });
    }

    private Mono<List<Official>> saveOfficialsForUser(List<OfficialDto> officialDtos, String email) {
        return Mono.fromCallable(() -> {
            // Find the user by username
            User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found."));

            officialRepository.deleteOfficialByUserId(user.getUserId());

            // Map DTO to Entity
            List<Official> officials = officialDtos.stream()
                    .map(dto -> new Official(dto.getName(), dto.getParty(), dto.getPhones().get(0), dto.getUrls().get(0), user))
                    .collect(Collectors.toList());

            // Save the officials in the database
            officialRepository.saveAll(officials);

            return officials;
        });
    }
}
