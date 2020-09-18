package com.bobandata.iot.dlt645_07.frame.data;

import com.bobandata.iot.dlt645_07.util.DataTypeConst;
import com.bobandata.iot.transport.util.HexUtils;
import io.netty.buffer.ByteBuf;

/**
 * @Author: liutuo
 * @Description: 主站侧请求数据域：数据域长度、数据项
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 11:36 2018/9/2.
 */
public class DataRequestArea extends DataArea{

    //数据项
    private DataTypeConst dataType;

    public DataRequestArea(){}

    public DataRequestArea(DataTypeConst dataType){
        this.dataType = dataType;
    }

    @Override
    public int encode(ByteBuf paramByteBuf) throws Exception {
        paramByteBuf.writeByte(HexUtils.intToByte(4));
        byte[] code = new byte[4];
        for(int i=0;i<4;i++){
            code[i]=(byte)(dataType.getCode()[3-i]+0x33);
        }
        paramByteBuf.writeBytes(code);
        return 0;
    }

    @Override
    public int decode(ByteBuf paramByteBuf) throws Exception {
        this.dataLength = HexUtils.byteToInt(paramByteBuf.readByte());
        byte[] code = new byte[4];
        paramByteBuf.readBytes(code);
        this.dataType=DataTypeConst.findByCode(code);
        return 0;
    }

    public DataTypeConst getDataType() {
        return dataType;
    }

    public void setDataType(DataTypeConst dataType) {
        this.dataType = dataType;
    }
}
