package com.example.movieticketbookingsystem.converter;

import com.example.movieticketbookingsystem.domain.Booking;
import com.example.movieticketbookingsystem.domain.Seat;
import com.example.movieticketbookingsystem.dto.SeatDto;

import java.util.List;

public class SeatConverter {

    private SeatConverter(){}

    public static SeatDto convertSeatEntityToDto(Seat seat){

        SeatDto seatDto = new SeatDto();
        seatDto.setDtoId(seat.getId());
        seatDto.setPrice(seat.getPrice());
        seatDto.setBooked(seat.isBooked());
        seatDto.setBookings((List<Booking>) seat.getBooking());

        return seatDto;
    }

    public static Seat convertSeatDtoToEntity(SeatDto seatDto){

        Seat seat = new Seat();
        seat.setId(seatDto.getDtoId());
        seat.setPrice(seatDto.getPrice());
        seat.setBooked(seatDto.isBooked());
        seat.setBooking((Booking) seatDto.getBookings());

        return seat;
    }
}
