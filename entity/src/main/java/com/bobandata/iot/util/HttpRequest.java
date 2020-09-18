package com.bobandata.iot.util;

import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: lizhipeng
 * @Company: 上海博般数据技术有限公司
 * @Date: 2019/2/18 16:17
 * @Description:
 */

public class HttpRequest {
    private static Logger logger = LoggerFactory.getLogger(HttpRequest.class);
    /**
     * 发起http请求并获取结果
     * @param requestUrl 请求地址
     */
    public static JsonArray getUrl(String requestUrl){
        //get方法中空格需要换成%
        requestUrl = requestUrl.replace(" ","%20");
        String res ;
        JsonArray object = new JsonArray();
        StringBuilder buffer = new StringBuilder();
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            if (200 == urlCon.getResponseCode()) {
                InputStream is = urlCon.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);

                String str;
                while ((str = br.readLine()) != null) {
                    buffer.append(str);
                }
                br.close();
                isr.close();
                is.close();
                res = buffer.toString();
                JsonParser parser = new JsonParser();
                object = (JsonArray) parser.parse(res);
            }else {
                logger.error("----------"+urlCon.getResponseCode()+"---------");
            }
        } catch (IOException e) {
            logger.error("HttpRequest address exception");
        }
        return object;
    }

//post请求
    public static String postUrl(String requestUrl, List<?> param){
        //get方法中空格需要换成%
        requestUrl = requestUrl.replace(" ","%20");
        String res ="";
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setRequestMethod("POST");
            urlCon.setDoOutput(true);
            urlCon.setDoInput(true);
            urlCon.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            OutputStreamWriter out = new OutputStreamWriter(urlCon.getOutputStream());
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            String paramStr =gson.toJson(param);
            out.write(paramStr);
            out.close();

            if(urlCon.getResponseCode()==200){
                return "success";
            }else {
                InputStreamReader reader = new InputStreamReader(urlCon.getInputStream());
                return reader.toString();
            }
        } catch (Exception e) {
            logger.error("HttpRequest address exception");
            e.printStackTrace();
        }
        return res;
    }

//将解析LinkedList转Bean
    public static Object toJson(Map map, Class clazz){
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        String jsonString = gson.toJson(map);
        return gson.fromJson(jsonString, clazz);
    }


    public static void main(String[] args){
//        JsonArray jsonArr =HttpRequest.getUrl("http://localhost:8001/HisView/findLimitData?startTime=2018-10-10 10:10:00&endTime=2019-02-18 06:36:00&startMark=1&endMark=3");
//        Gson googleJson = new Gson();
//        System.out.println(googleJson.fromJson(jsonArr, ArrayList.class));

//        List<Pulses> list = new ArrayList<>();
//        Pulses pulses = new Pulses();
//        pulses.setMeterId(11111L);
//        pulses.setPulseAddr(1);
//        pulses.setPulseName("q");
//        list.add(pulses);
//        list.add(pulses);
//        String post =HttpRequest.postUrl("http://localhost:8001/Pulses/saves",list);
//        System.out.println(post);

//        Timestamp y = Timestamp.valueOf("May 8, 2019 8:06:00 PM");
        Timestamp timestamp = new Timestamp((new Date()).getTime());
        System.out.println(new Date());
    }
}