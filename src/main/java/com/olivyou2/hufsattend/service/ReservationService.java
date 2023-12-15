package com.olivyou2.hufsattend.service;

import com.olivyou2.hufsattend.entity.Reservation;
import com.olivyou2.hufsattend.lib.Docker.DockerClient;
import com.olivyou2.hufsattend.lib.Hufs.HufsClient;
import com.olivyou2.hufsattend.lib.Request.HufsAccount;
import com.olivyou2.hufsattend.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Time;


@Service
@AllArgsConstructor
public class ReservationService {
    final ReservationRepository reservationRepository;
    final HufsClient hufsClient;

    public void createReservation(HufsAccount account, String lssn_cd, String start, String year, String semester, String period) throws IOException {

        Reservation reservation = Reservation
                .builder()
                .account(account.getId())
                .lssn_cd(lssn_cd)
                .reservation_start(start)
                .year(year)
                .semester(semester)
                .period(period)
                .build();

        String containerId = hufsClient.createReservation(account, reservation);
        reservation.setContainerId(containerId);

        reservationRepository.save(reservation);
    }
}
