package com.wangzhou.product.service.impl;

import com.wangzhou.product.DecreaseStockInput;
import com.wangzhou.product.ProductInfoOutput;
import com.wangzhou.product.dataobject.ProductInfo;
import com.wangzhou.product.enums.ProductStatusEnum;
import com.wangzhou.product.enums.ResultEnum;
import com.wangzhou.product.exception.ProductException;
import com.wangzhou.product.repository.ProductInfoRepository;
import com.wangzhou.product.service.ProductService;
import com.wangzhou.product.util.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/29
 * Time:11:09
 **/
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> cartDTOList) {

        List<ProductInfo> productInfoList= decreaseStockUtil(cartDTOList);
        List<ProductInfoOutput> productInfoOutputList=productInfoList.stream().map(e->{
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(e,productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());

        //发送扣库存的mq消息
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));

    }

//    将这个方法独立出来，并进行事务回滚处理
    @Transactional
    public List<ProductInfo> decreaseStockUtil(List<DecreaseStockInput> cartDTOList) {
       List<ProductInfo> productInfoList=new ArrayList<>();
        for (DecreaseStockInput cartDTO : cartDTOList) {
            Optional<ProductInfo> productInfo = productInfoRepository.findById(cartDTO.getProductId());
            if (!productInfo.isPresent()) {
                //判断商品是否存在
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo1 = productInfo.get();
            //库存-购买量
            Integer result = productInfo1.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                //判断商品库存是否足够
                throw new ProductException(ResultEnum.PRODUCT_NOT_ENOUGH);
            }
            productInfo1.setProductStock(result);
            productInfoRepository.save(productInfo1);
            productInfoList.add(productInfo1);
            }
            return productInfoList;
    }
}
