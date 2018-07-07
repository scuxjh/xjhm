package com.scu.xjhm.application;


import java.util.List;
import java.util.Set;

import com.scu.xjhm.feedcomment.core.domain.FeedComment;

public interface FeedCommentApplication {

	public FeedComment getFeedComment(Long id);
	
	public List<FeedComment> getFeedCommentByProperty(Long value,String propname);
	
	public void creatFeedComment(FeedComment feedComment);
	
	public void updateFeedComment(FeedComment feedComment);
	
	public void removeFeedComment(FeedComment feedComment);
	
	public void removeFeedComments(Set<FeedComment> feedComments);
	
	public List<FeedComment> findAllFeedComment();
	
}

