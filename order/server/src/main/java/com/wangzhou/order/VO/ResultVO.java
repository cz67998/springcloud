package com.wangzhou.order.VO;

import lombok.Data;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/29
 * Time:21:04
 **/
@Data
public class ResultVO<T> {
    private Integer code;

    private String msg;

    private T data;
}
