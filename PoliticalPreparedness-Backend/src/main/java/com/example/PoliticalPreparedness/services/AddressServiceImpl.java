package com.example.PoliticalPreparedness.services;

import com.example.PoliticalPreparedness.exceptions.AddressNotFoundException;
import com.example.PoliticalPreparedness.models.Address;
import com.example.PoliticalPreparedness.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address getUserAddress(int userId) {
        return addressRepository.findAddressByUserId(userId)
                .orElseThrow(
                        () -> new AddressNotFoundException("Address with user id " + userId + " not found.")
                );
    }
}
