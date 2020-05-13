package com.chuangmei.web.service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chuangmei.web.core.jdbc.common.DataGridModel;
import com.chuangmei.web.core.jdbc.common.PageModel;
import com.chuangmei.web.entity.User;
import com.chuangmei.web.repository.UserDao;
import com.chuangmei.web.utils.StringUtil;

@Component
public class UserService extends BaseService<User, Long> {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	private UserDao userDao;
	
	
	@Autowired
	public UserService(UserDao userDao) {
		this.userDao = userDao;
		this.res = userDao;
	}
	
	public List<User> findUsersByTel(String tel){
		String condition = " tel = :tel ";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("tel", tel);
		return userDao.find(condition, paramMap, "");
	}
	
	
	/** 根据用户名查询
	 * @param uname
	 * @return
	 */
	public User findByUname(String uname){
		String condition = " uname = :uname ";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uname", uname);
		List<User> ulist = userDao.find(condition , paramMap, "");
		if(ulist != null && ulist.size()>0){
			return ulist.get(0);
		}
		return null;
	}
	
	public User findByUnameAndUid(String uname,Long uid){
		String condition = " uname = :uname ";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uname", uname);
		
		if(uid!=null){
			condition = " and id != :id";
			paramMap.put("id", uid);
		}
		
		List<User> ulist = userDao.find(condition , paramMap, "");
		if(ulist != null && ulist.size()>0){
			return ulist.get(0);
		}
		return null;
	}
	
	/** 用户名模糊查询
	 * @param uname
	 * @return
	 */
	public List<User> findByUnameLike(String uname){
		String condition = " uname like :uname and roletype > 1 ";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(uname==null){
			uname="";
		}
		paramMap.put("uname", "%"+uname+"%");
		List<User> ulist = userDao.find(condition , paramMap, "");
		
		return ulist;
	}
	
	public User checkNikeNameRepead(String nikename,Long id){
		String condition = " nikename = :nikename ";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("nikename", nikename);
		if(id!=null){
			condition += " and id != :id";
			paramMap.put("id", id);
		}
		List<User> ulist = userDao.find(condition , paramMap, "");
		if(ulist != null && ulist.size()>0){
			return ulist.get(0);
		}
		return null;
	}
	
	/** 根据用户名密码查询
	 * @param uname
	 * @param upass
	 * @return
	 */
	public User findByUnameAndUpass(String uname,String upass){
		String condition = " uname = :uname and upass = :upass";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uname", uname);
		paramMap.put("upass", upass);
		List<User> ulist = userDao.find(condition , paramMap, "");
		if(ulist != null && ulist.size()>0){
			return ulist.get(0);
		}
		return null;
	}
	
	/** 根据用户名密码查询
	 * @param uname
	 * @param upass
	 * @return
	 */
	public User findByUnameAndMail(String uname,String mail){
		String condition = " uname = :uname and mail = :mail";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uname", uname);
		paramMap.put("mail", mail);
		List<User> ulist = userDao.find(condition , paramMap, "");
		if(ulist != null && ulist.size()>0){
			return ulist.get(0);
		}
		return null;
	}
	
	public String mathSupport(Integer count){
		
		if(count<=0){
			return 0+"段";
		}
		
		Integer level = getPowerLevel(count);
		
//		String need = getPowerNeed(level,count);
		
		return level+"段";
	}
	
	public String getPowerNeed(Integer level,Integer count){
		
		DecimalFormat df = new DecimalFormat("##0");
		String need = df.format(Math.pow(6, (level+1))-count);
		
		return need;
	}
	
	public Integer getPowerLevel(Integer count){
		if(count<=0){
			return 0;
		}
		String x = Math.log(count)/Math.log(6)+"";
		Integer level = Integer.parseInt(x.substring(0, x.lastIndexOf(".")));
		return level;
	}

	public Long checkRoleUse(Long id) {
		Long result = 0L;
		
		String condition = " roletype = :id";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		
		result = res.findCount(condition, paramMap);
		
		
		return result;
	}

