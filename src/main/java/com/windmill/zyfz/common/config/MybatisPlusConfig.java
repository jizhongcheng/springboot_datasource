package com.windmill.zyfz.common.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.windmill.zyfz.common.dbconfig.DBTypeEnum;
import com.windmill.zyfz.common.dbconfig.DynamicDataSource;

/**
 * @Author DGD
 * @date 2018/2/6.
 */
@Configuration
@MapperScan({ "com.windmill.zyfz.api.mapper*" })
public class MybatisPlusConfig {

	/**
	 * mybatis-plus分页插件<br>
	 * 文档：http://mp.baomidou.com<br>
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		// paginationInterceptor.setLocalPage(true);// 开启 PageHelper 的支持
		return paginationInterceptor;
	}

	/**
	 * mybatis-plus SQL执行效率插件【生产环境可以关闭】
	 */
	@Bean
	public PerformanceInterceptor performanceInterceptor() {
		return new PerformanceInterceptor();
	}

	@Bean(name = "zyfz")
	@ConfigurationProperties(prefix = "spring.datasource.zyfz")
	public DataSource db1() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean(name = "wlxy")
	@ConfigurationProperties(prefix = "spring.datasource.wlxy")
	public DataSource db2() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean(name = "js_zyfz")
	@ConfigurationProperties(prefix = "spring.datasource.js_zyfz")
	public DataSource db3() {
		return DruidDataSourceBuilder.create().build();
	}
	
	/**
	 * 动态数据源配置
	 * 
	 * @return
	 */
	@Bean
	@Primary
	public DataSource multipleDataSource(@Qualifier("zyfz") DataSource db1, @Qualifier("wlxy") DataSource db2, @Qualifier("js_zyfz") DataSource db3) {
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DBTypeEnum.db1.getValue(), db1);
		targetDataSources.put(DBTypeEnum.db2.getValue(), db2);
		targetDataSources.put(DBTypeEnum.db3.getValue(), db3);
		dynamicDataSource.setTargetDataSources(targetDataSources);
		dynamicDataSource.setDefaultTargetDataSource(db1);
		return dynamicDataSource;
	}

	@Bean("sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(multipleDataSource(db1(), db2(), db3()));
		sqlSessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:/mapperMysql/**/*Mapper.xml"));

		MybatisConfiguration configuration = new MybatisConfiguration();
		// configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
		configuration.setJdbcTypeForNull(JdbcType.NULL);
		configuration.setMapUnderscoreToCamelCase(true);
		configuration.setCacheEnabled(false);
		sqlSessionFactory.setConfiguration(configuration);
		sqlSessionFactory.setPlugins(new Interceptor[] { // PerformanceInterceptor(),OptimisticLockerInterceptor()
				paginationInterceptor() });
		sqlSessionFactory.setGlobalConfig(globalConfiguration());
		return sqlSessionFactory.getObject();
	}

	@Bean
	public GlobalConfiguration globalConfiguration() {
		GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
		conf.setLogicDeleteValue("-1");
		conf.setLogicNotDeleteValue("1");
		conf.setIdType(0);
		conf.setMetaObjectHandler(new MyMetaObjectHandler());
		conf.setDbColumnUnderline(true);
		conf.setRefresh(true);
		return conf;
	}

}
