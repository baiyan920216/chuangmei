package com.chuangmei.web.repository;

import com.chuangmei.web.core.jdbc.BaseRepository;
import com.chuangmei.web.entity.SysProp;

import org.springframework.stereotype.Repository;

@Repository
public class SysPropDao extends BaseRepository<SysProp, Long> {

	public String getValueByCode(String code){
		SysProp sysprop = this.findOne(" 1=1 and code='"+code+"'");
		if(sysprop!=null){
			return sysprop.getValue();
		}
		return "";
	}
	
}
