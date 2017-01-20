package com.scu.xjhm.news.facade.impl;

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

import com.scu.xjhm.common.core.utils.DataUtils;
import com.scu.xjhm.common.core.utils.SysConstants;
import com.scu.xjhm.common.facade.DataDictionaryFacade;
import com.scu.xjhm.news.core.domain.News;
import com.scu.xjhm.news.facade.NewsFacade;
import com.scu.xjhm.news.facade.dto.NewsDTO;
import com.scu.xjhm.news.facade.impl.assembler.NewsAssembler;
import com.scu.xjhm.application.NewsApplication;

@Transactional(value = "transactionManager_security")
@Named
public class NewsFacadeImpl implements NewsFacade {

	@Inject
	private NewsApplication  application;
	@Inject
	private DataDictionaryFacade dicFacade;

	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	public InvokeResult getNews(Long id) {
		return InvokeResult.success(NewsAssembler.toDTO(application.getNews(id)));
	}
	
	public InvokeResult createNews(NewsDTO newsDTO) {
		application.createNews(NewsAssembler.toEntity(newsDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult removeNews(Long id) {
		application.removeNews(application.getNews(id));
		return InvokeResult.success();
	}
	
	public InvokeResult removeNewss(Long[] ids) {
		Set<News> newss= new HashSet<News>();
		for (Long id : ids) {
			newss.add(application.getNews(id));
		}
		application.removeNewss(newss);
		return InvokeResult.success();
	}
	
	public List<NewsDTO> findAllNews() {
		return NewsAssembler.toDTOs(application.findAllNews());
	}
	/**
	 * 综合查询
	 * @version
	 *     20170119nt
	 *     1.new.
	 */
	public Page<NewsDTO> pageQueryNews(NewsDTO queryVo, int currentPage, int pageSize) {
		//获取并设置"news.CATEGORY"字典项。20170119nt
		dicFacade.getDictItems(SysConstants.DICT_NEWS_CATETORY);
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select t from News t where 1=1 ");
	   	
	   	if (!DataUtils.isNullOrEmpty(queryVo.getNewsTitle())) {
	   		jpql.append(" and t.newsTitle like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getNewsTitle()));
	   	}
	   	/*
	   	if (queryVo.getPosition() != null) {
	   		jpql.append(" and t.position=?");
	   		conditionVals.add(queryVo.getPosition());
	   	}
	   	if (queryVo.getCategoryId() != null) {
	   		jpql.append(" and t.categoryId=?");
	   		conditionVals.add(queryVo.getCategoryId());
	   	}	
	   	if (queryVo.getDisplay() != null) {
	   		jpql.append(" and t.display=?");
	   		conditionVals.add(queryVo.getDisplay());
	   	}*/	
        Page<News> pages = getQueryChannelService()
		   .createJpqlQuery(jpql.toString())
		   .setParameters(conditionVals)
		   .setPage(currentPage, pageSize)
		   .pagedList();
		   
        return new Page<NewsDTO>(pages.getStart(), pages.getResultCount(),pageSize, NewsAssembler.toDTOs(pages.getData()));
	}	
}