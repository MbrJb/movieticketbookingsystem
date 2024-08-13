package com.example.movieticketbookingsystem.service;

import com.example.movieticketbookingsystem.converter.SeatConverter;
import com.example.movieticketbookingsystem.domain.Seat;
import com.example.movieticketbookingsystem.dto.SeatDto;
import com.example.movieticketbookingsystem.exception.SeatNotFoundException;
import com.example.movieticketbookingsystem.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public SeatDto addNewSeat(SeatDto seatDto){
        Seat seat = SeatConverter.convertSeatDtoToEntity(seatDto);
        seat.setPrice(seat.getPrice());
        seatRepository.save(seat);

        return SeatConverter.convertSeatEntityToDto(seat);
    }

    public List<SeatDto> findAllSeats(){
        return seatRepository.findAll()
                .stream()
                .map(SeatConverter::convertSeatEntityToDto)
                .collect(Collectors.toList());

    }

    public void deleteSeatById(Long id){
        boolean exists = seatRepository.existsById(id);

        if(exists){
            seatRepository.deleteById(id);
        }
        else {
            throw new SeatNotFoundException("There is no seat with id " + id + ".");
        }
    }
}
