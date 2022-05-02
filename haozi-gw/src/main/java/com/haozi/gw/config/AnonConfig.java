package com.haozi.gw.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/5/2 10:51 下午
 */
@ConfigurationProperties(prefix = "gw.anon")
@Data
@Component
public class AnonConfig {

    private List<String> urls;

}
