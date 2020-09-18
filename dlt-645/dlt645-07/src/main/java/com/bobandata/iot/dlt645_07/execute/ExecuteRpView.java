package com.bobandata.iot.dlt645_07.execute;

import com.bobandata.iot.dlt645_07.connector.Dlt645MasterProtocol;
import com.bobandata.iot.dlt645_07.frame.Dlt645Frame;
import com.bobandata.iot.dlt645_07.frame.data.DataRequestArea;
import com.bobandata.iot.dlt645_07.frame.data.DataReturnArea;
import com.bobandata.iot.dlt645_07.frame.data.ReturnError;
import com.bobandata.iot.dlt645_07.util.DataTypeConst;
import com.bobandata.iot.dlt645_07.util.FunCodeConst;
import com.bobandata.iot.entity.his.HisRpView;
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
 * @Date: 2019/1/21 14:00
 * @Description:
 */

public class ExecuteRpView implements ITaskExecutor{
    private static final Logger logger = LoggerFactory.getLogger(ExecuteRpView.class);
    private TaskParam taskParam;
    private RxtxSocketAdapter channel;
    private String meterCode;
    private HisRpView hisRpView;
    private List<IData> iDatas = new ArrayList<IData>();

    @Override
    public void execute(ITaskParam iTaskParam) throws Exception {
        this.taskParam = (TaskParam) iTaskParam;
        this.channel = (RxtxSocketAdapter) taskParam.getChannel();
        List<String> meterCodes = taskParam.getList();
        for (String meterCode : meterCodes) {
            this.meterCode=meterCode;
            this.hisRpView = new HisRpView();

            this.obtainBean(DataTypeConst.SUM_FIRST_REACTIVE_ENERGY);
            this.obtainBean(DataTypeConst.SUM_SECOND_REACTIVE_ENERGY);
            this.obtainBean(DataTypeConst.SUM_THIRD_REACTIVE_ENERGY);
            this.obtainBean(DataTypeConst.SUM_FOURTH_REACTIVE_ENERGY);

            //封装数据结果
            hisRpView.setLastRefreshTime(new Timestamp((new Date()).getTime()));
            hisRpView.setMeterId(Long.parseLong(meterCode));
            hisRpView.setOccurTime(new Timestamp((new Date()).getTime()));
            iDatas.add(hisRpView);
        }
    }

    @Override
    public void save() {
        HttpDataManager.save(HisRpView.class,iDatas);
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
                case SUM_FIRST_REACTIVE_ENERGY:
                    hisRpView.setRp1RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisRpView.setRp1Value(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.01);
                    break;
                case SUM_SECOND_REACTIVE_ENERGY:
                    hisRpView.setRp2RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisRpView.setRp2Value(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.01);
                    break;
                case SUM_THIRD_REACTIVE_ENERGY:
                    hisRpView.setRp3RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisRpView.setRp3Value(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.01);
                    break;
                case SUM_FOURTH_REACTIVE_ENERGY:
                    hisRpView.setRp4RawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisRpView.setRp4Value(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.01);
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