package com.bobandata.iot.dlt645_07.execute;

import com.bobandata.iot.dlt645_07.connector.Dlt645MasterProtocol;
import com.bobandata.iot.dlt645_07.frame.Dlt645Frame;
import com.bobandata.iot.dlt645_07.frame.data.DataRequestArea;
import com.bobandata.iot.dlt645_07.frame.data.DataReturnArea;
import com.bobandata.iot.dlt645_07.frame.data.ReturnError;
import com.bobandata.iot.dlt645_07.util.DataTypeConst;
import com.bobandata.iot.dlt645_07.util.FunCodeConst;
import com.bobandata.iot.entity.his.HisDayDemand;
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
 * @Date: 2019/1/21 16:02
 * @Description:
 */

public class ExecuteDayDemand implements ITaskExecutor {
    private static final Logger logger = LoggerFactory.getLogger(ExecuteDayDemand.class);
    private TaskParam taskParam;
    private RxtxSocketAdapter channel;
    private String meterCode;
    private HisDayDemand hisDayDemand;
    private List<IData> iDatas = new ArrayList<IData>();

    @Override
    public void execute(ITaskParam iTaskParam) throws Exception {
        this.taskParam = (TaskParam) iTaskParam;
        this.channel = (RxtxSocketAdapter) taskParam.getChannel();
        List<String> meterCodes = taskParam.getList();
        for (String meterCode : meterCodes) {
            this.meterCode=meterCode;
            this.hisDayDemand = new HisDayDemand();

            this.obtainBean(DataTypeConst.DAY_POSITIVE_ACTIVE_MAX_DEMAND_AND_TIME);
            this.obtainBean(DataTypeConst.DAY_POSITIVE_REACTIVE_MAX_DEMAND_AND_TIME);
            this.obtainBean(DataTypeConst.DAY_REVERSE_ACTIVE_MAX_DEMAND_AND_TIME);
            this.obtainBean(DataTypeConst.DAY_REVERSE_REACTIVE_MAX_DEMAND_AND_TIME);

            //封装数据结果
            hisDayDemand.setMeterId(Long.parseLong(meterCode));
            hisDayDemand.setOccurTime(new Timestamp((new Date()).getTime()));
            iDatas.add(hisDayDemand);
        }
    }

    @Override
    public void save() {
        HttpDataManager.save(HisDayDemand.class,iDatas);
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
                case DAY_POSITIVE_ACTIVE_MAX_DEMAND_AND_TIME:
                    hisDayDemand.setPapRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisDayDemand.setPapValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.0001);
                    hisDayDemand.setPapTime(dataReturnArea.getDataValue().getTimestamp());
                    break;
                case DAY_POSITIVE_REACTIVE_MAX_DEMAND_AND_TIME:
                    hisDayDemand.setRapRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisDayDemand.setRapValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.0001);
                    hisDayDemand.setRapTime(dataReturnArea.getDataValue().getTimestamp());
                    break;
                case DAY_REVERSE_ACTIVE_MAX_DEMAND_AND_TIME:
                    hisDayDemand.setPrpRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisDayDemand.setPrpValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.0001);
                    hisDayDemand.setPrpTime(dataReturnArea.getDataValue().getTimestamp());
                    break;
                case DAY_REVERSE_REACTIVE_MAX_DEMAND_AND_TIME:
                    hisDayDemand.setRrpValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    hisDayDemand.setRrpValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.0001);
                    hisDayDemand.setRrpTime(dataReturnArea.getDataValue().getTimestamp());
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