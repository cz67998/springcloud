package com.wangzhou.product.service.impl;

import com.wangzhou.product.dataobject.ProductInfo;
import com.wangzhou.product.dto.CartDTO;
import com.wangzhou.product.service.ProductService;
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
 * Time:11:25
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() {
        List<ProductInfo> list = productService.findUpAll();
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void findList() {
        List<ProductInfo> list=productService.findList(Arrays.asList("1","2"));
        Assert.assertTrue(list.size()>0);
    }

    @Test
    public void decreaseStock() {
        CartDTO cartDTO=new CartDTO("1",1);
        productService.decreaseStock(Arrays.asList(cartDTO));
    }
}