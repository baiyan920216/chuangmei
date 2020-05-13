package com.chuangmei.web.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chuangmei.web.entity.Content;
import com.chuangmei.web.entity.Order;
import com.chuangmei.web.entity.Orderpay;
import com.chuangmei.web.entity.Role;
import com.chuangmei.web.entity.Seller;
import com.chuangmei.web.entity.User;
import com.chuangmei.web.repository.OrderDao;
import com.chuangmei.web.repository.OrderPayDao;
import com.chuangmei.web.repository.SellerDao;
import com.chuangmei.web.repository.SysPropDao;
import com.chuangmei.web.repository.UserDao;
import com.chuangmei.web.service.ContentService;
import com.chuangmei.web.service.RoleService;
import com.chuangmei.web.service.UserService;
import com.chuangmei.web.utils.ConstantUtils;
import com.chuangmei.web.utils.DateUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * @author Gang.fan
 * 页面跳转管理
 */
@Controller
public class IndexController {
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private RoleService roleService;
	
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private SysPropDao sysPropDao;
	
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderPayDao orderPayDao;
	
	@Autowired
	private UserDao userDao;

	@RequestMapping(value={"/","index"})
	public String index(){
		System.out.println("555");
		return "login";
	}
	
	@RequestMapping(value={"toSigin"})
	public String toSigin(Model model){
		List<Role> rList = roleService.findUserRoleList();
		model.addAttribute("rList", rList);
		return "sigin";
	}
	
	@RequestMapping(value={"toForget"})
	public String toForget(){
		return "forget";
	}
	@RequestMapping("toLogin")
	public String toLogin(){
		System.out.println("666");
		return "login";
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute(ConstantUtils.USER_IN_SESSION);
		return "login";
	}
	
	
	@RequestMapping(value={"/views/toUserTreePage"})
	public String toUserTreePage(Long id,Model model){
		
		return "user/tree";
	}
	
	@RequestMapping(value={"/views/showOrder"})
	public String showOrder(Long id,Model model){
		Order order = orderDao.findOne(id);
		//查询订单信息
		model.addAttribute("editData", order);
		
		//查询匹配记录,当前订单对应的付款记录
		String condition = " 1=1 and buyerorderid = :buyerorderid and beginpaydate = :beginpaydate  ";
		Map<String, Object> paramMap = new HashMap<>();
		//查询orderpay 
		paramMap.put("buyerorderid", order.getId());
		paramMap.put("beginpaydate", DateUtils.getCurrentDate());
		List<Orderpay> orderpayList = orderPayDao.find(condition, paramMap, " order by beginpaydate");
		
		model.addAttribute("orderpayList", orderpayList);
		return "order/show";
	}
	
	
	@RequestMapping(value={"/views/toOrderPairPage"})
	public String showOrder(){
		return "order/orderPair";
	}
	
	@RequestMapping(value={"/toPayMoney"})
	public String toPayMoney(Long id,Long buyerorderid,Model model){
		model.addAttribute("id", id);
		model.addAttribute("buyerorderid", buyerorderid);
		
		return "order/payMoney";
	}
	
	
	@RequestMapping(value={"/toAddCoin"})
	public String toAddCoin(Long uid,Model model){
		model.addAttribute("uid", uid);
		
		return "user/addCoin";
	}
	
	@RequestMapping(value={"/toAddOrder"})
	public String toAddOrder(Model model){
		
		return "order/addOrder";
	}
	
	@RequestMapping(value={"/views/toOrderPair"})
	public String toOrderPair(Long id,Model model){
		//
		model.addAttribute("id", id);
		return "order/pair";
	}
	
	@RequestMapping(value={"/views/adminindex"})
	public String adminindex(){
		return "adminindex/index";
	}
	
	@RequestMapping(value={"views/toOrderManagePage"})
	public String toOrderManagePage(){
		return "order/orderManage";
	}
	
	@RequestMapping("/views/toSellerPage")
	public String toSellerPage(Model model){
		
		//查询seller信息
		List<Seller> sellers = sellerDao.findAll();
		if(sellers != null && sellers.size()>0){
			model.addAttribute("editData", sellers.get(0));
		}
		
		return "seller/index";
	}
	
	/**文章管理跳转 begin*/
	@RequestMapping(value={"/views/toContentPage"})
	public String toContentPage(Model model){
		List<Role> rList = roleService.findUserRoleList();
		Map<String,Object> rMap = new HashMap<String,Object>();
		for (Role role : rList) {
			rMap.put(role.getId()+"", role.getName());
		}
		model.addAttribute("rMap", new Gson().toJson(rMap));
		model.addAttribute("rList", rList);
		return "contentManage/index";
	}
	@RequestMapping(value={"/views/toContentEdit"})
	public String toContentEdit(Long id,Model model){
		if(id!=null){
			//修改，查询content数据
			Content con = contentService.findOne(id);
			model.addAttribute("editData", con);
		}
		List<Role> rList = roleService.findUserRoleList();
		model.addAttribute("rList", rList);
		return "contentManage/edit";
	}
	@RequestMapping(value={"/views/showContent"})
	public String showContent(Long id,Model model){
		if(id!=null){
			//修改，查询content数据
			Content con = contentService.findOne(id);
			model.addAttribute("editData", con);
		}
		return "contentManage/show";
	}
	/**文章管理跳转 end*/
	
	
	
	/**权限管理相关跳转 start*/
	@RequestMapping(value={"/views/toRolePage"})
	public String toRolePage(Model model){
		List<Role> rList = roleService.findManageRoleList();
		Map<String,Object> rMap = new HashMap<String,Object>();
		for (Role role : rList) {
			rMap.put(role.getId()+"", role.getName());
		}
		model.addAttribute("rMap", new Gson().toJson(rMap));
		return "role/index";
	}
	
	@RequestMapping(value={"/views/toRoleEdit"})
	public String toRoleEdit(Long id,Model model){
		if(id!=null){
			Role role = roleService.findOne(id);
			model.addAttribute("editData", role);
		}
		return "role/edit";
	}
	/**权限管理相关跳转 end*/
	
	/**权限管理相关跳转 start*/
	@RequestMapping(value={"/views/toUserPage"})
	public String toUserPage(Model model){
		List<Role> rList = roleService.findManageRoleList();
		Map<String,Object> rMap = new HashMap<String,Object>();
		for (Role role : rList) {
			rMap.put(role.getId()+"", role.getName());
		}
		model.addAttribute("rList", rList);
		model.addAttribute("rMap", new Gson().toJson(rMap));
		return "user/index";
	}
	
	@RequestMapping(value={"/views/toClientUserPage"})
	public String toClientUserPage(Model model){
		List<Role> rList = roleService.findUserRoleList();
		Map<String,Object> rMap = new HashMap<String,Object>();
		for (Role role : rList) {
			rMap.put(role.getId()+"", role.getName());
		}
		model.addAttribute("rMap", new Gson().toJson(rMap));
		
		return "user/client";
	}
	
	@RequestMapping(value={"/views/toUserEdit"})
	public String toUserEdit(Long id,Model model){
		if(id!=null){
			User user = userService.findOne(id);
			model.addAttribute("editData", user);
		}
		
		List<Role> rList = roleService.findManageRoleList();
		model.addAttribute("rList", rList);
		
		return "user/edit";
	}
	/**权限管理相关跳转 end*/
	
	
}
