package com.bobandata.iot.dlt645_07.execute;

import com.bobandata.iot.dlt645_07.connector.Dlt645MasterProtocol;
import com.bobandata.iot.dlt645_07.frame.Dlt645Frame;
import com.bobandata.iot.dlt645_07.frame.data.DataRequestArea;
import com.bobandata.iot.dlt645_07.frame.data.DataReturnArea;
import com.bobandata.iot.dlt645_07.frame.data.ReturnError;
import com.bobandata.iot.dlt645_07.util.DataTypeConst;
import com.bobandata.iot.dlt645_07.util.FunCodeConst;
import com.bobandata.iot.entity.his.HisMeas;
import com.bobandata.iot.entity.his.IData;
import com.bobandata.iot.transport.connector.RxtxSocketAdapter;
import com.bobandata.iot.transport.protocol.ITaskExecutor;
import com.bobandata.iot.transport.protocol.ITaskParam;
import com.bobandata.iot.transport.util.TaskParam;
import com.bobandata.iot.util.HttpDataManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: lizhipeng
 * @Company: 上海博般数据技术有限公司
 * @Date: 2019/1/21 16:43
 * @Description:
 */

public class ExecuteMeas implements ITaskExecutor {
    private static final Logger logger = LoggerFactory.getLogger(ExecuteMeas.class);
    private TaskParam taskParam;
    private RxtxSocketAdapter channel;
    private String meterCode;
    private HisMeas hisMeas;
    private List<IData> iDatas = new ArrayList<IData>();

    @Override
    public void execute(ITaskParam iTaskParam) throws Exception {
        this.taskParam = (TaskParam) iTaskParam;
        this.channel = (RxtxSocketAdapter) taskParam.getChannel();
        List<String> meterCodes = taskParam.getList();
        for (String meterCode : meterCodes) {
            this.meterCode=meterCode;
            this.hisMeas = new HisMeas();

            /**电流 */
            this.obtainBean(DataTypeConst.A_A);
            this.obtainBean(DataTypeConst.B_A);
            this.obtainBean(DataTypeConst.C_A);
            /** 电压*/
            this.obtainBean(DataTypeConst.A_V);
            this.obtainBean(DataTypeConst.B_V);
            this.obtainBean(DataTypeConst.C_V);
            /** 有功功率*/
            this.obtainBean(DataTypeConst.A_ACTIVE_POWER);
            this.obtainBean(DataTypeConst.B_ACTIVE_POWER);
            this.obtainBean(DataTypeConst.C_ACTIVE_POWER);
            this.obtainBean(DataTypeConst.SUM_ACTIVE_POWER);
            /** 无功功率*/
            this.obtainBean(DataTypeConst.A_REACTIVE_POWER);
            this.obtainBean(DataTypeConst.B_REACTIVE_POWER);
            this.obtainBean(DataTypeConst.C_REACTIVE_POWER);
            this.obtainBean(DataTypeConst.SUM_REACTIVE_POWER);
            /** 总功率因素*/
            this.obtainBean(DataTypeConst.SUM_POWER_FACTOR);
            /** 频率*/
            this.obtainBean(DataTypeConst.FREQUENCY);
            
            //封装数据结果
            hisMeas.setLastRefreshTime(new Timestamp((new Date()).getTime()));
            hisMeas.setMeterId(Long.parseLong(meterCode));
            hisMeas.setOccurTime(new Timestamp((new Date()).getTime()));
            iDatas.add(hisMeas);
        }
    }

    @Override
    public void save() {
        HttpDataManager.save(HisMeas.class,iDatas);
    }

    private void obtainBean(DataTypeConst dataTypeConst) {
        DataRequestArea dataRequestArea =new DataRequestArea(dataTypeConst);
        //封装请求对象
        Dlt645Frame dlt645Frame = new Dlt645Frame(Long.parseLong(meterCode), FunCodeConst.REQ_METER, dataRequestArea);
        //发送报文,并获得接收报文
        Dlt645Frame result = (Dlt645Frame) Dlt645MasterProtocol.sendMsg(dlt645Frame, channel);
        byte funCode = result.getFunCode();

        if (funCode == FunCodeConst.RES_NORMAL) {
            DataReturnArea dataReturnArea = (DataReturnArea) result.getDataArea();
            switch (dataTypeConst) {
                /**电流*/
                case A_A:
                    hisMeas.setIaRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisMeas.setIaValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.001);
                    break;
                case B_A:
                    hisMeas.setIbRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisMeas.setIbValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.001);
                    break;
                case C_A:
                    hisMeas.setIcRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisMeas.setIcValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.001);
                    break;
                 /**电压*/
                case A_V:
                    hisMeas.setUaRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisMeas.setUaValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.1);
                    break;
                case B_V:
                    hisMeas.setUbRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisMeas.setUbValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.1);
                    break;
                case C_V:
                    hisMeas.setUcRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisMeas.setUcValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.1);
                    break;
                /** 有功功率*/
                case A_ACTIVE_POWER:
                    hisMeas.setPaRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisMeas.setPaValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.0001);
                    break;
                case B_ACTIVE_POWER:
                    hisMeas.setPbRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisMeas.setPbValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.0001);
                    break;
                case C_ACTIVE_POWER:
                    hisMeas.setPcRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisMeas.setPcValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.0001);
                    break;
                case SUM_ACTIVE_POWER:
                    hisMeas.setpRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisMeas.setpValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.0001);
                    break;
                /** 无功功率*/
                case A_REACTIVE_POWER:
                    hisMeas.setQaRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisMeas.setQaValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.0001);
                    break;
                case B_REACTIVE_POWER:
                    hisMeas.setQbRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisMeas.setQbValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.0001);
                    break;
                case C_REACTIVE_POWER:
                    hisMeas.setQcRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisMeas.setQcValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.0001);
                    break;
                case SUM_REACTIVE_POWER:
                    hisMeas.setqRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisMeas.setqValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.0001);
                    break;
                /** 总功率因素*/
                case SUM_POWER_FACTOR:
                    hisMeas.setFactorRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisMeas.setFactorValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.001);
                    break;
                /** 频率*/
                case FREQUENCY:
                    hisMeas.setFreqRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisMeas.setFreqValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.01);
                    break;
                default:
                    logger.error("---------------Not Found Type----------------");
                    break;
            }
        } else if (funCode == FunCodeConst.RES_ERROR) {
            ReturnError returnError = (ReturnError) result.getDataArea();
            logger.info(returnError.getErrorType().name());
        }
    }
}