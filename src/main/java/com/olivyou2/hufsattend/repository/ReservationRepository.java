package com.olivyou2.hufsattend.repository;

import com.olivyou2.hufsattend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    public List<Reservation> findReservationsByAccountIs(String account);
}
