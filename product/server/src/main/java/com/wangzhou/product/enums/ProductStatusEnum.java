package com.wangzhou.product.enums;

import lombok.Getter;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/29
 * Time:11:20
 **/
@Getter
public enum  ProductStatusEnum {
    UP(0,"在架"),
    DOWN(1,"下架"),
    ;
    private Integer code;
    private  String message;
    ProductStatusEnum(Integer code,String message){
       this.code=code;
       this.message=message;
    }


}
