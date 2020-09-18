package com.bobandata.iot.dlt645_07.frame.data;

import com.bobandata.iot.transport.util.HexUtils;
import io.netty.buffer.ByteBuf;

/**
 * @Author: lizhipeng
 * @Company: 上海博般数据技术有限公司
 * @Date: 2019/1/22 14:43
 * @Description:
 */

public class ReturnError extends DataArea{
    private ErrorType errorType;


    public enum ErrorType{
        TARIFF_OVER("01000000"),
        DAY_DATE_OVER("00100000"),
        YEAR_DATE_OVER("000100000"),
        COMMUNICATION_RATE_NO_CHANGE("00001000"),
        WRONG_PASSWORD_OR_UNAUTHORIZED("00000100"),
        NO_REQUEST_DATA("00000010"),
        OTHER("00000001"),
        ;
        private String code;

        ErrorType(String code) {
            this.code=code;
        }

        public String getCode() {
            return code;
        }

        public static ErrorType findByCode(String code){
            for(ErrorType errorType:ErrorType.values()){
                if(errorType.getCode().equals(code)){
                    return errorType;
                }
            }
            return null;
        }
    }


    @Override
    public int encode(ByteBuf paramByteBuf) throws Exception {
        /**
         * 电表完成
         */
        return 0;
    }

    @Override
    public int decode(ByteBuf paramByteBuf) throws Exception {
        this.dataLength = HexUtils.byteToInt(paramByteBuf.readByte());
        String code =Integer.toBinaryString(paramByteBuf.readByte()-0x33);
        switch (code.toCharArray().length){
            case 7:code="0"+code;break;
            case 6:code="00"+code;break;
            case 5:code="000"+code;break;
            case 4:code="0000"+code;break;
            case 3:code="00000"+code;break;
            case 2:code="000000"+code;break;
            case 1:code="0000000"+code;break;
        }
        this.errorType = ErrorType.findByCode(code);
        return 0;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }
}