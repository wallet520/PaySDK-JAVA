package com.block.model.resp;



import lombok.Data;
import java.util.Date;

@Data
public class TransferTradeVo {

    private String externalOrderId;

    private Long transferId;

    private Long sellerId;

    private Double amount;

    private String addressTo;

    private Double rate;

    private String currencyType;

    private Double transFee;

    private Double serviceFee;

    private Integer orderStatus;

    private String tradeHash;

    private Integer assetType;

    private Integer tokenType;

    private Date orderTime;

    private Date createTime;

    private Date updateTime;

}
