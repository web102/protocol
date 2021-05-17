package com.bobandata.iot.xb102.frame.format;

import com.bobandata.iot.transport.frame.IFrame;
import com.bobandata.iot.transport.util.ConvertUtil;
import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.xb102.frame.asdu.type.*;
import com.bobandata.iot.xb102.frame.util.VariableLengthCheckSum;
import com.bobandata.iot.xb102.frame.asdu.Asdu;
import com.bobandata.iot.xb102.frame.controldomain.ControlDomain;
import com.bobandata.iot.xb102.frame.controldomain.ControlDomain_C;
import com.bobandata.iot.xb102.frame.util.LinkAddress;
import com.bobandata.iot.xb102.frame.util.VariableLengthHead;
import com.bobandata.iot.xb102.util.Ti;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class VariableLengthFrame implements IFrame {
    private static final Logger logger = LoggerFactory.getLogger(VariableLengthFrame.class);
    private ByteBuf buffer;
    private String hexDump;
    private VariableLengthHead variableLengthHead;
    private ControlDomain controlDomain;
    private LinkAddress linkAddress;
    private Asdu asdu;
    private VariableLengthCheckSum variableLengthCheckSum;
    private byte endByte = 0x16;

    public VariableLengthFrame(ByteBuf buffer, ControlDomain controlDomain) {
        this.buffer = buffer;
        this.hexDump = ByteBufUtil.hexDump(this.buffer);
        this.variableLengthHead = new VariableLengthHead();
        this.controlDomain = controlDomain;
        this.linkAddress = new LinkAddress();
        this.variableLengthCheckSum = new VariableLengthCheckSum();
        try {
            this.decode();
        } catch (Exception e) {
            logger.error("E006-可变帧长解码异常！",e);
            e.printStackTrace();
        }
    }

    public VariableLengthFrame(ControlDomain controlDomain, LinkAddress linkAddress, Asdu asdu) {
        this.buffer = Unpooled.buffer(200, 256);
        this.variableLengthHead = new VariableLengthHead(asdu.getAsduLength());
        this.controlDomain = controlDomain;
        this.linkAddress = linkAddress;
        this.asdu = asdu;
        this.variableLengthCheckSum = new VariableLengthCheckSum();
        try {
            this.encode();
        } catch (Exception e) {
            logger.error("E005-可变帧长编码异常！",e);
        }
    }
    public int decode()
            throws Exception {
        byte type = this.buffer.getByte(7);
        byte cot = this.buffer.getByte(9);

        this.variableLengthHead.decode(this.buffer);

        this.controlDomain.decode(this.buffer);

        this.linkAddress.decode(this.buffer);

        asduDecode(type,cot, this.buffer);

        this.variableLengthCheckSum.decode(this.buffer);

        this.buffer.readByte();
        return 0;
    }

    public int encode() throws Exception {
        this.variableLengthHead.encode(this.buffer);

        this.controlDomain.encode(this.buffer);

        this.linkAddress.encode(this.buffer);

        this.asdu.encode(this.buffer);

        this.variableLengthCheckSum.encode(this.buffer);

        this.buffer.writeByte(this.endByte);

        this.hexDump = ByteBufUtil.hexDump(this.buffer);
        return 0;
    }

    public void asduDecode(int type,byte cot, ByteBuf buffer) throws Exception {
        type=type&0xff;
        if (cot == 8) {
            NullAsdu nullAsdu = new NullAsdu();
            nullAsdu.decode(buffer);
            setAsdu(nullAsdu);
            return;
        }

            switch (type){
                case Ti.singleInfo:
                case Ti.getTerminalTime:
                    NullAsdu nullAsdu = new NullAsdu();
                    nullAsdu.decode(buffer);
                    setAsdu(nullAsdu);
                    break;
                case Ti.timeLimitSingleInfo:
                    TimeLimitReqAsdu timeLimitReqAsdu = new TimeLimitReqAsdu();
                    timeLimitReqAsdu.decode(buffer);
                    setAsdu(timeLimitReqAsdu);
                    break;
                case Ti.data:
                case Ti.tariff:
                    DataReqAsdu dataReqAsdu = new DataReqAsdu();
                    dataReqAsdu.decode(buffer);
                    setAsdu(dataReqAsdu);

                    break;
                    //设置时间阀值的返回类型与回复相等
                //时间同步的返回类型与回复相等
                case Ti.timeAsyn:
                    if(this.controlDomain instanceof ControlDomain_C){
                        LongTimeAsdu time7ByteAsdu = new LongTimeAsdu();
                        time7ByteAsdu.decode(buffer);
                        setAsdu(time7ByteAsdu);
                        break;
                    }else {
                        LongTimeAsdu time7ByteAsdu1 = new LongTimeAsdu();
                        time7ByteAsdu1.decode(buffer);
                        setAsdu(time7ByteAsdu1);
                    }break;

                case Ti.returnTerminalTime:
                    LongTimeAsdu time7ByteAsdu1 = new LongTimeAsdu();
                    time7ByteAsdu1.decode(buffer);
                    setAsdu(time7ByteAsdu1);
                    break;
                case Ti.returnSingleInfo:
                    ListAsdu listAsdu = new ListAsdu();
                    listAsdu.decode(buffer);
                    setAsdu(listAsdu);
                    break;
                case Ti.returnData:
                case Ti.returnTariff:
                    DataAsdu dataAsdu1 = new DataAsdu();
                    dataAsdu1.decode(buffer);
                    setAsdu(dataAsdu1);
                    break;
                default:
                    logger.error("---------------Not Found Type----------------");
                    break;

            }

    }

    public String toExplain() {
        //可变帧长消息头
        String variableLengthHead = this.variableLengthHead.toExplain();
        //控制域
        String controlDomain = this.controlDomain.toExplain();
        //链路地址
        String linkAddress = this.linkAddress.toExplain();
        //asdu
        String asdu = this.asdu.toExplain();
        //校验位
        String checkSum = this.variableLengthCheckSum.toExplain();
        //结束符
        String endbyte = "结束符:16";
        return toHexString()+" \n"+ variableLengthHead +","+ controlDomain +","+ linkAddress +"\n"+ asdu +"\n"+ checkSum +","+ endbyte;
    }

    public String toHexString() {
        byte[] hexBytes = HexUtils.decodeHex(this.hexDump.toCharArray());
        return ConvertUtil.bytes2hex(hexBytes);
    }

    public ByteBuf getBuffer() {
        return this.buffer;
    }
    public VariableLengthHead getVariableLengthHead() {
        return this.variableLengthHead;
    }
    public void setVariableLengthHead(VariableLengthHead variableLengthHead) {this.variableLengthHead = variableLengthHead;}
    public ControlDomain getControlDomain() {
        return this.controlDomain;
    }
    public void setControlDomain(ControlDomain controlDomain) {
        this.controlDomain = controlDomain;
    }
    public LinkAddress getLinkAddress() {
        return this.linkAddress;
    }
    public void setLinkAddress(LinkAddress linkAddress) {
        this.linkAddress = linkAddress;
    }
    public Asdu getAsdu() {
        return this.asdu;
    }
    public void setAsdu(Asdu asdu) {
        this.asdu = asdu;
    }
    public VariableLengthCheckSum getFixedLengthCheckSum() {
        return this.variableLengthCheckSum;
    }
    public void setFixedLengthCheckSum(VariableLengthCheckSum variableLengthCheckSum) {this.variableLengthCheckSum = variableLengthCheckSum;}
}

