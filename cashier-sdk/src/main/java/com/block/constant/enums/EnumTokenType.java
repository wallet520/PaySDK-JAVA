package com.block.constant.enums;


public enum EnumTokenType{

    BTC(801,"BTC"),
    ETH(802,"ETH"),
    USDT(901,"USDT"),
    BNB(902,"BNB"),
    UNI(903,"UNI"),
    ;

    private final Integer value;

    private final String enumName;

    EnumTokenType(final Integer value, final String enumName) {
        this.value = value;
        this.enumName = enumName;
    }
    public static String getEnumName(Integer value) {
        for (EnumTokenType st : values()) {
            if (st.value().equals(value)) {
                return st.enumName();
            }
        }
        return null;
    }

    public static EnumTokenType getEnumByName(String value) {
        for (EnumTokenType st : values()) {
            if (st.enumName.equals(value)) {
                return st;
            }
        }
        return null;
    }

    public Integer value() {
        return this.value;
    }

    public String enumName() {
        return this.enumName;
    }
}
