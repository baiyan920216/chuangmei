package com.chuangmei.web.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.chuangmei.web.core.jdbc.BaseRepository;
import com.chuangmei.web.entity.ManagementAwart;

@Repository
public class ManagementAwartDao extends BaseRepository<ManagementAwart, Long> {
	
	public Long findSumMoneyByUser(Long uid){
		Long result = 0l;
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uid", uid);
		result = super.getNamedParameterJdbcTemplate().queryForLong("select sum(money) from t_management_awart where uid =:uid", paramMap);
		
		return result;
	}

}
