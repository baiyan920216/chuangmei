package com.chuangmei.web.repository;

import com.chuangmei.web.core.jdbc.BaseRepository;
import com.chuangmei.web.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseRepository<User, Long> {
	
	public List<Map<String ,Object>> getUserPowerTop(int top){
		String sql = "SELECT child_rank AS rank,child_locked as cheat,user_id AS id,nike_name as nikename,uname,child_num as supportnum FROM t_user_rank WHERE child_rank!=0 ORDER BY child_rank LIMIT 0,"+top;
		Map<String ,Object> paramMap = new HashMap<String, Object>();
		return super.getNamedParameterJdbcTemplate().queryForList(sql, paramMap );
	}
	
	public Integer findPowerLevelByUser(Long uid){
		String sql = "SELECT child_rank FROM t_user_rank WHERE user_id=:uid";
		Map<String ,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		return super.getNamedParameterJdbcTemplate().queryForInt(sql, paramMap);
	}
	
	public Integer findPowerLevelByUserDay(Long uid){
		String sql = "SELECT getChildRunk_day(:uid)";
		Map<String ,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		return super.getNamedParameterJdbcTemplate().queryForInt(sql, paramMap);
	}
	
	public Integer getSuppNo(Long uid){
		String sql = "SELECT getChildNum(u.id) as supportnum FROM t_user u where u.id = :id";
		Map<String ,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", uid);
		return super.getNamedParameterJdbcTemplate().queryForInt(sql, paramMap);
	}

	public List<Map<String ,Object>> getUserPowerTop(){
		String sql = "SELECT u.id,u.uname,u.nikename,supp.supportnum FROM (SELECT supportid,count(1) AS supportnum FROM t_user WHERE 1=1 AND supportid IS NOT NULL GROUP BY supportid) as supp LEFT JOIN t_user u ON u.id = supp.supportid ORDER BY supp.supportnum DESC LIMIT 0,20";
		Map<String ,Object> paramMap = new HashMap<String, Object>();
		return super.getNamedParameterJdbcTemplate().queryForList(sql, paramMap );
	}
	
//	public Long getUserPowerNum(Long uid){
//		String sql = "SELECT tmp.rownum FROM (SELECT u.id,(@i := @i + 1) as rownum FROM (SELECT supportid,count(1) AS supportnum FROM t_user WHERE 1=1 AND supportid IS NOT NULL GROUP BY supportid) as supp LEFT JOIN t_user u ON u.id = supp.supportid ,(select @i := 0) as rownum ORDER BY supp.supportnum DESC) as tmp WHERE tmp.id=:uid";
//		Map<String ,Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("uid", uid);
//		return super.getNamedParameterJdbcTemplate().queryForLong(sql, paramMap);
//	}

	public List<Map<String, Object>> getUserRankByUser(Long uid) {
		String sql = "SELECT * FROM t_user_rank WHERE user_id = " + uid;
		Map<String ,Object> paramMap = new HashMap<String, Object>();
		return super.getNamedParameterJdbcTemplate().queryForList(sql, paramMap );
	}
	
	public List<Map<String, Object>> getUserBeanTop(int top) {
		String sql = "SELECT bean_rank AS rank,score,user_id AS id,uname,nike_name nikename FROM t_user_rank WHERE bean_rank!=0 ORDER BY bean_rank LIMIT 0,"+top;
		Map<String ,Object> paramMap = new HashMap<String, Object>();
		return super.getNamedParameterJdbcTemplate().queryForList(sql, paramMap );
	}
	public Integer getUserBeanTopByUser(Long uid) {
		String sql = "SELECT bean_rank FROM t_user_rank WHERE user_id=:uid";
		Map<String ,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		return super.getNamedParameterJdbcTemplate().queryForInt(sql, paramMap );
	}
	
	public List<Map<String, Object>> getUserCoinsTop(int top) {
		String sql = "SELECT coin_rank AS rank,coin,user_id AS id,uname,nike_name nikename FROM t_user_rank WHERE coin_rank!=0 ORDER BY coin_rank LIMIT 0,"+top;
		Map<String ,Object> paramMap = new HashMap<String, Object>();
		return super.getNamedParameterJdbcTemplate().queryForList(sql, paramMap );
	}
	
	public Integer getUserCoinsTopByUser(Long uid) {
		String sql = "SELECT coin_rank FROM t_user_rank WHERE user_id=:uid";
		Map<String ,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		return super.getNamedParameterJdbcTemplate().queryForInt(sql, paramMap );
	}
	
	public Integer getScoreByUser(String uname){
		String sql = "SELECT MAX(score) FROM t_startupbean WHERE uname= :uname";
		Map<String ,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uname", uname);
		return super.getNamedParameterJdbcTemplate().queryForInt(sql, paramMap);
	}
	
	public Integer getCheckDaysByUser(String uname){
		String sql = "SELECT count(1) FROM t_startupbean WHERE uname= :uname and type=1";
		Map<String ,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uname", uname);
		return super.getNamedParameterJdbcTemplate().queryForInt(sql, paramMap);
	}
	
	public Integer getForwardNumByUser(long uid){
		String sql = "SELECT count(1) FROM `t_forwardlog` WHERE uid=:uid;";
		Map<String ,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		return super.getNamedParameterJdbcTemplate().queryForInt(sql, paramMap);
	}

//	public List<Map<String, Object>> getUserPowerDayTop() {
//		String sql = "SELECT u.id,u.uname,u.nikename,getChildNum_day(u.id) as supportnum FROM t_user u WHERE supporttime IS NOT NULL ORDER BY supportnum DESC LIMIT 0,20";
//		Map<String ,Object> paramMap = new HashMap<String, Object>();
//		return super.getNamedParameterJdbcTemplate().queryForList(sql, paramMap );
//	}
}
