package com.wangzhou.user.enums;

import lombok.Getter;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/23
 * Time:10:01
 **/
@Getter
public enum RoleEnum {
    BUYER(1,"买家"),
    SELLER(2,"卖家"),
    ;
    private Integer code;
    private String message;
    RoleEnum(Integer code, String message){
        this.code=code;
        this.message=message;
    }
}
