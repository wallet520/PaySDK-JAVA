package com.block.model.resp;

import lombok.Data;

import java.util.List;


@Data
public class SellerAccountVo {
    private Double allAmount;

    private Double allAmountCoverUSD;

    private List<SellerAccountDto> sellerAccounts;
}
