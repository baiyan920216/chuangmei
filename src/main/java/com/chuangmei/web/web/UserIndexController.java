package com.chuangmei.web.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chuangmei.web.entity.Coin;
import com.chuangmei.web.entity.Content;
import com.chuangmei.web.entity.ManagementAwart;
import com.chuangmei.web.entity.Order;
import com.chuangmei.web.entity.Orderpay;
import com.chuangmei.web.entity.Role;
import com.chuangmei.web.entity.User;
import com.chuangmei.web.repository.CoinDao;
import com.chuangmei.web.repository.ManagementAwartDao;
import com.chuangmei.web.repository.OrderDao;
import com.chuangmei.web.repository.OrderPayDao;
import com.chuangmei.web.repository.UserDao;
import com.chuangmei.web.service.ContentService;
import com.chuangmei.web.service.RoleService;
import com.chuangmei.web.utils.DateUtils;
import com.chuangmei.web.utils.SessionUtils;

@Controller
public class UserIndexController {

	@Autowired
	private ContentService contentService;

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderPayDao orderPayDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ManagementAwartDao managementAwartDao;
	
	@Autowired
	private CoinDao coinDao;
	
	@RequestMapping(value={"/toUserAddCoin"})
	public String toAddCoin(Long uid,Model model){
		model.addAttribute("uid", uid);
		
		return "user/userAddCoin";
	}

	@RequestMapping(value = { "/member/index" })
	public String index(Model model, HttpServletRequest request) {

		User loginUser = SessionUtils.getInstance().getUserInSession(request.getSession());

		List<Content> contents = null;
		String condition = " type = :type and flag = '1' ";
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("type", loginUser.getRoletype());
		model.addAttribute("locked", loginUser.getLocked());
		contents = contentService.find(condition, paramMap, "");

		if (contents != null && contents.size() > 0) {
			model.addAttribute("content", contents.get(0));

			// 查询用户是否已经预约
			condition = " buyernum=:buyernum and cid=:cid ";
			paramMap = new HashMap<>();
			paramMap.put("buyernum", loginUser.getId());
			paramMap.put("cid", contents.get(0).getId());

			Order order = orderDao.findOne(condition, paramMap);

			if (order != null) {
				// 用户已经预约
				model.addAttribute("orderStatus", order.getStatus());
				model.addAttribute("editData", order);
			}
		}

		return "member/index";
	}

	@RequestMapping("/member/userInfo")
	public String userInfo(Model model, HttpServletRequest request) {

		User loginUser = SessionUtils.getInstance().getUserInSession(request.getSession());
		User userInfo = userDao.findOne(loginUser.getId());

		model.addAttribute("editData", userInfo);

		List<Role> rList = roleService.findUserRoleList();
		model.addAttribute("rList", rList);

		return "member/userInfo";
	}
	
	
	@RequestMapping("/member/toGivenCoinpage")
	public String toGivenCoinpage(Model model, HttpServletRequest request) {

		User loginUser = SessionUtils.getInstance().getUserInSession(request.getSession());
		User userInfo = userDao.findOne(loginUser.getId());


		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("rootUname", userInfo.getUname());
		List<User> uList = userDao.find("FIND_IN_SET(uname,getChildListByRootIdAndLevel(:rootUname))", paramMap , "order by uname ");
		model.addAttribute("uList", uList);
		
		//查询剩余创币金额
		paramMap = new HashMap<>();
		String condition = " select sum(coin) from t_coin where uid = :uid ";
		paramMap.put("uid", loginUser.getId());
		Long sumcoin = coinDao.getNamedParameterJdbcTemplate().queryForLong(condition, paramMap);
		model.addAttribute("sumcoin", sumcoin);

		return "member/givenCoin";
	}
	
