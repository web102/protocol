package com.bobandata.iot.dlt645_07.frame.data;

import com.bobandata.iot.dlt645_07.util.DataTypeConst;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: liutuo
 * @Description: 数据块部分
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 11:39 2018/9/2.
 */
public class DataValue {
    private static final Logger logger = LoggerFactory.getLogger(DataValue.class);
    private String value;

    private Timestamp timestamp;

    private String value1;
    private String value2;
    private String value3;
    private String value4;
    private String value5;

    public DataValue(){
    }

    public DataValue(String value){
        this.value=value;
    }

    public DataValue(String value1,String value2 ,String value3, String value4, String value5){
        this.value1=value1;
        this.value2=value2;
        this.value3=value3;
        this.value4=value4;
        this.value5=value5;
    }

    public int encode(ByteBuf paramByteBuf ,int length) throws Exception {
        switch (length){
            case 8:
                paramByteBuf.writeBytes(stringToBytes(value,3));
                paramByteBuf.writeBytes(timestampTo5Bytes(timestamp));
                break;
            case 6:
                paramByteBuf.writeBytes(timestampTo6Bytes(timestamp));
                break;
            case 4:
            case 3:
            case 2:
                paramByteBuf.writeBytes(stringToBytes(value,length));
                break;
            case 20:
                paramByteBuf.writeBytes(stringToBytes(value1,4));
                paramByteBuf.writeBytes(stringToBytes(value2,4));
                paramByteBuf.writeBytes(stringToBytes(value3,4));
                paramByteBuf.writeBytes(stringToBytes(value4,4));
                paramByteBuf.writeBytes(stringToBytes(value5,4));
                break;
        }
        return 0;
    }

