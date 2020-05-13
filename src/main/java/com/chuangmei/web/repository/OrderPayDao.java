package com.chuangmei.web.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.chuangmei.web.core.jdbc.BaseRepository;
import com.chuangmei.web.entity.Orderpay;

@Repository
public class OrderPayDao extends BaseRepository<Orderpay, Long> {

	public Long findSumBySql(String sql){
		Map<String, Object>  paramMap = new HashMap<>();
		Long result = this.getNamedParameterJdbcTemplate().queryForLong(sql , paramMap);
		return result;
	}
	
}
