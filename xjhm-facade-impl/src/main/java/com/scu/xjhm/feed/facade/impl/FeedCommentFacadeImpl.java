package com.scu.xjhm.feed.facade.impl;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.text.MessageFormat;

import javax.inject.Inject;
import javax.inject.Named;

import org.dayatang.domain.InstanceFactory;
import org.dayatang.utils.Page;
import org.dayatang.querychannel.QueryChannelService;
import org.openkoala.koala.commons.InvokeResult;

import com.scu.xjhm.application.FeedApplication;
import com.scu.xjhm.application.FeedCommentApplication;
import com.scu.xjhm.feed.facade.FeedCommentFacade;
import com.scu.xjhm.feed.facade.dto.FeedCommentDTO;
import com.scu.xjhm.feed.facade.impl.assembler.FeedAssembler;
import com.scu.xjhm.feed.facade.impl.assembler.FeedCommentAssembler;
import com.scu.xjhm.feedcomment.core.domain.*;

@Named
public class FeedCommentFacadeImpl implements FeedCommentFacade {
     long testid=111;
	@Inject
	private FeedCommentApplication  application;
	@Inject
	private FeedApplication feedapplication;
	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	public InvokeResult getFeedComment(Long feedid) {  
		
		return InvokeResult.success(FeedCommentAssembler.toDTOs(application.getFeedCommentByProperty(feedid, "feedId")));
		//return InvokeResult.success(FeedCommentAssembler.toDTO(application.getFeedComment(testid)));
	
	}
	
	public void creatFeedComment(FeedCommentDTO feedCommentDTO) {
		application.creatFeedComment(FeedCommentAssembler.toEntity(feedCommentDTO));
	}
	
	public InvokeResult updateFeedComment(FeedCommentDTO feedCommentDTO) {
		application.updateFeedComment(FeedCommentAssembler.toEntity(feedCommentDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult removeFeedComment(Long id) {
		application.removeFeedComment(application.getFeedComment(id));
		return InvokeResult.success();
	}
	
	public InvokeResult removeFeedComments(Long[] ids) {
		Set<FeedComment> feedComments= new HashSet<FeedComment>();
		for (Long id : ids) {
			feedComments.add(application.getFeedComment(id));
		}
		application.removeFeedComments(feedComments);
		return InvokeResult.success();
	}
	
	public List<FeedCommentDTO> findAllFeedComment() {
		return FeedCommentAssembler.toDTOs(application.findAllFeedComment());
	}
	
	public Page<FeedCommentDTO> pageQueryFeedComment(FeedCommentDTO queryVo, int currentPage, int pageSize) {
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _feedComment from FeedComment _feedComment   where 1=1 ");
	   	if (queryVo.getCreateTime() != null) {
	   		jpql.append(" and _feedComment.createTime between ? and ? ");
	   		conditionVals.add(queryVo.getCreateTime());
	   		conditionVals.add(queryVo.getCreateTimeEnd());
	   	}	
	   	if (queryVo.getUpdateTime() != null) {
	   		jpql.append(" and _feedComment.updateTime between ? and ? ");
	   		conditionVals.add(queryVo.getUpdateTime());
	   		conditionVals.add(queryVo.getUpdateTimeEnd());
	   	}	
	   	if (queryVo.getFeedContent() != null && !"".equals(queryVo.getFeedContent())) {
	   		jpql.append(" and _feedComment.feedContent like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getFeedContent()));
	   	}		
	   	if (queryVo.getUnread() != null && !"".equals(queryVo.getUnread())) {
	   		jpql.append(" and _feedComment.unread like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getUnread()));
	   	}		
	   	if (queryVo.getCommenterName() != null && !"".equals(queryVo.getCommenterName())) {
	   		jpql.append(" and _feedComment.commenterName like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getCommenterName()));
	   	}		
	   	if (queryVo.getCommenterTel() != null && !"".equals(queryVo.getCommenterTel())) {
	   		jpql.append(" and _feedComment.commenterTel like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getCommenterTel()));
	   	}		
        Page<FeedComment> pages = getQueryChannelService()
		   .createJpqlQuery(jpql.toString())
		   .setParameters(conditionVals)
		   .setPage(currentPage, pageSize)
		   .pagedList();
		   
        return new Page<FeedCommentDTO>(pages.getStart(), pages.getResultCount(),pageSize, FeedCommentAssembler.toDTOs(pages.getData()));
	}
	
	
}
