package com.example.movieticketbookingsystem.converter;

import com.example.movieticketbookingsystem.domain.Booking;
import com.example.movieticketbookingsystem.dto.BookingDto;

public class BookingConverter {

    private BookingConverter(){}

    public static BookingDto convertBookingEntityToDto(Booking booking){

        BookingDto bookingDto = new BookingDto();
        bookingDto.setDtoBookingId(booking.getId());
        bookingDto.setDtoBookingDate(booking.getBookingDate());
        bookingDto.setDtoTotalPrice(booking.getTotalPrice());
        bookingDto.setDtoMovie(booking.getMovie());
        bookingDto.setDtoSeat(booking.getSeat());
        bookingDto.setDtoUser(booking.getUser());

        return bookingDto;
    }

    public static Booking convertBookingDtoToEntity(BookingDto bookingDto){

        Booking booking = new Booking();
        booking.setId(bookingDto.getDtoBookingId());
        booking.setBookingDate(bookingDto.getDtoBookingDate());
        booking.setTotalPrice(bookingDto.getDtoTotalPrice());
        booking.setMovie(bookingDto.getDtoMovie());
        booking.setSeat(bookingDto.getDtoSeat());
        booking.setUser(bookingDto.getDtoUser());

        return booking;
    }
}
