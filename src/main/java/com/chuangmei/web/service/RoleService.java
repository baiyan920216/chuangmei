package com.chuangmei.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chuangmei.web.entity.Role;
import com.chuangmei.web.repository.RoleDao;

@Component
public class RoleService extends BaseService<Role, Long> {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	private RoleDao roleDao;
	
	@Autowired
	public RoleService(RoleDao roleDao) {
		this.roleDao = roleDao;
		this.res = roleDao;
	}
	
	
	/** 查询角色列表
	 * @return
	 */
	public List<Role> findRoleList(){
		//返回所有角色列表
		return roleDao.findAll();
	}

	public List<Role> findManageRoleList() {
//		String condition = " id > 1";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return roleDao.find("", paramMap , "");
	}
	public List<Role> findUserRoleList() {
		String condition = " id != 0";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return roleDao.find(condition, paramMap , "");
	}
}
