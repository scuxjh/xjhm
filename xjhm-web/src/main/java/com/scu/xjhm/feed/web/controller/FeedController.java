package com.scu.xjhm.feed.web.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.dayatang.utils.Page;

import com.mysql.jdbc.StringUtils;
import com.scu.xjhm.feed.facade.FeedFacade;
import com.scu.xjhm.feed.facade.dto.*;

import org.openkoala.koala.commons.InvokeResult;

@Controller
@RequestMapping("/Feed")
public class FeedController {
		
	@Inject
	private FeedFacade feedFacade;
	private static final Logger LOGGER = LoggerFactory.getLogger(FeedController.class);
	@ResponseBody
	@RequestMapping("/add")
	public InvokeResult add(FeedDTO feedDTO) {
		LOGGER.info("1111,in add,FeedDTO.getfeedTitle:"+feedDTO.getFeedTitle()+",startTime:"+feedDTO.getStartTime()+",categoryId:"+feedDTO.getCategoryId());
		return feedFacade.creatFeed(feedDTO);
	}
	/**
	 * 图片上传。
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @version
	 * 20170910pm
	 *     1.new.
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	public InvokeResult uploadImage(HttpServletRequest request) throws IllegalStateException, IOException{
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (!multipartResolver.isMultipart(request))  return InvokeResult.failure("无文件上传");
		// 转换成多部分request
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		// 取得request中的所有文件名
		Iterator<String> iter = multiRequest.getFileNames();
		if(!iter.hasNext())  return InvokeResult.failure("无文件上传");
		// 取得上传文件
		MultipartFile f = multiRequest.getFile(iter.next());
		if(f == null)  return InvokeResult.failure("无文件上传");
		// 取得当前上传文件的文件名称
		String myFileName = f.getOriginalFilename();
		// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
		if(StringUtils.isNullOrEmpty(myFileName))  return InvokeResult.failure("无文件上传");
		// 定义上传路径
		String path = "E:\\TDDOWNLOAD\\" + myFileName;
		String realPath = request.getSession().getServletContext().getRealPath("uploadImage");
		LOGGER.info("the realPath of uploadImage:"+realPath);
		File localFile = new File(realPath);
		if(!localFile.exists()) localFile.mkdir();
		localFile = new File(realPath + "/" + myFileName);
		f.transferTo(localFile);
		Map resultMap = new HashMap();
		String imageUrl = request.getContextPath()+"/uploadImage/"+myFileName;
		LOGGER.info("imageUrl:"+imageUrl);
		resultMap.put("message", "上传成功。");
		resultMap.put("imageUrl", imageUrl);
		return InvokeResult.success(resultMap);
	}
	/*
	@ResponseBody
	@RequestMapping("/update")
	public InvokeResult update(FeedDTO feedDTO) {
		return feedFacade.updateFeed(feedDTO);
	}
	*/
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(FeedDTO feedDTO, @RequestParam int page, @RequestParam int pagesize) {
		LOGGER.info("1111,in pageJson..");
		Page<FeedDTO> all = feedFacade.pageQueryFeed(feedDTO, page, pagesize);
		return all;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public InvokeResult remove(@RequestParam String ids) {
		String[] value = ids.split(",");
        Long[] idArrs = new Long[value.length];
        for (int i = 0; i < value.length; i ++) {
        	        					idArrs[i] = Long.parseLong(value[i]);
						        }
        return feedFacade.removeFeeds(idArrs);
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public InvokeResult get(@PathVariable Long id) {
		return feedFacade.getFeed(id);
	}
	
		
  /*  @InitBinder    
    public void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
        dateFormat.setLenient(false);    
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
        //CustomDateEditor 可以换成自己定义的编辑器。  
        //注册一个Date 类型的绑定器 。
        binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);
    }*/
	
}