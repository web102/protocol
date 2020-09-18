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
                case Ti.factory:
                case Ti.singleInfo:
                case Ti.getTerminalTime:
                case Ti.getDeviation:
                    NullAsdu nullAsdu = new NullAsdu();
                    nullAsdu.decode(buffer);
                    setAsdu(nullAsdu);
                    break;
                case Ti.timeLimitSingleInfo:
                    TimeLimitReqAsdu timeLimitReqAsdu = new TimeLimitReqAsdu();
                    timeLimitReqAsdu.decode(buffer);
                    setAsdu(timeLimitReqAsdu);
                    break;
                case Ti.billView:
                case Ti.cycleBillView:
                case Ti.view:
                case Ti.cycleView:
                case Ti.rpView:
                case Ti.tariff:
                case Ti.dayView:
                case Ti.monthView:
                case Ti.dayRpView:
                case Ti.monthRpView:
                case Ti.dayTariff:
                case Ti.monthTariff:
                case Ti.dayDemand:
                case Ti.monthDemand:
                    DataReqAsdu dataReqAsdu = new DataReqAsdu();
                    dataReqAsdu.decode(buffer);
                    setAsdu(dataReqAsdu);
                    break;
                    //设置时间阀值的返回类型与回复相等
                case Ti.setDeviation:
                    if(this.controlDomain instanceof ControlDomain_C){
                        TwoByteAsdu twoByteAsdu = new TwoByteAsdu();
                        twoByteAsdu.decode(buffer);
                        setAsdu(twoByteAsdu);
                    }else {
                        LongTimeAsdu time7ByteAsdu1 = new LongTimeAsdu();
                        time7ByteAsdu1.decode(buffer);
                        setAsdu(time7ByteAsdu1);
                    }break;
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

                case Ti.returnFactory:
                    FactoryAsdu MPAsdu = new FactoryAsdu();
                    MPAsdu.decode(buffer);
                    setAsdu(MPAsdu);
                    break;
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
                case Ti.returnBillView:
                case Ti.returnCycleBillView:
                case Ti.returnView:
                case Ti.returnCycleView:
                case Ti.returnRpView:
                case Ti.returnTariff:
                case Ti.returnDayView:
                case Ti.returnMonthView:
                case Ti.returnDayRpView:
                case Ti.returnMonthRpView:
                case Ti.returnDayTariff:
                case Ti.returnMonthTariff:
                case Ti.returnDayDemand:
                case Ti.returnMonthDemand:
                    DataAsdu dataAsdu1 = new DataAsdu();
                    dataAsdu1.decode(buffer);
                    setAsdu(dataAsdu1);
                    break;
                case Ti.returnDeviation:
                    TwoByteAsdu twoByteAsdu1 = new TwoByteAsdu();
                    twoByteAsdu1.decode(buffer);
                    setAsdu(twoByteAsdu1);
                    break;
                default:
                    logger.error("---------------Not Found Type----------------");
                    break;

            }

