package com.wangzhou.order.enums;

import lombok.Getter;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/29
 * Time:18:54
 **/
@Getter
public enum OrderStatusEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "取消"),
    ;
    private Integer code;
    private String message;
    OrderStatusEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
