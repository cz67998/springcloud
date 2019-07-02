package com.wangzhou.product.repository;

import com.wangzhou.product.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/29
 * Time:10:57
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCategoryRepositoryTest {
@Autowired
private ProductCategoryRepository productCategoryRepository;
@Autowired
private ProductInfoRepository productInfoRepository;
    @Test
    public void findByCategoryTypeIn() {
      //  List<ProductCategory> list=productInfoRepository.findByProductIdIn()
      List<ProductCategory> list=productCategoryRepository.findByCategoryTypeIn(Arrays.asList(11,22));
        Assert.assertTrue(list.size()>0);
    }
}