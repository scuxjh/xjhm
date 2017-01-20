package com.scu.xjhm.news.facade;

import java.util.List;

import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import com.scu.xjhm.news.facade.dto.NewsDTO;

public interface NewsFacade {

	public InvokeResult getNews(Long id);
	
	public InvokeResult createNews(NewsDTO news);
	
	public InvokeResult removeNews(Long id);
	
	public InvokeResult removeNewss(Long[] ids);
	
	public List<NewsDTO> findAllNews();
	
	public Page<NewsDTO> pageQueryNews(NewsDTO news, int currentPage, int pageSize);
	

}

