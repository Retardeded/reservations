package com.example.reservations.repository;

import com.example.reservations.exception.HibernateUtil;
import com.example.reservations.model.Reservation;
import com.example.reservations.model.Tenant;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.hibernate.Session;

import javax.persistence.PersistenceException;

import java.util.List;

import org.hibernate.SessionFactory;



//@ExtendWith(SpringExtension.class)
//@WebMvcTest(ReservationsController.class)
@DataJpaTest
class ReservationsRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TenantRepository tenantRepository;

    @AfterEach
    void tearDown() {
        reservationRepository.deleteAll();
    }

    @Test
    void addNewReservationText() {
        Reservation reservation = new Reservation(new Tenant(1, "tenant1"));
        Tenant tenant = new Tenant(1, "tenant1");
        tenantRepository.saveAndFlush(tenant);
        reservation.setTenant(tenant);
        //
        reservationRepository.save(reservation);
        //
        //var reservations = reservationRepository.findReservationByTenant_name(tenant.getName());
        var reservations = reservationRepository.findAll();

        assertEquals(reservations.get(0).getTenant(), null);

    }

    @Test
    void saveReservationText() {
        Reservation reservation = new Reservation();

        Reservation savedReservation = reservationRepository.save(reservation);
        assertThat(savedReservation).usingRecursiveComparison().ignoringFields("reservationId").isEqualTo(reservation);
    }
}