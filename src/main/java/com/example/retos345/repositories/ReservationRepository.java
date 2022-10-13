package com.example.retos345.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.retos345.entities.Reservation;

public interface ReservationRepository  extends JpaRepository<Reservation, Integer>{
    public List<Reservation> findByStartDateBetween(String start, String end);
    public List<Reservation> findByStatus(String status);
}
