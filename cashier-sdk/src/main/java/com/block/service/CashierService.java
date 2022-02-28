package com.block.service;

import com.block.client.CashierClient;
import com.block.constant.Options;
import com.block.constant.enums.ExchangeEnum;
import com.block.exception.SDKException;
import com.block.model.req.ProductInfoAddDto;
import com.block.model.req.ProductsInfoQueryDto;
import com.block.model.req.RechargeAddDto;
import com.block.model.req.SellerTransferDto;
import com.block.model.resp.*;

import java.util.List;

public interface CashierService {

    SellerAccountVo getSellerAccountVo();

    List<ProductsInfoVo> listProducts(ProductsInfoQueryDto dto);

    ProductsVo addProducts(ProductInfoAddDto dto);

    RechargeAddVo addRecharge(RechargeAddDto dto);

    TransferTradeVo sellerTransfer(SellerTransferDto dto);

    static CashierService create(Options options) {
        if (options.getExchange().equals(ExchangeEnum.CASHIER)) {
            return new CashierClient(options);
        }
        throw new SDKException(SDKException.INPUT_ERROR, "Unsupport Exchange.");
    }
}
