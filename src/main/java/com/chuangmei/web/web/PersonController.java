package com.chuangmei.web.web;

import com.alibaba.fastjson.JSONObject;
import com.chuangmei.web.core.jdbc.common.BaseMessageModel;
import com.chuangmei.web.entity.Person;
import com.chuangmei.web.entity.Systems;
import com.chuangmei.web.repository.PersonDao;
import com.chuangmei.web.repository.SystemDao;
import com.chuangmei.web.utils.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("person")
public class PersonController {
	
	@Autowired
	private PersonDao personDao;

	@RequestMapping("/add")
	@ResponseBody
	public BaseMessageModel add(Person person) {
		BaseMessageModel msg = new BaseMessageModel();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String con= " tel = :tel and openid <> :openid";
		paramMap.put("tel", person.getTel());
		paramMap.put("openid", person.getOpenid());
		List<Person> per = personDao.find(con, paramMap, "");
		if(per.size() > 0){
			msg.initSuccess(false, "手机号已存在");
			return msg;
		}
		String condition = " openid = :openid";
		paramMap.put("openid", person.getOpenid());
		List<Person> persons = personDao.find(condition, paramMap, "");
		if(persons.size() > 0){
			person.setId(persons.get(0).getId());
			personDao.update(person);
		}else {
			personDao.save(person);
		}
		msg.initSuccess(true, "员工绑定成功");
		return msg;
	}
}
