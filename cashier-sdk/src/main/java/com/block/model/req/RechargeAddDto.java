package com.block.model.req;

import lombok.Data;

@Data
public class RechargeAddDto {
    private String payAddress;
    private Double amount;
    private Integer assetType;
    private Integer tokenType;
    private String currencyType;
    private String externalOrderId;
    private String remark;
    private Boolean isHiddenLogo;
    private Boolean isHiddenStoreName;
}
