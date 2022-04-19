package com.haozi.gw.config;



import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/17 7:39 下午
 */
@ConfigurationProperties("gw")
@Component
@Data
public class AnonymousConfig {

    private List<String> anon;
}
