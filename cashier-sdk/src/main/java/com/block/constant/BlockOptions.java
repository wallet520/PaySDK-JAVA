package com.block.constant;

import com.block.constant.enums.ExchangeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Anderson
 * */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BlockOptions implements Options {

    @Builder.Default
    private final String restHost = "Please contact technical support";
    private  String accessKey;
    private  String secretKey;

    @Override
    public String getAccessKey() {
        return this.accessKey;
    }

    @Override
    public String getSecretKey() {
        return this.secretKey;
    }

    @Override
    public ExchangeEnum getExchange() {
        return ExchangeEnum.CASHIER;
    }

    @Override
    public String getRestHost() {
        return this.restHost;
    }
}
