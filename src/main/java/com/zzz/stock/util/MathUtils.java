package com.zzz.stock.util;

import cn.hutool.core.util.NumberUtil;

import java.util.Arrays;

/**
 * @author: decaywood
 * @date: 2015/12/9 20:38
 */
public abstract class MathUtils {

    public static double min(double... nums) {
        if(nums.length == 0) return Double.MIN_VALUE;
        return Arrays.stream(nums).min().getAsDouble();
    }

    public static int min(int... nums) {
        if(nums.length == 0) return Integer.MIN_VALUE;
        return Arrays.stream(nums).min().getAsInt();
    }


    public static long min(long... nums) {
        if(nums.length == 0) return Long.MIN_VALUE;
        return Arrays.stream(nums).min().getAsLong();
    }


    public static double max(double... nums) {
        if(nums.length == 0) return Double.MIN_VALUE;
        return Arrays.stream(nums).max().getAsDouble();
    }

    public static int max(int... nums) {
        if(nums.length == 0) return Integer.MIN_VALUE;
        return Arrays.stream(nums).max().getAsInt();
    }


    public static long max(long... nums) {
        if(nums.length == 0) return Long.MIN_VALUE;
        return Arrays.stream(nums).max().getAsLong();
    }

    public static double avg(double... nums) {
        if(nums.length == 0) {
            throw new UnsupportedOperationException();
        }
        return Arrays.stream(nums).average().getAsDouble();
    }

    /**
     * a相对于b百分增长值
     * @param a
     * @param b 底
     * @return
     */
    public static double ratePercent(double a,double b) {
        double sub = NumberUtil.sub(a, b);
        double div = NumberUtil.div(sub, b);
        double mul = NumberUtil.mul(div, 100);
        return NumberUtil.round(mul, 2).doubleValue();
    }




}