//            if(this.controlDomain instanceof ControlDomain_C){
//                if(cot==8){
//                    NullReqAsdu getTimeAsdu = new NullReqAsdu();
//                    getTimeAsdu.decode(buffer);
//                    this.setAsdu(getTimeAsdu);
//                    return;
//                }
//
//                switch (type){
//                    //电能数据请求
//                    case DataTypeConst.view:            //0x7a	122
//                    case DataTypeConst.dayView:			//0xa2	162
//                    case DataTypeConst.cycleView:		//0x7b	123
//                    case DataTypeConst.monthView:		//0xa3	163
//                    case DataTypeConst.billView:		//0x78	120
//                    case DataTypeConst.cycleBillView:	//0x79	121
//
//                        //费率数据请求
//                    case DataTypeConst.tariff:			//0xa1	161
//                    case DataTypeConst.dayTariff:		//0xa6	166
//                    case DataTypeConst.monthTariff:		//0xa7	167
//
//                        //需量数据请求
//                    case DataTypeConst.dayDemand:		//0xa8	168
//                    case DataTypeConst.monthDemand:		//0xa9	169
//
//                        //四象限无功
//                    case DataTypeConst.rpView:	//0xa0	160
//                    case DataTypeConst.dayRpView:	//0xa4	164
//                    case DataTypeConst.monthRpView:	//0xa5	165
//                        //遥测请求
////			case DataTypeConst.telemeteringData:
//                        NullReqAsdu dataRequestAsdu = new NullReqAsdu();
//                        dataRequestAsdu.decode(buffer);
//                        this.setAsdu(dataRequestAsdu);
//                        break;
//                    case DataTypeConst.timeLimitSingleInfo:
//                        EventRequestAsdu eventAsdu1 = new EventRequestAsdu();
//                        eventAsdu1.decode(buffer);
//                        this.setAsdu(eventAsdu1);
//                        break;
//                    //事件请求
//                    case DataTypeConst.singleInfo:
//                        //获取终端时间请求
//                    case DataTypeConst.getTerminalTime:	//
//                        //厂家信息请求
//                    case DataTypeConst.factory:			//
//                        //读取系统时间差值
//                    case DataTypeConst.getDeviation:	//
//                        NullReqAsdu getTimeAsdu = new NullReqAsdu();
//                        getTimeAsdu.decode(buffer);
//                        this.setAsdu(getTimeAsdu);
//                        break;
//                    //时钟同步请求
//                    case DataTypeConst.timeAsyn:		//
//                        LongTimeAsdu synchClockAsdu = new LongTimeAsdu();
//                        synchClockAsdu.decode(buffer);
//                        this.setAsdu(synchClockAsdu);
//                        break;
//                    //设置时间差值
//                    case DataTypeConst.setDeviation:	//
//                        TwoByteAsdu setDeviationAsdu = new TwoByteAsdu();
//                        setDeviationAsdu.decode(buffer);
//                        this.setAsdu(setDeviationAsdu);
//                        break;
//                }
//            }
//
//            if(this.controlDomain instanceof ControlDomain_M) {
//                switch (type) {
//                    //场站
//                    case DataTypeConst.returnFactory:
//                        ManufacturerProductAsdu factoryInfoAsdu = new ManufacturerProductAsdu();
//                        factoryInfoAsdu.decode(buffer);
//                        this.setAsdu(factoryInfoAsdu);
//                        break;
//                    //获取时间差值应答
//                    case DataTypeConst.returnDeviation:
//                        TwoByteAsdu deviationAsdu = new TwoByteAsdu();
//                        deviationAsdu.decode(buffer);
//                        this.setAsdu(deviationAsdu);
//                        break;
//                    //时间应答
//                    case DataTypeConst.returnTimeAsyn:
//                    case DataTypeConst.returnSetDeviation:
//                    case DataTypeConst.returnTerminalTime:
//                        LongTimeAsdu ertuTimeAsdu = new LongTimeAsdu();
//                        ertuTimeAsdu.decode(buffer);
//                        this.setAsdu(ertuTimeAsdu);
//                        break;
//                    case DataTypeConst.returnView:
//                    case DataTypeConst.returnDayView:
//                    case DataTypeConst.returnCycleView:
//                    case DataTypeConst.returnMonthView:
//                    case DataTypeConst.returnBillView:
//                    case DataTypeConst.returnCycleBillView:
//                        //费率数据请求return
//                    case DataTypeConst.returnTariff:
//                    case DataTypeConst.returnDayTariff:
//                    case DataTypeConst.returnMonthTariff:
//                        //需量数据请求return
//                    case DataTypeConst.returnDayDemand:
//                    case DataTypeConst.returnMonthDemand:
//                        DataAsdu dataAsdu = new DataAsdu();
//                        dataAsdu.decode(buffer);
//                        this.setAsdu(dataAsdu);
//                        break;
//                    //事件请求应答
//                    case DataTypeConst.returnSingleInfo:
//                        DateLimitEventAsdu reventAsdu = new DateLimitEventAsdu();
//                        reventAsdu.decode(buffer);
//                        this.setAsdu(reventAsdu);
//                        break;
//                    default:
//                        break;
//                }
//            }
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

