package com.wangzhou.user.service.impl;

import com.wangzhou.user.dataobject.UserInfo;
import com.wangzhou.user.repository.UserinfoRepository;
import com.wangzhou.user.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/23
 * Time:9:29
 **/
@Service
public class UserinfoServiceImpl implements UserinfoService {
    @Autowired
    private UserinfoRepository userinfoRepository;
    @Override
    public UserInfo findByOpenid(String openid) {
        return userinfoRepository.findByOpenid(openid);
    }
}
