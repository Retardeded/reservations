package com.example.reservations.service;

import com.example.reservations.model.Reservation;
import com.example.reservations.model.ReservationObject;
import com.example.reservations.repository.LandlordRepository;
import com.example.reservations.repository.ReservationObjectRepository;
import com.example.reservations.repository.ReservationRepository;
import com.example.reservations.repository.TenantRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReservationsServiceTest {

    @Mock
    private TenantRepository tenantRepository;
    @Mock
    private LandlordRepository landlordRepository;
    @Mock
    private ReservationObjectRepository reservationObjectRepository;
    @Mock
    private ReservationRepository reservationRepository;

    @Test
    void getReservations() {
    }

    @Test
    void getReservationById() {
        ReservationsService reservationsService = new ReservationsService(tenantRepository, landlordRepository, reservationRepository, reservationObjectRepository);

        Reservation reservation = new Reservation(123);
        Mockito.when(reservationRepository.findById(123)).thenReturn(Optional.of(reservation));

        Optional<Reservation> optionalReservation = reservationsService.getReservation(123);
        Assertions.assertThat(optionalReservation.get().getReservationId()).isEqualTo(reservation.getReservationId());
        Assertions.assertThat(optionalReservation.get().getReservationId()).isEqualTo(123);
    }

    @Test
    void addReservation() {
        ReservationsService reservationsService = new ReservationsService(tenantRepository, landlordRepository, reservationRepository, reservationObjectRepository);

        ReservationObject reservationObject = new ReservationObject("obj1");
        Reservation reservation = new Reservation(123, reservationObject);
        //Mockito.when(reservationRepository.findById(123)).thenReturn(Optional.of(reservation));
        //Mockito.when(reservationObject.getName()).thenReturn("obj1");


        Reservation optionalReservation = reservationsService.addReservation(reservation);

        Mockito.verify(reservationRepository, Mockito.times(1)).save(ArgumentMatchers.any(Reservation.class));


        //List<Reservation> reservations = reservationsService.getReservations();
        //Assertions.assertThat(reservations.get(0).getReservationId()).isEqualTo(reservation.getReservationId());
        //Assertions.assertThat(reservations.get(0).getReservationId()).isEqualTo(123);
    }
}