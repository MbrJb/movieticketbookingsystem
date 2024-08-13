package com.example.movieticketbookingsystem.controller;

import com.example.movieticketbookingsystem.dto.SeatDto;
import com.example.movieticketbookingsystem.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping("/add") public SeatDto addNewSeat(@RequestBody SeatDto seatDto){
        return seatService.addNewSeat(seatDto);
    }

    @GetMapping
    public List<SeatDto> findAllSeats(){
        return seatService.findAllSeats();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSeatById(@PathVariable Long id){
        seatService.deleteSeatById(id);
    }
}
