package com.wangzhou.product.service;

import com.wangzhou.product.DecreaseStockInput;
import com.wangzhou.product.dataobject.ProductInfo;
import com.wangzhou.product.dto.CartDTO;

import java.util.List;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/29
 * Time:11:07
 **/
public interface ProductService {
    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品类表
     * @param productIdList
     * @return
     */
    List<ProductInfo> findList(List<String> productIdList);
    void decreaseStock(List<DecreaseStockInput> cartDTOList);
}
