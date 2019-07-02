package com.wangzhou.product.service;

import com.wangzhou.product.dataobject.ProductCategory;

import java.util.List;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/29
 * Time:11:50
 **/
public interface CategoryService {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
