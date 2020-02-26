package com.zzz.stock.service;

import com.zzz.stock.entity.DayTrend;
import com.zzz.stock.repo.DayTrendRepo;
import com.zzz.stock.util.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 锤子线
 */
@Service
public class HammerService {

    @Autowired
    private DayTrendRepo dayTrendRepo;

    /**
     * 找到锤子日线,并更新isHammer为true
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateHammerDayTrend(Integer stockId) {
        List<DayTrend> dayTrends = this.dayTrendRepo.findDayTrendByStockId(stockId);
        List<DayTrend> hammerDayTrends = new ArrayList<>(dayTrends);
        for (int i = 0; i < dayTrends.size(); i++) {
            if (i < 1) {
                continue;
            }
            DayTrend currentDayTrend = dayTrends.get(i);
            DayTrend oneDayAgo = dayTrends.get(i - 1);
            if (currentDayTrend.getKdjj() >= oneDayAgo.getKdjj()) {
                continue;
            }
            if (this.isHammer(currentDayTrend)) {
                currentDayTrend.setIsHammer(true);
                hammerDayTrends.add(currentDayTrend);
            }
        }
        dayTrendRepo.saveAll(hammerDayTrends);
    }

    public boolean isHammer(DayTrend block) {
        double percent = Math.abs(block.getPercent());
        double openCloseMax = MathUtils.max(block.getOpen().doubleValue(), block.getClose().doubleValue());
        double openCloseMin = MathUtils.min(block.getOpen().doubleValue(), block.getClose().doubleValue());
        double upperShadow = MathUtils.ratePercent(block.getHigh(), openCloseMax);
        double downShadow = Math.abs(MathUtils.ratePercent(block.getLow(), openCloseMin));
        boolean flag = percent < 1.5 && upperShadow < 0.5 && downShadow > 1.5;
        return flag;
    }

}
