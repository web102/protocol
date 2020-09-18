package com.bobandata.iot.dlt645_07.execute;

import com.bobandata.iot.dlt645_07.connector.Dlt645MasterProtocol;
import com.bobandata.iot.dlt645_07.frame.Dlt645Frame;
import com.bobandata.iot.dlt645_07.frame.data.DataRequestArea;
import com.bobandata.iot.dlt645_07.frame.data.DataReturnArea;
import com.bobandata.iot.dlt645_07.frame.data.ReturnError;
import com.bobandata.iot.dlt645_07.util.DataTypeConst;
import com.bobandata.iot.dlt645_07.util.FunCodeConst;
import com.bobandata.iot.entity.his.HisEvent;
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
 * @Date: 2019/1/22 16:50
 * @Description:
 */

public class ExecuteEvent implements ITaskExecutor {
    private static final Logger logger = LoggerFactory.getLogger(ExecuteEvent.class);
    private TaskParam taskParam;
    private RxtxSocketAdapter channel;
    private String meterCode;
    private HisEvent hisEvent;
    private List<IData> iDatas = new ArrayList<IData>();

    @Override
    public void execute(ITaskParam iTaskParam) throws Exception {
        this.taskParam = (TaskParam) iTaskParam;
        this.channel = (RxtxSocketAdapter) taskParam.getChannel();
        List<String> meterCodes = taskParam.getList();
        for (String meterCode : meterCodes) {
            this.meterCode = meterCode;

            /**50 A相失压发生时刻**/
            this.obtainBean(DataTypeConst.A_LOSE_V_START_TIME);
            /**51 A相失压结束时刻**/
            this.obtainBean(DataTypeConst.A_LOSE_V_END_TIME);
            /**52 B相失压发生时刻**/
            this.obtainBean(DataTypeConst.B_LOSE_V_START_TIME);
            /**53 B相失压结束时刻**/
            this.obtainBean(DataTypeConst.B_LOSE_V_END_TIME);
            /**54 C相失压发生时刻**/
            this.obtainBean(DataTypeConst.C_LOSE_V_START_TIME);
            /**55 C相失压结束时刻**/
            this.obtainBean(DataTypeConst.C_LOSE_V_END_TIME);
            /**56 A相失流发生时刻**/
            this.obtainBean(DataTypeConst.A_LOSE_A_START_TIME);
            /**57 A相失流结束时刻**/
            this.obtainBean(DataTypeConst.A_LOSE_A_END_TIME);
            /**58 B相失流发生时刻**/
            this.obtainBean(DataTypeConst.B_LOSE_A_START_TIME);
            /**59 B相失流结束时刻**/
            this.obtainBean(DataTypeConst.B_LOSE_A_END_TIME);
            /**60 C相失流发生时刻**/
            this.obtainBean(DataTypeConst.C_LOSE_A_START_TIME);
            /**61 C相失流结束时刻**/
            this.obtainBean(DataTypeConst.C_LOSE_A_END_TIME);

            //封装数据结果
            hisEvent.setLastRefreshTime(new Timestamp((new Date()).getTime()));
            hisEvent.setMeterId(Long.parseLong(meterCode));
            iDatas.add(hisEvent);
        }
    }

