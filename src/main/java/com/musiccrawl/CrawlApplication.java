package com.musiccrawl;

import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableJpaRepositories("com.musiccrawl.common.repository")
@EnableTransactionManagement
@ServletComponentScan
public class CrawlApplication {

	@Value("${spring.beetl.root}") String templatesPath;//模板跟目录
	@Bean(initMethod = "init", name = "beetlConfig")
	public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {

		BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
		ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader();
		beetlGroupUtilConfiguration.setResourceLoader(classpathResourceLoader);

		//读取配置文件信息
       // beetlGroupUtilConfiguration.setConfigFileResource(patternResolver.getResource("classpath:beetl.properties"));
		return beetlGroupUtilConfiguration;
	}

	@Bean(name = "beetlViewResolver")
	public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
		BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
//		beetlSpringViewResolver.setPrefix("/templates/");
		beetlSpringViewResolver.setSuffix(".html");
		beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
		beetlSpringViewResolver.setOrder(0);
		beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
		return beetlSpringViewResolver;
	}
	public static void main(String[] args) {
		SpringApplication.run(CrawlApplication.class, args);
	}
}
