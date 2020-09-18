package com.bobandata.iot.dlt645_07.connector;

import com.bobandata.iot.dlt645_07.connector.codec.Dlt645MasterDecoder;
import com.bobandata.iot.dlt645_07.connector.codec.Dlt645MasterEncoder;
import com.bobandata.iot.dlt645_07.execute.*;
import com.bobandata.iot.transport.connector.RxtxSocketAdapter;
import com.bobandata.iot.transport.frame.IFrame;
import com.bobandata.iot.transport.protocol.IMasterProtocol;
import com.bobandata.iot.transport.protocol.ITaskExecutor;
import com.bobandata.iot.transport.util.ETaskItem;
import com.bobandata.iot.transport.util.TaskParam;
import io.netty.channel.ChannelPipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Dlt645MasterProtocol extends IMasterProtocol {
    private static final Logger logger = LoggerFactory.getLogger(Dlt645MasterProtocol.class);

    public String getName() {
        return "102主站规约";
    }

    public String getVersion() {
        return "1.0";
    }

    public void installProtocolFilter(ChannelPipeline pipeline) {
        pipeline.addLast("decoder", new Dlt645MasterDecoder());
        pipeline.addLast("encoder", new Dlt645MasterEncoder());
    }

    public static IFrame sendMsg(IFrame message, RxtxSocketAdapter client) {
        IFrame result = null;
        try {
            result = (IFrame) client.getBody(message);
            logger.info("下行：" + message.toHexString());
            logger.info("上行：" + result.toHexString());
        } catch (Exception e) {
            logger.error("E004-消息接收异常！");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void executeTask(TaskParam taskParam) throws Exception {
        ITaskExecutor executor;
        switch (taskParam.getTaskType()){
            case ETaskItem.VIEW:
                executor = new ExecuteView();
                break;
            case ETaskItem.RP_VIEW:
                executor = new ExecuteRpView();
                break;
            case ETaskItem.TARIFF:
                executor= new ExecuteTariff();
                break;
            case ETaskItem.INSTANTANEOUS:
                executor = new ExecuteMeas();
                break;
            case ETaskItem.DEMAND:
                executor = new ExecuteDemand();
                break;
            case ETaskItem.DAY_DEMAND:
                executor = new ExecuteDayDemand();
                break;
            case ETaskItem.EVENT:
                executor = new ExecuteEvent();
                break;
            default:
                logger.error("---------------Not Found Type----------------");
                executor = null;
                break;
        }
        executor.execute(taskParam);
        executor.save();
    }

    public static void main(String[] args) throws Exception {
        logger.info("开始启动Rxtx服务器...");
        Dlt645MasterProtocol dlt645MasterProtocol = new Dlt645MasterProtocol();
        RxtxSocketAdapter rxtxSocketAdapter = new RxtxSocketAdapter("COM2",2400, (short)8,(short)2,(short)1,dlt645MasterProtocol);
        rxtxSocketAdapter.connect();
        TaskParam taskParam = new TaskParam();
        taskParam.setChannel(rxtxSocketAdapter);
        taskParam.setTaskType(ETaskItem.TARIFF);
        List<String> list = new ArrayList<>();
        list.add("800426811");
//        list.add("800426812");
        taskParam.setList(list);
        dlt645MasterProtocol.executeTask(taskParam);
        rxtxSocketAdapter.disconnect();

    }

    @Override
    public IFrame sendMsg(IFrame iFrame) {
        return null;
    }
}