	@RequestMapping("/member/coinHis")
	public String coinHis(Model model, HttpServletRequest request) {

		User loginUser = SessionUtils.getInstance().getUserInSession(request.getSession());
		User userInfo = userDao.findOne(loginUser.getId());


		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uname", userInfo.getUname());
		List<Coin> cList = coinDao.find("uname = :uname", paramMap , "order by createdtime desc ");
		model.addAttribute("cList", cList);
		
		//查询剩余创币金额
		paramMap = new HashMap<>();
		String condition = " select sum(coin) from t_coin where uid = :uid ";
		paramMap.put("uid", loginUser.getId());
		Long sumcoin = coinDao.getNamedParameterJdbcTemplate().queryForLong(condition, paramMap);
		model.addAttribute("sumcoin", sumcoin);

		return "member/coinHis";
	}
	
	
	/**
	 * @param id orderpayid
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/member/toUpProof")
	public String toUpProof(Long id,Model model, HttpServletRequest request) {
		
		
		Orderpay orderpay = orderPayDao.findOne(id);
		model.addAttribute("editData", orderpay);
		
		return "member/upProof";
	}
	
	@RequestMapping("/member/tofukuanpage")
	public String tofukuanpage(Model model, HttpServletRequest request) {
		
		User loginUser = SessionUtils.getInstance().getUserInSession(request.getSession());
		
		String condition = " 1=1 and buyeruserid = :buyeruserid and beginpaydate = :beginpaydate and  status = 0 ";
		Map<String, Object> paramMap = new HashMap<>();
		//查询orderpay 代付款
		paramMap.put("buyeruserid", loginUser.getId());
		paramMap.put("beginpaydate", DateUtils.getCurrentDate());
		List<Orderpay> orderpayList = orderPayDao.find(condition, paramMap, " order by beginpaydate");
		
		model.addAttribute("orderpayList", orderpayList);
		
		return "member/fukuan";
	}
	@RequestMapping("/member/toshoukuanpage")
	public String toshoukuanpage(Model model, HttpServletRequest request) {
		
		User loginUser = SessionUtils.getInstance().getUserInSession(request.getSession());
		
		String condition = " 1=1 and selleruserid = :selleruserid and status = 1  and beginpaydate <= :beginpaydate  ";
		Map<String, Object> paramMap = new HashMap<>();
		//查询orderpay 代付款
		paramMap.put("selleruserid", loginUser.getId());
		paramMap.put("beginpaydate", DateUtils.getCurrentDate());
		List<Orderpay> orderpayList = orderPayDao.find(condition, paramMap, " order by beginpaydate");
		
		model.addAttribute("orderpayList", orderpayList);
		
		return "member/shoukuan";
	}
	
	
	@RequestMapping("/member/toorderpayHispage")
	public String toorderpayHispage(Model model, HttpServletRequest request) {
		
		User loginUser = SessionUtils.getInstance().getUserInSession(request.getSession());
		
		String condition = " 1=1 and selleruserid = :selleruserid  and beginpaydate <= :beginpaydate";
		Map<String, Object> paramMap = new HashMap<>();
		//查询orderpay 
		paramMap.put("selleruserid", loginUser.getId());
		paramMap.put("beginpaydate", DateUtils.getCurrentDate());
		List<Orderpay> orderpayList = orderPayDao.find(condition, paramMap, " order by beginpaydate");
		
		model.addAttribute("orderpayList", orderpayList);
		
		return "member/orderpayHis";
	}
	@RequestMapping("/member/toManagementAwartpage")
	public String toManagementAwartpage(Model model, HttpServletRequest request) {
		
		User loginUser = SessionUtils.getInstance().getUserInSession(request.getSession());
		
		String condition = " 1=1 and uid = :uid ";
		Map<String, Object> paramMap = new HashMap<>();
		//查询orderpay 
		paramMap.put("uid", loginUser.getId());
		List<ManagementAwart> maList = managementAwartDao.find(condition, paramMap, " order by finishtime desc");
		
		model.addAttribute("data", maList);
		
		//查询余额
		Long money = managementAwartDao.findSumMoneyByUser(loginUser.getId());
		model.addAttribute("money", money);
		model.addAttribute("roleType", loginUser.getRoletype());
		
		return "member/managementAwart";
	}
	
	@RequestMapping("/member/toUserOrderHispage")
	public String toUserOrderHispage(Model model, HttpServletRequest request) {
		
		User loginUser = SessionUtils.getInstance().getUserInSession(request.getSession());
		
		String condition = " 1=1 and buyernum = :buyernum ";
		Map<String, Object> paramMap = new HashMap<>();
		//查询orderpay 代付款
		paramMap.put("buyernum", loginUser.getId());
		List<Order> orderList = orderDao.find(condition, paramMap, " order by orderdate desc");
		
		model.addAttribute("orderList", orderList);
		
		return "member/orderHis";
	}

	@RequestMapping("/member/toReserve")
	public String toReserve(Model model, HttpServletRequest request) {

		User loginUser = SessionUtils.getInstance().getUserInSession(request.getSession());
		
		List<Content> contents = null;
		String condition = " type = :type and flag = '1' ";
		Map<String, Object> paramMap = new HashMap<>();

		paramMap.put("type", loginUser.getRoletype());
		model.addAttribute("locked", loginUser.getLocked());
		contents = contentService.find(condition, paramMap, "");
		if (contents != null && contents.size() > 0) {
			model.addAttribute("content", contents.get(0));
		}
		

		// 查询用户是否已经预约
		condition = " money != 0 and buyernum=:buyernum ";
		paramMap = new HashMap<>();
		paramMap.put("buyernum", loginUser.getId());
		
		List<Order> orderList = orderDao.find(condition, paramMap, " order by orderdate desc ");

		if (orderList != null && orderList.size()>0) {
			Order order = orderList.get(0);
			// 用户已经预约
			model.addAttribute("orderStatus", order.getStatus());
			model.addAttribute("editData", order);
		}

		return "member/reserve";
	}

	@RequestMapping("/member/toUpdateUPass")
	public String toUpdateUPass(Model model, HttpServletRequest request) {

		User loginUser = SessionUtils.getInstance().getUserInSession(request.getSession());
		User userInfo = userDao.findOne(loginUser.getId());

		model.addAttribute("editData", userInfo);

		return "member/updateUPass";
	}
	
	@RequestMapping("/member/toorderpayDescPage")
	public String toorderpayDescPage(Long id,Model model, HttpServletRequest request) {

		Orderpay orderpay = orderPayDao.findOne(id);

		model.addAttribute("editData", orderpay);

		return "member/orderpayDesc";
	}
	@RequestMapping("/member/toorderpayOrverDescPage")
	public String toorderpayOrverDescPage(Long id,Model model, HttpServletRequest request) {

		Orderpay orderpay = orderPayDao.findOne(id);

		model.addAttribute("editData", orderpay);

		return "member/orderpayOrverDesc";
	}
	

}
