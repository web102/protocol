package com.bobandata.iot.dlt645_07.util;

/**
  * @Description: 功能码
  * @Author:liutuo
  * @Date:10:18 2018/8/31
  *
  */
public class FunCodeConst
{
  //读电表数据
  public static final byte REQ_METER = (byte) 0x11;

  //电表应答正常
  public static final byte RES_NORMAL = (byte) 0x91;

  //异常
  public static final byte RES_ERROR = (byte) 0xD1;

}