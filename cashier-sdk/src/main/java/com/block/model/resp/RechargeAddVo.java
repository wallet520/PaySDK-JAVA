package com.block.model.resp;

import lombok.Data;

@Data
public class RechargeAddVo {
    private String externalOrderId;

    private Long tradeId;

    private Double amount;

    private String address;

    private Double amountCover;

    private Double rate;

    private String currencyType;

}
