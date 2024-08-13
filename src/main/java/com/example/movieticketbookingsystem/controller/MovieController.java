package com.example.movieticketbookingsystem.controller;

import com.example.movieticketbookingsystem.dto.MovieDto;
import com.example.movieticketbookingsystem.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/add")
    public MovieDto addNewMovie(@RequestBody MovieDto movieDto){
        return movieService.addMovie(movieDto);
    }

    @GetMapping
    public List<MovieDto> getAllMovies(){
        return movieService.findAllMovies();
    }

    @GetMapping("/find/{name}")
    public List<MovieDto> getMoviesByName(@PathVariable String name){
        return movieService.findMovieByName(name);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMovieById(@PathVariable Long id){
        movieService.deleteMovieById(id);
    }


}
