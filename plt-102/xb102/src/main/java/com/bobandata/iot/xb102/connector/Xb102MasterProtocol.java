package com.bobandata.iot.xb102.connector;

import com.bobandata.iot.transport.frame.IFrame;
import com.bobandata.iot.transport.util.ConvertUtil;
import com.bobandata.iot.xb102.connector.codec.Xb102CodecFactory;
import com.bobandata.iot.xb102.connector.request.Iec102SendRequest;
import com.bobandata.iot.xb102.util.CommCoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.njcp.ias.data.TaskParam;
import net.njcp.ias.data.WebMessage;
import net.njcp.ias.entity.TaskItems;
import net.njcp.ias.protocol.AbstractProtocol;
import net.njcp.ias.protocol.IAsynProtocol;
import net.njcp.ias.protocol.IExplain;
import net.njcp.utils.Tracer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.io.IOException;

public class Xb102MasterProtocol extends AbstractProtocol implements IAsynProtocol {
    private static Log log= LogFactory.getLog(Xb102MasterProtocol.class);

    private TaskParam taskParam;

    @Override
    public void installProtocolFilter(DefaultIoFilterChainBuilder filterChain) {
        filterChain.addLast("protocol", new ProtocolCodecFilter(new Xb102CodecFactory()));
    }

    @Override
    public void init(TaskParam taskParam) {
        this.taskParam=taskParam;
    }

    @Override
    public void execute(TaskItems taskItems) throws Exception {
        try {
            Iec102SendRequest iec102SendRequest = new Iec102SendRequest(taskParam, this);
            //链路请求
            iec102SendRequest.sendLinkTask();
            //发送送数据请求
            iec102SendRequest.sendTask();
            //发送召唤一级数据(续帧请求)
            iec102SendRequest.sendLinkIncrease();
        }catch (Exception e){
            WebMessage.sendMsg(taskParam.getWebSession(), new WebMessage(WebMessage.Code.ERROR, e.getMessage()));
        }
    }

    @Override
    public void installTracer(Tracer tracer) {
    }

    @Override
    public void login() throws Exception {
        log.info("[102][login]-----------------------login---------------------");
    }

    @Override
    public void logout() throws Exception {
        log.info("[102][logout]-----------------------logout---------------------");
    }

    @Override
    public boolean diagonse() throws Exception {
        return false;
    }

    @Override
    public void interrupt() throws Exception {

    }

    @Override
    public boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) {
        log.info("----------doDecode---------------");
        return false;
    }

    @Override
    public void encode(IoSession ioSession, Object o, ProtocolEncoderOutput protocolEncoderOutput) {
        log.info("----------encode---------------");
    }


    public IFrame sendMsg(IFrame request) throws Exception{
        IFrame response = null;

        try {
            byte[] sendBytes = request.getBuffer().array();
            String send = ConvertUtil.bytes2hexstr(sendBytes,' ');

            for (int i = 0; i<3; i++) {
                this.getTransport().write(sendBytes);

                WebMessage.sendMsg(taskParam.getWebSession(), new WebMessage(WebMessage.Code.REQUEST_INFO, send, request.toExplain()));
                log.info("主站:" + request.toHexString());

                Object r = getTransport().read();
                if (r != null) {
                    byte[] responseBytes = (byte[]) r;
                    ByteBuf byteBuf = Unpooled.buffer();
                    byteBuf.writeBytes(responseBytes);
                    response = (IFrame) CommCoder.masterDecoder(byteBuf);
                    String receive = ConvertUtil.bytes2hexstr(responseBytes, ' ');
                    WebMessage.sendMsg(taskParam.getWebSession(), new WebMessage(WebMessage.Code.RESPONSE_INFO, receive, response.toExplain()));
                    log.info("从站:" + response.toHexString());
                    return response;
                }
            }
        } catch (IOException e) {
            log.error("E004-发送前段消息异常！",e);
            throw e;
        } catch (Exception e2) {
            throw e2;
        }

        throw new NullPointerException("重发三次无响应！");


    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getVersion() {
        return null;
    }

    @Override
    public IExplain explain(byte[] bytes, int i) {
        return null;
    }

}
