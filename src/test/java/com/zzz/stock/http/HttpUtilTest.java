package com.zzz.stock.http;

import cn.hutool.http.HttpRequest;
import com.zzz.stock.util.URLMapper;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HttpUtilTest {

    @Test
    public void test() {
        Map<String, Object> formMap = new HashMap<>();

        formMap.put("symbol", "SH600728");
        formMap.put("period", "day");
        formMap.put("type", "before");
        formMap.put("begin", "1581475398000");
        formMap.put("indicator", "kline,pe,pb,ps,pcf,market_capital,agt,ggt,balance,macd,ma");

        String body = HttpRequest.get(URLMapper.STOCK_TREND_JSON.toString())
                .form(formMap)
                .cookie("acw_tc=2760820e15820303911864982eeeb135015e7db5a6388be669d1c3d57d0e13; cookiesu=801582030387453; device_id=24700f9f1986800ab4fcc880530dd0ed; s=bz12o31f6x; xq_a_token=7773337c73ca0525e860d8ec7fb0549d5f518d65; xqat=7773337c73ca0525e860d8ec7fb0549d5f518d65; xq_r_token=4552d8d4c1eb2d5a1169f617b6c2ccecfa53b27b; xq_id_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1aWQiOjU0ODUzNjUwNjQsImlzcyI6InVjIiwiZXhwIjoxNTg0NjEwNDY5LCJjdG0iOjE1ODIwMzA0MDA4MzcsImNpZCI6ImQ5ZDBuNEFadXAifQ.UCV7ex6zD-fX2i7p0QvXTYb7Tv3vGhC3E2VQ1Orb9hx0fhnjYnS7nVNZd19Ig_LIlq6SQAxEAMt_W2S4UQdbZYhaqt8YB9dTtguot9udBM2k0-7DlztRQt172Foj_fKCwNLr_gCHMdcP0ldWxiwO-yE9ZUONweKbm5X2zqKVJVU8DnTQqZg45er1Ncw6GcVFlCfL5SW7rMPO6eowukWIVEHSFvQz9lFW-n5R5ObY8_YDuou2nVSXazRrcWpda7zcY08odhbODlP1uLILT5zuGbWZZoafzcUhtfU0UcEj4aMP1LzsB6_QFT14dZKVbCpcTHKz0CeRQv3fikaTPq6Dpg; xq_token_expire=Sat%20Mar%2014%202020%2020%3A53%3A20%20GMT%2B0800%20(China%20Standard%20Time); xq_is_login=1; u=5485365064; bid=8be813c3937c1f4fd15b52b67a79a466_k6s0rmv6; __utma=1.1511658707.1582038319.1582038319.1582038319.1; __utmz=1.1582038319.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); aliyungf_tc=AQAAAHInbSL0vQcA/nXmeOtsgg+S17zC; Hm_lvt_1db88642e346389874251b5a1eded6e3=1582030388,1582339305; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1582342181")
                .execute()
                .body();
        System.out.println(body);

    }
}
