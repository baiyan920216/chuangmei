package com.chuangmei.web.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chuangmei.web.core.jdbc.common.MessageModel;
import com.chuangmei.web.entity.Role;
import com.chuangmei.web.service.RoleService;
import com.chuangmei.web.service.UserService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/saveAuths")
	public MessageModel saveAuths(Role role){
		MessageModel msg = new MessageModel();
		msg.initSuccess(false, "授权失败");
		try {
			roleService.saveOrUpdate(role);
			msg.initSuccess(true, "授权成功");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("授权异常");
		}
		return msg;
	}
	
	
	@ResponseBody
	@RequestMapping("/save")
	public MessageModel saveRole(Role role){
		MessageModel msg = new MessageModel();
		msg.initSuccess(false, "保存失败");
		
		try {
			roleService.saveOrUpdate(role);
			msg.initSuccess(true, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("保存异常");
		}
		
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/rolelist")
	public MessageModel findRoleList(){
		MessageModel msg = new MessageModel();
		msg.initSuccess(false, "查询失败");
		
		try {
			List<Role> result = roleService.findRoleList();
			
			msg.setData(result);
			msg.initSuccess(true, "查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			msg.initSuccess(false, "查询异常");
		}
		
		
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/del")
	public MessageModel findRoleDel(Long id){
		MessageModel msg = new MessageModel();
		msg.initSuccess(false, "删除失败");
		
		try {
//			Role role = roleService.findOne(id);
			//删除角色
			if(id!=null&&id>1L){
				//校验角色是否被使用
				Long roleUseCount = userService.checkRoleUse(id);
				if(roleUseCount>0L){
					msg.setMsg("该角色已被分配，不允许删除！");
					return msg;
				}
				//删除
				roleService.delete(id);
				msg.initSuccess(true, "删除成功");
			}else{
				
				msg.setMsg("该角色不允许删除");
				
			}
		} catch (Exception e) {
			msg.setMsg("删除异常");
		}
		
		return msg;
	}
}
