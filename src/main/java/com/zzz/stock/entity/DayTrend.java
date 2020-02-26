package com.zzz.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "t_day_trend")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DayTrend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer stockId;


    @JoinColumn(name = "stockId", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Stock stock;


    @Column
    private Long volume;//成交量

    @Column
    private Double open;//开盘价

    @Column
    private Double high;//最高

    @Column
    private Double close;//收盘价

    @Column
    private Double low;//最低

    @Column
    private Double chg;//涨跌额

    @Column
    private Double percent;//涨跌幅

    @Column
    private Double turnoverrate;//换手率

    @Column
    private Double ma5;//5日线

    @Column
    private Double ma10;//10日线

    @Column
    private Double ma20;//20日线

    @Column
    private Double ma30;//30日线

    @Column
    private Double dif;//DIF

    @Column
    private Double dea;//DEA

    @Column
    private Double macd;//MACD

    @Column
    private Long time;//时间节点

    @Column
    @org.hibernate.annotations.Type(type="yes_no")
    private Boolean isHammer; //属于锤子线

    @Column
    private Double kdjk;//kdjk

    @Column
    private Double kdjd;//kdjd

    @Column
    private Double kdjj;//kdjj


}
