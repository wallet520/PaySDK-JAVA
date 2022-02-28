package com.block.examples;

import com.alibaba.fastjson.JSON;
import com.block.Constants;
import com.block.constant.BlockOptions;
import com.block.model.req.Goods;
import com.block.model.req.ProductInfoAddDto;
import com.block.model.req.ProductsInfoQueryDto;
import com.block.model.req.RechargeAddDto;
import com.block.model.resp.ProductsInfoVo;
import com.block.model.resp.SellerAccountVo;
import com.block.service.CashierService;

import java.util.ArrayList;
import java.util.List;


public class TestExamples {
    public static void main(String[] args)  {
        CashierService cashierService = CashierService.create(BlockOptions.builder()
                .accessKey(Constants.ACCESSKEY)
                .secretKey(Constants.SECRETKEY)
                .build());
        ProductsInfoQueryDto dto = new ProductsInfoQueryDto();
        dto.setExternalOrderId("abc-123-123");
        dto.setProductId(123131313L);
        List<ProductsInfoVo> voList = cashierService.listProducts(dto);
        System.out.println(voList);

    }
}
