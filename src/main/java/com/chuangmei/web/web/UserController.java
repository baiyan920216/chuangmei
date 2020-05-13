package com.chuangmei.web.web;

import com.chuangmei.web.core.jdbc.common.DataGridModel;
import com.chuangmei.web.core.jdbc.common.MessageModel;
import com.chuangmei.web.core.jdbc.common.PageModel;
import com.chuangmei.web.entity.Content;
import com.chuangmei.web.entity.Role;
import com.chuangmei.web.entity.User;
import com.chuangmei.web.repository.UserDao;
import com.chuangmei.web.service.RoleService;
import com.chuangmei.web.service.UserService;
import com.chuangmei.web.utils.DateUtils;
import com.chuangmei.web.utils.IpUtils;
import com.chuangmei.web.utils.SessionUtils;
import com.chuangmei.web.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleService roleService;
	
//	@RequestMapping("/save")
//	@ResponseBody
//	public void saveUser(User entity){
//		entity.setUname("admin");
//		entity.setUpass(CommonUtils.md5("123456"));
//		userService.save(entity);
//	}
	
	@RequestMapping("/save")
	@ResponseBody
	public MessageModel saveUser(User user,HttpServletRequest request){
		MessageModel msg = new MessageModel();
		msg.initSuccess(false, "保存失败");

		try {
			userService.saveOrUpdate(user);
			msg.initSuccess(true, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("保存异常");
		}

		return msg;
	}
	
	@RequestMapping("/tree")
	@ResponseBody
	public MessageModel tree(){
		MessageModel msg = new MessageModel();
		msg.initSuccess(false, "查询失败");
		
		try {
			String condition = " roletype = 2 or roletype = 1";
			Map<String, ?> paramMap = new HashMap<>();
			//TODO 属性结构用户列表
			List<User> userList = userDao.find(condition, paramMap , "" );
			msg.setData(userList);
			msg.initSuccess(true, "查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("查询异常");
		}
		
		return msg;
	}
	@RequestMapping("/updateUPass")
	@ResponseBody
	public MessageModel updateUPass(User user,HttpServletRequest request){
		MessageModel msg = new MessageModel();
		msg.initSuccess(false, "保存失败");
		
		try {
			user.MD5Password();
			userService.saveOrUpdate(user);
			msg.initSuccess(true, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("保存异常");
		}
		
		return msg;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public MessageModel delUser(Long id){
		MessageModel msg = new MessageModel();
		msg.initSuccess(false, "删除失败");
		
		try {
			
			userService.delete(id);
			
			msg.initSuccess(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("删除异常");
		}
		
		return msg;
	}
	
	/** 查询管理员用户
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public MessageModel findManagerUser(User user){
		MessageModel msg = new MessageModel();
		msg.initSuccess(false, "查询失败");
		try {
			List<User> uList = userService.findByUnameLike(user.getUname());
			msg.setData(uList);
			msg.initSuccess(true, "查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("查询异常");
		}
		
		return msg;
	}
	
	/** TODO  查询普通用户
	 * @return
	 */
	@RequestMapping("/clientList")
	@ResponseBody
	public PageModel<User> clientList(User user,DataGridModel dataGridModel){
		
		PageModel<User> pageMsg = null ;
		try {
			pageMsg = userService.getUserByPage(user.getUname(),user.getName(),user.getAlipay(),user.getTel(),dataGridModel);
			//查询分页数据
			pageMsg.initSuccess(true, "获得分页信息成功");
			
			String condition = " roletype = :roletype and locked = 0";
			Map<String, Object> paramMap = new HashMap<String, Object>();
			//查询会员激活数
			//黄金会员
			paramMap.put("roletype", 1);
			Long huangjinCount = userDao.findCount(condition, paramMap);
			//白金会员
			paramMap.put("roletype", 2);
			Long baijinCount = userDao.findCount(condition, paramMap);
			pageMsg.setCode("白金会员激活数量："+baijinCount+"; 黄金会员激活数量："+huangjinCount);
		} catch (Exception e) {
			e.printStackTrace();
			pageMsg.initSuccess(false, "获得分页信息失败");
		}
		return pageMsg;
	}
	
	@RequestMapping("/switchLock")
	@ResponseBody
	public MessageModel switchLock(User user){
		MessageModel msg = new MessageModel();
		msg.initSuccess(false, "操作失败");
		try {
			User userHis = userService.findOne(user.getId());
			if(userHis.getLocked() == 2 && user.getLocked() == 0){
				//激活时，设置激活时间
				user.setActivetime(DateUtils.getCurrentDateTime());
			}
			if(user.getLocked() == 2 && userHis.getLocked() == 0){
				//取消激活时
				user.setActivetime("");
			}
			
			userService.update(user);
			msg.initSuccess(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("操作异常");
		}
		
		return msg;
	}
	
	
	@RequestMapping("/roleList")
	@ResponseBody
	public MessageModel roleList(){
		MessageModel msg = new MessageModel();
		msg.initSuccess(false, "查询失败");
		try {
			List<Role> rList = roleService.findManageRoleList();
			msg.setData(rList);
			msg.initSuccess(true, "查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("查询异常");
		}
		
		return msg;
	}
	
	
}
