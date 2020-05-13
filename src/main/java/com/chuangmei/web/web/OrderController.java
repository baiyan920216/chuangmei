package com.chuangmei.web.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chuangmei.web.core.jdbc.common.BaseMessageModel;
import com.chuangmei.web.core.jdbc.common.DataGridModel;
import com.chuangmei.web.core.jdbc.common.MessageModel;
import com.chuangmei.web.core.jdbc.common.PageModel;
import com.chuangmei.web.entity.Coin;
import com.chuangmei.web.entity.ManagementAwart;
import com.chuangmei.web.entity.Order;
import com.chuangmei.web.entity.Orderpay;
import com.chuangmei.web.entity.User;
import com.chuangmei.web.repository.CoinDao;
import com.chuangmei.web.repository.ContentDao;
import com.chuangmei.web.repository.ManagementAwartDao;
import com.chuangmei.web.repository.OrderDao;
import com.chuangmei.web.repository.OrderPayDao;
import com.chuangmei.web.repository.SellerDao;
import com.chuangmei.web.repository.UserDao;
import com.chuangmei.web.service.UserService;
import com.chuangmei.web.utils.DateUtils;
import com.chuangmei.web.utils.FileUtil;
import com.chuangmei.web.utils.SessionUtils;
import com.chuangmei.web.utils.StringUtil;

import net.sf.jxls.transformer.XLSTransformer;

@Controller
@RequestMapping("order")
public class OrderController {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private SellerDao sellerDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ContentDao contentDao;

	@Autowired
	private OrderPayDao orderPayDao;

	@Autowired
	private ManagementAwartDao managementAwartDao;
	
	@Autowired
	private CoinDao coinDao;

	@Autowired
	private UserService userService;
	
	@Value("${export.fileSaveDir}")
	private String fileSavePath;

	@Value("${export.templatePath}")
	private String templatePath;

	
	
