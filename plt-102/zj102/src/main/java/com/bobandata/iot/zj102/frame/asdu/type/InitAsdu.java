package com.bobandata.iot.zj102.frame.asdu.type;

import com.bobandata.iot.zj102.frame.asdu.AsduHead;
import com.bobandata.iot.zj102.frame.asdu.Asdu;
import io.netty.buffer.ByteBuf;

public class InitAsdu extends Asdu {
		
	private byte code;
    //时间数据长度
    private static int dateTimeLength = 1;
	
	public InitAsdu() {
		// TODO Auto-generated constructor stub
	}

    public InitAsdu(AsduHead asduHead, byte code){
    	this.code = code;
        this.setAsduHead(asduHead);
        this.setAsduLength(this.getAsduHead().getAsduHeadLength()+dateTimeLength);
    }

	@Override
	public int encode(ByteBuf buffer) throws Exception {
		this.getAsduHead().encode(buffer);
		buffer.writeByte(this.getCode());
		return 0;
	}


	@Override
	public int decode(ByteBuf buffer) throws Exception {
		// TODO Auto-generated method stub
		this.getAsduHead().decode(buffer);
		this.setCode(buffer.readByte());
		return 0;
	}

	@Override
	public String toHexString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toExplain() {
		// TODO Auto-generated method stub
		return null;
	}



	public byte getCode() {
		return code;
	}

	public void setCode(byte code) {
		this.code = code;
	}
}
