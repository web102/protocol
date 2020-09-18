package com.bobandata.iot.util;


import com.bobandata.iot.entity.his.IData;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HttpDataManager {
	private static String url = "http://localhost:8002/";

	//读限定时间范围和地址范围
	@SuppressWarnings("unchecked")
	public static List<? extends IData> getData(Class zlass, Date startTime, Date endTime, Long startMark, Long endMark){
		String startTimeS =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);
		String endTimeS =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime);
		String urls = url+zlass.getSimpleName()+"/findLimitPulsesData?startTime="+startTimeS+"&endTime="+endTimeS+"&startMark="+startMark+"&endMark="+endMark;
		JsonArray jsonArr =HttpRequest.getUrl(urls);
		List<? extends IData> data = new ArrayList<>();
		Gson gson = new Gson();
		for(JsonElement jsonElement:jsonArr){
			data.add(gson.fromJson(jsonElement, (Type) zlass));
		}
		return data;
	}


	//读限定时间范围
	@SuppressWarnings("unchecked")
	public static List<? extends IData> getData(Class zlass, Date startTime, Date endTime){
		String startTimeS =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);
		String endTimeS =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime);
		String urls = url+zlass.getSimpleName()+"/findTimeLimitData?startTime="+startTimeS+"&endTime="+endTimeS;
		JsonArray jsonArr =HttpRequest.getUrl(urls);
		List<? extends IData> data = new ArrayList<>();
		Gson gson = new Gson();
		for(JsonElement jsonElement:jsonArr){
			data.add(gson.fromJson(jsonElement, (Type) zlass));
		}
		return data;
	}

	//读最新的一条
	public static List<? extends IData> getData(Class zlass){
		String urls = url+zlass.getSimpleName()+"/findNewestData";
		JsonArray jsonArr =HttpRequest.getUrl(urls);
		List<? extends IData> data = new ArrayList<>();
		Gson gson = new Gson();
		for(JsonElement jsonElement:jsonArr){
			data.add(gson.fromJson(jsonElement, (Type) zlass));
		}
		return data;
	}

	public static String save(Class zlass,List<?> data){
		String urls = url+zlass.getSimpleName()+"/saves";
		return HttpRequest.postUrl(urls,data);
	}

	public static void main(String[] args) {
//		List<HisMeas> meas = new ArrayList<>();
//		HisMeas hisMeas = new HisMeas();
//		hisMeas.setMeasId(123L);
//		hisMeas.setOccurTime(new Timestamp((new Date()).getTime()));
//		meas.add(hisMeas);
//		meas.add(hisMeas);

	}

}
