package com.example.movieticketbookingsystem.repository;

import com.example.movieticketbookingsystem.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {}
