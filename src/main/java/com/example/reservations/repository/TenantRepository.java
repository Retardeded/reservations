package com.example.reservations.repository;

import com.example.reservations.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer> {
    Optional<Tenant> findById(Integer id);
    Optional<Tenant> findByName(String name);
}