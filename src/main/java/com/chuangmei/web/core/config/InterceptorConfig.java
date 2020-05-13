package com.chuangmei.web.core.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.chuangmei.web.interceptor.AppLoginInterceptor;

/**
 * 配置拦截器
 * 
 * @author 
 * 
 */

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(InterceptorConfig.class);
	}

//	 public static void main(String[] args) {
//	 SpringApplication.run(InterceptorConfig.class, args);
//	 }

	/**
	 * 配置拦截器
	 * 
	 * @param registry
	 */
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AppLoginInterceptor()).addPathPatterns("/app");
	}
}
