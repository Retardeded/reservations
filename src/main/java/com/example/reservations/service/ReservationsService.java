package com.example.reservations.service;

import com.example.reservations.model.ReservationObject;
import com.example.reservations.model.Tenant;
import com.example.reservations.model.Reservation;
import com.example.reservations.repository.LandlordRepository;
import com.example.reservations.repository.ReservationObjectRepository;
import com.example.reservations.repository.TenantRepository;
import com.example.reservations.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationsService {

    private TenantRepository tenantRepository;

    private LandlordRepository landlordRepository;
    private ReservationObjectRepository reservationObjectRepository;
    private ReservationRepository reservationRepository;

    public ReservationsService(TenantRepository tenantRepository, LandlordRepository landlordRepository, ReservationRepository reservationRepository, ReservationObjectRepository reservationObjectRepository) {
        this.tenantRepository = tenantRepository;
        this.reservationRepository = reservationRepository;
        this.reservationObjectRepository = reservationObjectRepository;
        this.landlordRepository = landlordRepository;
    }

    public Optional<Tenant> getClient(String name) {
        Optional<Tenant> data = tenantRepository.findByName(name);
        return data;
    }

    public Tenant saveClient(Tenant tenant) {
        System.out.println(tenant);
        tenantRepository.save(tenant);
        return tenant;
    }

    public Optional<Reservation> getReservation(Integer id) {
        Optional<Reservation> data  = reservationRepository.findById(id);
        return data;
    }

    public List<Reservation> getReservations() {
        List<Reservation> data  = reservationRepository.findAll();
        return data;
    }

    public List<ReservationObject> getReservationObjects() {
        List<ReservationObject> data  = reservationObjectRepository.findAll();
        return data;
    }

    public Reservation addReservation(Reservation reservation) {
        var reservationObject = reservation.getReservationObject();
        var tenant = reservation.getTenant();
        var landLord = reservation.getLandLord();

        var possibleObject = reservationObjectRepository.findByName(reservationObject.getName());
        if(possibleObject.isEmpty()) {
            reservationObjectRepository.save(reservationObject);
            possibleObject = Optional.of(reservationObject);
        } else {
            var object = possibleObject.get();
            var newStartDate = reservation.getReservationPeriodStart();
            var newEndDate = reservation.getReservationPeriodEnd();
            var reservationList = object.getReservations();

            for (Reservation objReservation:reservationList
                 ) {
                var oldStartDate = objReservation.getReservationPeriodStart();
                var oldEndDate = objReservation.getReservationPeriodEnd();

                if( (oldStartDate.before(newEndDate) && (oldEndDate.after(newStartDate)  )) ) {
                    return null;
                }
            }
        }

        var possibleLandlord = landlordRepository.findByName(landLord.getName());
        if(possibleLandlord.isEmpty()) {
            landlordRepository.save(landLord);
            possibleLandlord = Optional.of(landLord);
        }
        var possibleTenant = tenantRepository.findByName(tenant.getName());
        if(possibleTenant.isEmpty()) {
            tenantRepository.save(tenant);
            possibleTenant = Optional.of(tenant);
        }

        var newReservation = new Reservation(possibleLandlord.get(), possibleTenant.get(), possibleObject.get(),
                reservation.getReservationPeriodStart(), reservation.getReservationPeriodEnd());

        reservationRepository.saveAndFlush(newReservation);

        return newReservation;
    }

    public Reservation updateReservation(Reservation reservation) {

        var possibleReservation = reservationRepository.findById(reservation.getReservationId());
        if(possibleReservation.isPresent()) {
            var updatedReservation = possibleReservation.get();
            updatedReservation.setReservationPeriodStart(reservation.getReservationPeriodStart());
            updatedReservation.setReservationPeriodEnd(reservation.getReservationPeriodEnd());
            reservationRepository.save(updatedReservation);
            return updatedReservation;
        }

        return null;
    }

    public List<Reservation> getAllTenantReservations(String tenantName) {
        var allReservations = reservationRepository.findReservationByTenant_name(tenantName);
        return allReservations;
    }

    public List<Reservation> getAllObjectReservations(String objectName) {
        var allReservations = reservationRepository.findReservationByReservationObject_name(objectName);
        return allReservations;
    }
}
