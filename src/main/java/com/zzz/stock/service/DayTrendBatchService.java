package com.zzz.stock.service;

import com.zzz.stock.entity.Stock;
import com.zzz.stock.repo.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class DayTrendBatchService {


    @Autowired
    private StockRepo stockRepo;

    @Autowired
    private DayTrendService dayTrendService;

    private ExecutorService executorService = Executors.newFixedThreadPool(100);

    @Transactional
    public void insertAllStocksDayTrends() {
        List<Stock> stocks = this.stockRepo.findAll();
        this.batchInsert(stocks);
    }

    @Transactional
    public void insertUnInsertStocksDayTrends() {
        List<Stock> stocks = this.stockRepo.findUnInsertStocks();
        this.batchInsert(stocks);
    }

    private void batchInsert(Collection<Stock> stocks) {
        if (stocks.isEmpty()) {
            return;
        }
        List<Future<String>> futures = new ArrayList<>(stocks.size());
        for (Stock stock : stocks) {
            InsertTask insertTask = new InsertTask(dayTrendService, stock);
            Future<String> submit = executorService.submit(insertTask);
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

    public static class InsertTask implements Callable<String> {

        private DayTrendService dayTrendService;

        private Stock stock;

        public InsertTask(DayTrendService dayTrendService, Stock stock) {
            this.dayTrendService = dayTrendService;
            this.stock = stock;
        }

        @Override
        public String call() {
            this.dayTrendService.insertDayTrends(stock);
            return "";
        }
    }

}
