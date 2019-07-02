package com.wangzhou.product.repository;

import com.wangzhou.product.dataobject.ProductInfo;
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
 * Time:10:41
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {
@Autowired
private ProductInfoRepository productInfoRepository;
    @Test
    public void findByProductStatus() {
       List<ProductInfo> list= productInfoRepository.findByProductStatus(0);
      Assert.assertTrue(list.size()>0);
    }

    @Test
    public void findByProductIdIn() {
    }

    @Test
    public void findByProductIdIn1() {
        List<ProductInfo> list=productInfoRepository.findByProductIdIn(Arrays.asList("1","2"));
        Assert.assertTrue(list.size()>0);
    }
}