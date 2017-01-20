package com.scu.xjhm.application.impl;

import java.util.List;
import java.util.Set;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.scu.xjhm.application.NewsApplication;
import com.scu.xjhm.news.core.domain.News;

@Named
@Transactional
public class NewsApplicationImpl implements NewsApplication {

	public News getNews(Long id) {
		return News.get(News.class, id);
	}
	
	public void createNews(News news) {
		news.save();
	}
	
	public void updateNews(News news) {
		news.save();
	}
	
	public void removeNews(News news) {
		if(news != null){
			news.remove();
		}
	}
	
	public void removeNewss(Set<News> newss) {
		for (News news : newss) {
			news.remove();
		}
	}
	
	public List<News> findAllNews() {
		return News.findAll(News.class);
	}
	
}