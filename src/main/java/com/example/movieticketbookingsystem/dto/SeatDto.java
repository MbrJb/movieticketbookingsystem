package com.example.movieticketbookingsystem.dto;

import com.example.movieticketbookingsystem.domain.Booking;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SeatDto {

    private Long dtoId;
    private BigDecimal price;
    private boolean booked;
    private List<Booking> bookings;

}
