package com.olivyou2.hufsattend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Reservation {
    @Id
    public long id;

    public String account;

    public String lssn_cd;

    public String period;

    public String reservation_start;
    public String year;
    public String semester;
    public String containerId;
}
