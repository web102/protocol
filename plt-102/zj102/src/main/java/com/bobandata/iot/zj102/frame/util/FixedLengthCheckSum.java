 package com.bobandata.iot.zj102.frame.util;

 import com.bobandata.iot.transport.util.HexUtils;
 import io.netty.buffer.ByteBuf;
 import io.netty.buffer.ByteBufUtil;

 public class FixedLengthCheckSum implements IUtilFrame
 {
   private byte cs;
   private String hexDump;

   public int encode(ByteBuf buffer)
     throws Exception
   {
       this.cs = HexUtils.cs(buffer, 1);
       buffer.writeByte(this.cs);
       return 0;
   }

   public int decode(ByteBuf buffer) throws Exception
   {
       this.hexDump = ByteBufUtil.hexDump(buffer);
       this.cs = buffer.readByte();
       return 0;
   }

   public byte getCs() {
/* 27 */     return this.cs;
   }

   public void setCs(byte cs) {
/* 31 */     this.cs = cs;
   }

     public String toExplain() {
         return "校验位:"+(this.cs&0xFF);
     }
 }
