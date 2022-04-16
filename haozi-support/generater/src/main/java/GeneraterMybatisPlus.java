import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

import java.util.Collections;

/**
 * @author zyh
 * @version 1.0
 * @date 2022/4/16 3:33 下午
 */
public class GeneraterMybatisPlus {

    public static void main(String[] args) {

        new DataSourceConfig.Builder("jdbc:mysql://1.15.157.125:3306","application","19960118")
                .dbQuery(new MySqlQuery())
                .schema("test_demo")
                .typeConvert(new MySqlTypeConvert())
                .keyWordsHandler(new MySqlKeyWordsHandler())
                .build();
    }

}
