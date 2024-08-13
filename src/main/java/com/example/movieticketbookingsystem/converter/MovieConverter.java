package com.example.movieticketbookingsystem.converter;

import com.example.movieticketbookingsystem.domain.Movie;
import com.example.movieticketbookingsystem.dto.MovieDto;

public class MovieConverter {

    private MovieConverter(){}

    public static MovieDto convertMovieEntityToDto(Movie movie){

        MovieDto movieDto = new MovieDto();
        movieDto.setDtoMovieId(movie.getId());
        movieDto.setDtoMovieTitle(movie.getTitle());
        movieDto.setDtoMovieDirector(movie.getDirector());
        movieDto.setDtoMovieReleaseDate(movie.getReleaseDate());
        movieDto.setDtoMoviePrice(movie.getPrice());
        movieDto.setDtoBooking(movie.getBooking());

        return movieDto;
    }

    public static Movie convertMovieDtoToEntity(MovieDto movieDto){

        Movie movie = new Movie();
        movie.setId(movieDto.getDtoMovieId());
        movie.setTitle(movieDto.getDtoMovieTitle());
        movie.setDirector(movieDto.getDtoMovieDirector());
        movie.setReleaseDate(movieDto.getDtoMovieReleaseDate());
        movie.setPrice(movieDto.getDtoMoviePrice());
        movie.setBooking(movieDto.getDtoBooking());

        return movie;
    }
}
