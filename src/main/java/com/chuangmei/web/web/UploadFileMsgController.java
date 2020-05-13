package com.chuangmei.web.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.chuangmei.web.utils.FileUtil;
import com.chuangmei.web.utils.PropertyUtil;
import com.chuangmei.web.utils.RestResponse;
import com.chuangmei.web.utils.StringUtil;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

@Controller
@RequestMapping("/uploadFileMsg")
public class UploadFileMsgController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private String uploadFileDiskPath = PropertyUtil.PropertiesInfo("uploadFileDiskPath");;

	private String uploadFileAccessPath = PropertyUtil.PropertiesInfo("uploadFileAccessPath");;
	
	public static final String FILE_PREVIEW = ".xls,.xlsx,.doc,.docx,.pdf";// 可预览

	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	@ResponseBody
	public void fileUpload(@RequestParam MultipartFile file, String type, String projectNo, HttpServletRequest request,
			MultipartRequest mr, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		logger.info(" [附件文件上传]开始");
		try {
			request.setCharacterEncoding("UTF-8");
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			logger.error(e1.getMessage());
		}

		// 判断文件是否为空
		if (file != null) {
			logger.info(" 附件文件不为空");
			try {
				String uploadFilePath = uploadFileDiskPath;
				// 不存在则创建文件
				File f = new File(uploadFilePath);
				if (!f.exists()) {
					// f.createNewFile();
					f.mkdirs();
				}

				// 获取文件的后缀
				String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));// .png

				// 使用UUID生成文件名称
				String uuidFilename = StringUtil.GenerateUUID() + suffix;
				// 文件保存路径(包含盤符)
				String filePath = uploadFilePath + uuidFilename;
				// 文件保存路径(不包含盤符)
				String accessFilePath = uploadFileAccessPath + uuidFilename;
				logger.debug(" 附件文件保存路径{} " + filePath);
				// 转存文件
				file.transferTo(new File(filePath));
				// 真实文件名
				String filename = file.getOriginalFilename();
				logger.debug(" 附件文件真实文件名{} " + filename);
				// 文件大小
				Long fileSize = file.getSize();// 获取文件的字节大小，单位byte
				Map<String, String> map = new HashMap<String, String>();
				map.put("attachmentName", filename);// 附件名称
				map.put("attachmentUrl", accessFilePath);// 附件地址
				map.put("attachmentSize", String.valueOf(fileSize));// 附件大小,单位为byte
				// 通过后缀名判断是否可预览 contains()查找是否含有某些字符如果true就是含有
				if (FILE_PREVIEW.contains(suffix)) {
					map.put("readFlag", "1");// 是否可读取标识，1：可预览；0：不可预览
				} else {
					map.put("readFlag", "0");// 是否可读取标识，1：可预览；0：不可预览
				}
				resultMap.put("STATUS_OK", 1);
				resultMap.put("msg", "文件上传成功");
				resultMap.put("data", map);
				logger.debug(" 附件文件大小{} " + String.valueOf(fileSize));
			} catch (Exception e) {
				resultMap.put("STATUS_ERROR", 0);
				resultMap.put("msg", "文件上传失败");
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		} else {
			logger.info(" 附件文件为空");
		}
		// System.out.println(new Gson().toJson(resultMap));
		try {
			response.getWriter().write(new Gson().toJson(resultMap));
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("附件文件上传异常", e.getMessage());
			// e.printStackTrace();
		}
		// return messageModel;
		// return new Gson().toJson(messageModel);
		logger.info(" [附件文件上传]结束");
	}
	
	@RequestMapping(value = "/isExist")
	@ResponseBody
	public RestResponse<Map<String, String>> getFileIsExist(String attachmentUrl, HttpServletRequest request) {
		logger.info(" [判断文件是否存在]开始");
		Map<String, String> statusMap = Maps.newHashMap();
		try {
			if (!Strings.isNullOrEmpty(attachmentUrl)) {
				String attaUrl = attachmentUrl.replace(uploadFileAccessPath, uploadFileDiskPath);
				File file = new File(attaUrl);
				if (file.exists()) {// 文件存在
					logger.info(" [文件存在]");
					statusMap.put("flag", "1");
					return RestResponse.ok(statusMap);
				} else {
					logger.info(" [文件不存在]结束");
					statusMap.put("flag", "0");
					statusMap.put("msg", "文件不存在");
					return RestResponse.ok(statusMap);
				}
			} else {
				logger.debug(" 附件不存在");
				logger.info(" [文件是否存在]结束");
				statusMap.put("flag", "0");
				statusMap.put("msg", "文件路径为空");
				return RestResponse.ok(statusMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("判断文件是否存在异常", e);
			return RestResponse.error("判断文件是否存在异常");
		}
	}

	@RequestMapping(value = "/fileDownload")
	@ResponseBody
	public void fileDownload(HttpServletRequest request, String attachmentUrl, String attachmentName,
			HttpServletResponse response) {
		logger.info(" [下载文件]开始");
		// 下载
		if (!Strings.isNullOrEmpty(attachmentUrl) && !Strings.isNullOrEmpty(attachmentName)) {
			String attaUrl = attachmentUrl.replace(uploadFileAccessPath, uploadFileDiskPath);
			File file = new File(attaUrl);
			try {
				logger.debug(" 下载文件的名称]{} " + attachmentName);
				logger.debug(" 下载文件的文件地址]{} " + attaUrl);
				FileUtil.download(file, attachmentName, request, response);
				logger.info(" [下载文件]成功");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("下载文件异常", e);
			}
		}
		logger.info(" [下载文件]结束");
	}


}
