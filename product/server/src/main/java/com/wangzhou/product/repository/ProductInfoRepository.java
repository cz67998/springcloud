package com.wangzhou.product.repository;

import com.wangzhou.product.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/29
 * Time:10:37
 **/
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>{
    List<ProductInfo>  findByProductStatus(Integer productstatus);
    // in (List<Long>)  加in是把List作为入参
    List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
