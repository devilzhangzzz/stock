package com.zzz.stock.hammer;

import com.zzz.stock.service.HammerGrownService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class HammerGrownServiceTest {


    @Autowired
    private HammerGrownService hammerGrownService;

    @Test
    public void updateHammerDayTrend() {
        this.hammerGrownService.saveHammerGrowns(4356);
    }
}
