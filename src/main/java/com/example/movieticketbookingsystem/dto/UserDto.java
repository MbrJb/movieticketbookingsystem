package com.example.movieticketbookingsystem.dto;

import com.example.movieticketbookingsystem.domain.Booking;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long dtoId;
    private String dtoName;
    private List<Booking> dtoBookings;
}
