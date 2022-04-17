package com.haozi.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.stereotype.Component;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 9:46 上午
 */
@ConfigurationProperties("token.config")
@Data
@Component
public class TokenConfig {
    long limit;


}
