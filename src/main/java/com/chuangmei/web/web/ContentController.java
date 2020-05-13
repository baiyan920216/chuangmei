package com.chuangmei.web.web;

import com.chuangmei.web.core.jdbc.common.BaseMessageModel;
import com.chuangmei.web.core.jdbc.common.DataGridModel;
import com.chuangmei.web.core.jdbc.common.MessageModel;
import com.chuangmei.web.core.jdbc.common.PageModel;
import com.chuangmei.web.entity.Content;
import com.chuangmei.web.service.ContentService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("list")
	@ResponseBody
	public PageModel<Content> QueryContentByPage(String type,String title,DataGridModel dataGridModel ){
		PageModel<Content> pageMsg = null ;
		try {
			pageMsg = contentService.getContentByPage(type,title,dataGridModel);
			//查询分页数据
			pageMsg.initSuccess(true, "获得分页信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			pageMsg.initSuccess(false, "获得分页信息失败");
		}
		return pageMsg;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public MessageModel delContent(Long id){
		MessageModel msg = new MessageModel();
		msg.initSuccess(false, "保存失败");
		try {
			contentService.delete(id);
			msg.initSuccess(true, "保存成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return msg;
	}
	
	/** 保存
	 * @param content
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public BaseMessageModel saveHomepageInfo(Content content){
		BaseMessageModel msg = new BaseMessageModel();
		msg.initSuccess(false, "保存失败");
		try {
			if(content!=null&&content.getId()==null){
				//新增文章
				content.setRownum(0L);
				//默认有效
				content.setFlag("1");
			}
			//保存用户信息
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			content.setCreatetime(df.format(new Date()));
			contentService.saveOrUpdate(content);
			msg.initSuccess(true, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}
	
}
