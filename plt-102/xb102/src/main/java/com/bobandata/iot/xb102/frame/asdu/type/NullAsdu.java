package com.bobandata.iot.xb102.frame.asdu.type;

import com.bobandata.iot.xb102.frame.asdu.AsduHead;
import com.bobandata.iot.xb102.frame.asdu.Asdu;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

/**
 * @Author: lizhipeng
 * @Description:获取终端时间
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 11:35 2018/8/22.
 */
public class NullAsdu extends Asdu {

    public NullAsdu(AsduHead asduHead) {
        setAsduHead(asduHead);
        getAsduHead();
        setAsduLength(AsduHead.getAsduHeadLength());
    }

    public NullAsdu() {
        setAsduHead(new AsduHead());
        getAsduHead();
        setAsduLength(AsduHead.getAsduHeadLength());
    }

    public int encode(ByteBuf buffer) throws Exception {
        getAsduHead().encode(buffer);
        setHexDump(ByteBufUtil.hexDump(buffer));
        return 0;
    }

    public int decode(ByteBuf buffer) throws Exception {
        setHexDump(ByteBufUtil.hexDump(buffer));
        getAsduHead().decode(buffer);
        return 0;
    }
    @Override
    public String toExplain() {
        return getAsduHead().toExplain();
    }
}
