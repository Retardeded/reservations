package com.example.reservations.repository;

import com.example.reservations.model.ReservationObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationObjectRepository extends JpaRepository<ReservationObject, Integer> {
    Optional<ReservationObject> findById(Integer id);
    Optional<ReservationObject> findByName(String name);
}
