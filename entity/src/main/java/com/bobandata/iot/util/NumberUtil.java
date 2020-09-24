package com.bobandata.iot.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @Author: 李志鹏
 * @Date: 2020/9/21 17:55
 * @Desc:
 * @param:
 * @return:
 **/
public class NumberUtil {

    //乘以精度,获取小数
    public static double multiply(Long longValue, Double accuracy){
        BigDecimal bigDecimal = new BigDecimal(longValue.toString());
        BigDecimal bigDecimal1 = new BigDecimal(accuracy.toString());
        return bigDecimal.multiply(bigDecimal1).doubleValue();
    }

    //除以进度，获取整数
    public static long divide(String doubleValue, Double accuracy){
        BigDecimal bigDecimal = new BigDecimal(doubleValue);
        BigDecimal bigDecimal1 = new BigDecimal(accuracy.toString());
        return bigDecimal.divide(bigDecimal1,0,BigDecimal.ROUND_DOWN).longValue();
    }

    //除以进度，获取整数
    public static long divide(Double doubleValue, Double accuracy){
        BigDecimal bigDecimal = new BigDecimal(doubleValue);
        BigDecimal bigDecimal1 = new BigDecimal(accuracy.toString());
        return bigDecimal.divide(bigDecimal1,0,BigDecimal.ROUND_DOWN).longValue();
    }


    //保留4位小数并补0，长度为12
    public static String strFormat(Object a){
        String b = new BigDecimal(a.toString()).setScale(4,BigDecimal.ROUND_DOWN).toString();
        return String.format("%12s",b);
    }

    public static void main(String[] args) {
        Double a =NumberUtil.multiply(5L,0.0001);
        System.out.println(NumberUtil.strFormat(a));
        a =NumberUtil.multiply(1456789L,0.01);
        System.out.println(NumberUtil.strFormat(a));
    }

}
