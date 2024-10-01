package com.example.PoliticalPreparedness.controllers;

import com.example.PoliticalPreparedness.models.Official;
import com.example.PoliticalPreparedness.services.CivicsInfoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@CrossOrigin
@RestController
public class CivicInfoController {

    private final CivicsInfoService civicsInfoService;

    public CivicInfoController(CivicsInfoService civicsInfoService) {
        this.civicsInfoService = civicsInfoService;
    }

    @GetMapping("/api/representatives")
    public Mono<List<Official>> getRepresentativesInfo(@RequestParam String address, @RequestParam String email) {
        return civicsInfoService.getRepresentatives(address, email);
    }

}
