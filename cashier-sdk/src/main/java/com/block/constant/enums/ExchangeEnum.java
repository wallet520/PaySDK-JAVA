package com.block.constant.enums;

import lombok.Getter;

@Getter
public enum ExchangeEnum {
    CASHIER("cashier"),
    ;
    private final String code;
    ExchangeEnum(String code) {
        this.code = code;
    }

}
