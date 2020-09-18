package com.bobandata.iot.dlt645_07.execute;

import com.bobandata.iot.dlt645_07.connector.Dlt645MasterProtocol;
import com.bobandata.iot.dlt645_07.frame.Dlt645Frame;
import com.bobandata.iot.dlt645_07.frame.data.DataRequestArea;
import com.bobandata.iot.dlt645_07.frame.data.DataReturnArea;
import com.bobandata.iot.dlt645_07.frame.data.ReturnError;
import com.bobandata.iot.dlt645_07.util.DataTypeConst;
import com.bobandata.iot.dlt645_07.util.FunCodeConst;
import com.bobandata.iot.entity.his.HisTariff;
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
 * @Date: 2019/1/21 15:45
 * @Description:
 */

public class ExecuteTariff implements ITaskExecutor {
    private static final Logger logger = LoggerFactory.getLogger(ExecuteTariff.class);
    private TaskParam taskParam;
    private RxtxSocketAdapter channel;
    private String meterCode;
    private HisTariff hisTariff;
    private List<IData> iDatas = new ArrayList<IData>();

    @Override
    public void execute(ITaskParam iTaskParam) throws Exception {
        this.taskParam = (TaskParam) iTaskParam;
        this.channel = (RxtxSocketAdapter) taskParam.getChannel();
        List<String> meterCodes = taskParam.getList();
        for (String meterCode : meterCodes) {
            this.meterCode=meterCode;
            this.hisTariff = new HisTariff();

            this.obtainBean(DataTypeConst.DAY_POSITIVE_ACTIVE_ENERGY);
            this.obtainBean(DataTypeConst.DAY_REVERSE_ACTIVE_ENERGY);
            this.obtainBean(DataTypeConst.DAY_POSITIVE_REACTIVE_ENERGY);
            this.obtainBean(DataTypeConst.DAY_REVERSE_REACTIVE_ENERGY);

            //封装数据结果
            hisTariff.setLastRefreshTime(new Timestamp((new Date()).getTime()));
            hisTariff.setMeterId(Long.parseLong(meterCode));
            hisTariff.setOccurTime(new Timestamp((new Date()).getTime()));
            iDatas.add(hisTariff);
        }
    }

    @Override
    public void save() {
        HttpDataManager.save(HisTariff.class,iDatas);
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
                case DAY_POSITIVE_ACTIVE_ENERGY:
                    this.hisTariff.setPap1RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue1()));
                    this.hisTariff.setPap1RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue1()));
                    this.hisTariff.setPap2RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue2()));
                    this.hisTariff.setPap3RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue3()));
                    this.hisTariff.setPap4RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue4()));
                    this.hisTariff.setPap5RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue5()));

                    this.hisTariff.setPap1Value(Double.parseDouble(dataReturnArea.getDataValue().getValue1()) * 0.01);
                    this.hisTariff.setPap1Value(Double.parseDouble(dataReturnArea.getDataValue().getValue1()) * 0.01);
                    this.hisTariff.setPap2Value(Double.parseDouble(dataReturnArea.getDataValue().getValue2()) * 0.01);
                    this.hisTariff.setPap3Value(Double.parseDouble(dataReturnArea.getDataValue().getValue3()) * 0.01);
                    this.hisTariff.setPap4Value(Double.parseDouble(dataReturnArea.getDataValue().getValue4()) * 0.01);
                    this.hisTariff.setPap5Value(Double.parseDouble(dataReturnArea.getDataValue().getValue5()) * 0.01);
                    break;
                case DAY_REVERSE_ACTIVE_ENERGY:
                    this.hisTariff.setRap1RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue1()));
                    this.hisTariff.setRap2RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue2()));
                    this.hisTariff.setRap3RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue3()));
                    this.hisTariff.setRap4RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue4()));
                    this.hisTariff.setRap5RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue5()));

                    this.hisTariff.setRap1Value(Double.parseDouble(dataReturnArea.getDataValue().getValue1()) * 0.01);
                    this.hisTariff.setRap2Value(Double.parseDouble(dataReturnArea.getDataValue().getValue2()) * 0.01);
                    this.hisTariff.setRap3Value(Double.parseDouble(dataReturnArea.getDataValue().getValue3()) * 0.01);
                    this.hisTariff.setRap4Value(Double.parseDouble(dataReturnArea.getDataValue().getValue4()) * 0.01);
                    this.hisTariff.setRap5Value(Double.parseDouble(dataReturnArea.getDataValue().getValue5()) * 0.01);
                    break;
                case DAY_POSITIVE_REACTIVE_ENERGY:
                    this.hisTariff.setPrp1RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue1()));
                    this.hisTariff.setPrp2RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue2()));
                    this.hisTariff.setPrp3RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue3()));
                    this.hisTariff.setPrp4RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue4()));
                    this.hisTariff.setPrp5RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue5()));

                    this.hisTariff.setPrp1Value(Double.parseDouble(dataReturnArea.getDataValue().getValue1()) * 0.01);
                    this.hisTariff.setPrp2Value(Double.parseDouble(dataReturnArea.getDataValue().getValue2()) * 0.01);
                    this.hisTariff.setPrp3Value(Double.parseDouble(dataReturnArea.getDataValue().getValue3()) * 0.01);
                    this.hisTariff.setPrp4Value(Double.parseDouble(dataReturnArea.getDataValue().getValue4()) * 0.01);
                    this.hisTariff.setPrp5Value(Double.parseDouble(dataReturnArea.getDataValue().getValue5()) * 0.01);
                    break;
                case DAY_REVERSE_REACTIVE_ENERGY:
                    this.hisTariff.setRrp1RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue1()));
                    this.hisTariff.setRrp2RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue2()));
                    this.hisTariff.setRrp3RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue3()));
                    this.hisTariff.setRrp4RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue4()));
                    this.hisTariff.setRrp5RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue5()));

                    this.hisTariff.setRrp1Value(Double.parseDouble(dataReturnArea.getDataValue().getValue1()) * 0.01);
                    this.hisTariff.setRrp2Value(Double.parseDouble(dataReturnArea.getDataValue().getValue2()) * 0.01);
                    this.hisTariff.setRrp3Value(Double.parseDouble(dataReturnArea.getDataValue().getValue3()) * 0.01);
                    this.hisTariff.setRrp4Value(Double.parseDouble(dataReturnArea.getDataValue().getValue4()) * 0.01);
                    this.hisTariff.setRrp5Value(Double.parseDouble(dataReturnArea.getDataValue().getValue5()) * 0.01);
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