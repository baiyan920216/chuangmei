/**
 * Project Name:guoren-xintianyou-web
 * File Name:FilterConfig.java
 * Package Name:com.chuangmei.web.core.config
 * Date:2015年2月5日下午3:26:49
 * Copyright (c) 2015, www.sim.com All Rights Reserved.
 *
 */

package com.chuangmei.web.core.config;

import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chuangmei.web.filter.LoginFilter;

/**
 * ClassName:FilterConfig <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015年2月5日 下午3:26:49 <br/>
 * 
 * @author gang.fan
 * @version
 * @since JDK 1.7
 * @see
 */
@Configuration
public class FilterConfig {
    
    @Bean(name = "LoginFilter")
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new LoginFilter());
        bean.addUrlPatterns("/wap/*","/member/*","/views/*","/seller/*");
        bean.setOrder(1);
        return bean;
    }
	
//    @Bean(name = "permissionFilter")
//    public FilterRegistrationBean permissionFilter() {
//        FilterRegistrationBean bean = new FilterRegistrationBean();
//        bean.setFilter(new PermissionFilter());
//        bean.addUrlPatterns("/*");
//        bean.setOrder(2);
//        return bean;
//    }
//
    @Bean(name = "sitemeshFilter")
    public FilterRegistrationBean sitemeshFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new ConfigurableSiteMeshFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }
//
//    @Bean(name = "catFilter")
//    public FilterRegistrationBean catFilter() {
//        FilterRegistrationBean bean = new FilterRegistrationBean();
//        bean.setFilter(new CatFilter());
//        bean.addUrlPatterns("/*");
//        bean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD);
//        return bean;
//    }
}
