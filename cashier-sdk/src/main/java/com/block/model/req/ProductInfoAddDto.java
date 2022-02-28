package com.block.model.req;

import lombok.Data;

import java.util.List;


@Data
public class ProductInfoAddDto {

    private String storeName;

    private Double productsAmount;

    private List<Goods> goodsList;

    private String externalOrderId;

    private Integer assetType;

    private Integer tokenType;

    private String remark;

    private String currencyType;

    private Boolean isHiddenLogo;

    private Boolean isHiddenStoreName;
}
