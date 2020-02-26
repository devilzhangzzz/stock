package com.zzz.stock.repo;

import com.zzz.stock.entity.DayTrend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DayTrendRepo extends JpaRepository<DayTrend,Integer> {



    @Query(value = "SELECT * FROM t_day_trend where stock_id=? order by time", nativeQuery = true)
    List<DayTrend> findDayTrendsByStockId(Integer stockId);

    @Query(value = "SELECT * FROM t_day_trend where time BETWEEN ? and ? time", nativeQuery = true)
    List<DayTrend> findDayTrendsBetweenTimes(long startTime, long endTime);

}
