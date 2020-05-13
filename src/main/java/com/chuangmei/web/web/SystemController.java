package com.chuangmei.web.web;

import com.chuangmei.web.entity.Systems;
import com.chuangmei.web.repository.SystemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("system")
public class SystemController {
	
	@Autowired
	private SystemDao systemDao;

	@RequestMapping("/getSystemByCid")
	@ResponseBody
	public List<Systems> getSystemByCid(String cid) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String condition = " cid = :cid";
		paramMap.put("cid", cid);
		List<Systems> systems = systemDao.find(condition, paramMap, "");
		return systems;
	}
}
