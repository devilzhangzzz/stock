package com.zzz.stock.hammer;

import com.zzz.stock.service.HammerBatchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HammerBatchServiceTest {

    @Autowired
    private HammerBatchService dayTrendBatchService;

    @Test
    public void batchUpdateHammerDayTrend() {
          this.dayTrendBatchService.bathcUpdateHammerDayTrend();
    }

}
