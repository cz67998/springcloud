package com.wangzhou.user.util;


import com.wangzhou.user.VO.ResultVO;
import com.wangzhou.user.enums.ResultEnum;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/29
 * Time:21:03
 **/
public class ResultVOUtil {
    public static ResultVO success() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
    public static ResultVO error(ResultEnum resultEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(1);
        resultVO.setMsg(resultEnum.getMessage());

        return resultVO;
    }
}
