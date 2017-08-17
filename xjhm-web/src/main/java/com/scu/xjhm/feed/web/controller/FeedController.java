package com.scu.xjhm.feed.web.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.dayatang.utils.Page;

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
