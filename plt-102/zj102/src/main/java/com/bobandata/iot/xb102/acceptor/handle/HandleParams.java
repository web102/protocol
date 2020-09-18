package com.bobandata.iot.xb102.acceptor.handle;

import com.bobandata.iot.transport.frame.IFrame;
import com.bobandata.iot.transport.util.ObjToMap;
import com.bobandata.iot.xb102.frame.controldomain.ControlDomain_C;
import com.bobandata.iot.xb102.frame.format.FixedLengthFrame;
import com.bobandata.iot.xb102.frame.format.VariableLengthFrame;
import com.bobandata.iot.xb102.frame.util.LinkAddress;
import com.bobandata.iot.xb102.frame.asdu.Asdu;

import java.util.HashMap;
import java.util.Map;

public class HandleParams {
    private Map<String, Object> parameter;
    private IFrame iFrame;

    public HandleParams(IFrame iFrame) {
        this.iFrame = iFrame;
        this.parameter = new HashMap<>();
        parameterAssignment();
    }

    public void parameterAssignment() {
        if ((this.iFrame instanceof FixedLengthFrame)) {
            FixedLengthFrame fixedLengthFrame = (FixedLengthFrame) this.iFrame;

            LinkAddress linkAddress = fixedLengthFrame.getLinkAddress();
            setParameter("linkAddress", linkAddress);

            ControlDomain_C controlDomainC = (ControlDomain_C) fixedLengthFrame.getControlDomain();
            setParameterByControl(controlDomainC);
        } else {
            VariableLengthFrame variableLengthFrame = (VariableLengthFrame) this.iFrame;
            LinkAddress linkAddress = variableLengthFrame.getLinkAddress();
            setParameter("linkAddress", linkAddress);

            Asdu asdu = variableLengthFrame.getAsdu();
            Map<String, Object> map = (Map<String, Object>) ObjToMap.objectToMap(asdu);
            for (String key : map.keySet()) {
                setParameter(key, map.get(key));
            }

            ControlDomain_C controlDomainC = (ControlDomain_C) variableLengthFrame.getControlDomain();
            setParameterByControl(controlDomainC);
        }
    }

    public void setParameterByControl(ControlDomain_C controlDomainC) {
        byte funcode = controlDomainC.getFuncCode();

        byte fcb = controlDomainC.getFcb();
        setParameter("funcode", funcode);
        setParameter("fcb", fcb);
    }

    public Map<String, Object> getAllParameter() {
        return this.parameter;
    }

    public void setParameters(Map<String, Object> parameter) {
        this.parameter = parameter;
    }

    public void setParameter(String key, Object value) {
        this.parameter.put(key, value);
    }

    public Object getParameter(String key) {
        return this.parameter.get(key);
    }

    public void remove(String key) {
        this.parameter.remove(key);
    }

    public IFrame getiFrame() {
        return this.iFrame;
    }

    public void setiFrame(IFrame iFrame) {
        this.iFrame = iFrame;
    }
}