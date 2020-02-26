package com.zzz.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "t_hammer_grown")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HammerGrown {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer stockId;

    private Integer hammerDayTrendId;

    private Integer daysDiff;

    private Double relativeGrownRate;
}
