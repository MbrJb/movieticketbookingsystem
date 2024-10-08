package com.example.movieticketbookingsystem.dto;

import com.example.movieticketbookingsystem.domain.Movie;
import com.example.movieticketbookingsystem.domain.Seat;
import com.example.movieticketbookingsystem.domain.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDto {

    private Long dtoBookingId;
    private LocalDateTime dtoBookingDate;
    private BigDecimal dtoTotalPrice;
    private Movie dtoMovie;
    private Seat dtoSeat;
    private User dtoUser;
}
