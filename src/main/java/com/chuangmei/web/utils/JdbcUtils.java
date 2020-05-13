package com.chuangmei.web.utils;

import com.chuangmei.web.core.jdbc.common.JdbcException;
import com.chuangmei.web.core.jdbc.common.Page;
import com.chuangmei.web.core.jdbc.common.PageModel;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <br>
 * 创建日期：2015年10月15日 <br>
 * <b>Copyright 2015 UTOUU All Rights Reserved</b>
 * 
 * @author FG
 * @since 1.0
 * @version 1.0
 */
public class JdbcUtils {

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
	 * @author he.sun
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

			return this.getNamedParameterJdbcTemplate().queryForObject(sql, paramMap,
					new BeanPropertyRowMapper<T>(clases));
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
	 * @author he.sun
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
			return getNamedParameterJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper<T>(clases));
		} catch (Exception e) {
			log.error("[find is error]", e);
		}
		return null;
	}

	/**
	 * findTotalCountByCondition:获得行数 <br/>
	 *
	 * @author he.sun
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
		String excusql = "select count(1) from (" + sql + ")  pagetmp ";
		log.debug("[findCount sql ->{}]", excusql);
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		return getNamedParameterJdbcTemplate().queryForObject(excusql, paramMap, Long.class);
	}

	/**
	 * getListByPage:分页查询<br/>
	 *
	 * @author he.sun
	 * @param <T>
	 *            <T>
	 * @param offset
	 *            o
	 * @param limit
	 *            l
	 * @param sql
	 *            查询条件
	 * @param paramMap
	 *            参数值
	 * @param order
	 *            排序
	 * @param clases
	 *            clases
	 * @return Page<T>
	 * @since JDK 1.7
	 */
	public <T> PageModel<T> getListByPageModel(int offset, int limit, String sql, Map<String, Object> paramMap,
											   String order, Class<T> clases) {
		PageModel<T> page = new PageModel<T>();
		if (sql == null) {
			throw new JdbcException("sql不能为空");
		}
		if (paramMap == null) {
			paramMap = Maps.newHashMap();
		}
		if (order == null) {
			order = "";
		}
//		sql = sql.replaceFirst("select", "select rownum rn,");
//		String excusql = "select tmp.* from (" + sql + order + ") tmp where tmp.rn  > " + offset + " and  tmp.rn <= "
//				+ (offset + limit);
		String excusql = "select tmp.* from ( SELECT a.*, ROWNUM RN  FROM(" + sql + order + ")a) tmp where tmp.rn  > "
				+ offset + " and  tmp.rn <= " + (offset + limit);
		log.debug("[getListByPage  sql->{}]", excusql);

		page.setRows(getNamedParameterJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper(clases)));

		log.debug("[page  Rows->{}]", page.getRows());

		long allCount = this.findCount(excusql, paramMap);
		log.debug("[page  allCount->{}]", allCount);

		if (allCount % limit == 0) {
			page.setPageCount(allCount / limit);
		} else {
			page.setPageCount((allCount / limit) + 1);
		}
		page.setTotal(allCount);
		page.setPageNumber(offset);
		page.setPageSize(limit);

		if (log.isDebugEnabled()) {
			Gson gson = new Gson();
			log.debug("[page  page->{}]", gson.toJson(page));

		}

		return page;
	}

	public <T> Page<T> getListByPage(int offset, int limit, String sql, Map<String, Object> paramMap, String order,
									 Class<T> clases) {
		Page<T> page = new Page<T>();
		if (sql == null) {
			throw new JdbcException("sql不能为空");
		}
		if (paramMap == null) {
			paramMap = Maps.newHashMap();
		}
		if (order == null) {
			order = "";
		}
		// sql = sql.replaceFirst("select", "select rownum rn,");
		// String excusql = "select * from (" + sql + order + ") pagetmp where
		// pagetmp.rn > " + offset + " and pagetmp.rn <= " + (offset + limit);
		String excusql = "select tmp.* from ( SELECT a.*, ROWNUM RN  FROM(" + sql + order + ")a) tmp where tmp.rn  > "
				+ offset + " and  tmp.rn <= " + (offset + limit);
		log.debug("[getListByPage  sql->{}]", excusql);

		page.setData(getNamedParameterJdbcTemplate().query(excusql, paramMap, new BeanPropertyRowMapper(clases)));

		log.debug("[page  Rows->{}]", page.getData());

		long allCount = this.findCount(sql, paramMap);
		log.debug("[page  allCount->{}]", allCount);

		if (allCount % limit == 0) {
			page.setPageCount(allCount / limit);
		} else {
			page.setPageCount((allCount / limit) + 1);
		}
		page.setTotal(allCount);
		page.setiTotalRecords(allCount);
		page.setiTotalDisplayRecords(allCount);
		page.setPageNumber(offset);
		page.setPageSize(limit);

		if (log.isDebugEnabled()) {
			Gson gson = new Gson();
			log.debug("[page  page->{}]", gson.toJson(page));

		}

		return page;
	}

	/**
	 * @since 1.0
	 * @param <T>
	 *            <T>
	 * @param sql
	 *            查询条件
	 * @param paramMap
	 *            参数值
	 * @param order
	 *            排序
	 * @param clases
	 *            clases
	 * @return <br>
	 *         <b>作者： @author ZhangTt</b> <br>
	 *         创建时间：2015年11月19日 下午5:05:48
	 */
	public <T> List<T> findAll(String sql, Map<String, Object> paramMap, String order, Class<T> clases) {
		List<T> list = new ArrayList<T>();
		if (sql == null) {
			throw new JdbcException("sql不能为空");
		}
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		if (order == null) {
			order = "";
		}
		String excusql = "select * from (" + sql + order + ")  pagetmp ";
		log.debug("[getListByPage  sql->{}]", excusql);

		list = getNamedParameterJdbcTemplate().query(excusql, paramMap, new BeanPropertyRowMapper<T>(clases));

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
