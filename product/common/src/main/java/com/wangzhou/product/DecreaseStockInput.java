package com.wangzhou.product;

import lombok.Data;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/16
 * Time:20:47
 **/
@Data
public class DecreaseStockInput {

    private String productId;

    private Integer productQuantity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
