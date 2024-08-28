package com.example.movieticketbookingsystem.controller;

import com.example.movieticketbookingsystem.dto.BookingDto;
import com.example.movieticketbookingsystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping("/add")
    public BookingDto createNewBooking(@RequestBody BookingDto bookingDto, @RequestParam Long seatId){
        return bookingService.addNewBooking(bookingDto, seatId);
    }

    @GetMapping
    public List<BookingDto> findAllBookings(){
        return bookingService.findAllBookings();
    }

    @GetMapping("/find/{id}")
    public BookingDto findBookingById(@PathVariable Long id){
        return bookingService.findBookingById(id);
    }

    @PutMapping("/update")
    public BookingDto updateBooking(@RequestBody BookingDto bookingDto){
        return bookingService.updateBooking(bookingDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBooking(@PathVariable Long id){
        bookingService.deleteBookingById(id);
    }

}
