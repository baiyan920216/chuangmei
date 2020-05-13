package com.chuangmei.web.utils;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

import com.google.gson.Gson;

public class MemcachedUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String memPath = PropertyUtil.PropertiesInfo("memAddr");
	
	private static int memPort = 11211;
	
	/**
	 * 登陆时效24小时
	 */
	public static final int CACHE_EXP_HOUR_24 = 24*60*60;
	
	public static final int CACHE_EXP_HOUR_2 = 2*60*60;
	public static final int CACHE_EXP_DAY_30 = 30*24*60*60;
	
	/**
	 * @param key 键值
	 * @param exp 有效时间，以秒为单位，最大为30天
	 * @param o value
	 * @return boolean
	 * @throws IOException 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static boolean set(String key, int exp, Object o) throws IOException, InterruptedException, ExecutionException{
		boolean result = false;
		MemcachedClient memcacheClient=new MemcachedClient(  
                new InetSocketAddress(memPath, memPort)); 
		Future<Boolean> fbl=null;  
        /*将key,过期时间,对应的值设入到MemCache*/  
        fbl=memcacheClient.set(key, exp, o);  
        result = fbl.get().booleanValue();
        memcacheClient.shutdown();  
		return result;
	}
	
	/**
	 * @param key 键值
	 * @return Object
	 * @throws IOException
	 */
	public static Object get(String key) throws IOException{
		MemcachedClient memcacheClient=new MemcachedClient(  
                new InetSocketAddress(memPath, memPort)); 
		 Object obj=memcacheClient.get(key);  
		 
		 memcacheClient.shutdown(); 
		 return obj;
	}
	
	/**
	 * @param  key 键值
	 * @return boolean
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static boolean delete(String key) throws IOException, InterruptedException, ExecutionException{
		boolean result = false;
		MemcachedClient memcacheClient=new MemcachedClient(  
                new InetSocketAddress(memPath, memPort)); 
		 OperationFuture<Boolean> p = memcacheClient.delete(key);
		 result = p.get().booleanValue();
		 return result;
	}
	
	
	public static List<String> test(int genNum){
		List<String> list = new ArrayList<String>();
		DecimalFormat df = new DecimalFormat("000000");
        for(int i=1; i<=genNum; i++){
        	list.add("150604" + df.format(i));
        }
        return list;
	}
	
	public static void main(String[] args) throws Exception {
		List<String> list = test(1);
//		for (int i = 0; i < 999999; i++) {
//			list.add("150601"+i);
//		}
		Gson gson = new Gson();
		System.out.println(gson.toJson(list).getBytes().length);
		String tmp_byte = ZipUtils.encodeString(gson.toJson(list));
		System.out.println(tmp_byte.getBytes().length);
//		System.out.println(a);
		MemcachedUtil.set("test", 0, tmp_byte);
		
		
		
		String a = (String) MemcachedUtil.get("test");
		String b = ZipUtils.decodeString(a);
		List<String> li = gson.fromJson(b, List.class);
		for (int i = 0; i < li.size(); i++) {
			System.out.println(li.get(i));
		}
    }  
}