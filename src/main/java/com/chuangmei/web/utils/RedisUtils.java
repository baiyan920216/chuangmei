package com.chuangmei.web.utils;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.chuangmei.web.core.config.RedisConfig;
import com.chuangmei.web.entity.AccessToken;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

/**
 *
 * 类名称：RedisUtils
 * 类描述：  -== RedisUtils工具类 ==-
 * 创建人：yuanqi.jing
 * 创建时间：2015-3-16 下午17:51:11
 * 修改备注：
 * @version  1.0
 *
 */
@Component
public class RedisUtils {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<Serializable, Object> redisTemplate;

	/**
	 * @Title：setString
	 * @Description: 向redis中添加String
	 * @author yuanqi.jing
	 * @date 2015年3月16日 下午5:04:28
	 * @param key 键
	 * @param value 值
	 * @param timeOut 时效（毫秒）
	 */
	public void setString(String key, String value, long timeOut) {
		stringRedisTemplate.opsForValue().set(key, value);
		stringRedisTemplate.expire(key, timeOut, TimeUnit.MILLISECONDS);
	}

	/**
	 * @Title：setString
	 * @Description: 向redis中添加String,永不超时
	 * @author yuanqi.jing
	 * @date 2015年3月16日 下午5:04:28
	 * @param key 键
	 * @param value 值
	 */
	public void setString(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}

	/**
	 * @Title：getString
	 * @Description: 从redis中获得String
	 * @author yuanqi.jing
	 * @date 2015年3月16日 下午5:08:52
	 * @return
	 */
	public String getString(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	/**
	 * @Title：putObjectToRedis
	 * @Description: 将对象放入redis
	 * @author yuanqi.jing
	 * @date 2016年7月22日 下午4:28:03
	 * @param key
	 * @param value
	 * @param clazz
	 * @param timeout
	 */
	public void putObjectToRedis(String key, final Object value, Class clazz, long timeout) {
		redisTemplate.setValueSerializer(new JacksonJsonRedisSerializer<T>(clazz));

		redisTemplate.setKeySerializer(RedisConfig.StringSerializer.INSTANCE);
		redisTemplate.setValueSerializer(new JacksonJsonRedisSerializer<T>(clazz));

		redisTemplate.afterPropertiesSet();
		ValueOperations<Serializable, Object> ops = redisTemplate.opsForValue();
//		ops.set(key, value);
		ops.set(key, value, timeout, TimeUnit.MILLISECONDS);
	}



	/**
	 * @Title：getObjectFromRedis
	 * @Description: 从redis获取对象
	 * @author yuanqi.jing
	 * @date 2016年7月22日 下午4:28:17
	 * @param key
	 * @return
	 */
	public Object getObjectFromRedis(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * @Title：flushDb
	 * @Description: 清空redis所有数据
	 * @author yuanqi.jing
	 * @date 2016年7月22日 下午3:52:01
	 */
	public void flushDb() {
		redisTemplate.getConnectionFactory().getConnection().flushDb();
	}

	/**
	 * @Title：putAccessTokenInRedis
	 * @Description: 将access_token放入redis缓存
	 * @author yuanqi.jing
	 * @date 2016年10月19日 下午1:43:38
	 * @param tokens
	 * @param token
	 * @return
	 */
	public  boolean putAccessTokenInRedis(String tokens, AccessToken token) {
		boolean flag = false;

		try {
			this.putObjectToRedis(ConstantUtils.ACCESS_TOKEN_KEY_PREFIX + tokens, token, AccessToken.class, ConstantUtils.LOGIN_TIMEOUT);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * @Title：getLoginUserInRedis
	 * @Description: 从redis缓存中获取登录用户信息
	 * @author yuanqi.jing
	 * @date 2016年10月19日 下午2:03:07
	 * @param token
	 * @return
	 */
	public  AccessToken getAccessTokenInRedis(String token) {
		AccessToken p = (AccessToken)this.getObjectFromRedis(ConstantUtils.ACCESS_TOKEN_KEY_PREFIX + token);
		return p;
	}

	/**
	 * 删除对应的value
	 *
	 * @param key
	 */
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * 判断缓存中是否有对应的value
	 *
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}


}
