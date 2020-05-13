package com.chuangmei.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * ClassName: WebApplication <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * date: 2015年2月4日 上午9:28:17 <br/>
 *
 * @author gang.fan
 * @version
 * @since JDK 1.7
 */
@EnableAutoConfiguration
@SpringBootApplication
public class ChuangmeiApplication extends SpringBootServletInitializer {
	@Autowired
	private StringRedisTemplate template;

	/**
	 * TODO 自动配置
	 * 
	 * @see org.springframework.boot.context.web.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(ChuangmeiApplication.class);
	}

	@Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver(){
     CommonsMultipartResolver resolver = new CommonsMultipartResolver();
     resolver.setDefaultEncoding("UTF-8");
     resolver.setResolveLazily(true);//resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
     resolver.setMaxInMemorySize(40960);
     resolver.setMaxUploadSize(50*1024*1024);//上传文件大小 50M 50*1024*1024
     return resolver;
 }

	/**
	 * main:独立运行主类<br/>
	 *
	 * @author gang.fan
	 * @param args
	 * @throws Exception
	 * @since JDK 1.7
	 */
	public static void main(String[] args) throws Exception {
//		SpringApplication springApplication = new SpringApplication(
//				WebApplication.class);
//		springApplication.addListeners(new SessionDestroyedEventListener());
//		springApplication.run(args);
		SpringApplication.run(ChuangmeiApplication.class, args);
	}
}
