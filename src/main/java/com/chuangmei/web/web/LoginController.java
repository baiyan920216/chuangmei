package com.chuangmei.web.web;

import com.chuangmei.web.core.jdbc.common.MessageModel;
import com.chuangmei.web.entity.User;
import com.chuangmei.web.repository.UserDao;
import com.chuangmei.web.service.UserService;
import com.chuangmei.web.utils.CheckCodeUtil;
import com.chuangmei.web.utils.CommonUtils;
import com.chuangmei.web.utils.DateUtils;
import com.chuangmei.web.utils.IpUtils;
import com.chuangmei.web.utils.Mail;
import com.chuangmei.web.utils.PropertyUtil;
import com.chuangmei.web.utils.SessionUtils;
import com.chuangmei.web.utils.StringUtil;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;
	
	private String mailFrom = PropertyUtil.PropertiesInfo("email.from");
	
	private String smtp = PropertyUtil.PropertiesInfo("email.smtp");
	
	private String mailPass = PropertyUtil.PropertiesInfo("email.pass");;
	
	@ResponseBody
	@RequestMapping("/forget")
	public MessageModel forget(String uname,String mail){
		MessageModel msg = new MessageModel();
		msg.initSuccess(true,"操作成功");
		try {
			User loginuser = userService.findByUnameAndMail(uname, mail);
			if(loginuser == null){
				msg.setSuccess(false);
				msg.setMsg("用户名或邮箱错误");
			}else{
				//校验通过，更新密码
				String newPass = CheckCodeUtil.createCode(6);
				String decodePass = CommonUtils.md5(newPass);
				loginuser.setUpass(decodePass);
				userService.update(loginuser);
				//发邮件
				Mail mailUtil = new Mail(smtp, mailFrom, mailPass);
				String mailContent="密码重置成功，新密码为：" + newPass;
				mailUtil.sendMail(loginuser.getMail(), mailFrom, "密码找回", mailContent, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作异常");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/resetPass")
	public MessageModel resetPass(Long uid){
		MessageModel msg = new MessageModel();
		msg.initSuccess(true,"操作成功");
		try {
			User loginuser = userService.findOne(uid);
			if(loginuser == null){
				msg.setSuccess(false);
				msg.setMsg("用户不存在");
			}else{
				//校验通过，更新密码
				String newPass = "123456";
				String decodePass = CommonUtils.md5(newPass);
				loginuser.setUpass(decodePass);
				userService.update(loginuser);
				msg.initSuccess(true,"重置成功，密码为123456");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setSuccess(false);
			msg.setMsg("操作异常");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/sigin")
	public MessageModel sigin(HttpServletRequest request,User registerUser){
		MessageModel msg = new MessageModel();
		msg.setSuccess(false);
		msg.setMsg("注册失败");
		try {
			//校验用户名重复
			Long resultCount = userDao.findCount(" uname='"+registerUser.getUname()+"'", new HashMap<String,Object>());
			
			if(resultCount>0){
				msg.setMsg("用户名已存在");
				return msg;
			}
			
			if(StringUtil.checkNotNull(registerUser.getSupportid())){
				//校验推荐人用户名是否存在
				if(userDao.findCount(" uname = '"+registerUser.getSupportid()+"' ",new HashMap<String, Object>())<=0){
					msg.setMsg("推荐人账户不存在");
					return msg;
				}
			}
			
			
			//校验手机号注册数量
			List<User> resultUsers = userService.findUsersByTel(registerUser.getTel());
			if(resultUsers==null || resultUsers.size()<=3){
				//添加用户
				registerUser.setId(null);
				registerUser.MD5Password();
				
				registerUser.setLocked(0);
				User resultUser = userService.save(registerUser);
				msg.initSuccess(true,"注册成功");
				msg.setData(resultUser);
				
			}else if(resultUsers!= null && resultUsers.size()>3){
				//手机号注册帐号数量超过3个
				msg.initSuccess(false,"您的手机号注册帐号数量已经超过3个");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping("/login")
	public MessageModel login(HttpServletRequest request,String uname,String upass){
		MessageModel msg = new MessageModel();
		msg.setSuccess(false);
		msg.setMsg("登陆失败");
		try {
			upass = CommonUtils.md5(upass.toLowerCase());
			User loginuser = userService.findByUnameAndUpass(uname, upass);
			if(loginuser != null){
				
				if(loginuser.getLocked()==1){
					msg.setMsg("登陆失败,用户已被锁定");
					return msg;
				}
//				else if(loginuser.getLocked()==2){
//					msg.setMsg("等待管理员激活，暂时无法登录");
//					return msg;
//				}
				
				SessionUtils.getInstance().putUserInSession(loginuser, request.getSession());
				msg.setSuccess(true);
				msg.setMsg("登陆成功");
				
				
				userService.update(loginuser);
				
				if(loginuser.getRoletype()==0){
					//管理员
					msg.setData("/views/adminindex");
					
				}else{
					//普通用户
					msg.setData("/member/index");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
}
