package com.bobandata.iot.dlt645_07.frame.data;

import com.bobandata.iot.dlt645_07.util.DataTypeConst;
import com.bobandata.iot.transport.util.HexUtils;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: liutuo
 * @Description: 从站侧响应数据域：数据域长度、数据项
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 11:36 2018/9/2.
 */
public class DataReturnArea extends DataArea{
    private static Logger logger = LoggerFactory.getLogger(DataReturnArea.class);

    //数据项
    private DataTypeConst dataType;


    public DataReturnArea(){}

    public DataReturnArea(DataTypeConst dataType,DataValue dataValue){
        this.dataType = dataType;
        this.dataValue = dataValue;
    }

    @Override
    public int encode(ByteBuf paramByteBuf) throws Exception {
        //判断数据类型确定长度
        switch (dataType){
                /**正反有无总电量**/
            case POSITIVE_ACTIVE_ENERGY:
            case POSITIVE_REACTIVE_ENERGY:
            case REVERSE_ACTIVE_ENERGY:
            case REVERSE_REACTIVE_ENERGY:
                /**四象限无功总电量**/
            case SUM_FIRST_REACTIVE_ENERGY:
            case SUM_SECOND_REACTIVE_ENERGY:
            case SUM_THIRD_REACTIVE_ENERGY:
            case SUM_FOURTH_REACTIVE_ENERGY:
                setDataLength(4+4);
                paramByteBuf.writeByte(HexUtils.intToByte(getDataLength()));
                paramByteBuf.writeBytes(dataType.getReCode());
                dataValue.encode(paramByteBuf,4);
                break;

                /** 5,6,7 三项电流**/
            case A_A:
            case B_A:
            case C_A:
                /** 11,12,13,14 三项有功功率**/
            case A_ACTIVE_POWER:
            case B_ACTIVE_POWER:
            case C_ACTIVE_POWER:
            case SUM_ACTIVE_POWER:
                /** 15,16,17,18 三项无功功率**/
            case A_REACTIVE_POWER:
            case B_REACTIVE_POWER:
            case C_REACTIVE_POWER:
            case SUM_REACTIVE_POWER:
                /** 19,20,21,22三项视在功率**/
            case A_APPARENT_POWER:
            case B_APPARENT_POWER:
            case C_APPARENT_POWER:
            case SUM_APPARENT_POWER:
                /** 62,63,64 当前需量**/
            case ACTIVE_DEMAND:
            case REACTIVE_DEMAND:
            case APPARENT_DEMAND:
                setDataLength(4+3);
                paramByteBuf.writeByte(HexUtils.intToByte(getDataLength()));
                paramByteBuf.writeBytes(dataType.getReCode());
                dataValue.encode(paramByteBuf,3);
                break;

            /** 8,9,10 三项电压**/
            case A_V:
            case B_V:
            case C_V:
            case SUM_POWER_FACTOR:
            case A_POWER_FACTOR:
            case B_POWER_FACTOR:
            case C_POWER_FACTOR:
            /** 27,28,29,30,31,32 三相线电流/电压谐波畸变率(A电流波形失真度)**/
            case A_A_DISTORTION:
            case B_A_DISTORTION:
            case C_A_DISTORTION :
            case A_V_DISTORTION:
            case B_V_DISTORTION:
            case C_V_DISTORTION:
            /** 33 频率**/
            case FREQUENCY:
                setDataLength(4+2);
                paramByteBuf.writeByte(HexUtils.intToByte(getDataLength()));
                paramByteBuf.writeBytes(dataType.getReCode());
                dataValue.encode(paramByteBuf,2);
                break;
            case A_LOSE_A_END_TIME:
            case A_LOSE_A_START_TIME:
            case A_LOSE_V_END_TIME:
            case A_LOSE_V_START_TIME:
            case B_LOSE_A_END_TIME:
            case B_LOSE_A_START_TIME:
            case B_LOSE_V_END_TIME:
            case B_LOSE_V_START_TIME:
            case C_LOSE_V_START_TIME:
            case C_LOSE_A_START_TIME:
            case C_LOSE_V_END_TIME:
            case C_LOSE_A_END_TIME:
                setDataLength(4+6);
                paramByteBuf.writeByte(HexUtils.intToByte(getDataLength()));
                paramByteBuf.writeBytes(dataType.getReCode());
                dataValue.encode(paramByteBuf,6);
                break;

            /**当前最大需量**/
            case POSITIVE_ACTIVE_MAX_DEMAND_AND_TIME:
            case POSITIVE_REACTIVE_MAX_DEMAND_AND_TIME:
            case REVERSE_ACTIVE_MAX_DEMAND_AND_TIME:
            case REVERSE_REACTIVE_MAX_DEMAND_AND_TIME:
                /**日冻结需量**/
            case DAY_POSITIVE_ACTIVE_MAX_DEMAND_AND_TIME:
            case DAY_POSITIVE_REACTIVE_MAX_DEMAND_AND_TIME:
            case DAY_REVERSE_ACTIVE_MAX_DEMAND_AND_TIME:
            case DAY_REVERSE_REACTIVE_MAX_DEMAND_AND_TIME:
                setDataLength(4+8);
                paramByteBuf.writeByte(HexUtils.intToByte(getDataLength()));
                paramByteBuf.writeBytes(dataType.getReCode());
                dataValue.encode(paramByteBuf,8);
                break;
            /**日冻结费率**/
            case DAY_POSITIVE_ACTIVE_ENERGY:
            case DAY_REVERSE_ACTIVE_ENERGY:
            case DAY_REVERSE_REACTIVE_ENERGY:
            case DAY_POSITIVE_REACTIVE_ENERGY:
                setDataLength(4+20);
                paramByteBuf.writeByte(HexUtils.intToByte(getDataLength()));
                paramByteBuf.writeBytes(dataType.getReCode());
                dataValue.encode(paramByteBuf,20);
                break;
            default:
                logger.error("---------------Not Found Type----------------");
        }
        return 0;
}

    @Override
    public int decode(ByteBuf paramByteBuf) throws Exception {
        this.dataLength = HexUtils.byteToInt(paramByteBuf.readByte());
        byte[] bytes = new byte[4];
        paramByteBuf.readBytes(bytes);
        dataType = DataTypeConst.findByCode(bytes);
        dataValue = new DataValue();
        dataValue.decode(paramByteBuf,dataType);
        return 0;
    }


    public DataTypeConst getDataType() {
        return dataType;
    }

    public void setDataType(DataTypeConst dataType) {
        this.dataType = dataType;
    }
}
