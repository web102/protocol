package com.bobandata.iot.dlt645_07.frame;

import com.bobandata.iot.dlt645_07.connector.Dlt645MasterProtocol;
import com.bobandata.iot.dlt645_07.frame.data.DataArea;
import com.bobandata.iot.dlt645_07.frame.data.DataRequestArea;
import com.bobandata.iot.dlt645_07.frame.data.DataReturnArea;
import com.bobandata.iot.dlt645_07.frame.data.ReturnError;
import com.bobandata.iot.dlt645_07.frame.util.CheckSum;
import com.bobandata.iot.dlt645_07.frame.util.PostalAddress;
import com.bobandata.iot.dlt645_07.util.DataTypeConst;
import com.bobandata.iot.dlt645_07.util.FinalConst;
import com.bobandata.iot.dlt645_07.util.FunCodeConst;
import com.bobandata.iot.transport.frame.IFrame;
import com.bobandata.iot.transport.util.ConvertUtil;
import com.bobandata.iot.transport.util.HexUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: liutuo
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 19:58 2018/9/1.
 */
public class Dlt645Frame implements IFrame {

    private static final Logger logger = LoggerFactory.getLogger(Dlt645MasterProtocol.class);
    private ByteBuf buffer;
    private String hexDump;
    private PostalAddress postalAddress;
    private byte funCode;
    private DataArea dataArea;
    private CheckSum checkSum;


    /**
      * @Description: 构造消息解码框架
      * @Param: buffer字节流
      * @Throws:
      * @Return:
      * @Author:liutuo
      * @Date:11:12 2018/9/2
      *
      */
    public Dlt645Frame(ByteBuf buffer){
        this.buffer = buffer;
        this.hexDump = ByteBufUtil.hexDump(this.buffer);
        this.postalAddress = new PostalAddress();
        this.checkSum = new CheckSum();
        try {
            this.decode();
        } catch (Exception e) {
            logger.error("E006-可变帧长解码异常！2"+e);
        }
    }

    /**
      * @Description: 构造消息编码框架
      * @Param: 电表通讯地址、控制域、数据类型
      * @Throws:
      * @Return:
      * @Author:liutuo
      * @Date:11:04 2018/9/2
      *
      */
    public Dlt645Frame(Long postalAddress, byte funCode, DataArea dataArea){
        //构造一个空的可变的缓冲区
        this.buffer = Unpooled.buffer(16, 512);
        this.postalAddress = new PostalAddress(postalAddress);
        this.funCode = funCode;
        this.dataArea = dataArea;
        this.checkSum = new CheckSum();
        try {
            this.encode();
        } catch (Exception e) {
            logger.error("E006-可变帧长解码异常！3");
            e.printStackTrace();
        }
    }

    @Override
    public String toHexString() {
        byte[] hexBytes = HexUtils.decodeHex(this.hexDump.toCharArray());
        return ConvertUtil.bytes2hex(hexBytes);
    }

    @Override
    public String toExplain() {
        byte[] hexBytes = HexUtils.decodeHex(this.hexDump.toCharArray());
        return ConvertUtil.bytes2hex(hexBytes);
    }

    @Override
    public int encode() throws Exception {
        //写入开始字节
        this.buffer.writeByte(FinalConst.STARTBYTE);
        //写入电表通讯地址编码字节
        postalAddress.encode(this.buffer);
        //写入开始字节
        this.buffer.writeByte(FinalConst.STARTBYTE);
        //写入功能码字节
        this.buffer.writeByte(funCode);
        //写入用户数据部分编码字节
        dataArea.encode(this.buffer);
        //写入校验和字节
        checkSum.encode(this.buffer);
        //写入结束字节
        this.buffer.writeByte(FinalConst.ENDBYTE);
        this.hexDump = ByteBufUtil.hexDump(this.buffer);
        return 0;
    }

    @Override
    public int decode() throws Exception {
        //读消息头
        buffer.readByte();
        postalAddress.decode(buffer);
        buffer.readByte();
        funCode = buffer.readByte();
        switch (funCode){
            //判断是不是请求报文
            case FunCodeConst.REQ_METER:
                dataArea = new DataRequestArea();
                dataArea.decode(buffer);
                break;
            //判断是不是正常响应报文
            case FunCodeConst.RES_NORMAL:
                dataArea = new DataReturnArea();
                dataArea.decode(buffer);
                break;
            case FunCodeConst.RES_ERROR:
                dataArea = new ReturnError();
                dataArea.decode(buffer);
            default:
                logger.error("---------------Not Found Type----------------");
                break;
        }
        //解析校验和
        checkSum.decode(buffer);
        //读结束字符
        buffer.readByte();
        return 0;
    }

    @Override
    public ByteBuf getBuffer() {
        return this.buffer;
    }

    public void setBuffer(ByteBuf buffer) {
        this.buffer = buffer;
    }

    public PostalAddress getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(PostalAddress postalAddress) {
        this.postalAddress = postalAddress;
    }

    public byte getFunCode() {
        return funCode;
    }

    public void setFunCode(byte funCode) {
        this.funCode = funCode;
    }

    public DataArea getDataArea() {
        return dataArea;
    }

    public void setDataArea(DataArea dataArea) {
        this.dataArea = dataArea;
    }

    public CheckSum getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(CheckSum checkSum) {
        this.checkSum = checkSum;
    }

    public static void main(String[] args){
        /*String hexString = "68 08 00 00 00 00 00 68 91 07 33 33 37 35 BA 59 B3 08 16";
        byte[] content = HexUtils.decodeHex(hexString.replaceAll(" ", "").toCharArray());
        ByteBuf buffer = Unpooled.copiedBuffer(content);
        Dlt645Frame dlt645Frame = new Dlt645Frame(buffer);
        System.out.println(dlt645Frame.toString());*/
        DataRequestArea dataRequestArea = new DataRequestArea(DataTypeConst.REVERSE_ACTIVE_ENERGY);
        Dlt645Frame dlt645Frame = new Dlt645Frame(8l, FunCodeConst.REQ_METER, dataRequestArea);
        logger.info("dtu85发送正向有功电能量报文:" + dlt645Frame.toHexString());
        dataRequestArea = new DataRequestArea(DataTypeConst.REVERSE_REACTIVE_ENERGY);
        dlt645Frame = new Dlt645Frame(8l, FunCodeConst.REQ_METER, dataRequestArea);
        logger.info("dtu85发送正向无功电能量报文:" + dlt645Frame.toHexString());
    }
}
