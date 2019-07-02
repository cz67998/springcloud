package com.wangzhou.order.exception;

import com.wangzhou.order.enums.ResultEnum;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/29
 * Time:20:58
 **/
public class OrderException extends RuntimeException {
    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
