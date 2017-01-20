package com.scu.xjhm.news.web.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.dayatang.utils.Page;

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
		LOGGER.info("1111,in add,newsDTO.getNewsTitle:"+newsDTO.getNewsTitle()+",startTime:"+newsDTO.getStartTime()+",categoryId:"+newsDTO.getCategoryId());
		return newsFacade.createNews(newsDTO);
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
