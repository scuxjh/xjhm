package com.scu.xjhm.feed.facade.impl.assembler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scu.xjhm.feed.core.domain.*;
import com.scu.xjhm.feed.facade.dto.*;
import com.scu.xjhm.news.core.domain.News;
import com.scu.xjhm.news.facade.impl.assembler.NewsAssembler;
import com.scu.xjhm.common.core.utils.DataBuffered;
import com.scu.xjhm.common.core.utils.DataUtils;
import com.scu.xjhm.common.core.utils.DateUtils;
import com.scu.xjhm.common.core.utils.SysConstants;

public class FeedAssembler {
	private static final Logger LOGGER = LoggerFactory.getLogger(FeedAssembler.class);
	public static FeedDTO  toDTO(Feed  feed){
		if (feed == null) {
			return null;
		}
    	FeedDTO result  = new FeedDTO();
	    	result.setId (feed.getId());
     	    	result.setVersion (feed.getVersion());
     	    	result.setFeedTitle (feed.getFeedTitle());
     	    	result.setStartTime(DateUtils.convertDateToStr(feed.getStartTime(), DateUtils.DATE_TIME_PATTERN));
     	     	result.setEndTime(DateUtils.convertDateToStr(feed.getEndTime(), DateUtils.DATE_TIME_PATTERN));
     	    	result.setContent (feed.getContent());
     	    	result.setCountNum (feed.getCountNum());
     	    	result.setPosition (feed.getPosition());
     	    	result.setPageView (feed.getPageView());
     	    	result.setCategoryId (feed.getCategoryId());
     	    	 String categoryName = String.valueOf(DataBuffered.dataDictionaryMap.get(SysConstants.DICT_FEED_CATETORY).get(String.valueOf(feed.getCategoryId())));
     	    	result.setCategoryName(categoryName);
     	    	result.setDisplay (feed.getDisplay());
     	    	result.setAdminId (feed.getAdminId());
     	    	result.setCreateTime(DateUtils.convertDateToStr(feed.getCreateTime(), DateUtils.DATE_TIME_PATTERN));
     		    result.setUpdateTime(DateUtils.convertDateToStr(feed.getUpdateTime(), DateUtils.DATE_TIME_PATTERN));
     	    return result;
	 }
	
	public static List<FeedDTO>  toDTOs(Collection<Feed>  feeds){
		if (feeds == null) {
			return null;
		}
		List<FeedDTO> results = new ArrayList<FeedDTO>();
		for (Feed each : feeds) {
			results.add(toDTO(each));
		}
		return results;
	}
	
	 public static Feed  toEntity(FeedDTO  feedDTO){
	 	if (feedDTO == null) {
			return null;
		}
	 	Feed result = null;
	 	Long id = feedDTO.getId();
	 	if(DataUtils.isNullOrEmpty(id)){
	 		LOGGER.info("1111,in toEntity, isNullOrEmpty...");
	 		result = new Feed();
	 	}else{
	 		result = Feed.get(Feed.class, id); 
	 	}
        //result.setId (feedDTO.getId());
         //result.setVersion (feedDTO.getVersion());
         result.setFeedTitle (feedDTO.getFeedTitle());
         String startTime = feedDTO.getStartTime();
//       LOGGER.info("2222,in toEntity,startTime:"+startTime);
       result.setStartTime(DateUtils.convertStrToDate(startTime, DateUtils.DATE_TIME_PATTERN));
       String endTime = feedDTO.getEndTime();
       result.setEndTime(DateUtils.convertStrToDate(endTime, DateUtils.DATE_TIME_PATTERN));
      // String createTime = "2017-4-25 10:08:50";
       //String createTime = feedDTO.getCreateTime();
      // result.setCreateTime(DateUtils.convertStrToDate("2017", DateUtils.DATE_TIME_PATTERN));
         result.setContent (feedDTO.getContent());
        // result.setCountNum (feedDTO.getCountNum());
         //result.setPosition (feedDTO.getPosition());
        // result.setPageView (feedDTO.getPageView());
         result.setCategoryId (feedDTO.getCategoryId());
         result.setDisplay (feedDTO.getDisplay());
         //result.setAdminId (feedDTO.getAdminId());
         //result.setCreateTime (feedDTO.getCreateTime());
        // result.setUpdateTime (feedDTO.getUpdateTime());
 	  	return result;
	 }
	
	public static List<Feed> toEntities(Collection<FeedDTO> feedDTOs) {
		if (feedDTOs == null) {
			return null;
		}
		
		List<Feed> results = new ArrayList<Feed>();
		for (FeedDTO each : feedDTOs) {
			results.add(toEntity(each));
		}
		return results;
	}
}
