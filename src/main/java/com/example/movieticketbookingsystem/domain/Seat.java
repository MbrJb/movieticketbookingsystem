package com.example.movieticketbookingsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "seats")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    private boolean booked;

    @OneToOne(mappedBy = "seat", cascade = CascadeType.ALL, orphanRemoval = true)
    private Booking booking;


}
