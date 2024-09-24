package com.example.PoliticalPreparedness.repositories;

import com.example.PoliticalPreparedness.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

    Optional<Address> findAddressByUserId(int userId);
}
