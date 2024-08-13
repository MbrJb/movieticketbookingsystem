package com.example.movieticketbookingsystem.converter;

import com.example.movieticketbookingsystem.domain.Seat;
import com.example.movieticketbookingsystem.dto.SeatDto;

public class SeatConverter {

    private SeatConverter(){}

    public static SeatDto convertSeatEntityToDto(Seat seat){

        SeatDto seatDto = new SeatDto();
        seatDto.setDtoId(seat.getId());
        seatDto.setPrice(seat.getPrice());
        seatDto.setBookings(seat.getBooking());

        return seatDto;
    }

    public static Seat convertSeatDtoToEntity(SeatDto seatDto){

        Seat seat = new Seat();
        seat.setId(seatDto.getDtoId());
        seat.setPrice(seatDto.getPrice());
        seat.setBooking(seatDto.getBookings());

        return seat;
    }
}
