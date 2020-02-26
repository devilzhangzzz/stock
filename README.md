# stock
## 1、把所有股票的开盘以来的日趋到数据库中
```
    @Test
    public void insertAllStocksDayTrends(){
        this.dayTrendBatchService.insertAllStocksDayTrends();
    }
```
