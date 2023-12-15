package com.olivyou2.hufsattend.controller;

import com.olivyou2.hufsattend.annotation.Auth;
import com.olivyou2.hufsattend.dto.ReservationRequestDto;
import com.olivyou2.hufsattend.exception.AuthorizationNotSuggestedException;
import com.olivyou2.hufsattend.lib.Hufs.HufsClient;
import com.olivyou2.hufsattend.lib.Hufs.HufsLesson;
import com.olivyou2.hufsattend.lib.Request.HufsAccount;
import com.olivyou2.hufsattend.service.ReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class HufsController {

    final private HufsClient hufsClient;

    final private ReservationService reservationService;

    @GetMapping("/lessons")
    @ResponseBody
    @Auth
    public ResponseEntity<?> getLessons() throws AuthorizationNotSuggestedException, IOException {
        HufsAccount account = HufsAccount.fromContext();

        List<HufsLesson> lessonList = hufsClient.getLessons(
            account.getId(),
            account.getPassword()
        );

        return ResponseEntity.ok().body(lessonList);
    }

    @PostMapping("/reservation")
    @ResponseBody
    @Auth
    public ResponseEntity<?> createReservation(@RequestBody @Valid ReservationRequestDto payload) {
        HufsAccount account = HufsAccount.fromContext();

        try{
            reservationService.createReservation(account,
                    payload.lssn_cd, payload.reservation_start, payload.year, payload.semester, payload.period);
        }
        catch(IOException exception){

        }
        return ResponseEntity.ok().body(payload);
    }
}
