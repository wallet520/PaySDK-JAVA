package com.block.constant.enums;



public enum EnumTradeStatus{

    WAIT_PAY(1,"待支付"),
    FINISH_PAY(2,"已支付"),
    ERROR_PAY(3,"地址异常订单"),
    ;

    private final Integer value;

    private final String enumName;

    EnumTradeStatus(final Integer value, final String enumName) {
        this.value = value;
        this.enumName = enumName;
    }
    public static String getEnumName(Integer value) {
        for (EnumTradeStatus st : values()) {
            if (st.value().equals(value)) {
                return st.enumName();
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
