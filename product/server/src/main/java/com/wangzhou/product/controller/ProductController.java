package com.wangzhou.product.controller;

import com.wangzhou.product.DecreaseStockInput;
import com.wangzhou.product.VO.ProductInfoVO;
import com.wangzhou.product.dataobject.ProductCategory;
import com.wangzhou.product.VO.ProductVO;
import com.wangzhou.product.VO.ResultVO;
import com.wangzhou.product.dataobject.ProductInfo;
import com.wangzhou.product.dto.CartDTO;
import com.wangzhou.product.service.ProductService;
import com.wangzhou.product.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/29
 * Time:9:39
 **/
@RestController
@RequestMapping("/product")
public class ProductController {
    /**
     * 1.查询所有在架的商品
     * 2.获取类目type列表
     * 3.查询类目
     * 4。构造数据
     */
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    //@CrossOrigin(allowCredentials = "true")//allowCredentials允许cookie跨域
    public ResultVO<ProductVO> list() {
        List<ProductInfo> productInfoList = productService.findUpAll();
        //lambda构造器引用，将productInfoList字符串列表，把它转换成ProductInfo对象
        //为此要在各个字符串上调用构造器
        //作用是获得CategoryType的list
        List<Integer> categoryTypeList = productInfoList.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
        //就算categoryType有重复也没有关系，3个查到两个也正常
        List<ProductCategory> ProductCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        System.out.println("ProductCategoryList.size()"+ProductCategoryList.size());
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : ProductCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if(productCategory.getCategoryType().equals(productInfo.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
//              productInfoVO.setProductDescription(productInfo.getProductDescription());
//              productInfoVO.
                    BeanUtils.copyProperties(productInfo, productInfoVO);//把productInfo拷贝到productInfoVO  如果productInfoVO不存在的属性就不会copy过去就
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return success(productVOList);
    }

    /**
     * 给订单服务用的获取商品列表
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList) throws InterruptedException {
     //  Thread.sleep(2000);
     return  productService.findList(productIdList);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> cartDTOList){
        productService.decreaseStock(cartDTOList);
    }

    private ResultVO success(List<ProductVO> productVOList) {
        ResultVO resultVO=new ResultVO();
        resultVO.setData(productVOList);
        resultVO.setCode(0);
        resultVO.setMsg("成功");

        return resultVO;
    }
    private ResultVO fail(List<ProductVO> productVOList) {
        ResultVO resultVO=new ResultVO();
        resultVO.setData(productVOList);
        resultVO.setCode(1);
        resultVO.setMsg("失败");
        return resultVO;
    }

}
