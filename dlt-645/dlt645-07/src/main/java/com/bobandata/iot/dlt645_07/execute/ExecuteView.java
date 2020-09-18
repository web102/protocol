package com.bobandata.iot.dlt645_07.execute;


import com.bobandata.iot.dlt645_07.connector.Dlt645MasterProtocol;
import com.bobandata.iot.dlt645_07.frame.Dlt645Frame;
import com.bobandata.iot.dlt645_07.frame.data.DataRequestArea;
import com.bobandata.iot.dlt645_07.frame.data.DataReturnArea;
import com.bobandata.iot.dlt645_07.frame.data.ReturnError;
import com.bobandata.iot.dlt645_07.util.DataTypeConst;
import com.bobandata.iot.dlt645_07.util.FunCodeConst;
import com.bobandata.iot.entity.his.HisView;
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
 * @Author: liutuo
 * @Description: 分时底码
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 15:20 2018/9/5.
 */
public class ExecuteView implements ITaskExecutor {
    private static final Logger logger = LoggerFactory.getLogger(ExecuteView.class);

    private TaskParam taskParam;
    private RxtxSocketAdapter channel;
    private String meterCode;
    private HisView hisView;
    private List<IData> iDatas = new ArrayList<IData>();

    @Override
    public void execute(ITaskParam iTaskParam) throws Exception {
        this.taskParam = (TaskParam) iTaskParam;
        this.channel = (RxtxSocketAdapter) taskParam.getChannel();
        List<String> meterCodes = taskParam.getList();

        for (String meterCode :meterCodes) {
            this.meterCode=meterCode;
            hisView = new HisView();

            this.obtainBean(DataTypeConst.POSITIVE_ACTIVE_ENERGY);
            this.obtainBean(DataTypeConst.REVERSE_ACTIVE_ENERGY);
            this.obtainBean(DataTypeConst.POSITIVE_REACTIVE_ENERGY);
            this.obtainBean(DataTypeConst.REVERSE_REACTIVE_ENERGY);
            
            //封装数据结果
            hisView.setLastRefreshTime(new Timestamp((new Date()).getTime()));
            hisView.setMeterId(Long.parseLong(meterCode));
            hisView.setLastRefreshTime(new Timestamp((new Date()).getTime()));
            hisView.setOccurTime(new Timestamp((new Date()).getTime()));
            iDatas.add(hisView);
        }
    }

    @Override
    public void save() {
        HttpDataManager.save(HisView.class,iDatas);
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
                case POSITIVE_ACTIVE_ENERGY:
                    this.hisView.setPapRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    this.hisView.setPapValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.01);
                    break;
                case REVERSE_ACTIVE_ENERGY:
                    this.hisView.setPrpRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    this.hisView.setPrpValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.01);
                    break;
                case POSITIVE_REACTIVE_ENERGY:
                    this.hisView.setRapRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    this.hisView.setRapValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.01);
                    break;
                case REVERSE_REACTIVE_ENERGY:
                    this.hisView.setRapRawValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()));
                    this.hisView.setRapValue(Double.parseDouble(dataReturnArea.getDataValue().getValue()) * 0.01);
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
