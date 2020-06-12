package com.rekadanilaci.accenture.repository;

import com.rekadanilaci.accenture.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
