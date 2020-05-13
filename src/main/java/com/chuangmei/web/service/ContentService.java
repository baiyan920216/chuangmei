package com.chuangmei.web.service;

import com.chuangmei.web.core.jdbc.common.DataGridModel;
import com.chuangmei.web.core.jdbc.common.PageModel;
import com.chuangmei.web.entity.Content;
import com.chuangmei.web.entity.User;
import com.chuangmei.web.repository.ContentDao;
import com.chuangmei.web.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContentService extends BaseService<Content, Long> {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	private ContentDao contentDao;
	
	@Autowired
	public ContentService(ContentDao contentDao) {
		this.contentDao = contentDao;
		this.res = contentDao;
	}
	
	/**web分页方法
	 * @param dataGridModel
	 * @return
	 */
	public PageModel<Content> getContentByPage(String type,String title,DataGridModel dataGridModel){
		String order = dataGridModel.getOrder();
		String orderCon = "";
		if("new".equals(order)){
			orderCon = " order by createtime desc";
		}else if("hot".equals(order)){
			orderCon = " order by rownum desc, createtime desc";
		}
		String condition = " 1=1 ";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(StringUtil.checkNotNull(type)){
			condition += " and type = :type ";
			paramMap.put("type", type);
		}
		if(StringUtil.checkNotNull(title)){
			condition += " and title like :title ";
			paramMap.put("title", "%"+title+"%");
		}
		PageModel<Content> page = contentDao.getListByPage(dataGridModel.getPage(), dataGridModel.getRows(),condition, paramMap , orderCon);
		return page;
	}
	
	/**APP分页接口
	 * @param dataGridModel
	 * @return
	 */
	public PageModel<Content> getAppContentByPage(String type,User user,DataGridModel dataGridModel){
		String order = dataGridModel.getOrder();
		String orderCon = "";
		if("new".equals(order)){
			orderCon = " order by createtime desc";
		}else if("hot".equals(order)){
			orderCon = " order by rownum desc, createtime desc";
		}
		String condition = " type = :type ";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type", type);
		PageModel<Content> page = contentDao.getListByPage(dataGridModel.getPage(), dataGridModel.getRows(),condition, paramMap , orderCon);
		List<Content> cList = page.getRows();
		for (int i = 0; i < cList.size(); i++) {
			//TODO 处理文章连接，后续可能还要处理图片连接
			cList.get(i).setContent("/appContent?cid="+cList.get(i).getId()+"&forwardid="+user.getId());
		}
		page.setRows(cList);
		return page;
	}
}
