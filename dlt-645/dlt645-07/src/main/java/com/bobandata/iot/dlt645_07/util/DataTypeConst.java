package com.bobandata.iot.dlt645_07.util;

import java.util.Arrays;

public enum DataTypeConst{

    /**
     * 广播报文，请求电表地址
     */
    METER_ADDRESS{
        public byte getValue(){
            return 0;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x00;
            value[2] = (byte)0x00;
            value[3] = (byte)0x00;
            return value;
        }
    },
    /**01 正向有功总电能**/
    POSITIVE_ACTIVE_ENERGY{
        public byte getValue(){
            return 1;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x00;
            value[2] = (byte)0x01;
            value[3] = (byte)0x00;
            return value;
        }
    },
    /**02 反向有功总电能**/
    REVERSE_ACTIVE_ENERGY{
        public byte getValue(){
            return 2;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x00;
            value[2] = (byte)0x02;
            value[3] = (byte)0x00;
            return value;
        }
    },
    /**03 正向无功总电能**/
    POSITIVE_REACTIVE_ENERGY{
        public byte getValue(){
            return 3;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x00;
            value[2] = (byte)0x03;
            value[3] = (byte)0x00;
            return value;
        }
    },
    /**04 反向无功总电能**/
    REVERSE_REACTIVE_ENERGY{
        public byte getValue(){
            return 4;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x00;
            value[2] = (byte)0x04;
            value[3] = (byte)0x00;
            return value;
        }
    },
    /**05 A相电流**/
    A_A{
        public byte getValue(){
            return 5;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x01;
            value[2] = (byte)0x02;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**06 B相电流**/
    B_A{
        public byte getValue(){
            return 6;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x02;
            value[2] = (byte)0x02;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**07 C相电流**/
    C_A{
        public byte getValue(){
            return 7;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x03;
            value[2] = (byte)0x02;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**08 A相电压**/
    A_V{
        public byte getValue(){
            return 8;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x01;
            value[2] = (byte)0x01;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**09 B相电压**/
    B_V{
        public byte getValue(){
            return 9;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x02;
            value[2] = (byte)0x01;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**10 C相电压**/
    C_V{
        public byte getValue(){
            return 10;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x03;
            value[2] = (byte)0x01;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**11 A相有功功率**/
    A_ACTIVE_POWER{
        public byte getValue(){
            return 11;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x01;
            value[2] = (byte)0x03;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**12 B相有功功率**/
    B_ACTIVE_POWER{
        public byte getValue(){
            return 12;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x02;
            value[2] = (byte)0x03;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**13 C相有功功率**/
    C_ACTIVE_POWER{
        public byte getValue(){
            return 13;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x03;
            value[2] = (byte)0x03;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**14 总有功功率**/
    SUM_ACTIVE_POWER{
        public byte getValue(){
            return 14;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x00;
            value[2] = (byte)0x03;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**15 A相无功功率**/
    A_REACTIVE_POWER{
        public byte getValue(){
            return 15;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x01;
            value[2] = (byte)0x04;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**16 B相无功功率**/
    B_REACTIVE_POWER{
        public byte getValue(){
            return 16;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x02;
            value[2] = (byte)0x04;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**17 C相无功功率**/
    C_REACTIVE_POWER{
        public byte getValue(){
            return 17;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x03;
            value[2] = (byte)0x04;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**18 总无功功率**/
    SUM_REACTIVE_POWER{
        public byte getValue(){
            return 18;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x00;
            value[2] = (byte)0x04;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**19 A相视在功率**/
    A_APPARENT_POWER{
        public byte getValue(){
            return 19;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x01;
            value[2] = (byte)0x05;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**20 B相视在功率**/
    B_APPARENT_POWER{
        public byte getValue(){
            return 20;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x02;
            value[2] = (byte)0x05;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**21 C相视在功率**/
    C_APPARENT_POWER{
        public byte getValue(){
            return 21;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x03;
            value[2] = (byte)0x05;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**22 总视在功率**/
    SUM_APPARENT_POWER{
        public byte getValue(){
            return 22;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x00;
            value[2] = (byte)0x05;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**23 A相功率因数**/
    A_POWER_FACTOR{
        public byte getValue(){
            return 23;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x01;
            value[2] = (byte)0x06;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**24 B相功率因数**/
    B_POWER_FACTOR{
        public byte getValue(){
            return 24;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x02;
            value[2] = (byte)0x06;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**25 C相功率因数**/
    C_POWER_FACTOR{
        public byte getValue(){
            return 25;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x03;
            value[2] = (byte)0x06;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**26 总功率因数**/
    SUM_POWER_FACTOR{
        public byte getValue(){
            return 26;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x00;
            value[2] = (byte)0x06;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**27 A相电流波形失真度**/
    A_A_DISTORTION{
        public byte getValue(){
            return 27;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x01;
            value[2] = (byte)0x09;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**28 B相电流波形失真度**/
    B_A_DISTORTION{
        public byte getValue(){
            return 28;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x02;
            value[2] = (byte)0x09;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**29 C相电流波形失真度**/
    C_A_DISTORTION{
        public byte getValue(){
            return 29;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x03;
            value[2] = (byte)0x09;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**30 A相电压波形失真度**/
    A_V_DISTORTION{
        public byte getValue(){
            return 30;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x01;
            value[2] = (byte)0x08;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**31 B相电压波形失真度**/
    B_V_DISTORTION{
        public byte getValue(){
            return 31;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x02;
            value[2] = (byte)0x08;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**32 C相电压波形失真度**/
    C_V_DISTORTION{
        public byte getValue(){
            return 32;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x03;
            value[2] = (byte)0x08;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**33 频率**/
    FREQUENCY{
        public byte getValue(){
            return 33;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x02;
            value[1] = (byte)0x00;
            value[2] = (byte)0x80;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**34 第一象限无功总电能量**/
    SUM_FIRST_REACTIVE_ENERGY{
        public byte getValue(){
            return 34;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x00;
            value[2] = (byte)0x05;
            value[3] = (byte)0x00;
            return value;
        }
    },
    /**35 第二象限无功总电能量**/
    SUM_SECOND_REACTIVE_ENERGY{
        public byte getValue(){
            return 35;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x00;
            value[2] = (byte)0x06;
            value[3] = (byte)0x00;
            return value;
        }
    },
    /**36 第三象限无功总电能量**/
    SUM_THIRD_REACTIVE_ENERGY{
        public byte getValue(){
            return 36;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x00;
            value[2] = (byte)0x07;
            value[3] = (byte)0x00;
            return value;
        }
    },
    /**37 第四象限无功总电能量**/
    SUM_FOURTH_REACTIVE_ENERGY{
        public byte getValue(){
            return 37;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x00;
            value[2] = (byte)0x08;
            value[3] = (byte)0x00;
            return value;
        }
    },
    /**38 日冻结正向有功总电能（总尖峰平谷）**/
    DAY_POSITIVE_ACTIVE_ENERGY{
        public byte getValue(){
            return 38;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x01;
            value[1] = (byte)0x01;
            value[2] = (byte)0x06;
            value[3] = (byte)0x05;
            return value;
        }
    },
    /**39 日冻结反向有功总电能（总尖峰平谷）**/
    DAY_REVERSE_ACTIVE_ENERGY{
        public byte getValue(){
            return 39;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x01;
            value[1] = (byte)0x02;
            value[2] = (byte)0x06;
            value[3] = (byte)0x05;
            return value;
        }
    },
    /**40 日冻结正向无功总电能（总尖峰平谷）**/
    DAY_POSITIVE_REACTIVE_ENERGY{
        public byte getValue(){
            return 40;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x01;
            value[1] = (byte)0x03;
            value[2] = (byte)0x06;
            value[3] = (byte)0x05;
            return value;
        }
    },
    /**41 日冻结反向无功总电能（总尖峰平谷）**/
    DAY_REVERSE_REACTIVE_ENERGY{
        public byte getValue(){
            return 41;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x01;
            value[1] = (byte)0x04;
            value[2] = (byte)0x06;
            value[3] = (byte)0x05;
            return value;
        }
    },
    /**42 正向有功总最大需量及发生时间**/
    POSITIVE_ACTIVE_MAX_DEMAND_AND_TIME{
        public byte getValue(){
            return 42;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x00;
            value[2] = (byte)0x01;
            value[3] = (byte)0x01;
            return value;
        }
    },
    /**43 反向有功总最大需量及发生时间**/
    REVERSE_ACTIVE_MAX_DEMAND_AND_TIME{
        public byte getValue(){
            return 43;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x00;
            value[2] = (byte)0x02;
            value[3] = (byte)0x01;
            return value;
        }
    },
    /**44 正向无功总最大需量及发生时间**/
    POSITIVE_REACTIVE_MAX_DEMAND_AND_TIME{
        public byte getValue(){
            return 44;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x00;
            value[2] = (byte)0x03;
            value[3] = (byte)0x01;
            return value;
        }
    },
    /**45 反向无功总最大需量及发生时间**/
    REVERSE_REACTIVE_MAX_DEMAND_AND_TIME{
        public byte getValue(){
            return 45;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x00;
            value[1] = (byte)0x00;
            value[2] = (byte)0x04;
            value[3] = (byte)0x01;
            return value;
        }
    },
    /**46 日冻结正向有功总最大需量及发生时间**/
    DAY_POSITIVE_ACTIVE_MAX_DEMAND_AND_TIME{
        public byte getValue(){
            return 46;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x01;
            value[1] = (byte)0x00;
            value[2] = (byte)0x01;
            value[3] = (byte)0x01;
            return value;
        }
    },
    /**47 日冻结反向有功总最大需量及发生时间**/
    DAY_REVERSE_ACTIVE_MAX_DEMAND_AND_TIME{
        public byte getValue(){
            return 47;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x01;
            value[1] = (byte)0x00;
            value[2] = (byte)0x02;
            value[3] = (byte)0x01;
            return value;
        }
    },
    /**48 日冻结正向无功总最大需量及发生时间**/
    DAY_POSITIVE_REACTIVE_MAX_DEMAND_AND_TIME{
        public byte getValue(){
            return 48;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x01;
            value[1] = (byte)0x00;
            value[2] = (byte)0x03;
            value[3] = (byte)0x01;
            return value;
        }
    },
    /**49 日冻结反向无功总最大需量及发生时间**/
    DAY_REVERSE_REACTIVE_MAX_DEMAND_AND_TIME{
        public byte getValue(){
            return 49;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x01;
            value[1] = (byte)0x00;
            value[2] = (byte)0x04;
            value[3] = (byte)0x01;
            return value;
        }
    },
    /**50 A相失压发生时刻**/
    A_LOSE_V_START_TIME{
        public byte getValue(){
            return 50;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x01;
            value[1] = (byte)0x01;
            value[2] = (byte)0x01;
            value[3] = (byte)0x10;
            return value;
        }
    },
    /**51 A相失压结束时刻**/
    A_LOSE_V_END_TIME{
        public byte getValue(){
            return 51;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x01;
            value[1] = (byte)0x25;
            value[2] = (byte)0x01;
            value[3] = (byte)0x10;
            return value;
        }
    },
    /**52 A相失压发生时刻**/
    B_LOSE_V_START_TIME{
        public byte getValue(){
            return 52;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x01;
            value[1] = (byte)0x01;
            value[2] = (byte)0x02;
            value[3] = (byte)0x10;
            return value;
        }
    },
    /**53 B相失压结束时刻**/
    B_LOSE_V_END_TIME{
        public byte getValue(){
            return 53;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x01;
            value[1] = (byte)0x25;
            value[2] = (byte)0x02;
            value[3] = (byte)0x10;
            return value;
        }
    },
    /**54 C相失压发生时刻**/
    C_LOSE_V_START_TIME{
        public byte getValue(){
            return 54;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x01;
            value[1] = (byte)0x01;
            value[2] = (byte)0x03;
            value[3] = (byte)0x10;
            return value;
        }
    },
    /**55 C相失压结束时刻**/
    C_LOSE_V_END_TIME{
        public byte getValue(){
            return 55;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x01;
            value[1] = (byte)0x25;
            value[2] = (byte)0x03;
            value[3] = (byte)0x10;
            return value;
        }
    },
    /**56 A相失流发生时刻**/
    A_LOSE_A_START_TIME{
        public byte getValue(){
            return 56;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x01;
            value[1] = (byte)0x01;
            value[2] = (byte)0x01;
            value[3] = (byte)0x18;
            return value;
        }
    },
    /**57 A相失流结束时刻**/
    A_LOSE_A_END_TIME{
        public byte getValue(){
            return 57;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x01;
            value[1] = (byte)0x21;
            value[2] = (byte)0x01;
            value[3] = (byte)0x18;
            return value;
        }
    },
    /**58 B相失流发生时刻**/
    B_LOSE_A_START_TIME{
        public byte getValue(){
            return 58;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x01;
            value[1] = (byte)0x01;
            value[2] = (byte)0x02;
            value[3] = (byte)0x18;
            return value;
        }
    },
    /**59 B相失流结束时刻**/
    B_LOSE_A_END_TIME{
        public byte getValue(){
            return 59;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x01;
            value[1] = (byte)0x21;
            value[2] = (byte)0x02;
            value[3] = (byte)0x18;
            return value;
        }
    },
    /**60 C相失流发生时刻**/
    C_LOSE_A_START_TIME{
        public byte getValue(){
            return 60;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x01;
            value[1] = (byte)0x01;
            value[2] = (byte)0x03;
            value[3] = (byte)0x18;
            return value;
        }
    },
    /**61 C相失流结束时刻**/
    C_LOSE_A_END_TIME{
        public byte getValue(){
            return 61;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x01;
            value[1] = (byte)0x21;
            value[2] = (byte)0x03;
            value[3] = (byte)0x18;
            return value;
        }
    },
    /**62 当前有功需量**/
    ACTIVE_DEMAND{
        public byte getValue(){
            return 62;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x04;
            value[1] = (byte)0x00;
            value[2] = (byte)0x80;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**63 当前无功需量**/
    REACTIVE_DEMAND{
        public byte getValue(){
            return 63;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x05;
            value[1] = (byte)0x00;
            value[2] = (byte)0x80;
            value[3] = (byte)0x02;
            return value;
        }
    },
    /**64 当前有功需量**/
    APPARENT_DEMAND{
        public byte getValue(){
            return 64;
        }
        public byte[] getCode(){
            byte[] value = new byte[4];
            value[0] = (byte)0x06;
            value[1] = (byte)0x00;
            value[2] = (byte)0x80;
            value[3] = (byte)0x02;
            return value;
        }
    },
    ;

    public byte getValue(){
        return 0;
    }
    public byte[] getCode() {
        return null;
    }

    public byte[] getReCode(){
        byte[] code =getCode();
        byte[] reCode = new byte[4];
        reCode[3] = (byte) (code[0]+0x33);
        reCode[2] = (byte) (code[1]+0x33);
        reCode[1] = (byte) (code[2]+0x33);
        reCode[0] = (byte) (code[3]+0x33);
        return reCode;
    }

    public static DataTypeConst findByCode(byte[] bytes){
        byte[] bytes1 = new byte[4];
        for(int i=0;i<4;i++){
            bytes1[i]=(byte)(bytes[bytes.length-i-1]-0x33);
        }
        for(DataTypeConst c : DataTypeConst.values()){
            if(Arrays.toString(c.getCode()).equals(Arrays.toString(bytes1))){
                return c;
            }
        }
        return null;
    }
}