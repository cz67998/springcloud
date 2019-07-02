package com.wangzhou.product.service.impl;

import com.wangzhou.product.dataobject.ProductInfo;
import com.wangzhou.product.dto.CartDTO;
import com.wangzhou.product.enums.ProductStatusEnum;
import com.wangzhou.product.enums.ResultEnum;
import com.wangzhou.product.exception.ProductException;
import com.wangzhou.product.repository.ProductInfoRepository;
import com.wangzhou.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
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
        }
    }
}
