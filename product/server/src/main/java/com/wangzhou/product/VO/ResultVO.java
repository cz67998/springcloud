package com.wangzhou.product.VO;

import lombok.Data;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/29
 * Time:14:16
 **/
@Data
public class ResultVO<T> {
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;
}
