package com.example.movieticketbookingsystem.service;

import com.example.movieticketbookingsystem.converter.BookingConverter;
import com.example.movieticketbookingsystem.domain.Booking;
import com.example.movieticketbookingsystem.domain.Movie;
import com.example.movieticketbookingsystem.domain.Seat;
import com.example.movieticketbookingsystem.dto.BookingDto;
import com.example.movieticketbookingsystem.exception.BookingNotFoundException;
import com.example.movieticketbookingsystem.exception.MovieNotFoundException;
import com.example.movieticketbookingsystem.exception.SeatBookedException;
import com.example.movieticketbookingsystem.exception.SeatNotFoundException;
import com.example.movieticketbookingsystem.repository.BookingRepository;
import com.example.movieticketbookingsystem.repository.MovieRepository;
import com.example.movieticketbookingsystem.repository.SeatRepository;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, SeatRepository seatRepository, MovieRepository movieRepository) {
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
        this.movieRepository = movieRepository;
    }

    @Transactional
    public BigDecimal calculateTotalPrice(BookingDto bookingDto){

        Optional<Movie> movieOptional = movieRepository.findById(bookingDto.getDtoMovie().getId());
        if(movieOptional.isEmpty()){
            throw new MovieNotFoundException("Movie not found");
        }

        Optional<Seat> seatOptional = seatRepository.findById(bookingDto.getDtoSeat().getId());
        if (seatOptional.isEmpty()){
            throw new SeatNotFoundException("Seat not found");
        }

        Movie movie = movieOptional.get();
        BigDecimal moviePrice = movie.getPrice();

        Seat seat = seatOptional.get();
        BigDecimal seatPrice = seat.getPrice();

        return moviePrice.add(seatPrice);
    }


    public boolean seatBooked(Long id){
        return seatRepository.existsById(id);
    }

    @Transactional
    public BookingDto addNewBooking(BookingDto bookingDto, Long seatId){
        try {

            calculateTotalPrice(bookingDto);

            if(seatBooked(seatId)){
                throw new SeatBookedException("Please pick a different seat");
            }

            Booking booking = BookingConverter.convertBookingDtoToEntity(bookingDto);
            booking.setBookingDate(booking.getBookingDate());
            booking.setMovie(booking.getMovie());
            booking.setSeat(booking.getSeat());
            booking.setUser(booking.getUser());
            bookingRepository.save(booking);

            return BookingConverter.convertBookingEntityToDto(booking);
        } catch (OptimisticLockException e){

            throw new SeatBookedException("The seat was booked by someone else. Please pick a different seat.");
        }
    }

    public List<BookingDto> findAllBookings(){
        return bookingRepository.findAll()
                .stream().map(BookingConverter::convertBookingEntityToDto)
                .collect(Collectors.toList());
    }

    public BookingDto findBookingById(Long id){
        Optional<Booking> booking = bookingRepository.findById(id);

        if(booking.isPresent()){
            return BookingConverter.convertBookingEntityToDto(booking.get());
        }
        else {
            throw new BookingNotFoundException("There is no booking with id " + id + ".");
        }
    }

    @Transactional
    public BookingDto updateBooking(BookingDto bookingDto){

        Long id = bookingDto.getDtoBookingId();

        if(bookingRepository.existsById(id)){
            Booking booking = BookingConverter.convertBookingDtoToEntity(bookingDto);
            booking.setBookingDate(booking.getBookingDate());
            booking.setMovie(booking.getMovie());
            bookingRepository.save(booking);

            return BookingConverter.convertBookingEntityToDto(booking);
        }
        else {
            throw new BookingNotFoundException("There is no booking with id " + id + ".");
        }
    }

    @Transactional
    public void deleteBookingById(Long id){
        boolean exists = bookingRepository.findById(id).isPresent();

        if(exists){
            bookingRepository.deleteById(id);
        }
        else {
            throw new BookingNotFoundException("There is no booking with id " + id + ".");

        }
    }


}
