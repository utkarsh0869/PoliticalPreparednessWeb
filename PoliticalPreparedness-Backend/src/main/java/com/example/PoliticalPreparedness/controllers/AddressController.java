package com.example.PoliticalPreparedness.controllers;

import com.example.PoliticalPreparedness.models.Address;
import com.example.PoliticalPreparedness.services.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressServiceImpl addressService;

    @Autowired
    public AddressController(AddressServiceImpl addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Address> getUserAddress(@PathVariable int userId) {
        return ResponseEntity.ok(addressService.getUserAddress(userId));
    }
}
