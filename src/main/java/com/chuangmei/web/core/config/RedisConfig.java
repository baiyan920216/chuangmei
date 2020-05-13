/**
 * Project Name:guoren-xintianyou-web
 * File Name:FilterConfig.java
 * Package Name:guoren.xintianyou.web.core.config
 * Date:2015年2月5日下午3:26:49
 * Copyright (c) 2015, www.sim.com All Rights Reserved.
 *
 */

package com.chuangmei.web.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.Serializable;

/**
 * ClassName:FilterConfig <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015年2月5日 下午3:26:49 <br/>
 * 
 * @author he.sun
 * @version
 * @since JDK 1.7
 * @see
 */
@Configuration
public class RedisConfig {
	
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
    
//	 @Bean  
//	    public RedisConnectionFactory redisConnectionFactory() {  
//	        JedisConnectionFactory cf = new JedisConnectionFactory();  
//	        cf.setHostName("192.168.235.182");  
//	        cf.setPort(6379);  
//	        cf.setPassword("5918.sim.com");  
//	        cf.afterPropertiesSet();  
//	        return cf;  
//	    }  
	  
    @Bean  
    public RedisTemplate<Serializable, Object> redisTemplate() {  
        RedisTemplate<Serializable, Object> rt = new RedisTemplate<Serializable, Object>();  
//        rt.setConnectionFactory(redisConnectionFactory());  
        rt.setConnectionFactory(jedisConnectionFactory);  
        return rt;  
    }  
    
    public static enum StringSerializer implements RedisSerializer<String> {  
        INSTANCE;  
  
        @Override  
        public byte[] serialize(String s) throws SerializationException {  
            return (null != s ? s.getBytes() : new byte[0]);  
        }  
  
        @Override  
        public String deserialize(byte[] bytes) throws SerializationException {  
            if (bytes.length > 0) {  
                return new String(bytes);  
            } else {  
                return null;  
            }  
        }  
    }
}
