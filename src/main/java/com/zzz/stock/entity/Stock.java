package com.zzz.stock.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="t_stock")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id
    private int id;

    @Column
    private String stockNo;

    @Column
    private String stockShortNo;

    @Column
    private String stockName;

    @Column
    private String board;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStockNo() {
        return stockNo;
    }

    public void setStockNo(String stockNo) {
        this.stockNo = stockNo;
    }

    public String getStockShortNo() {
        return stockShortNo;
    }

    public void setStockShortNo(String stockShortNo) {
        this.stockShortNo = stockShortNo;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }
}
