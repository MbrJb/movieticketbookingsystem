package com.example.movieticketbookingsystem.controller;

import com.example.movieticketbookingsystem.dto.MovieDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addNewMovie() throws Exception{

        MovieDto movie = new MovieDto();
        movie.setDtoMovieId(1L);
        movie.setDtoMovieTitle("Titanic");
        movie.setDtoMovieDirector("Jay");
        movie.setDtoMovieReleaseDate(LocalDate.now());
        movie.setDtoMoviePrice(BigDecimal.valueOf(20));

        String movieJson = objectMapper.writeValueAsString(movie);

        mockMvc.perform(post("/movies/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(movieJson))
                .andExpect(status().isOk())
                .andExpect(content().json(movieJson));
    }
}