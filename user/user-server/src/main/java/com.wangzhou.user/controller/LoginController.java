package com.wangzhou.user.controller;

import com.wangzhou.user.VO.ResultVO;
import com.wangzhou.user.constant.CookieConstant;
import com.wangzhou.user.constant.RedisConstant;
import com.wangzhou.user.dataobject.UserInfo;
import com.wangzhou.user.enums.ResultEnum;
import com.wangzhou.user.enums.RoleEnum;
import com.wangzhou.user.service.UserinfoService;
import com.wangzhou.user.util.CookieUtil;
import com.wangzhou.user.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/23
 * Time:9:31
 **/
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 买家登录
     *
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid, HttpServletResponse response) {
        //1.openid和数据库里的openid相匹配
        UserInfo userinfo = userinfoService.findByOpenid(openid);
        System.out.println(openid + " " + userinfo);
        if (userinfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_ERROR);
        }
        //2.判断角色
        if (!RoleEnum.BUYER.getCode().equals(userinfo.getRole())) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        //3.设置cookie
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);
        return ResultVOUtil.success();
    }

    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid, HttpServletRequest request, HttpServletResponse response) {
        //判断是否已登录
        String token = UUID.randomUUID().toString();
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null
                && !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue()))
        )) {
            return ResultVOUtil.success();
        } else {//1.openid和数据库里的openid相匹配
            UserInfo userinfo = userinfoService.findByOpenid(openid);
            System.out.println(openid + " " + userinfo);
            if (userinfo == null) {
                return ResultVOUtil.error(ResultEnum.LOGIN_ERROR);
            }
            //2.判断角色
            if (!RoleEnum.SELLER.getCode().equals(userinfo.getRole())) {
                return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
            }
            //3.往redis里写     TimeUnit.SECONDS单位s

            stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token)
                    , openid, RedisConstant.expire, TimeUnit.SECONDS);
            //4.设置cookie
            CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.expire);
            return ResultVOUtil.success();
        }

    }
}
