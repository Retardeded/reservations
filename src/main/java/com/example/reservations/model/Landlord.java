package com.example.reservations.model;

import lombok.*;

import javax.persistence.*;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Landlord {

    @Id
    @Column
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String name;

    @Override
    public String toString() {
        return "Landlord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
