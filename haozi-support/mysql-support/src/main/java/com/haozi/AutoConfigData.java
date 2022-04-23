package com.haozi;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.haozi.Interceptor.SnowIdInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/22 4:21 下午
 */
@Configuration
@AutoConfigureAfter({MybatisPlusAutoConfiguration.class})
public class AutoConfigData {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @PostConstruct
    public void addPageInterceptor() {


        SnowIdInterceptor idInterceptor = new SnowIdInterceptor();

        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(idInterceptor);
        }


    }


}
