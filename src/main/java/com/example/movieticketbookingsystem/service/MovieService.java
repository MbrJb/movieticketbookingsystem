package com.example.movieticketbookingsystem.service;

import com.example.movieticketbookingsystem.converter.MovieConverter;
import com.example.movieticketbookingsystem.domain.Movie;
import com.example.movieticketbookingsystem.dto.MovieDto;
import com.example.movieticketbookingsystem.exception.MovieNotFoundException;
import com.example.movieticketbookingsystem.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieDto addMovie(MovieDto movieDto){

        Movie movie = MovieConverter.convertMovieDtoToEntity(movieDto);
        movie.setTitle(movie.getTitle());
        movie.setDirector(movie.getDirector());
        movie.setReleaseDate(movie.getReleaseDate());
        movie.setPrice(movie.getPrice());

        movieRepository.save(movie);

        return MovieConverter.convertMovieEntityToDto(movie);
    }

    public List<MovieDto> findAllMovies(){
        return movieRepository.findAll()
                .stream()
                .map(MovieConverter::convertMovieEntityToDto)
                .collect(Collectors.toList());
    }

    public List<MovieDto> findMovieByName(String name){
        Optional<Movie> movie = movieRepository.findByMovieName(name);

        if(movie.isPresent()){

            return movie.stream().map(MovieConverter::convertMovieEntityToDto)
                    .collect(Collectors.toList());
        }
        else {
            throw new MovieNotFoundException("There are no movies with that name");
        }

    }

    @Transactional
    public void deleteMovieById(Long id){

        boolean exists = movieRepository.findById(id).isPresent();

        if(exists){
            movieRepository.deleteById(id);
        }
        else {
            throw new MovieNotFoundException("There is no movie with id " + id +".");
        }
    }
}
