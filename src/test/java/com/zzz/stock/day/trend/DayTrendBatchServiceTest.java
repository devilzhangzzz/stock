package com.zzz.stock.day.trend;

import com.zzz.stock.entity.DayTrend;
import com.zzz.stock.repo.DayTrendRepo;
import com.zzz.stock.service.DayTrendBatchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DayTrendBatchServiceTest {

    @Autowired
    private DayTrendBatchService dayTrendBatchService;

    @Autowired
    private DayTrendRepo dayTrendRepo;

    @Test
    public void insertAllStocksDayTrends(){
        this.dayTrendBatchService.insertAllStocksDayTrends();
    }

    @Test
    public void insertUnInsertStocksDayTrends(){
        this.dayTrendBatchService.insertUnInsertStocksDayTrends();
    }


    @Test
    public void xunhuanInsertUnInsertStocksDayTrends(){
        this.dayTrendBatchService.insertUnInsertStocksDayTrends();
        List<DayTrend> dayTrends = this.dayTrendRepo.findDayTrendsByStockId(954);
        if (dayTrends.size() == 0) {
            this.xunhuanInsertUnInsertStocksDayTrends();
        }

    }

}
