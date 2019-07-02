package com.wangzhou.product.dto;

import lombok.Data;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/15
 * Time:11:35
 **/
@Data
public class CartDTO {
    private String productId;
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
