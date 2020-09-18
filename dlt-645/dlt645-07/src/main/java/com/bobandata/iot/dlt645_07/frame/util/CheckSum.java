 package com.bobandata.iot.dlt645_07.frame.util;

 import com.bobandata.iot.transport.util.HexUtils;
 import io.netty.buffer.ByteBuf;

 public class CheckSum implements IUtilFrame
 {
   private byte cs;

   public int encode(ByteBuf buffer)
     throws Exception
   {
     buffer.writeByte(HexUtils.cs(buffer, 0));
     return 0;
   }

   public int decode(ByteBuf buffer) throws Exception
   {
    this.cs = buffer.readByte();
    return 0;
   }

   public byte getCs() {
/* 27 */     return this.cs;
   }

   public void setCs(byte cs) {
/* 31 */     this.cs = cs;
   }
 }
