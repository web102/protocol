package com.bobandata.iot.xb102.connector.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class Xb102CodecFactory implements ProtocolCodecFactory {

	private Xb102MasterEncoder encoder;
	private Xb102MasterDecoder decoder;

	public Xb102CodecFactory() {

		encoder = new Xb102MasterEncoder();
		decoder = new Xb102MasterDecoder();

	}

	@Override
	public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
		return encoder;
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
		return decoder;
	}

}
