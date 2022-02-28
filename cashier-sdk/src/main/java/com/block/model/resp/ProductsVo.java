package com.block.model.resp;

import com.block.model.req.Goods;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProductsVo {
    private Long productId;
    private Long sellerId;

    private String storeName;

    private Double productsAmount;

    private List<Goods> goodsList;

    private String externalOrderId;

    private Integer tokenType;

    private Integer assetType;

    private String remark;

    private String redirectUrl;

    private Integer isValid;

    private Date createTime;

    private Date updateTime;
}
