package a.demo.server.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class TheAutoGenerator {
    public static void main(String[] args) {
        generator(true,true,true,true,true,true);
    }
//    final static String[] TABLE_NAMES={"user","article","article_comment","article_up_down"};
//    final static String[] TABLE_NAMES={"role","permission","user_bind_role","role_bind_permission"};
    final static String[] TABLE_NAMES={};

    final static String DRIVER_NAME="com.mysql.cj.jdbc.Driver";
    final static String URL="jdbc:mysql://127.0.0.1:3306/a_database?serverTimezone=UTC";
    final static String USERNAME="root";
    final static String PASSWORD="root";

    final static String PROJECT_MODULE="";
    final static String PACKAGE_PARENT="a.demo.server";
    final static String PACKAGE_MODULE="module";

    static void generator(boolean entity,boolean mapper,boolean xml,boolean service,boolean serviceImpl,boolean controller){
        AutoGenerator autoGenerator=new AutoGenerator();

        GlobalConfig globalConfig=new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir")+("".equals(PROJECT_MODULE)?"":"/"+PROJECT_MODULE)+"/src/main/java/");
        globalConfig.setFileOverride(false);
        globalConfig.setOpen(true);
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setIdType(IdType.ID_WORKER);
        globalConfig.setAuthor("Master Spark");
        autoGenerator.setGlobalConfig(globalConfig);

        DataSourceConfig dataSourceConfig=new DataSourceConfig();
        dataSourceConfig.setDriverName(DRIVER_NAME);
        dataSourceConfig.setUrl(URL);
        dataSourceConfig.setUsername(USERNAME);
        dataSourceConfig.setPassword(PASSWORD);
        autoGenerator.setDataSource(dataSourceConfig);

        PackageConfig packageConfig=new PackageConfig();
        packageConfig.setParent(PACKAGE_PARENT);
        packageConfig.setModuleName(PACKAGE_MODULE);
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setXml("mapper.xml");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setController("controller");
        autoGenerator.setPackageInfo(packageConfig);

        TemplateConfig templateConfig=new TemplateConfig();
        templateConfig.setEntity("template/mine/entity.java");
        templateConfig.setMapper("template/mine/mapper.java");
        templateConfig.setXml("template/mine/mapper.xml");
        templateConfig.setService("template/mine/service.java");
        templateConfig.setServiceImpl("template/mine/serviceImpl.java");
        templateConfig.setController("template/mine/controller.java");
        if (!entity)
            templateConfig.setEntity(null);
        if (!mapper)
            templateConfig.setMapper(null);
        if (!xml)
            templateConfig.setXml(null);
        if (!service)
            templateConfig.setService(null);
        if (!serviceImpl)
            templateConfig.setServiceImpl(null);
        if (!controller)
            templateConfig.setController(null);
        autoGenerator.setTemplate(templateConfig);

        StrategyConfig strategyConfig=new StrategyConfig();
        strategyConfig.setInclude(TABLE_NAMES);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.execute();
    }
}
