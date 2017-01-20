package com.scu.xjhm.news.facade.impl.assembler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scu.xjhm.common.core.utils.DataBuffered;
import com.scu.xjhm.common.core.utils.DateUtils;
import com.scu.xjhm.common.core.utils.SysConstants;
import com.scu.xjhm.news.core.domain.News;
import com.scu.xjhm.news.facade.dto.NewsDTO;
import com.scu.xjhm.common.core.utils.DataUtils;

public class NewsAssembler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NewsAssembler.class);
	
	public static NewsDTO  toDTO(News  news){
		if (news == null) {
			return null;
		}
    	NewsDTO result  = new NewsDTO();
	    result.setId (news.getId());
     	result.setVersion (news.getVersion());
     	result.setNewsTitle (news.getNewsTitle());     	    	
     	result.setStartTime(DateUtils.convertDateToStr(news.getStartTime(), DateUtils.DATE_TIME_PATTERN));
     	result.setEndTime(DateUtils.convertDateToStr(news.getEndTime(), DateUtils.DATE_TIME_PATTERN));
     	result.setContent (news.getContent());
	    result.setCountNum (news.getCountNum());
	    result.setPosition (news.getPosition());
	    result.setPageView (news.getPageView());
	    result.setCategoryId (news.getCategoryId());
	    String categoryName = String.valueOf(DataBuffered.dataDictionaryMap.get(SysConstants.DICT_NEWS_CATETORY).get(String.valueOf(news.getCategoryId())));
	    result.setCategoryName(categoryName);
	    result.setDisplay (news.getDisplay());
	    result.setAdminId (news.getAdminId());
	    result.setCreateTime(DateUtils.convertDateToStr(news.getCreateTime(), DateUtils.DATE_TIME_PATTERN));
	    result.setUpdateTime(DateUtils.convertDateToStr(news.getUpdateTime(), DateUtils.DATE_TIME_PATTERN));
     	return result;
	 }
	
	public static List<NewsDTO>  toDTOs(Collection<News>  newss){
		if (newss == null) {
			return null;
		}
		List<NewsDTO> results = new ArrayList<NewsDTO>();
		for (News each : newss) {
			results.add(toDTO(each));
		}
		return results;
	}
	/**
	 * DTO 转换为  Entity。
	 * @param newsDTO
	 * @return
	 * @version
	 * 		1.new.
	 */
	 public static News toEntity(NewsDTO  newsDTO){
	 	if (newsDTO == null) {
			return null;
		}
	 	News result = null;
	 	Long id = newsDTO.getId();
	 	if(DataUtils.isNullOrEmpty(id)){
	 		LOGGER.info("1111,in toEntity, isNullOrEmpty...");
	 		result = new News();
	 	}else{
	 		result = News.get(News.class, id);
	 	}
        result.setNewsTitle (newsDTO.getNewsTitle());
        result.setContent (newsDTO.getContent());
        //result.setCountNum (newsDTO.getCountNum());
        result.setCategoryId (newsDTO.getCategoryId());
        String startTime = newsDTO.getStartTime();
//        LOGGER.info("2222,in toEntity,startTime:"+startTime);
        result.setStartTime(DateUtils.convertStrToDate(startTime, DateUtils.DATE_TIME_PATTERN));
        String endTime = newsDTO.getEndTime();
        result.setEndTime(DateUtils.convertStrToDate(endTime, DateUtils.DATE_TIME_PATTERN));
        /*
        result.setPosition (newsDTO.getPosition());
        result.setPageView (newsDTO.getPageView());
        result.setDisplay (newsDTO.getDisplay());
        result.setAdminId (newsDTO.getAdminId());
        */
        //20170118pm
        /*String startTime = newsDTO.getStartTime();
        startTime += ":00";
        result.setStartTime(DateUtils.convertStrToDate(startTime, DateUtils.DATE_TIME_PATTERN));
        */
        /*String endTime = newsDTO.getEndTime();
        endTime += ":00";
        result.setEndTime(DateUtils.convertStrToDate(endTime, DateUtils.DATE_TIME_PATTERN));
        */
 	  	return result;
	 }
	
	public static List<News> toEntities(Collection<NewsDTO> newsDTOs) {
		if (newsDTOs == null) {
			return null;
		}
		
		List<News> results = new ArrayList<News>();
		for (NewsDTO each : newsDTOs) {
			results.add(toEntity(each));
		}
		return results;
	}
}
