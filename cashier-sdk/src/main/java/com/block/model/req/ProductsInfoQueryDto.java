package com.block.model.req;

import lombok.Data;

@Data
public class ProductsInfoQueryDto {
    private Long productId;
    private String storeName;
    private String externalOrderId;
}
