package com.example.reservations.repository;

import com.example.reservations.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findById(Integer id);
    List<Reservation> findReservationByTenant_name(String name);
    List<Reservation> findReservationByReservationObject_name(String name);
}
