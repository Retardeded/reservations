package com.example.reservations.controller;

import com.example.reservations.model.Reservation;
import com.example.reservations.model.ReservationObject;
import com.example.reservations.model.Tenant;
import com.example.reservations.repository.ReservationRepository;
import com.example.reservations.service.ReservationsService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(controllers = ReservationsController.class)
class ReservationsControllerTest {

    @MockBean
    private ReservationsService reservationsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllObjectReservations() throws Exception {

        ReservationObject reservationObject1 = new ReservationObject("obj1");
        ReservationObject reservationObject2 = new ReservationObject("obj2");

        Mockito.when(reservationsService.getReservationObjects()).thenReturn(Arrays.asList(reservationObject1, reservationObject2));


        mockMvc.perform(MockMvcRequestBuilders.get("/reservationObjects/"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("obj1")));
    }
}