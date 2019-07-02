package com.wangzhou.user.service;

import com.wangzhou.user.dataobject.UserInfo;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/23
 * Time:9:27
 **/
public interface UserinfoService {
    UserInfo findByOpenid(String openid);
}
