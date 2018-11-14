package com.scu.xjhm.news.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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
import com.scu.xjhm.common.core.utils.DateUtils;
import com.scu.xjhm.news.facade.NewsFacade;
import com.scu.xjhm.news.facade.dto.NewsDTO;

import org.openkoala.koala.commons.InvokeResult;

@Controller
@RequestMapping("/news")
public class NewsController {
		
	@Inject
	private NewsFacade newsFacade;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NewsController.class);
	
	/**
	 * 新增/修改 新闻 
	 * @param newsDTO
	 * @return
	 * @version
	 * 20170117nt
	 * 		1.new。
	 */
	@ResponseBody
	@RequestMapping("/add")
	public InvokeResult add(NewsDTO newsDTO) {
		LOGGER.info("1111,in add,newsDTO.getNewsTitle:"+newsDTO.getNewsTitle()+",startTime:"+newsDTO.getStartTime()+",categoryId:"+newsDTO.getCategoryId()+",display:"+newsDTO.getDisplay()+",content:"+newsDTO.getContent());
		return newsFacade.createNews(newsDTO);
	}
	/**
	 * 图片上传。
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @version
	 * 20181110
	 * 	   1.上传的媒体文件保存到本地磁盘.
	 * 20170910pm
	 *     1.new.
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadMedia", method = RequestMethod.POST)
	public InvokeResult uploadMedia(HttpServletRequest request) throws IllegalStateException, IOException{
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
		//20181111pm
		int dotIndex = myFileName.lastIndexOf(".");
		myFileName = myFileName.substring(0, dotIndex) + DateUtils.getTimeInMillis() + myFileName.substring(dotIndex);
		LOGGER.info("renamed myFileName: " + myFileName);
		// 定义上传路径
		/*
		String path = "d:\\upload\\uploadMedia\\";
		String realPath = request.getSession().getServletContext().getRealPath("uploadMedia");
		LOGGER.info("the realPath of uploadImage:" + realPath);
		File localFile = new File(path);
		if(!localFile.exists()) localFile.mkdir();
		File localFile2 = new File(realPath);
		if(!localFile2.exists()) localFile2.mkdir();
		localFile = new File(path + "/" + myFileName);
		localFile2 = new File(realPath + "/" + myFileName);
		*/
		//String realPath = "/uploadMedia";
		String realPath = "d:\\upload\\uploadMedia\\";//20181114
		File localFile = new File(realPath);
		if(!localFile.exists()) localFile.mkdir();
		String myFilePath = realPath + myFileName;
		localFile = new File(myFilePath);
		
		f.transferTo(localFile);
		//FileUtils.copyFile(localFile, localFile2);
		Map resultMap = new HashMap();
		//String mediaUrl = request.getContextPath()+"/uploadMedia/"+myFileName;
		String mediaUrl = "/uploadMedia/" + myFileName;//20181111pm
		LOGGER.info("mediaUrl:" + mediaUrl);
		resultMap.put("message", "上传成功。");
		resultMap.put("mediaUrl", mediaUrl);
		return InvokeResult.success(resultMap);
	}
	
	/**
	 * 
	 * @param newsDTO
	 * @param page
	 * @param pagesize
	 * @return
	 * @version
	 * 20170117nt
	 * 		1.new.
	 */
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(NewsDTO newsDTO, @RequestParam int page, @RequestParam int pagesize) {
		LOGGER.info("1111,in pageJson..");
		Page<NewsDTO> all = newsFacade.pageQueryNews(newsDTO, page, pagesize);
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
        return newsFacade.removeNewss(idArrs);
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public InvokeResult get(@PathVariable Long id) {
		return newsFacade.getNews(id);
	}
	
}
