package com.block.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.block.client.connection.RestConnection;
import com.block.constant.Options;
import com.block.constant.ResponseResult;
import com.block.model.req.ProductInfoAddDto;
import com.block.model.req.ProductsInfoQueryDto;
import com.block.model.req.RechargeAddDto;
import com.block.model.req.SellerTransferDto;
import com.block.model.resp.*;
import com.block.service.CashierService;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class CashierClient implements CashierService {

    private final RestConnection restConnection;

    private static final String GET_CASHIER_ACCOUNT = "/api/v1/cashier/accounts";

    private static final String GET_CASHIER_PRODUCTS = "/api/v1/cashier/products";

    private static final String POST_CASHIER_ADD_PRODUCT = "/api/v1/cashier/product";

    private static final String POST_CASHIER_ADD_RECHARGE = "/api/v1/cashier/add/recharge";

    private static final String POST_CASHIER_ADD_TRANSFER = "/api/v1/cashier/add/transfer";

    public CashierClient(Options options) {
        this.restConnection = new RestConnection(options);
    }

    @Override
    public SellerAccountVo getSellerAccountVo() {
        JSONObject result = restConnection.executeGetWithSignature(GET_CASHIER_ACCOUNT,null);
        ResponseResult<SellerAccountVo> responseResult = JSONObject.parseObject(result.toJSONString(),ResponseResult.class);
        log.info("---result:{}",responseResult);
        return JSON.parseObject(JSON.toJSONString(responseResult.getData()),SellerAccountVo.class);
    }

    @Override
    public List<ProductsInfoVo> listProducts(ProductsInfoQueryDto dto) {
        JSONObject result = restConnection.executeGetWithSignature(GET_CASHIER_PRODUCTS,objectToMap(dto));
        ResponseResult<List<ProductsInfoVo>> responseResult = JSONObject.parseObject(result.toJSONString(),ResponseResult.class);
        log.info("---result:{}",responseResult);
        return JSON.parseObject(JSON.toJSONString(responseResult.getData()),List.class);
    }

    @Override
    public ProductsVo addProducts(ProductInfoAddDto dto) {
        JSONObject result = restConnection.executePostWithSignature(POST_CASHIER_ADD_PRODUCT, JSON.toJSONString(dto));
        ResponseResult<ProductsVo> responseResult = JSONObject.parseObject(result.toJSONString(),ResponseResult.class);
        log.info("---result:{}",responseResult);
        return JSON.parseObject(JSON.toJSONString(responseResult.getData()),ProductsVo.class);
    }

    @Override
    public RechargeAddVo addRecharge(RechargeAddDto dto) {
        JSONObject result = restConnection.executePostWithSignature(POST_CASHIER_ADD_RECHARGE, JSON.toJSONString(dto));
        ResponseResult<RechargeAddVo> responseResult = JSONObject.parseObject(result.toJSONString(),ResponseResult.class);
        log.info("---result:{}",responseResult);
        return JSON.parseObject(JSON.toJSONString(responseResult.getData()),RechargeAddVo.class);
    }

    @Override
    public TransferTradeVo sellerTransfer(SellerTransferDto dto) {
        JSONObject result = restConnection.executePostWithSignature(POST_CASHIER_ADD_TRANSFER, JSON.toJSONString(dto));
        ResponseResult<TransferTradeVo> responseResult = JSONObject.parseObject(result.toJSONString(),ResponseResult.class);
        log.info("---result:{}",responseResult);
        return JSON.parseObject(JSON.toJSONString(responseResult.getData()),TransferTradeVo.class);
    }


    //java对象转map
    public Map<String, Object> objectToMap(Object obj) {
        String jsonStr = JSONObject.toJSONString(obj);
        return JSONObject.parseObject(jsonStr);
    }
}
