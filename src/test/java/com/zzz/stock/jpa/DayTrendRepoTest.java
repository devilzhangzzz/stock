package com.zzz.stock.jpa;


import com.zzz.stock.entity.DayTrend;
import com.zzz.stock.entity.Stock;
import com.zzz.stock.repo.DayTrendRepo;
import com.zzz.stock.repo.StockRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DayTrendRepoTest {


    @Resource
    private DayTrendRepo repo;

    @Test
    @Transactional
    public void test(){
        DayTrend dayTrend = this.repo.findById(1).get();
        Stock stock = dayTrend.getStock();
        System.out.println(dayTrend);
        System.out.println(stock);
    }

}
