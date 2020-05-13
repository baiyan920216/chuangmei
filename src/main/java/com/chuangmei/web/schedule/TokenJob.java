//package com.chuangmei.web.schedule;
//
//import com.chuangmei.web.dto.app.WXTicketDto;
//import com.chuangmei.web.dto.app.WXTokenDto;
//import com.chuangmei.web.utils.ConstantUtils;
//import com.chuangmei.web.utils.HttpAgent;
//import com.chuangmei.web.utils.MemcachedUtil;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.google.gson.Gson;
//
////@Component
////@Configurable
////@EnableScheduling
//public class TokenJob {
//	
//	protected final Logger log = LoggerFactory.getLogger(getClass());
//	
////	@Scheduled(cron = "0 0 0/1 * * ?")
//	public void exicute() {
//		
//		try {
//			
//		
//		//调用微信api获得token
//		String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx84b6b84f1e0de426&secret=c03b3bfe003d8c2637cdf5b98dbd1e32";
//		String token_str = HttpAgent.sendRequest(token_url, "");
//		WXTokenDto tdto = new Gson().fromJson(token_str, WXTokenDto.class);
//		String token = tdto.getAccess_token();
//		
//		MemcachedUtil.set(ConstantUtils.WEIXIN_TOKEN, MemcachedUtil.CACHE_EXP_HOUR_2, token);
//		
//		
//		//获得jsapi_ticket
//		String jsapi_ticket = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+token+"&type=jsapi";
//		String ticket_str = HttpAgent.sendRequest(jsapi_ticket, "");
//		
//		WXTicketDto dto = new Gson().fromJson(ticket_str, WXTicketDto.class);
//		String ticket = dto.getTicket();
//		
//		MemcachedUtil.set(ConstantUtils.WEIXIN_TICKET, MemcachedUtil.CACHE_EXP_HOUR_2, ticket);
//		
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void main(String[] args) {
//		new TokenJob().exicute();
//	}
//}
