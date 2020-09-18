package com.bobandata.iot.dlt645_07.frame.util;

import io.netty.buffer.ByteBuf;

public abstract interface IUtilFrame
{
  public abstract int encode(ByteBuf paramByteBuf)
    throws Exception;

  public abstract int decode(ByteBuf paramByteBuf)
    throws Exception;
}