package com.zzz.stock.day.trend;

import com.zzz.stock.service.DayTrendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DayTrendServiceTest {

    @Autowired
    private DayTrendService dayTrendService;


    @Test
    public void test(){
        this.dayTrendService.insertDayTrends(954);
    }
}
