package com.block.constant.enums;



public enum EnumAssetType {

    BTC(101,"BTC"),
    ETH(102,"ERC20"),
    TRX(103,"TRX"),
    ;

    private final Integer value;

    private final String enumName;

    EnumAssetType(final Integer value, final String enumName) {
        this.value = value;
        this.enumName = enumName;
    }
    public static String getEnumName(Integer value) {
        for (EnumAssetType st : values()) {
            if (st.value().equals(value)) {
                return st.enumName();
            }
        }
        return null;
    }

    public static EnumAssetType getEnum(Integer value) {
        for (EnumAssetType st : values()) {
            if (st.value().equals(value)) {
                return st;
            }
        }
        return null;
    }


    public static EnumAssetType getEnumByName(String value) {
        for (EnumAssetType st : values()) {
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
