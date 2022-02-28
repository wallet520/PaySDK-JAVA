package com.block.model.req;

import lombok.Data;


@Data
public class SellerTransferDto {

    private Double amount;
    private String addressTo;
    private Integer assetType;
    private Integer tokenType;
    private String externalOrderId;
}
