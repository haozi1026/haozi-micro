package com.haozi;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

import java.util.Collections;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 3:51 下午
 */
public class Generater {

    public static void main(String[] args) {


        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder("jdbc:mysql://1.15.157.125:3306/test_demo", "application", "19960118")
                .dbQuery(new MySqlQuery())
                .schema("test_demo")
                .typeConvert(new MySqlTypeConvert())
                .keyWordsHandler(new MySqlKeyWordsHandler())
                .build();

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator(dataSourceConfig);
        //2. 全局配置
        GlobalConfig globalConfig = new GlobalConfig.Builder()
                .outputDir("/Users/yihaozhao/development/workspace/self/haozi-micro/haozi-account/src/main/java/")
                .author("haozi")
                .dateType(DateType.TIME_PACK)
                .commentDate("yyyy-MM-dd")
                .build();



        //包配置
        PackageConfig packageConfig = new PackageConfig.Builder()
                .parent("com.haozi.account")
                .entity("dao.po")
                .service("service")
                .serviceImpl("service.impl")
                .mapper("dao.mapper")
                .xml("mapper.xml")
                .pathInfo(Collections.singletonMap(OutputFile.xml, "/Users/yihaozhao/development/workspace/self/haozi-micro/haozi-account/src/main/resources/mapper"))
                .build();


        // 5、策略配置
        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .enableCapitalMode()
                .enableSkipView()
                .disableSqlFilter()
                .addInclude("user_role,token_config,role_resources,role,resouces")
                .addTablePrefix("t_", "c_")
                .entityBuilder().enableLombok().fileOverride()
                .build();

        strategyConfig.entityBuilder().fileOverride().enableLombok();

        // 6、执行
        mpg.global(globalConfig);
        mpg.strategy(strategyConfig);
        mpg.packageInfo(packageConfig);
        mpg.execute();




    }
}
