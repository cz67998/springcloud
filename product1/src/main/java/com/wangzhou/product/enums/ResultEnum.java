package com.wangzhou.product.enums;

import lombok.Getter;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/15
 * Time:11:55
 **/
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(1,"商品不存在"),
    PRODUCT_NOT_ENOUGH(1,"商品库存不足"),
    ;
    private Integer code;
    private String message;
    ResultEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }

}
