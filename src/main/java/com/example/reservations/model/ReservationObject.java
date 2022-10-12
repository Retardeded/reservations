package com.example.reservations.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationObject {

    @Id
    @Column
    @GeneratedValue
    private Integer reservationObjectId;

    @Column(unique = true)
    private String name;

    @Column
    private Integer price;

    @Column
    private Integer area;

    @Column
    private String description;

    //@JsonManagedReference
    //@OneToMany(targetEntity=Reservation.class, mappedBy="reservationId",cascade=CascadeType.ALL)
    @OneToMany(mappedBy = "reservationObject", fetch = FetchType.EAGER)
    private List<Reservation> reservations = new ArrayList<>();

    public ReservationObject(String name) {
        this.name = name;
    }

}