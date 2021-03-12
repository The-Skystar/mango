package com.tss.mangoauth;

import com.github.davidfantasy.mybatisplus.generatorui.GeneratorConfig;
import com.github.davidfantasy.mybatisplus.generatorui.MybatisPlusToolsApplication;
import com.github.davidfantasy.mybatisplus.generatorui.mbp.NameConverter;

/**
 * @author YC01224 yangxiangjun
 * @description
 * @date 2021/3/8 15:03
 * @since JDK 1.8
 */
public class GeberatorUIServer {
    public static void main(String[] args) {
        GeneratorConfig config = GeneratorConfig.builder().jdbcUrl("jdbc:mysql://127.0.0.1:3306/mango?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC")
                .userName("root")
                .password("root")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                //数据库schema，POSTGRE_SQL,ORACLE,DB2类型的数据库需要指定
                .schemaName("mango")
                //如果需要修改各类生成文件的默认命名规则，可自定义一个NameConverter实例，覆盖相应的名称转换方法：
                .nameConverter(new NameConverter() {
                    /**
                     * 自定义Service类文件的名称规则
                     */
                    @Override
                    public String serviceNameConvert(String tableName) {
                        return this.entityNameConvert(tableName) + "Service";
                    }
                    /**
                     * 自定义Controller类文件的名称规则
                     */
                    @Override
                    public String controllerNameConvert(String tableName) {
                        return this.entityNameConvert(tableName) + "Controller";
                    }
                })
                .basePackage("com.tss.mangoauth")
                .port(8068)
                .build();
        MybatisPlusToolsApplication.run(config);
    }
}
