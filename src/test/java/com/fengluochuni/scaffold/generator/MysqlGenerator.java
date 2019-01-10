package com.fengluochuni.scaffold.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 代码生成器演示
 * 
 * @author rongsheng.xu
 * @since 2019-1-10
 */
public class MysqlGenerator {

    /** 配置文件路径 */
    private final static String PROPERTIES_PATH = "/config/application.properties";
    /** 生成文件目录 */
    private final static String OUTPUT_DIR_PATH = "D:/BaiduNetdiskDownload/gencode";
    /** 生成前端JSP页面目录 */
    private final static String VIEW_OUTPUT_DIR_PATH = OUTPUT_DIR_PATH + "/view/";
    /* 获取 JDBC 配置文件 */
    private final Properties props = getProperties();

    private final AutoGenerator mpg = new AutoGenerator();

    /**
     * MySQL 生成演示
     */
    public static void main(String[] args) {
        MysqlGenerator mysqlGenerator = new MysqlGenerator();
        mysqlGenerator.buildAndExecutorGenerator();
    }

    /**
     * 构造并执行代码生成
     */
    public void buildAndExecutorGenerator(){
        buildAutoGenerator();
        mpg.execute();
    }

    /**
     * 执行代码生成
     */
    private void executeGenerator(){
        mpg.execute();
    }

    /**
     * 构造代码生成配置
     * @return
     */
    private AutoGenerator buildAutoGenerator(){
        // 全局配置
        GlobalConfig gc = buildGlobalConfig();
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = buildDataSourceConfig();
        mpg.setDataSource(dsc);
        // 策略配置
        StrategyConfig strategy = buildStrategyConfig();
        mpg.setStrategy(strategy);
        // 包配置
        PackageConfig pc = buildPackageConfig();
        mpg.setPackageInfo(pc);
        // 自定义配置
        InjectionConfig ic = buildInjectionConfig();
        mpg.setCfg(ic);
        return mpg;
    }

    /**
     * 全局配置
     * @return
     */
    private GlobalConfig buildGlobalConfig(){
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(OUTPUT_DIR_PATH);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);// 开启 activeRecord 模式
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("rongsheng.xu");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("I%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        return gc;
    }

    /**
     * 数据源配置
     * @return
     */
    private DataSourceConfig buildDataSourceConfig(){
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert());
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(props.getProperty("db.master.user"));
        dsc.setPassword(props.getProperty("db.master.password"));
        dsc.setUrl(props.getProperty("db.master.url"));
        return dsc;
    }

    /**
     * 策略配置
     * @return
     */
    private StrategyConfig buildStrategyConfig(){
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名
        // strategy.setDbColumnUnderline(true);//全局下划线命名
//		strategy.setTablePrefix(new String[] { "bmd_", "mp_" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略

        strategy.setInclude(new String[] { "temp_ywy_match" }); // 需要生成的表
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表

        // 自定义实体父类
        // strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });

        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");

        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");

        // 自定义 controller 父类
        strategy.setSuperControllerClass("BaseController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuliderModel(true);
        return strategy;
    }

    /**
     * 包配置
     * @return
     */
    private PackageConfig buildPackageConfig(){
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("test"); //所属模块
        pc.setParent("com.fengluochuni.scffold"); // 自定义包路径
        pc.setController("controller"); // 这里是控制器包名，默认 web
        pc.setEntity("model");
        pc.setXml("sqlMapperXml");
        return pc;
    }

    /**
     * 自定义配置
     * <p>注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值</p>
     *
     * @return
     */
    private InjectionConfig buildInjectionConfig(){
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {}
        };
        // 生成的模版路径，不存在时需要先新建
        File viewDir = new File(VIEW_OUTPUT_DIR_PATH);
        if (!viewDir.exists()) {
            viewDir.mkdirs();
        }
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/templates/add.jsp.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getGeneratorViewPath(VIEW_OUTPUT_DIR_PATH, tableInfo, "Add.jsp");
            }
        });
        focList.add(new FileOutConfig("/templates/edit.jsp.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getGeneratorViewPath(VIEW_OUTPUT_DIR_PATH, tableInfo, "Edit.jsp");
            }
        });
        focList.add(new FileOutConfig("/templates/list.jsp.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getGeneratorViewPath(VIEW_OUTPUT_DIR_PATH, tableInfo, "List.jsp");
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

	/**
	 * 获取配置文件
	 *
	 * @return 配置Props
	 */
	private static Properties getProperties() {
		// 读取配置文件
		Resource resource = new ClassPathResource(PROPERTIES_PATH);
		Properties props = new Properties();
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
	
	/**
	 * 页面生成的文件名
	 */
	private static String getGeneratorViewPath(String viewOutputDir, TableInfo tableInfo, String suffixPath) {
		String name = StringUtils.firstToLowerCase(tableInfo.getEntityName());
		String path = viewOutputDir + "/" + name + "/" + name + suffixPath;
		File viewDir = new File(path).getParentFile();
		if (!viewDir.exists()) {
			viewDir.mkdirs();
		}
		return path;
	}
}