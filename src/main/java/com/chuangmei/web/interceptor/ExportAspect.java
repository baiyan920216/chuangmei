//package com.chuangmei.web.interceptor;
//
//import com.chuangmei.web.core.export.annotation.Export;
//import com.chuangmei.web.core.export.exception.ExportException;
//import com.chuangmei.web.core.jdbc.common.DataGridModel;
//import com.chuangmei.web.core.jdbc.common.PageModel;
//import com.chuangmei.web.dto.ValidMessageDto;
//import com.chuangmei.web.utils.ExcelUtil;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
///**
// * 
// * @ClassName: LogAspect
// * @Description: 注解，权限控制AOP实现
// * @author shaojian.yu
// * @date 2014年11月3日 下午1:51:59
// * 
// */
//@Aspect
//@Component
//public class ExportAspect {
//
//	private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	@Value("${export.fileSaveDir}")
//	private String fileSavePath;
//
//	@Value("${export.templatePath}")
//	private String templatePath;
//
//	@Value("${export.exportAllNumber}")
//	private String exportAllNumber;
//
//	/**
//	 * @Title：doExport
//	 * @Description: 环绕处理 .export 导出当前页面 .allexport 导出所有数据 default 不进行导出处理
//	 * @author gang.fan
//	 * @date 2014年12月19日 上午10:18:20
//	 * @param jpt
//	 * @param export
//	 * @return
//	 * @throws Throwable
//	 */
//	@Around("execution(* com.chuangmei.web.web.*.*(..)) && @annotation(export)")
//	public Object doExport(ProceedingJoinPoint jpt, Export export) throws Throwable, ExportException {
//		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//		HttpServletRequest request = sra.getRequest();
//
//		/*
//		 * 1:.export为当前页面导出，我们需要获得方法的返回值后，把值拆卡获得数据集。
//		 * 
//		 * 2:.allexport为全部导出，我们需要从新计算页面值，然后再次调用方法。
//		 * 
//		 * 3：正常情况，不处理
//		 */
//		Object result = null;
//		if (request.getRequestURI().endsWith(".export")) {
//			result = export(jpt, export);
//			logger.info("当前页面导出");
//		}else{
//			result = jpt.proceed();
//		}
////		else if (request.getRequestURI().endsWith(".allexport")) {
////			logger.info("当前页面导出");
////			result = exportAll(jpt, export);
////		} else {
////			result = jpt.proceed();
////		}
//		return result;
//	}
//
//	/**
//	 * @Title：exportAll
//	 * @Description: TODO
//	 * @author gang.fan
//	 * @date 2014年12月18日 下午2:42:46
//	 * @param jpt
//	 * @param export
//	 * @return
//	 */
//	private Object exportAll(ProceedingJoinPoint jpt, Export export) throws Throwable, ExportException {
//		// TODO Auto-generated method stub
//		Object result = jpt.proceed();
//		Object[] args = jpt.getArgs();
//		try {
//			/*
//			 * 简单的说，这里需要获得原输出中的页面大小，当前页，和总页数，然后算出一个足够大的页面大小用来一次性的再次输出全部的数据
//			 */
//			if (result instanceof PageModel) {
//				for (int i = 0; i < args.length; i++) {
//					if (args[i] instanceof DataGridModel) {
//						PageModel<?> pm = (PageModel<?>) result;
//						DataGridModel d = (DataGridModel) args[i];
//						Long total = pm.getTotal();
//						// 如果返回总数为O,是因为没有数据，在这种话情况下应该把total 设为1.不然在分页计算的时候会出错
//						if (total.intValue() == 0) {
//							total = 1L;
//						}
//						// 如果不是导出全部
//						if (!isExportAll()) {
//							total = findExportAllNumber();
//						}
//						d.setRows(total.intValue());
//						d.setPage(1);
//						args[i] = d;
//						// 跳出循环
//						break;
//					}
//				}
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			throw new ExportException("PageModel分解进行全部数据查询时错误");
//		}
//		result = jpt.proceed(args);
//		exportForPageModel(result, export, jpt);
//		return result;
//	}
//
//	/**
//	 * 如果配置的exportAllNumber为 null 或者是 0，这不进行导出限制。
//	 * 
//	 * @return
//	 */
//	private Boolean isExportAll() {
//		boolean result = false;
//		result = StringUtils.isBlank(exportAllNumber);
//		result = (!result && exportAllNumber.equals("0"));
//		return result;
//	}
//
//	/**
//	 * 获得配置文件，如果转换出错则导出1
//	 * 
//	 * @return
//	 */
//	private Long findExportAllNumber() {
//		Long l = 1L;
//		try {
//			l = Long.parseLong(exportAllNumber);
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//		}
//		return l;
//	}
//
//	/**
//	 * @Title：export
//	 * @Description: TODO
//	 * @author gang.fan
//	 * @date 2014年12月18日 下午1:17:29
//	 * @param jpt
//	 * @param export
//	 * @return
//	 * @throws Throwable
//	 */
//	public Object export(ProceedingJoinPoint jpt, Export export) throws Throwable, ExportException {
//
//		Object result = jpt.proceed();
//
//		//} else if (result instanceof ValidMessageDto) {
//			exportForMessageModel(result, export, jpt);
//		//}
//
//		return result;
//	}
//
//	/**
//	 * @Title：exportForPageModel
//	 * @Description: 输出为PageModel类型的数据转换
//	 * @author gang.fan
//	 * @date 2014年12月18日 下午1:17:31
//	 * @param result
//	 * @param export
//	 * @param jpt
//	 * @return
//	 */
//	public void exportForPageModel(Object result, Export export, ProceedingJoinPoint jpt) throws ExportException {
//
//		try {
////			PageModel<?> pm = (PageModel<?>) result;
//			ValidMessageDto mm = (ValidMessageDto) result;
//			List<?> list = (List<?>) mm.getData();
//			String filePath = buildExcel(list, export, jpt);
//			mm.setExportUrl(filePath);
//			mm.setData(null);
//			result = mm;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			throw new ExportException(" 输出为PageModel类型的数据转换时错误", e);
//		}
//	}
//
//	public void exportForMessageModel(Object result, Export export, ProceedingJoinPoint jpt) throws ExportException {
//		try {
//			ValidMessageDto mm = (ValidMessageDto) result;
//			Object data = mm.getData();
//			 if (data instanceof PageModel<?>) {
//				PageModel<?> page = (PageModel<?>) data;
//				String filePath = buildExcel(page.getRows(), export, jpt);
//				mm.setExportUrl(filePath);
//				mm.setData(null);
//			 }else if(data instanceof HashMap<?, ?>){
//				 HashMap<String,Object> dataMap = (HashMap<String, Object>) data;
//				 String filePath = buildExcel(dataMap.get("rows"), export, jpt);
//					mm.setExportUrl(filePath);
//					mm.setData(null);
//			 }
////			ValidMessageDto mm = (ValidMessageDto) result;
////			Object rows = mm.getData();
//			
//			result = mm;
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			throw new ExportException(" 输出为PageModel类型的数据转换时错误", e);
//		}
//	}
//
//	/**
//	 * @Title：buildExcel
//	 * @Description: 构建当前创建
//	 * @author gang.fan
//	 * @date 2014年12月18日 下午1:17:35
//	 * @param in
//	 * @param export
//	 * @param jpt
//	 * @return
//	 */
//	public String buildExcel(Object in, Export export, ProceedingJoinPoint jpt) throws ExportException {
//		/*
//		 * 1：根据模版文件导出 2：根据column导出
//		 */
//		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//		HttpServletRequest request = sra.getRequest();
//		String ctxPath = request.getSession().getServletContext().getRealPath("");
//		String filename = "";
//
//		// 三种方式处理导出 template 优先级
//		// 1. 不为空的时候按 template进行导出 。
//		// 2. columns 优先级2，不为空的时候按columns处理
//		// 3. template 与 columns都是空的时候,按类名.方法名+.xls模版处理
//
//		// 有导出的模版文件
//		if (StringUtils.isNotBlank(export.template())) {
//			filename = ExcelUtil.exportExcel(ctxPath + fileSavePath, ctxPath + templatePath + export.template(), in);
//		} else if (export.columns().length > 0) {
//			if (in instanceof Collection) {
//				List<?> list = (List<?>) in;
//				filename = ExcelUtil.exportExcel(ctxPath + fileSavePath, list, export.columns());
//			}
//
//		} else {
//			String cname = jpt.getTarget().getClass().getName();
//			String mname = jpt.getSignature().getName();
//			filename = ExcelUtil.exportExcel(ctxPath + fileSavePath,
//			      ctxPath + templatePath + cname + "." + mname + ".xls", in);
//		}
//
//		return filename;
//	}
//}
