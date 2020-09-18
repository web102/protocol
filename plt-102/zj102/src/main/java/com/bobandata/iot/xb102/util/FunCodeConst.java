package com.bobandata.iot.xb102.util;

/**
  * @Description: 功能码
  * @Author:liutuo
  * @Date:10:18 2018/8/31
  *
  */
public class FunCodeConst
{
  //处理链路复位帧
  public static final byte resetLink = 0;
  //处理请求数据帧
  public static final byte dataRequest = 3;
  //处理链路状态请求帧
  public static final byte requestLink = 9;
  //处理召唤一级数据帧
  public static final byte dataNext = 10;
}