package com.wangzhou.order.utils;

import com.wangzhou.order.VO.ResultVO;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/29
 * Time:21:03
 **/
public class ResultVOUtil {
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
}
