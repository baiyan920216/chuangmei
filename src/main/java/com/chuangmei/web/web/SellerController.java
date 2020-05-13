package com.chuangmei.web.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chuangmei.web.core.jdbc.common.BaseMessageModel;
import com.chuangmei.web.entity.Content;
import com.chuangmei.web.entity.Seller;
import com.chuangmei.web.repository.SellerDao;

@Controller
@RequestMapping("seller")
public class SellerController {
	
	@Autowired
	private SellerDao sellerDao;
	
	/** 保存
	 * @param content
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public BaseMessageModel saveSellerInfo(Seller seller){
		BaseMessageModel msg = new BaseMessageModel();
		msg.initSuccess(false, "保存失败");
		try {
			//保存信息
			sellerDao.saveOrUpdate(seller);
			msg.initSuccess(true, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}

}
