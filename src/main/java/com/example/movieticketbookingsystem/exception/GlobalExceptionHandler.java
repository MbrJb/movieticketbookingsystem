package com.example.movieticketbookingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BookingNotFoundException.class, MovieNotFoundException.class,
            SeatNotFoundException.class, SeatBookedException.class, UserNotFoundException.class})
    public ProblemDetail handleNotFoundException(RuntimeException e){

        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());

    }


}