	@RequestMapping("/exportExcel")
	@ResponseBody
	public void exportExcel(HttpServletRequest request,HttpServletResponse response) {
		
		
		String ctxPath = request.getSession().getServletContext().getRealPath("");
		OutputStream os = null;
		String templateName = "orderpay_template.xlsx";
		String filename = UUID.randomUUID() + ".xlsx";
		try {
			
			String condition = " 1=1 ";
			Map<String, Object> paramMap = new HashMap<>();
			//查询orderpay 
			List<Orderpay> orderpayList = orderPayDao.find(condition, paramMap, " order by beginpaydate desc");
			
			InputStream is = new FileInputStream(ctxPath+templatePath+templateName);
			Map beans = new HashMap();
			
			//如果是null 则使数组为{}
			if(orderpayList==null){
				orderpayList = new ArrayList<>();
			}
			beans.put("edata", orderpayList);
			// 关联模板
			XLSTransformer transformer = new XLSTransformer();
			Workbook workBook = transformer.transformXLS(is, beans);
			os = new FileOutputStream(ctxPath + fileSavePath+filename);
			workBook.write(os);
			
			
			String attachmentName = "打款记录_"+DateUtils.getCurrentDateTime()+".xlsx";
			File file = new File(ctxPath + fileSavePath+filename);
			FileUtil.download(file, attachmentName, request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@RequestMapping("/tixian")
	@ResponseBody
	public MessageModel tixian(Long money, HttpServletRequest request) {
		MessageModel msg = new MessageModel();
		msg.initSuccess(false, "操作失败");
		try {
			
			User user = SessionUtils.getInstance().getUserInSession(request.getSession());
			
			String condition = " uid = :uid and finishtime like :finishtime and flag='提现'";
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("uid", user.getId());
			paramMap.put("finishtime", DateUtils.getCurrentDate() +"%");
			//每天只能提取一次 校验
			Long his_count = managementAwartDao.findCount(condition, paramMap);
			if(his_count>0){
				msg.initSuccess(false, "每天只能提取一次！");
				return msg;
			}

			
			ManagementAwart managementAwart = new ManagementAwart();
			managementAwart.setSuid(user.getId());
			managementAwart.setSubuname(user.getName());
			managementAwart.setSubuseruname(user.getUname());
			managementAwart.setFinishtime(DateUtils.getCurrentDateTime());

			managementAwart.setUid(user.getId());
			managementAwart.setUname(user.getName());
			managementAwart.setUseruname(user.getUname());

			managementAwart.setMoney(money);
			managementAwart.setFlag("提现");

			managementAwartDao.save(managementAwart);

			// 添加收款记录
			Order order = new Order();
			// 上传凭证状态，可收款
			order.setStatus("3");
			order.setMoney("0");
			order.setReceivemoney(0-money+"");
			order.setOrderdate(DateUtils.getCurrentDateTime());

			// 装载买方信息
			order.setBuyername(user.getName());
			order.setBuyernum(user.getId());
			order.setBuyertel(user.getTel());
			order.setBuyeralipay(user.getAlipay());
			order.setBuyeruname(user.getUname());
			order.setBuyeraddress(user.getAddress());

			if (StringUtil.checkNotNull(user.getSupportid())) {
				User supportUser = userDao.findOne(" uname = '" + user.getSupportid() + "'");
				if (supportUser != null) {
					order.setBuyersuportname(supportUser.getName());
					order.setBuyersuporttel(supportUser.getTel());
				}
			}

			// 保存信息
			orderDao.save(order);

			msg.initSuccess(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("操作异常");
		}

		return msg;
	}

	@RequestMapping("/opsavema")
	@ResponseBody
	public MessageModel opsavema() {
		MessageModel msg = new MessageModel();
		msg.initSuccess(false, "操作失败");
		try {
			Map<String, Object> paramMap = new HashMap<>();
			String condition = "status >= 3 ";
			List<Order> oList = orderDao.find(condition, paramMap, "");
			for (Order order : oList) {
				User user = userDao.findOne(order.getBuyernum());
				User secondUser = null;
				User firstUser = null;
				// 获得上级用户
				if (user != null && StringUtil.checkNotNull(user.getSupportid())) {
					secondUser = userService.findByUname(user.getSupportid());

					ManagementAwart managementAwart = new ManagementAwart();
					managementAwart.setSuid(user.getId());
					managementAwart.setSubuname(user.getName());
					managementAwart.setSubuseruname(user.getUname());
					managementAwart.setFinishtime(DateUtils.getCurrentDateTime());

					managementAwart.setUid(secondUser.getId());
					managementAwart.setUname(secondUser.getName());
					managementAwart.setUseruname(secondUser.getUname());
					managementAwart.setType("一代利润");
					Long money = Long.parseLong(order.getReceivemoney()) - Long.parseLong(order.getMoney());

					managementAwart.setMoney((long) (money * 0.05));
					managementAwart.setFlag("收入");

					managementAwartDao.save(managementAwart);

				}
				// 获得上上级用户
				if (secondUser != null && StringUtil.checkNotNull(secondUser.getSupportid())) {

					firstUser = userService.findByUname(secondUser.getSupportid());

					ManagementAwart managementAwart = new ManagementAwart();
					managementAwart.setSuid(user.getId());
					managementAwart.setSubuname(user.getName());
					managementAwart.setSubuseruname(user.getUname());
					managementAwart.setFinishtime(DateUtils.getCurrentDateTime());

					managementAwart.setUid(firstUser.getId());
					managementAwart.setUname(firstUser.getName());
					managementAwart.setUseruname(firstUser.getUname());
					managementAwart.setType("二代利润");
					Long money = Long.parseLong(order.getReceivemoney()) - Long.parseLong(order.getMoney());

					managementAwart.setMoney((long) (money * 0.1));
					managementAwart.setFlag("收入");

					managementAwartDao.save(managementAwart);
				}
			}

			msg.initSuccess(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("操作异常");
		}

		return msg;
	}

	/**
	 * @param orderid
	 *            orderpayid
	 * @param proofPic
	 *            凭证地址
	 * @return
	 */
	@RequestMapping("/iploadProof")
	@ResponseBody
	public MessageModel iploadProof(Long orderid, String proofPic) {
		MessageModel msg = new MessageModel();
		msg.initSuccess(false, "操作失败");
		try {
			// 获得order信息
			Orderpay orderpay = orderPayDao.findOne(orderid);
			orderpay.setProof(proofPic);
			orderpay.setStatus("1");
			orderpay.setProoftime(DateUtils.getCurrentDateTime());
			orderPayDao.update(orderpay);

			// 验证付款方当前付款订单付款金额是否等于应付金额
			Long buyerorderid = orderpay.getBuyerorderid();
			Order buyerorder = orderDao.findOne(buyerorderid);

			// 当前订单已上传凭证的付款记录 付款总金额
			Long payable = orderPayDao.findSumBySql(
					"SELECT sum(paymoney) from t_orderpay where status != 0 and  buyerorderid = " + buyerorderid);
			if (payable == Long.parseLong(buyerorder.getMoney())) {
				// 当前付款订单已经全部上传凭证
				buyerorder.setStatus("3");
				orderDao.update(buyerorder);

				// 计算管理奖
				User user = userDao.findOne(buyerorder.getBuyernum());
				User secondUser = null;
				User firstUser = null;

				// 获得上级用户
				if (StringUtil.checkNotNull(user.getSupportid())) {
					secondUser = userService.findByUname(user.getSupportid());

					ManagementAwart managementAwart = new ManagementAwart();
					managementAwart.setSuid(user.getId());
					managementAwart.setSubuname(user.getName());
					managementAwart.setSubuseruname(user.getUname());
					managementAwart.setFinishtime(DateUtils.getCurrentDateTime());

					managementAwart.setUid(secondUser.getId());
					managementAwart.setUname(secondUser.getName());
					managementAwart.setUseruname(secondUser.getUname());
					managementAwart.setType("一代利润");
					Long money = Long.parseLong(buyerorder.getReceivemoney()) - Long.parseLong(buyerorder.getMoney());

					managementAwart.setMoney((long) (money * 0.05));
					managementAwart.setFlag("收入");

					managementAwartDao.save(managementAwart);

				}
				// 获得上上级用户
				if (StringUtil.checkNotNull(secondUser.getSupportid())) {

					firstUser = userService.findByUname(secondUser.getSupportid());

					ManagementAwart managementAwart = new ManagementAwart();
					managementAwart.setSuid(user.getId());
					managementAwart.setSubuname(user.getName());
					managementAwart.setSubuseruname(user.getUname());
					managementAwart.setFinishtime(DateUtils.getCurrentDateTime());

					managementAwart.setUid(firstUser.getId());
					managementAwart.setUname(firstUser.getName());
					managementAwart.setUseruname(firstUser.getUname());
					managementAwart.setType("二代利润");
					Long money = Long.parseLong(buyerorder.getReceivemoney()) - Long.parseLong(buyerorder.getMoney());

					managementAwart.setMoney((long) (money * 0.1));
					managementAwart.setFlag("收入");

					managementAwartDao.save(managementAwart);
				}
			}

			msg.initSuccess(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("操作异常");
		}

		return msg;
	}

	@RequestMapping("/shoukuan")
	@ResponseBody
	public MessageModel shoukuan(Long orderpayid, HttpServletRequest request) {
		MessageModel msg = new MessageModel();
		msg.initSuccess(false, "操作失败");
		try {

			// 获得用户信息
			// User loginUser =
			// SessionUtils.getInstance().getUserInSession(request.getSession());

			// 获得order信息
			Orderpay orderpay = orderPayDao.findOne(orderpayid);
			// 设置支付记录状态为已支付
			orderpay.setStatus("2");
			orderPayDao.update(orderpay);

			// 验证当前订单收款是否均已完成
			Order sellerOrder = orderDao.findOne(orderpay.getSellerorderid());
			Long receiveMoney = orderPayDao
					.findSumBySql("SELECT sum(paymoney) from t_orderpay where status = 2 and  sellerorderid = "
							+ orderpay.getSellerorderid());
			if (receiveMoney == Long.parseLong(sellerOrder.getReceivemoney())) {
				// 设置收款订单状态为已完成（已收款完成）
				sellerOrder.setStatus("4");
				orderDao.update(sellerOrder);
			}

			msg.initSuccess(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("操作异常");
		}

		return msg;
	}

	@RequestMapping("/switchOrderStatus")
	@ResponseBody
	public MessageModel switchLock(Long id, String status) {
		MessageModel msg = new MessageModel();
		msg.initSuccess(false, "操作失败");
		try {
			// 获得order信息
			Order order = orderDao.findOne(id);
			order.setStatus(status);

			if ("1".equals(status)) {
				// 预约通过，设置上传凭证截止时间为当前时间+8小时

				order.setProofendtime(DateUtils.getDateTime(DateUtils.addHours(new Date(), 8)));

				// 设置卖家返现时间,当前时间+6天

				order.setOrderenddate(DateUtils.getDateStr(DateUtils.addDate(new Date(), 0, 0, 6)));
			} else if ("0".equals(status)) {
				order.setProofendtime("");

				order.setOrderenddate("");
			}
			orderDao.update(order);
			msg.initSuccess(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("操作异常");
		}

		return msg;
	}

	@RequestMapping("/addSysOrder")
	@ResponseBody
	public MessageModel addSysOrder(String orderusername) {
		MessageModel msg = new MessageModel();
		msg.initSuccess(false, "操作失败");
		try {

			// 查询用户
			User user = userDao.findOne(" uname = '" + orderusername + "'");
			if (user == null) {
				msg.setMsg("用户不存在！");
				return msg;
			}
			Order order = new Order();
			// 上传凭证状态，可收款
			order.setStatus("3");
			order.setMoney("0");
			order.setReceivemoney("1000000");
			order.setOrderdate(DateUtils.getCurrentDateTime());

			// 装载买方信息
			order.setBuyername(user.getName());
			order.setBuyernum(user.getId());
			order.setBuyertel(user.getTel());
			order.setBuyeralipay(user.getAlipay());
			order.setBuyeruname(user.getUname());
			order.setBuyeraddress(user.getAddress());

			if (StringUtil.checkNotNull(user.getSupportid())) {
				User supportUser = userDao.findOne(" uname = '" + user.getSupportid() + "'");
				if (supportUser != null) {
					order.setBuyersuportname(supportUser.getName());
					order.setBuyersuporttel(supportUser.getTel());
				}
			}

			// 保存信息
			orderDao.save(order);

			msg.initSuccess(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("操作异常");
		}

		return msg;
	}

	/**
	 * @param id
	 *            收款订单编号
	 * @param payMoney
	 * @param beginpaydate
	 * @param buyerorderid
	 *            付款订单编号
	 * @return
	 */
	@RequestMapping("/saveOrderPay")
	@ResponseBody
	public MessageModel saveOrderPay(Long id, Float payMoney, String beginpaydate, Long buyerorderid) {
		MessageModel msg = new MessageModel();
		msg.initSuccess(false, "操作失败");
		try {
			// TODO 收款人对应订单
			Order sellerOrder = orderDao.findOne(id);
			// 付款人对应订单
			Order buyerOrder = orderDao.findOne(buyerorderid);

			// 验证收款人收款是否超出应收金额
			Long receiveMoney = orderPayDao
					.findSumBySql("SELECT sum(paymoney) from t_orderpay where sellerorderid = " + id);
			if (payMoney + receiveMoney > Long.parseLong(sellerOrder.getReceivemoney())) {
				// 已存在匹配的收款金额与本次匹配的金额总和超过应收金额
				msg.setMsg("已存在匹配的收款金额与本次匹配的金额总和超过应收金额:" + sellerOrder.getReceivemoney());
				return msg;
			}

			// 验证付款人付款金额是否超出应付金额
			Long payable = orderPayDao
					.findSumBySql("SELECT sum(paymoney) from t_orderpay where buyerorderid = " + buyerorderid);
			if (payMoney + payable > Long.parseLong(buyerOrder.getMoney())) {
				// 付款人付款已超过应付款
				msg.setMsg("付款人付款已超过应付款：" + buyerOrder.getMoney());
				return msg;
			}

			Orderpay orderPay = new Orderpay();
			// 买方订单id
			orderPay.setBuyerorderid(buyerorderid);
			orderPay.setPaymoney(payMoney);
			orderPay.setBuyeruserid(buyerOrder.getBuyernum());
			orderPay.setBuyername(buyerOrder.getBuyername());
			orderPay.setBuyeralipay(buyerOrder.getBuyeralipay());
			orderPay.setBuyeruname(buyerOrder.getBuyeruname());
			orderPay.setBuyersupportname(buyerOrder.getBuyersuportname());
			orderPay.setBuyersupporttel(buyerOrder.getBuyersuporttel());
			orderPay.setBuyeraddress(buyerOrder.getBuyeraddress());
			// 匹配时间
			orderPay.setPairtime(DateUtils.getCurrentDateTime());
			orderPay.setBeginpaydate(beginpaydate);
			orderPay.setStatus("0");

			// 卖方订单id
			orderPay.setSellerorderid(id);
			orderPay.setSelleralipay(sellerOrder.getBuyeralipay());
			orderPay.setSellername(sellerOrder.getBuyername());
			orderPay.setSelleruname(sellerOrder.getBuyeruname());
			orderPay.setSellertel(sellerOrder.getBuyertel());
			orderPay.setSelleruserid(sellerOrder.getBuyernum());
			orderPay.setSellersupportname(sellerOrder.getBuyersuportname());
			orderPay.setSellersupporttel(sellerOrder.getBuyersuporttel());
			orderPay.setSelleraddress(sellerOrder.getBuyeraddress());

			orderPayDao.save(orderPay);

			msg.initSuccess(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("操作异常");
		}

		return msg;
	}

	@RequestMapping("list")
	@ResponseBody
	public PageModel<Order> QueryContentByPage(String query_name, String query_buyernum, String query_alipay,
			String query_endtime, DataGridModel dataGridModel) {
		PageModel<Order> pageMsg = null;
		try {
			pageMsg = this.getOrderByPage(query_name, query_buyernum, query_alipay, query_endtime, dataGridModel);
			// 查询分页数据
			pageMsg.initSuccess(true, "获得分页信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			pageMsg.initSuccess(false, "获得分页信息失败");
		}
		return pageMsg;
	}

	@RequestMapping("orderPairSellerList")
	@ResponseBody
	public PageModel<Order> orderPairSellerList(String query_name, String query_buyernum, String query_alipay,
			String query_endtime, String status, DataGridModel dataGridModel) {
		PageModel<Order> pageMsg = null;
		try {
			Map<String, Object> paramMap = new HashMap<>();
			String condition = " status = :status ";
			paramMap.put("status", status);
			if (StringUtil.checkNotNull(query_name)) {
				condition += " and buyername like :buyername ";
				paramMap.put("buyername", "%" + query_name + "%");
			}
			if (StringUtil.checkNotNull(query_buyernum)) {
				condition += " and buyeruname like :buyeruname ";
				paramMap.put("buyeruname", "%" + query_buyernum + "%");
			}
			if (StringUtil.checkNotNull(query_alipay)) {
				condition += " and buyeralipay like :buyeralipay ";
				paramMap.put("buyeralipay", "%" + query_alipay + "%");
			}
			if (StringUtil.checkNotNull(query_endtime)) {
				condition += " and orderenddate like :orderenddate ";
				paramMap.put("orderenddate", "%" + query_endtime + "%");
			}

			pageMsg = orderDao.getListByPage(dataGridModel.getPage(), dataGridModel.getRows(), condition, paramMap,
					" order by orderenddate asc");
			// 查询分页数据
			pageMsg.initSuccess(true, "获得分页信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			pageMsg.initSuccess(false, "获得分页信息失败");
		}
		return pageMsg;
	}

	@RequestMapping("delOrderPair")
	@ResponseBody
	public MessageModel delOrderPair(Long id) {
		MessageModel msg = new MessageModel();
		msg.initSuccess(false, "操作失败");
		try {

			orderPayDao.delete(id);

			msg.initSuccess(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("操作异常");
		}

		return msg;
	}

	@RequestMapping("delOrder")
	@ResponseBody
	public MessageModel delOrder(Long id) {
		MessageModel msg = new MessageModel();
		msg.initSuccess(false, "操作失败");
		try {

			orderDao.delete(id);

			msg.initSuccess(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("操作异常");
		}

		return msg;
	}

	@RequestMapping("orderPairList")
	@ResponseBody
	public MessageModel orderPairList(Long sellerorderid) {
		MessageModel pageMsg = new MessageModel();
		try {
			Map<String, Object> paramMap = new HashMap<>();
			String condition = " sellerorderid = :sellerorderid ";
			paramMap.put("sellerorderid", sellerorderid);

			List<Orderpay> datalist = orderPayDao.find(condition, paramMap, "");
			pageMsg.setData(datalist);

			// 查询分页数据
			pageMsg.initSuccess(true, "获得分页信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			pageMsg.initSuccess(false, "获得分页信息失败");
		}
		return pageMsg;
	}

	public PageModel<Order> getOrderByPage(String query_name, String query_buyernum, String query_alipay,
			String query_endtime, DataGridModel dataGridModel) {
		String condition = " 1=1 ";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtil.checkNotNull(query_name)) {
			condition += " and buyername like :buyername ";
			paramMap.put("buyername", "%" + query_name + "%");
		}
		if (StringUtil.checkNotNull(query_buyernum)) {
			condition += " and buyeruname like :buyeruname ";
			paramMap.put("buyeruname", "%" + query_buyernum + "%");
		}
		if (StringUtil.checkNotNull(query_alipay)) {
			condition += " and buyeralipay like :buyeralipay ";
			paramMap.put("buyeralipay", "%" + query_alipay + "%");
		}
		if (StringUtil.checkNotNull(query_endtime)) {
			condition += " and orderenddate like :orderenddate ";
			paramMap.put("orderenddate", "%" + query_endtime + "%");
		}
		PageModel<Order> page = orderDao.getListByPage(dataGridModel.getPage(), dataGridModel.getRows(), condition,
				paramMap, " order by orderdate desc");
		return page;
	}

	/**
	 * 会员预约
	 * 
	 * @param content
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public BaseMessageModel saveSellerInfo(Long cid, HttpServletRequest request) {
		BaseMessageModel msg = new BaseMessageModel();
		msg.initSuccess(false, "预约失败");
		try {
			// 获得用户信息
			User loginUser = SessionUtils.getInstance().getUserInSession(request.getSession());
			
			//验证创币余额 最少有一个创业币
			String condition = " select sum(coin) from t_coin where uid = :uid ";
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("uid", loginUser.getId());
			Long sumcoin = coinDao.getNamedParameterJdbcTemplate().queryForLong(condition, paramMap);
			if(sumcoin < 1){
				msg.initSuccess(false, "创美币余额不足，请联系管理员！");
				return msg;
			}

			Coin coin = new Coin();
			Order order = new Order();
			order.setCid(cid);

			

			// 预约中
			order.setStatus("0");
			// 根据页面金额设定
			if (loginUser.getRoletype() == 1) {
				// 黄金会员 1000
				order.setMoney("1000");
				order.setReceivemoney("1200");
				
				coin.setCoin(-1l);
				
			} else if (loginUser.getRoletype() == 2) {
				// 白金会员3000
				order.setMoney("3000");
				order.setReceivemoney("3600");
				
				coin.setCoin(-3l);
			}
			order.setOrderdate(DateUtils.getCurrentDateTime());

			condition = " money != 0 and cid=:cid and buyernum=:buyernum and status in ('0','1','2')";
			paramMap = new HashMap<>();
			paramMap.put("cid", cid);
			paramMap.put("buyernum", loginUser.getId());

			// 验证此条消息是否已经被该用户预约
			List<Order> orderHis = orderDao.find(condition, paramMap, "");
			if (orderHis != null && orderHis.size() > 0) {
				msg.initSuccess(false, "您已经预约，请不要重复预约！");
				return msg;
			}
			// 验证预约消息是否在预约时间段内
			condition = " id=:cid and now() between starttime and endtime ";
			paramMap = new HashMap<>();
			paramMap.put("cid", cid);
			if (contentDao.findCount(condition, paramMap) <= 0) {
				msg.initSuccess(false, "暂不在预约时间段内，不能预约！");
				return msg;
			}

			// 装载买方信息
			order.setBuyername(loginUser.getName());
			order.setBuyernum(loginUser.getId());
			order.setBuyertel(loginUser.getTel());
			order.setBuyeralipay(loginUser.getAlipay());
			order.setBuyeruname(loginUser.getUname());
			order.setBuyeraddress(loginUser.getAddress());

			if (StringUtil.checkNotNull(loginUser.getSupportid())) {
				User suportUser = userDao.findOne(" uname = '" + loginUser.getSupportid() + "'");
				order.setBuyersuportname(suportUser.getName());
				order.setBuyersuporttel(suportUser.getTel());
			}

			// 保存信息
			orderDao.saveOrUpdate(order);
			
			//扣除1个创美币
			
			coin.setCreatedtime(DateUtils.getCurrentDateTime());
			coin.setName(loginUser.getName());
			coin.setTel(loginUser.getTel());
			coin.setUid(loginUser.getId());
			coin.setUname(loginUser.getUname());	
			coin.setRemark("预约扣除");
			coin.setType("预约扣除");
			coin.setUid(loginUser.getId());
			
			//保存信息
			coinDao.saveOrUpdate(coin);
			
			msg.initSuccess(true, "预约成功");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return msg;
	}
	// public static void main(String[] args) {
	// System.out.println(DateUtils.getDateTime(new Date()));
	// System.out.println(DateUtils.getDateTime(DateUtils.addDate1(new Date(),
	// 0, 0, 6)));
	// }
}
