package com.example.reservations.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation {

    @Id
    @Column
    @GeneratedValue
    private Integer reservationId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Landlord landLord;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Tenant tenant;

    @JoinColumn(name="reservationObjectId", referencedColumnName = "reservationObjectId")
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private ReservationObject reservationObject;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date reservationPeriodStart;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date reservationPeriodEnd;

    public Reservation(Landlord landlord, Tenant tenant, ReservationObject reservationObject, Date reservationPeriodStart, Date reservationPeriodEnd) {
        this.landLord = landlord;
        this.tenant = tenant;
        this.reservationObject = reservationObject;
        this.reservationPeriodStart = reservationPeriodStart;
        this.reservationPeriodEnd = reservationPeriodEnd;
    }

    public Reservation(Tenant tenant) {
        this.tenant = tenant;
    }

    public Reservation(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Reservation(Integer reservationId, ReservationObject reservationObject) {
        this.reservationId = reservationId;
    }
}
