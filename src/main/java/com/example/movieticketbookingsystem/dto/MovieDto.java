package com.example.movieticketbookingsystem.dto;

import com.example.movieticketbookingsystem.domain.Booking;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MovieDto {

    private Long dtoMovieId;
    private String dtoMovieTitle;
    private String dtoMovieDirector;
    private LocalDate dtoMovieReleaseDate;
    private BigDecimal dtoMoviePrice;
    private Booking dtoBooking;

}
