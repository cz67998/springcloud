package com.wangzhou.product.service;

import com.wangzhou.product.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/29
 * Time:14:09
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryServiceTest {
@Autowired
private  CategoryService categoryService;
    @Test
    public void findByCategoryTypeIn() {
      List<ProductCategory> list=  categoryService.findByCategoryTypeIn(java.util.Arrays.asList(11,22));
        Assert.assertTrue(list.size()>0);
    }
}