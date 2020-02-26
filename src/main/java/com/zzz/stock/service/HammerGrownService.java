package com.zzz.stock.service;

import com.zzz.stock.entity.DayTrend;
import com.zzz.stock.entity.HammerGrown;
import com.zzz.stock.repo.DayTrendRepo;
import com.zzz.stock.repo.HammerGrownRepo;
import com.zzz.stock.util.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class HammerGrownService {

    @Autowired
    private DayTrendRepo dayTrendRepo;

    @Autowired
    private HammerGrownRepo hammerGrownRepo;


    public void saveHammerGrowns(Integer stockId) {
        List<DayTrend> dayTrends = this.dayTrendRepo.findDayTrendsByStockId(stockId);
        if (dayTrends.isEmpty()) {
            return;
        }
        int dayTrendSize = dayTrends.size();
        Map<Integer, DayTrend> hammerDayTrendMap = new LinkedHashMap<>();
        List<HammerGrown> hammerGrowns = new ArrayList<>(dayTrendSize * 30);
        for (int i = 0; i < dayTrendSize; i++) {
            DayTrend currentDayTrend = dayTrends.get(i);
            Boolean isHammer = currentDayTrend.getIsHammer();
            if (isHammer != null && isHammer) {
                hammerDayTrendMap.put(i, currentDayTrend);
                for (int j = 1; j <= 30; j++) {
                    int index = i + j;
                    if (index > dayTrendSize) {
                        continue;
                    }
                    HammerGrown hammerGrown = this.buildHammerGrown(stockId, currentDayTrend, j, dayTrends.get(index));
                    hammerGrowns.add(hammerGrown);
                }
            }
        }
        this.hammerGrownRepo.saveAll(hammerGrowns);
    }

    private HammerGrown buildHammerGrown(Integer stockId, DayTrend hammerDayTrend, int dayDiff, DayTrend someDaysLaterDayTrend) {
        double rate = MathUtils.ratePercent(someDaysLaterDayTrend.getClose(), hammerDayTrend.getClose());
        HammerGrown hammerGrown = new HammerGrown(null, stockId, hammerDayTrend.getId(), dayDiff, rate);
        return hammerGrown;
    }


}
