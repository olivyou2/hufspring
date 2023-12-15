package com.olivyou2.hufsattend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRequestDto {
    @NotNull
    public String lssn_cd;

    @NotNull
    public String period;

    @NotNull
    public String reservation_start;

    @NotNull
    public String year;

    @NotNull
    public String semester;
}
