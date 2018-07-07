package com.scu.xjhm.feed.facade.impl.assembler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import com.scu.xjhm.common.core.utils.DateUtils;
import com.scu.xjhm.feed.facade.dto.FeedCommentDTO;
import com.scu.xjhm.feedcomment.core.domain.*;

public class FeedCommentAssembler {
	public static FeedCommentDTO  toDTO(FeedComment  feedComment){
		if (feedComment == null) {
			return null;
		}
    	FeedCommentDTO result  = new FeedCommentDTO();
	    	result.setId (feedComment.getId());
     	    	result.setVersion (feedComment.getVersion());
     	    	result.setCreateTime (DateUtils.convertDateToStr(feedComment.getCreateTime(), DateUtils.DATE_TIME_PATTERN));
     	    	result.setUpdateTime (DateUtils.convertDateToStr(feedComment.getUpdateTime(), DateUtils.DATE_TIME_PATTERN));
     	    	result.setIc (feedComment.getIc());
     	    	result.setFeedId (feedComment.getFeedId());
     	    	result.setFeedContent (feedComment.getFeedContent());
     	    	result.setCommunityId (feedComment.getCommunityId());
     	    	result.setYardId (feedComment.getYardId());
     	    	result.setCategoryId (feedComment.getCategoryId());
     	    	result.setCommentTime (DateUtils.convertDateToStr(feedComment.getCommentTime(), DateUtils.DATE_TIME_PATTERN));
     	    	result.setUnread (feedComment.getUnread());
     	    	result.setBuildingId (feedComment.getBuildingId());
     	    	result.setCommenterName (feedComment.getCommenterName());
     	    	result.setCommenterTel (feedComment.getCommenterTel());
     	    return result;
	 }
	
	public static List<FeedCommentDTO>  toDTOs(List<FeedComment>  feedComments){
		if (feedComments == null) {
			return null;
		}
		List<FeedCommentDTO> results = new ArrayList<FeedCommentDTO>();
		for (FeedComment each : feedComments) {
			results.add(toDTO(each));
		}
		return results;
	}
	
	 public static FeedComment  toEntity(FeedCommentDTO  feedCommentDTO){
	 	if (feedCommentDTO == null) {
			return null;
		}
	 	FeedComment result  = new FeedComment();
        //result.setId (feedCommentDTO.getId());
        // result.setVersion (feedCommentDTO.getVersion());
        String createTime=feedCommentDTO.getCreateTime();
         result.setCreateTime (DateUtils.convertStrToDate(createTime, DateUtils.DATE_TIME_PATTERN));
         //result.setUpdateTime (feedCommentDTO.getUpdateTime());
         //result.setIc (feedCommentDTO.getIc());
         result.setFeedId (feedCommentDTO.getFeedId());
         result.setFeedContent (feedCommentDTO.getFeedContent());
         //result.setCommunityId (feedCommentDTO.getCommunityId());
         //result.setYardId (feedCommentDTO.getYardId());
        // result.setCategoryId (feedCommentDTO.getCategoryId());
         String commentTime=feedCommentDTO.getCommentTime();
         result.setCommentTime (DateUtils.convertStrToDate(commentTime, DateUtils.DATE_TIME_PATTERN));
         //result.setUnread (feedCommentDTO.getUnread());
        // result.setBuildingId (feedCommentDTO.getBuildingId());
         result.setCommenterName (feedCommentDTO.getCommenterName());
         result.setCommenterTel (feedCommentDTO.getCommenterTel());
 	  	return result;
	 }
	
	public static List<FeedComment> toEntities(Collection<FeedCommentDTO> feedCommentDTOs) {
		if (feedCommentDTOs == null) {
			return null;
		}
		
		List<FeedComment> results = new ArrayList<FeedComment>();
		for (FeedCommentDTO each : feedCommentDTOs) {
			results.add(toEntity(each));
		}
		return results;
	}
}
