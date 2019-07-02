package com.wangzhou.user.repository;

import com.wangzhou.user.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/23
 * Time:9:24
 **/
public interface UserinfoRepository extends JpaRepository<UserInfo,String>{


    UserInfo findByOpenid(String openid);
}
