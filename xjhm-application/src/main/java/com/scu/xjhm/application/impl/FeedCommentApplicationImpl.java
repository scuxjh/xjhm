package com.scu.xjhm.application.impl;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;
import com.scu.xjhm.application.FeedApplication;
import com.scu.xjhm.application.FeedCommentApplication;
import com.scu.xjhm.feedcomment.core.domain.FeedComment;

@Named
@Transactional
public class FeedCommentApplicationImpl implements FeedCommentApplication {
   @Inject
	private FeedApplication feedApplication;
   long a=123;
	public FeedComment getFeedComment(Long id) {
		return FeedComment.get(FeedComment.class, id);
	}
	//通过字段属性来查找符合的对象
	public List<FeedComment> getFeedCommentByProperty(Long value,String propname) {
		
		return FeedComment.findByProperty(FeedComment.class,propname,value);
	}
	
	
	public void creatFeedComment(FeedComment feedComment) {
		feedComment.save();
	}
	
	public void updateFeedComment(FeedComment feedComment) {
		feedComment .save();
	}
	
	public void removeFeedComment(FeedComment feedComment) {
		if(feedComment != null){
			feedComment.remove();
		}
	}
	
	public void removeFeedComments(Set<FeedComment> feedComments) {
		for (FeedComment feedComment : feedComments) {
			feedComment.remove();
		}
	}
	
	public List<FeedComment> findAllFeedComment() {
		return FeedComment.findAll(FeedComment.class);
	}
	
}
