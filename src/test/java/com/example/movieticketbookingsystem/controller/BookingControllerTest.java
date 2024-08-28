package com.example.movieticketbookingsystem.controller;

import com.example.movieticketbookingsystem.domain.Movie;
import com.example.movieticketbookingsystem.domain.Seat;
import com.example.movieticketbookingsystem.domain.User;
import com.example.movieticketbookingsystem.dto.BookingDto;
import com.example.movieticketbookingsystem.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BookingService bookingService;


    @Autowired
    private ObjectMapper objectMapper;



    @Test
    public void BookingController_CreateNewBooking_ReturnBookingDto() throws Exception {


        User user = new User();
        user.setName("Jay");
        user.setId(1L);

        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Titanic");
        movie.setReleaseDate(LocalDate.now());
        movie.setPrice(BigDecimal.valueOf(15));

        Seat seat = new Seat();
        seat.setId(1L);
        seat.setPrice(BigDecimal.valueOf(5));

        BookingDto booking = new BookingDto();
        booking.setDtoBookingDate(LocalDateTime.now());
        booking.setDtoBookingId(4L);
        booking.setDtoMovie(movie);
        booking.setDtoSeat(seat);
        booking.setDtoUser(user);
        booking.setDtoTotalPrice(booking.getDtoTotalPrice());


        String bookingJson = objectMapper.writeValueAsString(booking);

        mockMvc.perform(post("/bookings/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookingJson)
                        .param("movieId",movie.getId().toString())
                        .param("seatId", seat.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().json(bookingJson));

    }

}
