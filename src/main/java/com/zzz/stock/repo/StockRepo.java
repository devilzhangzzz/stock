package com.zzz.stock.repo;

import com.zzz.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepo extends JpaRepository<Stock,Integer> {

@Query(value = "SELECT * FROM " +
        "t_stock s " +
        "WHERE NOT exists ( " +
        "SELECT 1 FROM " +
        "t_day_trend d " +
        "WHERE " +
        "d.stock_id = s.id " +
        ")", nativeQuery = true)
    List<Stock> findUnInsertStocks();

        }
