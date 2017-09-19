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
import org.springframework.transaction.annotation.Transactional;

import com.scu.xjhm.feed.core.domain.*;
import com.scu.xjhm.feed.facade.FeedFacade;
import com.scu.xjhm.feed.facade.dto.*;
import com.scu.xjhm.feed.facade.impl.assembler.FeedAssembler;
import com.scu.xjhm.application.FeedApplication;
import com.scu.xjhm.common.core.utils.DataUtils;
import com.scu.xjhm.common.core.utils.SysConstants;
import com.scu.xjhm.common.facade.DataDictionaryFacade;
@Transactional(value = "transactionManager_security")
@Named
public class FeedFacadeImpl implements FeedFacade {

	@Inject
	private FeedApplication  application;
	@Inject
	private DataDictionaryFacade dicFacade;
	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	public InvokeResult getFeed(Long id) {
		return InvokeResult.success(FeedAssembler.toDTO(application.getFeed(id)));
	}
	
	public InvokeResult creatFeed(FeedDTO feedDTO) {
		application.creatFeed(FeedAssembler.toEntity(feedDTO));
		System.out.println();
		return InvokeResult.success();
		
	}
	
	public InvokeResult updateFeed(FeedDTO feedDTO) {
		application.updateFeed(FeedAssembler.toEntity(feedDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult removeFeed(Long id) {
		application.removeFeed(application.getFeed(id));
		return InvokeResult.success();
	}
	
	public InvokeResult removeFeeds(Long[] ids) {
		Set<Feed> feeds= new HashSet<Feed>();
		for (Long id : ids) {
			feeds.add(application.getFeed(id));
		}
		application.removeFeeds(feeds);
		return InvokeResult.success();
	}
	
	public List<FeedDTO> findAllFeed() {
		return FeedAssembler.toDTOs(application.findAllFeed());
	}
	
	public Page<FeedDTO> pageQueryFeed(FeedDTO queryVo, int currentPage, int pageSize) {
		//获取并设置"feed.CATEGORY"字典项。20170119nt
		dicFacade.getDictItems(SysConstants.DICT_FEED_CATETORY);
		List<Object> conditionVals = new ArrayList<Object>();
	   	//StringBuilder jpql = new StringBuilder("select _feed from Feed _feed   where 1=1 ");
		StringBuilder jpql = new StringBuilder("select _feed from Feed _feed where 1=1 ");
		if (!DataUtils.isNullOrEmpty(queryVo.getFeedTitle())) {
	   		jpql.append(" and _feed.feedTitle like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getFeedTitle()));
	   	}
	   	/*if (queryVo.getFeedTitle() != null && !"".equals(queryVo.getFeedTitle())) {
	   		jpql.append(" and _feed.feedTitle like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getFeedTitle()));
	   	}		
	   	if (queryVo.getContent() != null && !"".equals(queryVo.getContent())) {
	   		jpql.append(" and _feed.content like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getContent()));
	   	}		
	   	if (queryVo.getCountNum() != null) {
	   		jpql.append(" and _feed.countNum=?");
	   		conditionVals.add(queryVo.getCountNum());
	   	}	
	   	if (queryVo.getPosition() != null) {
	   		jpql.append(" and _feed.position=?");
	   		conditionVals.add(queryVo.getPosition());
	   	}	
	   	if (queryVo.getPageView() != null) {
	   		jpql.append(" and _feed.pageView=?");
	   		conditionVals.add(queryVo.getPageView());
	   	}	
	   	if (queryVo.getCategoryId() != null) {
	   		jpql.append(" and _feed.categoryId=?");
	   		conditionVals.add(queryVo.getCategoryId());
	   	}	
	   	if (queryVo.getDisplay() != null) {
	   		jpql.append(" and _feed.display=?");
	   		conditionVals.add(queryVo.getDisplay());
	   	}	*/
        Page<Feed> pages = getQueryChannelService()
		   .createJpqlQuery(jpql.toString())
		   .setParameters(conditionVals)
		   .setPage(currentPage, pageSize)
		   .pagedList();
		   
        return new Page<FeedDTO>(pages.getStart(), pages.getResultCount(),pageSize, FeedAssembler.toDTOs(pages.getData()));
	}
	
	
}
