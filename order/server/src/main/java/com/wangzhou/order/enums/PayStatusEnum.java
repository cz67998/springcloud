package com.wangzhou.order.enums;

import lombok.Getter;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/29
 * Time:18:57
 **/
@Getter
public enum PayStatusEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    ;
    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
