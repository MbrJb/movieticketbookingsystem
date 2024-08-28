package com.example.movieticketbookingsystem.service;

import com.example.movieticketbookingsystem.converter.BookingConverter;
import com.example.movieticketbookingsystem.domain.Booking;
import com.example.movieticketbookingsystem.domain.Seat;
import com.example.movieticketbookingsystem.dto.BookingDto;
import com.example.movieticketbookingsystem.exception.BookingNotFoundException;
import com.example.movieticketbookingsystem.exception.SeatBookedException;
import com.example.movieticketbookingsystem.repository.BookingRepository;
import com.example.movieticketbookingsystem.repository.MovieRepository;
import com.example.movieticketbookingsystem.repository.SeatRepository;
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

//    @Transactional
//    public BigDecimal calculateTotalPrice(){
//
//        Movie movie = new Movie();
//        Optional<Movie> movieOptional = Optional.of(movie);
//
//        boolean movieExists = movieRepository.existsById(movieOptional.get().getId());
//
//        if(!movieExists){
//            throw new MovieNotFoundException("Movie not found");
//        }
//
//        Seat seat = new Seat();
//        Optional<Seat> seatOptional = Optional.of(seat);
//
//        boolean seatExists = seatRepository.existsById(seatOptional.get().getId());
//
//        if(!seatExists){
//            throw new SeatNotFoundException("Seat not found");
//        }
//
//        BigDecimal moviePrice = movieOptional.get().getPrice();
//        BigDecimal seatPrice = seatOptional.get().getPrice();
//
//        return moviePrice.add(seatPrice);
//    }


    @Transactional
    public boolean seatBooked(Long id){
//        return seatRepository.findById(id)
//                .map(seat -> !seat.isBooked())
//                .orElse(false);

        Seat seat = new Seat();
        Optional<Seat> seatOptional = Optional.of(seat);

        boolean exists = seatRepository.existsById(id);
        if(exists){
           return seatOptional.get().isBooked();
        }
        return false;
    }

    @Transactional
    public BookingDto addNewBooking(BookingDto bookingDto, Long seatId){

            if(seatBooked(seatId)){
                throw new SeatBookedException("Please pick a different seat");
            }

            BigDecimal totalPrice = bookingDto.getDtoMovie().getPrice()
                                    .add(bookingDto.getDtoSeat().getPrice());

            Booking booking = BookingConverter.convertBookingDtoToEntity(bookingDto);
            booking.setBookingDate(booking.getBookingDate());
            booking.setTotalPrice(totalPrice);
            booking.setMovie(booking.getMovie());
            booking.setSeat(booking.getSeat());
            booking.setUser(booking.getUser());
            bookingRepository.save(booking);

            return BookingConverter.convertBookingEntityToDto(booking);
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
