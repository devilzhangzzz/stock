package com.zzz.stock.service;

import com.zzz.stock.entity.Stock;
import com.zzz.stock.repo.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class HammerBatchService {

    private ExecutorService executorService = Executors.newFixedThreadPool(100);

    @Autowired
    private StockRepo stockRepo;

    @Autowired
    private HammerService hammerService;


    @Transactional
    public void bathcUpdateHammerDayTrend() {
        List<Stock> stocks = this.stockRepo.findAll();
        this.batchUpdateHammerDayTrend(stocks);
    }

    private void batchUpdateHammerDayTrend(List<Stock> stocks) {
        List<Future<String>> futures = new ArrayList<>(stocks.size());
        for (Stock stock : stocks) {
            UpdateHammerTask updateHammerTask = new UpdateHammerTask(hammerService, stock);
            Future<String> submit = executorService.submit(updateHammerTask);
            futures.add(submit);
        }
        boolean isAllFinished;
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isAllFinished = futures.stream().filter(Future::isDone).count() == futures.size();
        } while (!isAllFinished);
    }


    public static class UpdateHammerTask implements Callable<String> {

        private HammerService hammerService;

        private Stock stock;

        public UpdateHammerTask(HammerService hammerService, Stock stock) {
            this.hammerService = hammerService;
            this.stock = stock;
        }

        @Override
        public String call() {
            this.hammerService.updateHammerDayTrend(stock.getId());
            return "";
        }
    }
}
