package com.example.demo.mybatisplus;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @author yao
 * @date 2019/12/11
 */

public class GeneratorDemo {

    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator();

        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = "D:\\autoTestSpace\\demo";
        globalConfig.setOutputDir(projectPath + "\\src\\main\\java");
        globalConfig.setAuthor("yao");
        globalConfig.setOpen(false);
        generator.setGlobalConfig(globalConfig);

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/xoauth?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&serverTimezone=Asia/Shanghai");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        generator.setDataSource(dataSourceConfig);

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName("automodule");
        packageConfig.setParent("com.example.demo.mybatisplus");
        generator.setPackageInfo(packageConfig);

        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };
//        String templatePath = "/template/mapper.xml.ftl";
//        List<FileOutConfig> fileOutConfigList = new ArrayList<>();
//        fileOutConfigList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return projectPath + "/src/main/resources/mapper/" + packageConfig.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//        injectionConfig.setFileOutConfigList(fileOutConfigList);
        generator.setCfg(injectionConfig);
//
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        generator.setTemplate(templateConfig);

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategyConfig.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
//        strategyConfig.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategyConfig.setSuperEntityColumns("id");
        strategyConfig.setInclude("app_key_settings");
        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setTablePrefix(packageConfig.getModuleName() + "_");

        generator.setStrategy(strategyConfig);
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        generator.execute();
    }
}
