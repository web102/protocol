package com.bobandata.iot.zj102.frame.util;

import io.netty.buffer.ByteBuf;

public abstract interface IUtilFrame
{
  public abstract int encode(ByteBuf paramByteBuf)
    throws Exception;

  public abstract int decode(ByteBuf paramByteBuf)
    throws Exception;
}