	public List<User> findClientByUnameLike(String uname) {
		String condition = " uname like :uname and roletype = 1 ";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(uname==null){
			uname="";
		}
		paramMap.put("uname", "%"+uname+"%");
		List<User> ulist = userDao.find(condition , paramMap, "");
		
		return ulist;
	}


//	public AppRankingInfo getAppPowerRankingDayInfo(Long uid) {
//		AppRankingInfo result = new AppRankingInfo();
//		User user = userDao.findOne(uid);
//		
//		//支持数
////		Integer supportNum = userDao.getSuppNo(user.getId());
//		result.setLevel(userDao.findPowerLevelByUserDay(uid));
//		
//		//根据九阳神功类型查询未领取奖励
//		Integer prize = rankingdetailService.getUnprizeByUserAndType(uid, "power");
//		result.setPrize(prize);
//		
//		//查询九阳神功排名情况，前20
//		List<Map<String,Object>> powerRankingList = userDao.getUserPowerDayTop();
//		Map<String,Object> row = null;
//		for (int i = 0; i < powerRankingList.size(); i++) {
//			row = powerRankingList.get(i);
//			
//			//计算九阳神功等级
//			Integer row_supportNum = (Integer) row.get("supportnum");
//			powerRankingList.get(i).put("level", getPowerLevel(row_supportNum));
//			//TODO 作弊人数，暂无法获取
//			powerRankingList.get(i).put("cheat",0);
//			//TODO 以获得奖金数，暂无法获取
//			powerRankingList.get(i).put("prize",0);
//		}
//		
//		result.setRankingList(powerRankingList);
//		
//		return result;
//	}

	public PageModel<User> getSupportByPage(Long uid,Integer level,DataGridModel dataGridModel) {
		String order = dataGridModel.getOrder();
		String orderCon = " order by supporttime desc";
		String funcStr = "";
		if(level==9){
			funcStr = "getChildLv(:uid,"+level+",'all')";
		}else{
			funcStr = "getChildLv(:uid,"+level+",'eq')";
		}
		String condition = " FIND_IN_SET(id, "+funcStr+") ";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		PageModel<User> page = userDao.getListByPage(dataGridModel.getPage(), dataGridModel.getRows(),condition, paramMap , orderCon);
		return page;
	}

	public PageModel<User> getUserByPage(String uname,String name,String alipay,String tel,
			DataGridModel dataGridModel) {
		// TODO Auto-generated method stub
		String order = dataGridModel.getOrder();
		String orderCon = " order by uname ";
		String condition = "  roletype != 0 ";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(StringUtil.checkNotNull(uname)){
			condition += " and uname like :uname ";
			paramMap.put("uname", "%"+uname+"%");
		}
		if(StringUtil.checkNotNull(name)){
			condition += " and name like :name ";
			paramMap.put("name", "%"+name+"%");
		}
		if(StringUtil.checkNotNull(alipay)){
			condition += " and alipay like :alipay ";
			paramMap.put("alipay", "%"+alipay+"%");
		}
		if(StringUtil.checkNotNull(tel)){
			condition += " and tel like :tel ";
			paramMap.put("tel", "%"+tel+"%");
		}
		PageModel<User> page = userDao.getListByPage(dataGridModel.getPage(), dataGridModel.getRows(),condition, paramMap , orderCon);
		return page;
//		
//		
//		
//		
//		String condition = " uname like :uname and roletype = 1 ";
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		if(uname==null){
//			uname="";
//		}
//		paramMap.put("uname", "%"+uname+"%");
//		List<User> ulist = userDao.find(condition , paramMap, "");
//		return null;
	}

	public List<Map<String, Object>> getRankingByType(int top, String type) {
		List<Map<String, Object>> result = null;
		
		if(type.equals("power")){
			result = userDao.getUserPowerTop(top);
		}else if(type.equals("bean")){
			result = userDao.getUserBeanTop(top);
		}else if(type.equals("coins")){
			result = userDao.getUserCoinsTop(top);
		}
		
		return result;
	}
}
