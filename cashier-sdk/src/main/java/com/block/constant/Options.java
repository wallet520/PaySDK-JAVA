package com.block.constant;

import com.block.constant.enums.ExchangeEnum;

public interface Options {
    String getAccessKey();

    String getSecretKey();

    ExchangeEnum getExchange();

    String getRestHost();
}
