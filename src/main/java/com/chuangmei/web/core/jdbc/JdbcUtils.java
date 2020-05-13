package com.chuangmei.web.core.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.chuangmei.web.core.jdbc.common.JdbcException;
import com.chuangmei.web.core.jdbc.common.PageModel;

/**
 * <br>
 * 创建日期：2015年10月15日 <br>
 * <b>Copyright 2015 UTOUU All Rights Reserved</b>
 * 
 * @author FG
 * @since 1.0
 * @version 1.0
 */
public class JdbcUtils<T> {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate npjt;

	/**
	 * @param jdbcTemplate
	 *            jdbcTemplate
	 */
	public JdbcUtils(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * findOneByCondition:根据条件获得一个实体 <br/>
	 * 
	 * @author gang.fan
	 * @param <T>
	 *            <T>
	 * @param sql
	 *            sql
	 * @param clases
	 *            clases
	 * @param paramMap
	 *            参数。如 map.put("id",111L);
	 * @return T 实体对象
	 * @since JDK 1.7
	 */
	public <T> T findOne(String sql, Class<T> clases, Map<String, ?> paramMap) {
		try {
			log.debug("[findOne sql ->{}]", sql);

			return this.getNamedParameterJdbcTemplate().queryForObject(sql,
					paramMap, new BeanPropertyRowMapper<T>(clases));
		} catch (EmptyResultDataAccessException e) {
			log.info(sql + ", no result");
		} catch (Exception e) {
			log.error("[findOne is error]", e);
		}
		return null;
	}

	/**
	 * findAll:获得全部实体 <br/>
	 *
	 * @author gang.fan
	 * @param <T>
	 *            <T>
	 * @param sql
	 *            sql
	 * @param paramMap
	 *            参数。如 map.put("id",111L);
	 * @param clases
	 *            clases
	 * @return List<T>
	 * @since JDK 1.7
	 */
	public <T> List<T> find(String sql, Map<String, ?> paramMap, Class<T> clases) {
		if (sql == null) {
			throw new JdbcException("sql不能为空");
		}
		try {
			log.debug("[find sql ->{}]", sql);
			return getNamedParameterJdbcTemplate().query(sql, paramMap,
					new BeanPropertyRowMapper<T>(clases));
		} catch (Exception e) {
			log.error("[find is error]", e);
		}
		return null;
	}

	/**
	 * findTotalCountByCondition:获得行数 <br/>
	 *
	 * @author gang.fan
	 * @param sql
	 *            sql语句。使用命名占位符如：id=:id
	 * @param paramMap
	 *            参数。如 map.put("id",111L);
	 * @return Long
	 * @since JDK 1.7
	 */
	@SuppressWarnings("deprecation")
	public Long findCount(String sql, Map<String, Object> paramMap) {
		if (sql == null) {
			throw new JdbcException("sql不能为空");
		}
		String excusql = "select count(1) from (" + sql + ") as pagetmp ";
		log.debug("[findCount sql ->{}]", excusql);
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		return getNamedParameterJdbcTemplate().queryForLong(excusql, paramMap);
	}

	/**
	 * getListByPage:分页查询<br/>
	 *
	 * @author gang.fan
	 * @param <T>
	 *            <T>
	 * @param offset o
	 * @param limit l
	 * @param sql
	 *            查询条件
	 * @param paramMap
	 *            参数值
	 * @param orderAndAsc
	 *            排序
	 * @param clases
	 *            clases
	 * @return Page<T>
	 * @since JDK 1.7
	 */
	public PageModel<T> getListByPage(int pageNum, int pageSize, String sql,
			Map<String, Object> paramMap, String orderAndAsc, Class<T> clases) {
		PageModel<T> page = new PageModel<T>();
		if (sql == null) {
			throw new JdbcException("sql不能为空");
		}
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		if (orderAndAsc == null) {
			orderAndAsc = "";
		}
		String excusql = "select * from (" + sql + ") as pagetmp " + orderAndAsc
				+ " limit " + pageSize * (pageNum - 1) + "," + pageSize;
		log.debug("[getListByPage  sql->{}]", excusql);

		page.setRows(getNamedParameterJdbcTemplate().query(excusql, paramMap,
				new BeanPropertyRowMapper<T>(clases)));

		log.debug("[page  Rows->{}]", page.getRows());
		long allCount = this.findCount(sql, paramMap);
		log.debug("[page  allCount->{}]", allCount);
		if (allCount % pageSize == 0) {
            page.setPageCount(allCount / pageSize);
        } else {
            page.setPageCount((allCount / pageSize) + 1);
        }
        page.setTotal(allCount);
        page.setPageNumber(pageNum);
        page.setPageSize(pageSize);
		page.setTotal(allCount);

		return page;
	}
	
	/**
	 * @since 1.0
	 * @param <T> <T>
	 * @param sql 查询条件
	 * @param paramMap 参数值
	 * @param orderAndAsc 排序
	 * @param clases clases
	 * @return
	 * <br><b>作者： @author ZhangTt</b>
	 * <br>创建时间：2015年11月19日 下午5:05:48
	 */
	public <T> List<T> findAll(String sql,Map<String, Object> paramMap, 
			String orderAndAsc, Class<T> clases) {
		List<T> list = new ArrayList<T>();
		if (sql == null) {
			throw new JdbcException("sql不能为空");
		}
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		if (orderAndAsc == null) {
			orderAndAsc = "";
		}
		String excusql = "select * from (" + sql + ") as pagetmp " + orderAndAsc;
		log.debug("[getListByPage  sql->{}]", excusql);

		list = getNamedParameterJdbcTemplate().query(excusql, paramMap,
				new BeanPropertyRowMapper<T>(clases));


		return list;
	}
	
	/**
	 * @since 1.0
	 * @return <br>
	 *         <b>作者： @author FG</b> <br>
	 *         创建时间：2015年10月15日 下午5:23:05
	 */
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		if (npjt == null) {
			npjt = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
		}
		return npjt;
	}

}
