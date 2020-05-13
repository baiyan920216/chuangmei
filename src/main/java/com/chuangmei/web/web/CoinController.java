package com.chuangmei.web.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chuangmei.web.core.jdbc.common.BaseMessageModel;
import com.chuangmei.web.entity.Coin;
import com.chuangmei.web.entity.User;
import com.chuangmei.web.repository.CoinDao;
import com.chuangmei.web.repository.UserDao;
import com.chuangmei.web.utils.DateUtils;
import com.chuangmei.web.utils.SessionUtils;
import com.sun.mail.imap.protocol.UID;

@Controller
@RequestMapping("coin")
public class CoinController {
	
	@Autowired
	private CoinDao coinDao;
	
	@Autowired
	private UserDao userDao;
	
	/** 保存
	 * @param content
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public BaseMessageModel saveSellerInfo(Coin coin,HttpServletRequest request){
		BaseMessageModel msg = new BaseMessageModel();
		msg.initSuccess(false, "保存失败");
		try {
			User user = userDao.findOne(coin.getUid());
			
			coin.setCreatedtime(DateUtils.getCurrentDateTime());
			coin.setName(user.getName());
			coin.setTel(user.getTel());
			coin.setUid(user.getId());
			coin.setUname(user.getUname());	
			coin.setRemark("管理员分配");
			coin.setType("管理员分配");
			
			//保存信息
			
			coinDao.saveOrUpdate(coin);
			msg.initSuccess(true, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}

	
	@RequestMapping("/saveUserAddCoin")
	@ResponseBody
	public BaseMessageModel saveUserAddCoin(Coin coin,HttpServletRequest request){
		BaseMessageModel msg = new BaseMessageModel();
		msg.initSuccess(false, "保存失败");
		try {
			
			//
			// 获得用户信息
			User loginUser = SessionUtils.getInstance().getUserInSession(request.getSession());
			
			User user = userDao.findOne(coin.getUid());
			
			//查询剩余创币金额
			HashMap paramMap = new HashMap<>();
			String condition = " select sum(coin) from t_coin where uid = :uid ";
			paramMap.put("uid", loginUser.getId());
			Long sumcoin = coinDao.getNamedParameterJdbcTemplate().queryForLong(condition, paramMap);
			
			
			//校验当前用户余额是否可以转赠
			if(sumcoin<coin.getCoin()){
				msg.initSuccess(false, "余额不足！");
				return msg;
			}
			Coin coin2 = new Coin();
			coin2.setCreatedtime(DateUtils.getCurrentDateTime());
			coin2.setName(loginUser.getName());
			coin2.setTel(loginUser.getTel());
			coin2.setUid(loginUser.getId());
			coin2.setUname(loginUser.getUname());	
			coin2.setRemark("分配到下级用户："+user.getUname());
			coin2.setType("分配下级");
			coin2.setCoin(-coin.getCoin());
			coinDao.saveOrUpdate(coin2);
			
			
			coin.setCreatedtime(DateUtils.getCurrentDateTime());
			coin.setName(user.getName());
			coin.setTel(user.getTel());
			coin.setUid(user.getId());
			coin.setUname(user.getUname());	
			coin.setRemark("上级用户:"+loginUser.getUname()+"分配");
			coin.setType("上级分配");
			
			//保存信息
			
			coinDao.saveOrUpdate(coin);
			msg.initSuccess(true, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}
}
