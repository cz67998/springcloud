package com.wangzhou.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/19
 * Time:10:48
 **/
@Data
@Component
@ConfigurationProperties("wangzhou")
@RefreshScope
public class WangzhouConfig {
    private String age;
}
