package com.wangzhou.user.dataobject;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/23
 * Time:9:12
 **/
@Data
@Entity
public class UserInfo {
    @Id
    private String id;
    private String username;
    private String password;
    private String openid;
    private Integer role;
}
