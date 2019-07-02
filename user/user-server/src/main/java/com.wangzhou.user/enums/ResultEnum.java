package com.wangzhou.user.enums;

import lombok.Getter;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/15
 * Time:11:55
 **/
@Getter
public enum ResultEnum {

    LOGIN_ERROR(1,"登录失败"),
    ROLE_ERROR(2,"用户权限有误"),
    ;
    private Integer code;
    private String message;
    ResultEnum(Integer code, String message){
        this.code=code;
        this.message=message;
    }

}
