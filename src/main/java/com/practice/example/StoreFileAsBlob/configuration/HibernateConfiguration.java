package com.practice.example.StoreFileAsBlob.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {
	@Value("${db.driver}")
	private String DRIVER;

	@Value("${db.url}")
	private String URL;

	@Value("${db.username}")
	private String USERNAME;

	@Value("${db.password}")
	private String PASSWORD;

	@Value("${hb.dialect}")
	private String DIALECT;

	@Value("${hb.packageToScan}")
	private String PACKAGE_TO_SCAN;

	@Value("${hb.hbm2ddl.auto}")
	private String HBM2DDL_AUTO;

	@Value("${hb.show_sql}")
	private String SHOW_SQL;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(DRIVER);
		driverManagerDataSource.setUrl(URL);
		driverManagerDataSource.setUsername(USERNAME);
		driverManagerDataSource.setPassword(PASSWORD);
		return driverManagerDataSource;
	}

	@Bean
	public LocalSessionFactoryBean localSessionFactoryBean() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan(PACKAGE_TO_SCAN);
		Properties properties = new Properties();
		properties.put("hibernate.dialect", DIALECT);
		properties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
		properties.put("hibernate.show_sql", SHOW_SQL);
		factoryBean.setHibernateProperties(properties);
		return factoryBean;
	}

	@Bean
	public HibernateTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(localSessionFactoryBean().getObject());
		return hibernateTransactionManager;
	}
}
