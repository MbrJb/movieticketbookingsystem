package com.example.movieticketbookingsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "movies")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String director;
    private LocalDate releaseDate;
    private BigDecimal price;

    @OneToOne(mappedBy = "movie")
    private Booking booking;

}
