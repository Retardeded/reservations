package com.example.reservations.repository;

import com.example.reservations.model.Landlord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LandlordRepository extends JpaRepository<Landlord, Integer> {
    Optional<Landlord> findById(Integer id);
    Optional<Landlord> findByName(String name);
}