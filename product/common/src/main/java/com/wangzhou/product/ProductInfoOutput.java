package com.wangzhou.product;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/16
 * Time:20:48
 **/
@Data
public class ProductInfoOutput {

    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 状态, 0正常1下架. */
    private Integer productStatus;

    /** 类目编号. */
    private Integer categoryType;
}