    public int decode(ByteBuf paramByteBuf, DataTypeConst dataType) throws Exception {
        byte[] bytes =null;
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
                 bytes= new byte[4];
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
                bytes = new byte[3];
                break;
                /** 事件**/
            case A_LOSE_A_END_TIME:
            case B_LOSE_A_END_TIME:
            case C_LOSE_A_END_TIME:
            case A_LOSE_V_END_TIME:
            case B_LOSE_V_END_TIME:
            case C_LOSE_V_END_TIME:
            case A_LOSE_A_START_TIME:
            case A_LOSE_V_START_TIME:
            case B_LOSE_A_START_TIME:
            case B_LOSE_V_START_TIME:
            case C_LOSE_A_START_TIME:
            case C_LOSE_V_START_TIME:
                bytes = new byte[6];
                break;

            /** 8,9,10 三项电压**/
            case A_V:
            case B_V:
            case C_V:
            /**功率因素**/
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
                bytes = new byte[2];
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
                bytes = new byte[8];
                break;

            /**日冻结费率**/
            case DAY_POSITIVE_ACTIVE_ENERGY:
            case DAY_REVERSE_ACTIVE_ENERGY:
            case DAY_REVERSE_REACTIVE_ENERGY:
            case DAY_POSITIVE_REACTIVE_ENERGY:
                bytes = new byte[20];
                break;
            default:
                logger.error("---------------Not Found Type----------------");
                break;
        }
        paramByteBuf.readBytes(bytes);
        this.bytesToValue(bytes);
        return 0;
    }


    private byte[] stringToBytes(String value ,Integer byteLength){
        value=value.replace(".","");
        value=Integer.toHexString(Integer.parseInt(value));

        while ((byteLength*2-value.toCharArray().length>0)){
            value="0"+ value;
        }

        byte[] bytes =new byte[byteLength];
        for(int i=0;i<byteLength;i++){
            bytes[byteLength-i-1]=(byte)(Integer.parseInt(value.substring(i*2,2*i+2),16)+0x33);
        }
        return bytes;
    }

    private void bytesToValue(byte[] bytes) throws ParseException {
        StringBuilder dataValue= new StringBuilder();
        switch (bytes.length){
            case 4:
            case 3:
            case 2:
                for (byte aByte : bytes) {
                    dataValue.insert(0, byteToHexString((aByte & 0xFF) - 0x33));
                }
                this.value = Integer.parseInt(dataValue.toString(),16)+"";
                break;
            case 8:
                bytes[0] = (byte) (bytes[0]-0x33);
                bytes[1] = (byte) (bytes[1]-0x33);
                bytes[2] = (byte) (bytes[2]-0x33);
                for (byte aByte : bytes) {
                    dataValue.insert(0, byteToHexString((aByte & 0xFF)));
                }
                this.value=Integer.parseInt(dataValue.substring(10,16),16)+"";
                String yyyy ="20"+(Integer.parseInt(dataValue.substring(0,2),16));
                String MM =Integer.parseInt(dataValue.substring(2,4),16)+"";
                String dd =Integer.parseInt(dataValue.substring(4,6),16)+"";
                String HH =Integer.parseInt(dataValue.substring(6,8),16)+"";
                String mm =Integer.parseInt(dataValue.substring(8,10),16)+"";
                String ss ="00";
                String datetime = yyyy+"-"+MM+"-"+dd+" "+HH+":"+mm+":"+ss;
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                this.timestamp=  new Timestamp(dateFormat.parse(datetime).getTime());
                break;
            case 6:
                for (byte aByte : bytes) {
                    dataValue.insert(0, byteToHexString((aByte & 0xFF)));
                }
                String yyyy1 ="20"+(Integer.parseInt(dataValue.substring(0,2),16));
                String MM1 =Integer.parseInt(dataValue.substring(2,4),16)+"";
                String dd1 =Integer.parseInt(dataValue.substring(4,6),16)+"";
                String HH1 =Integer.parseInt(dataValue.substring(6,8),16)+"";
                String mm1 =Integer.parseInt(dataValue.substring(8,10),16)+"";
                String ss1 =Integer.parseInt(dataValue.substring(10,12),16)+"";
                String datetime1 = yyyy1+"-"+MM1+"-"+dd1+" "+HH1+":"+mm1+":"+ss1;
                DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                this.timestamp= new Timestamp(dateFormat1.parse(datetime1).getTime());
                break;
            case 20:
                for (byte aByte : bytes) {
                    dataValue.insert(0, byteToHexString((aByte & 0xFF) - 0x33));
                }
                this.value5 = Integer.parseInt(dataValue.substring(0,8),16)+"";
                this.value4 = Integer.parseInt(dataValue.substring(8,16),16)+"";
                this.value3 = Integer.parseInt(dataValue.substring(16,24),16)+"";
                this.value2 = Integer.parseInt(dataValue.substring(24,32),16)+"";
                this.value1 = Integer.parseInt(dataValue.substring(32,40),16)+"";
                break;
            default:
                logger.error("---------------Not Found Type----------------");
                break;

        }
    }

    private byte[] timestampTo6Bytes(Timestamp timestamp){
        byte[] bytes = new byte[6];
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String times = format.format(timestamp);
        bytes[5] =Byte.parseByte(times.substring(2,4));
        bytes[4] =Byte.parseByte(times.substring(4,6));
        bytes[3] =Byte.parseByte(times.substring(6,8));
        bytes[2] =Byte.parseByte(times.substring(8,10));
        bytes[1] =Byte.parseByte(times.substring(10,12));
        bytes[0] =Byte.parseByte(times.substring(10,12));
        return bytes;
    }

    private byte[] timestampTo5Bytes(Timestamp timestamp){
        byte[] bytes = new byte[5];
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String times = format.format(timestamp);
        bytes[4] =Byte.parseByte(times.substring(2,4));
        bytes[3] =Byte.parseByte(times.substring(4,6));
        bytes[2] =Byte.parseByte(times.substring(6,8));
        bytes[1] =Byte.parseByte(times.substring(8,10));
        bytes[0] =Byte.parseByte(times.substring(10,12));
        return bytes;
    }

    private String byteToHexString(int b){
        String s = Integer.toHexString(b);
        if(s.toCharArray().length==2){
            return s;
        }
        else if(s.toCharArray().length==1){
            return "0"+s;
        }
        else{
            return "00";
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public String getValue4() {
        return value4;
    }

    public void setValue4(String value4) {
        this.value4 = value4;
    }

    public String getValue5() {
        return value5;
    }

    public void setValue5(String value5) {
        this.value5 = value5;
    }
}
