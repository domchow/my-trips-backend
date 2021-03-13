package com.my.trips.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "trip")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String City;
    private LocalDate date;
    private String description;
}
