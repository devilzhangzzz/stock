package com.zzz.stock.service;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzz.stock.entity.DayTrend;
import com.zzz.stock.entity.Stock;
import com.zzz.stock.repo.DayTrendRepo;
import com.zzz.stock.repo.StockRepo;
import com.zzz.stock.util.URLMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DayTrendService {

    @Autowired
    private DayTrendRepo dayTrendRepo;

    @Autowired
    private StockRepo  stockRepo;


    @Value("${xueqiu.cookies}")
    private String cookies;

    @Transactional
    public void insertDayTrends(Integer stockId) {
        Stock stock = this.stockRepo.findById(stockId).get();
        this.insertDayTrends(stock);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertDayTrends(Stock stock) {
        Map<String, Object> formMap = new LinkedHashMap<>();
        formMap.put("symbol", stock.getStockNo());
        formMap.put("period", "day");
        formMap.put("type", "before");
        formMap.put("begin", "0");
        formMap.put("end", System.currentTimeMillis());
        formMap.put("indicator", "kline,pe,pb,ps,pcf,market_capital,agt,ggt,balance,macd,ma,kdj");
        String json = HttpRequest.get(URLMapper.STOCK_TREND_JSON.toString())
                .form(formMap)
                .cookie(cookies)
                .execute()
                .body();
        JSONObject jsonObject = JSON.parseObject(json);
        JSONObject data = (JSONObject) jsonObject.get("data");
        JSONArray columns = (JSONArray) data.get("column");
        if (columns == null) {
            return;
        }
        JSONArray items = (JSONArray) data.get("item");
        this.saveDayTrends(stock, columns, items);
    }

    private void saveDayTrends(Stock stock, JSONArray columns, JSONArray items) {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < columns.size(); i++) {
            map.put(columns.getString(i), i);
        }
        List<DayTrend> dayTrends = new ArrayList<>(items.size());
        for (Object item : items) {
            JSONArray array = (JSONArray) item;
            DayTrend block = DayTrend.builder()
                    .chg(array.getDouble(map.get("chg")))
                    .close(array.getDouble(map.get("close")))
                    .dea(array.getDouble(map.get("dea")))
                    .dif(array.getDouble(map.get("dif")))
                    .high(array.getDouble(map.get("high")))
                    .id(null)
                    .isHammer(null)
                    .kdjd(array.getDouble(map.get("kdjd")))
                    .kdjj(array.getDouble(map.get("kdjj")))
                    .kdjk(array.getDouble(map.get("kdjk")))
                    .low(array.getDouble(map.get("low")))
                    .ma5(array.getDouble(map.get("ma5")))
                    .ma10(array.getDouble(map.get("ma10")))
                    .ma20(array.getDouble(map.get("ma20")))
                    .ma30(array.getDouble(map.get("ma30")))
                    .macd(array.getDouble(map.get("macd")))
                    .open(array.getDouble(map.get("open")))
                    .percent(array.getDouble(map.get("percent")))
                    .stock(null)
                    .stockId(stock.getId())
                    .time(array.getLong(map.get("timestamp")))
                    .turnoverrate(array.getDouble(map.get("turnoverrate")))
                    .volume(array.getLong(map.get("volume")))
                    .build();
            dayTrends.add(block);
        }
        this.dayTrendRepo.saveAll(dayTrends);

    }




}
