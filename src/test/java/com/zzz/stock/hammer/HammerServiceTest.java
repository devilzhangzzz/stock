package com.zzz.stock.hammer;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.zzz.stock.entity.DayTrend;
import com.zzz.stock.repo.DayTrendRepo;
import com.zzz.stock.service.HammerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HammerServiceTest {

    @Autowired
    private HammerService hammerService;

    @Autowired
    private DayTrendRepo dayTrendRepo;

    @Test
    public void updateHammerDayTrend() {
        this.hammerService.updateHammerDayTrend(1666);
    }

    @Test
    public void updateHammerDayTrendBetweenTimes() {
        DateTime start = DateUtil.parse("2020-02-17", "yyyy-MM-dd");
        DateTime end = DateUtil.parse("2020-02-18", "yyyy-MM-dd");
        this.hammerService.updateHammerDayTrendBetweenTimes(start.getTime() , end.getTime());
    }

    @Test
    public void isHammer() {
        DayTrend dayTrend = dayTrendRepo.findById(1321596).get();
        boolean hammer = this.hammerService.isHammer(dayTrend);
        System.out.println(hammer);
    }
}
