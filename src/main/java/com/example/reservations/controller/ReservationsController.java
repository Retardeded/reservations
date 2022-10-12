package com.example.reservations.controller;


import com.example.reservations.model.ReservationObject;
import com.example.reservations.model.Tenant;
import com.example.reservations.model.Reservation;
import com.example.reservations.service.ReservationsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "")
public class ReservationsController {

    private final ReservationsService reservationsService;

    public ReservationsController(ReservationsService reservationsService) {
        this.reservationsService = reservationsService;
    }

    @GetMapping("/clients/{client}")
    public Tenant getClient(@PathVariable String client) {
        var clientData = reservationsService.getClient(client);
        return clientData.orElse(null);
    }

    @PostMapping("/clients")
    public Tenant postClient(@RequestBody Tenant tenant) {
        System.out.println(tenant);
        reservationsService.saveClient(tenant);
        return tenant;
    }

    @GetMapping("/reservations/{landLord}")
    public Reservation getReservation(@PathVariable Integer landLordId) {
        var reservationData = reservationsService.getReservation(landLordId);
        return reservationData.orElse(null);
    }

    @GetMapping("/reservations")
    public List<Reservation> getReservations() {
        var reservationData = reservationsService.getReservations();
        return reservationData;
    }

    @GetMapping("/reservationObjects")
    public List<ReservationObject> getReservationObjects() {
        var reservationData = reservationsService.getReservationObjects();
        return reservationData;
    }

    @GetMapping("/reservationsForTenant/{tenantName}")
    public List<Reservation> getAllTenantReservations(@PathVariable String tenantName) {
        var reservationData = reservationsService.getAllTenantReservations(tenantName);
        return reservationData;
    }

    @GetMapping("/reservationsForObject/{objectName}")
    public List<Reservation> getAllObjectReservations(@PathVariable String objectName) {
        var reservationData = reservationsService.getAllObjectReservations(objectName);
        return reservationData;
    }


    @PostMapping("/reservationsAdd")
    public Reservation addNewReservation(@RequestBody Reservation reservation) {
        return reservationsService.addReservation(reservation);
    }

    @PostMapping("/reservationsUpdate")
    public Reservation updateReservation(@RequestBody Reservation reservation) {
        return reservationsService.updateReservation(reservation);
    }

}