    @Override
    public void save() {
        HttpDataManager.save(HisEvent.class,iDatas);
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
                case A_LOSE_V_START_TIME:
                    hisEvent = new HisEvent();
                    hisEvent.setOccurTime(dataReturnArea.getDataValue().getTimestamp());
                    hisEvent.setEventType((short) 1);
                    hisEvent.setEventFlag((short) 0);
                    hisEvent.setMeterId(Long.parseLong(meterCode));
                    iDatas.add(hisEvent);
                    break;
                case A_LOSE_V_END_TIME:
                    hisEvent = new HisEvent();
                    hisEvent.setOccurTime(dataReturnArea.getDataValue().getTimestamp());
                    hisEvent.setEventType((short) 1);
                    hisEvent.setEventFlag((short) 1);
                    hisEvent.setMeterId(Long.parseLong(meterCode));
                    iDatas.add(hisEvent);
                    break;
                case B_LOSE_V_START_TIME:
                    hisEvent = new HisEvent();
                    hisEvent.setOccurTime(dataReturnArea.getDataValue().getTimestamp());
                    hisEvent.setEventType((short) 3);
                    hisEvent.setEventFlag((short) 0);
                    hisEvent.setMeterId(Long.parseLong(meterCode));
                    iDatas.add(hisEvent);
                    break;
                case B_LOSE_V_END_TIME:
                    hisEvent = new HisEvent();
                    hisEvent.setOccurTime(dataReturnArea.getDataValue().getTimestamp());
                    hisEvent.setEventType((short) 3);
                    hisEvent.setEventFlag((short) 1);
                    hisEvent.setMeterId(Long.parseLong(meterCode));
                    iDatas.add(hisEvent);
                    break;
                case C_LOSE_V_START_TIME:
                    hisEvent = new HisEvent();
                    hisEvent.setOccurTime(dataReturnArea.getDataValue().getTimestamp());
                    hisEvent.setEventType((short) 5);
                    hisEvent.setEventFlag((short) 0);
                    hisEvent.setMeterId(Long.parseLong(meterCode));
                    iDatas.add(hisEvent);
                case C_LOSE_V_END_TIME:
                    hisEvent = new HisEvent();
                    hisEvent.setOccurTime(dataReturnArea.getDataValue().getTimestamp());
                    hisEvent.setEventType((short) 5);
                    hisEvent.setEventFlag((short) 1);
                    hisEvent.setMeterId(Long.parseLong(meterCode));
                    iDatas.add(hisEvent);
                    break;
                case A_LOSE_A_START_TIME:
                    hisEvent = new HisEvent();
                    hisEvent.setOccurTime(dataReturnArea.getDataValue().getTimestamp());
                    hisEvent.setEventType((short) 2);
                    hisEvent.setEventFlag((short) 0);
                    hisEvent.setMeterId(Long.parseLong(meterCode));
                    iDatas.add(hisEvent);
                    break;
                case A_LOSE_A_END_TIME:
                    hisEvent = new HisEvent();
                    hisEvent.setOccurTime(dataReturnArea.getDataValue().getTimestamp());
                    hisEvent.setEventType((short) 2);
                    hisEvent.setEventFlag((short) 1);
                    hisEvent.setMeterId(Long.parseLong(meterCode));
                    iDatas.add(hisEvent);
                    break;
                case B_LOSE_A_START_TIME:
                    hisEvent = new HisEvent();
                    hisEvent.setOccurTime(dataReturnArea.getDataValue().getTimestamp());
                    hisEvent.setEventType((short) 4);
                    hisEvent.setEventFlag((short) 0);
                    hisEvent.setMeterId(Long.parseLong(meterCode));
                    iDatas.add(hisEvent);
                    break;
                case B_LOSE_A_END_TIME:
                    hisEvent = new HisEvent();
                    hisEvent.setOccurTime(dataReturnArea.getDataValue().getTimestamp());
                    hisEvent.setEventType((short) 4);
                    hisEvent.setEventFlag((short) 1);
                    hisEvent.setMeterId(Long.parseLong(meterCode));
                    iDatas.add(hisEvent);
                    break;
                case C_LOSE_A_START_TIME:
                    hisEvent = new HisEvent();
                    hisEvent.setOccurTime(dataReturnArea.getDataValue().getTimestamp());
                    hisEvent.setEventType((short) 6);
                    hisEvent.setEventFlag((short) 0);
                    hisEvent.setMeterId(Long.parseLong(meterCode));
                    iDatas.add(hisEvent);
                    break;
                case C_LOSE_A_END_TIME:
                    hisEvent = new HisEvent();
                    hisEvent.setOccurTime(dataReturnArea.getDataValue().getTimestamp());
                    hisEvent.setEventType((short) 6);
                    hisEvent.setEventFlag((short) 1);
                    hisEvent.setMeterId(Long.parseLong(meterCode));
                    iDatas.add(hisEvent);
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