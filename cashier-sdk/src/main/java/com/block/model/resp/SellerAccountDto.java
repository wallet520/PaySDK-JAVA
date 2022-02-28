package com.block.model.resp;

import lombok.Data;

@Data
public class SellerAccountDto {
    private Long accountId;

    private Integer assetType;

    private Integer tokenType;

    private Double amount;

    private Double amountCoverUSD;

    private Double usableAmount;

    private Double usableAmountCoverUSD;

    private String withdrawalAddress;


}
