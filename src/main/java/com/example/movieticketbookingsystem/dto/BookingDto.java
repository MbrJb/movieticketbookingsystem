package com.example.movieticketbookingsystem.dto;

import com.example.movieticketbookingsystem.domain.Seat;
import com.example.movieticketbookingsystem.domain.Movie;
import com.example.movieticketbookingsystem.domain.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingDto {

    private Long dtoBookingId;
    private LocalDateTime dtoBookingDate;
    private Movie dtoMovie;
    private Seat dtoSeat;
    private User dtoUser;
}
