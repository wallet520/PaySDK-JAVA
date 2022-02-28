package com.block.model.resp;

import lombok.Data;

import java.util.Date;

@Data
public class TradeListVo {

    private Long tradeId;

    private Long productId;

    private Integer tradeType;

    private String clientIp;

    private String tradeHash;

    private Date tradeTime;

    private Integer tradeStatus;

    private Date payTime;

    private Integer assetType;

    private Integer tokenType;

    private Double tradeAmount;

    private Double actualPayAmount;

    private Double conversionExchangeRate;

    private String addressFrom;

    private String addressTo;

    private String remark;

    private Date createTime;

    private Date updateTime;

}
