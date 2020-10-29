package com.bobandata.iot.zj102.frame.format;

import com.bobandata.iot.transport.frame.IFrame;
import com.bobandata.iot.transport.util.ConvertUtil;
import com.bobandata.iot.transport.util.HexUtils;
import com.bobandata.iot.zj102.frame.asdu.Asdu;
import com.bobandata.iot.zj102.frame.asdu.type.*;
import com.bobandata.iot.zj102.frame.controldomain.ControlDomain;
import com.bobandata.iot.zj102.frame.util.LinkAddress;
import com.bobandata.iot.zj102.frame.util.VariableLengthCheckSum;
import com.bobandata.iot.zj102.frame.util.VariableLengthHead;
import com.bobandata.iot.zj102.util.Ti;
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
            logger.error("E006-可变帧长解码异常！1");
            e.printStackTrace();
        }
    }

    public VariableLengthFrame(ControlDomain controlDomain, LinkAddress linkAddress, Asdu asdu) {
        this.buffer = Unpooled.buffer(200, 512);
        this.variableLengthHead = new VariableLengthHead(asdu.getAsduLength());
        this.controlDomain = controlDomain;
        this.linkAddress = linkAddress;
        this.asdu = asdu;
        this.variableLengthCheckSum = new VariableLengthCheckSum();
        try {
            this.encode();
        } catch (Exception e) {
            logger.error("E005-可变帧长编码异常！");
        }
    }

    public int decode()
            throws Exception {
        byte type = this.buffer.getByte(7);
        byte cot = this.buffer.getByte(9);

        this.variableLengthHead.decode(this.buffer);

        this.controlDomain.decode(this.buffer);

        this.linkAddress.decode(this.buffer);

        asduDecode(type, cot, this.buffer);

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

    public void asduDecode(int type, byte cot, ByteBuf buffer) throws Exception {
        type = type & 0xff;
        if (cot == 8) {
            NullAsdu nullAsdu = new NullAsdu();
            nullAsdu.decode(buffer);
            setAsdu(nullAsdu);
            return;
        }

        switch (Ti.findByResponse(type)) {
            case FACTORY:
                FactoryAsdu MPAsdu = new FactoryAsdu();
                MPAsdu.decode(buffer);
                setAsdu(MPAsdu);
                return;
            case GETTERMINALTIME:
                LongTimeAsdu time7ByteAsdu1 = new LongTimeAsdu();
                time7ByteAsdu1.decode(buffer);
                setAsdu(time7ByteAsdu1);
                return;
            case SINGLEINFO:
            case TIMELIMITSINGLEINFO:
                ListAsdu listAsdu = new ListAsdu();
                listAsdu.decode(buffer);
                setAsdu(listAsdu);
                return;
            case GET_DEVIATION:
            case SET_DEVIATION:
                TwoByteAsdu twoByteAsdu1 = new TwoByteAsdu();
                twoByteAsdu1.decode(buffer);
                setAsdu(twoByteAsdu1);
                return;
            case VIEW:
            case DAY_VIEW:
            case MONTH_VIEW:

            case FIRST_VIEW     :
            case FIRST_ADDR_VIEW:
            case LIMIT_VIEW     :
            case LIMIT_ADDR_VIEW:

            case CYCLEVIEW:
            case FIRST_CYCLEVIEW     :
            case FIRST_ADDR_CYCLEVIEW:
            case LIMIT_CYCLEVIEW     :
            case LIMIT_ADDR_CYCLEVIEW:

            case BILLVIEW:
            case FIRST_BILLVIEW     :
            case FIRST_ADDR_BILLVIEW:
            case LIMIT_BILLVIEW     :
            case LIMIT_ADDR_BILLVIEW:

            case CYCLEBILLVIEW:
            case FIRST_CYCLEBILLVIEW:
            case FIRST_ADDR_CYCLEBILLVIEW:
            case LIMIT_CYCLEBILLVIEW     :
            case LIMIT_ADDR_CYCLEBILLVIEW:

            case RPVIEW:
            case DAY_RPVIEW:
            case MONTH_RPVIEW:

            case TARIFF:
            case DAY_TARIFF:
            case MONTH_TARIFF:

            case DAY_DEMAND:
            case MONTH_DEMAND:
                DataAsdu dataAsdu1 = new DataAsdu();
                dataAsdu1.decode(buffer);
                setAsdu(dataAsdu1);
                return;
        }

        switch (Ti.findByRequest(type)){
            case FACTORY:
            case SINGLEINFO:
            case GETTERMINALTIME:
            case GET_DEVIATION:
                NullAsdu nullAsdu = new NullAsdu();
                nullAsdu.decode(buffer);
                setAsdu(nullAsdu);
                return;
            case TIMELIMITSINGLEINFO:
                TimeLimitReqAsdu timeLimitReqAsdu = new TimeLimitReqAsdu();
                timeLimitReqAsdu.decode(buffer);
                setAsdu(timeLimitReqAsdu);
                return;

            case VIEW:
            case DAY_VIEW:
            case MONTH_VIEW:

            case FIRST_VIEW     :
            case FIRST_ADDR_VIEW:
            case LIMIT_VIEW     :
            case LIMIT_ADDR_VIEW:

            case CYCLEVIEW:
            case FIRST_CYCLEVIEW     :
            case FIRST_ADDR_CYCLEVIEW:
            case LIMIT_CYCLEVIEW     :
            case LIMIT_ADDR_CYCLEVIEW:

            case BILLVIEW:
            case FIRST_BILLVIEW     :
            case FIRST_ADDR_BILLVIEW:
            case LIMIT_BILLVIEW     :
            case LIMIT_ADDR_BILLVIEW:

            case CYCLEBILLVIEW:
            case FIRST_CYCLEBILLVIEW:
            case FIRST_ADDR_CYCLEBILLVIEW:
            case LIMIT_CYCLEBILLVIEW     :
            case LIMIT_ADDR_CYCLEBILLVIEW:

            case RPVIEW:
            case DAY_RPVIEW:
            case MONTH_RPVIEW:

            case TARIFF:
            case DAY_TARIFF:
            case MONTH_TARIFF:

            case DAY_DEMAND:
            case MONTH_DEMAND:
                DataReqAsdu dataReqAsdu = new DataReqAsdu();
                dataReqAsdu.decode(buffer);
                setAsdu(dataReqAsdu);
                return;
            //设置时间阀值的返回类型与回复相等
            case SET_DEVIATION:
                TwoByteAsdu twoByteAsdu = new TwoByteAsdu();
                twoByteAsdu.decode(buffer);
                setAsdu(twoByteAsdu);
                return;
            //时间同步的返回类型与回复相等
            case TIMEASYN:
                LongTimeAsdu time7ByteAsdu = new LongTimeAsdu();
                time7ByteAsdu.decode(buffer);
                setAsdu(time7ByteAsdu);
                return;
        }

        logger.error("no found identityType :"+type);
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
        return toHexString() + " \n" + variableLengthHead + "," + controlDomain + "," + linkAddress + "\n" + asdu + "\n" + checkSum + "," + endbyte;
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

    public void setVariableLengthHead(VariableLengthHead variableLengthHead) {
        this.variableLengthHead = variableLengthHead;
    }

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

    public void setFixedLengthCheckSum(VariableLengthCheckSum variableLengthCheckSum) {
        this.variableLengthCheckSum = variableLengthCheckSum;
    }
}

