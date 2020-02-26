package com.zzz.stock.jpa;


import com.zzz.stock.entity.Stock;
import com.zzz.stock.repo.StockRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockRepositoryTest {


    @Resource
    private StockRepo repo;

    @Test
    public void test(){
        Stock stock = this.repo.findById(1).get();
        System.out.println(stock);
    }

}
