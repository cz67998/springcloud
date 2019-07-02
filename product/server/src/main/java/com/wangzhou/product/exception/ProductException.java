package com.wangzhou.product.exception;

import com.wangzhou.product.enums.ResultEnum;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/15
 * Time:11:50
 **/
public class ProductException  extends RuntimeException{
   private Integer code;
   public ProductException(Integer code,String message){
      super(message);
      this.code=code;
   }
   public ProductException(ResultEnum resultEnum){
    super(resultEnum.getMessage());
    this.code=resultEnum.getCode();
   }
}
