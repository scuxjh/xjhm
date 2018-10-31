package com.scu.xjhm.facade.impl;

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

import com.scu.xjhm.facade.dto.*;
import com.scu.xjhm.facade.impl.assembler.MessageAssembler;
import com.scu.xjhm.facade.MessageFacade;
import com.scu.xjhm.application.MessageApplication;
import com.scu.xjhm.common.core.utils.DataUtils;
import com.scu.xjhm.message.core.domain.*;

@Transactional(value = "transactionManager_security")
@Named
public class MessageFacadeImpl implements MessageFacade {

	@Inject
	private MessageApplication  application;

	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	public InvokeResult getMessage(Long id) {
		return InvokeResult.success(MessageAssembler.toDTO(application.getMessage(id)));
	}
	
	public InvokeResult createMessage(MessageDTO messageDTO) {
		application.createMessage(MessageAssembler.toEntity(messageDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult updateMessage(MessageDTO messageDTO) {
		application.updateMessage(MessageAssembler.toEntity(messageDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult removeMessage(Long id) {
		application.removeMessage(application.getMessage(id));
		return InvokeResult.success();
	}
	
	public InvokeResult removeMessages(Long[] ids) {
		Set<Message> messages= new HashSet<Message>();
		for (Long id : ids) {
			messages.add(application.getMessage(id));
		}
		application.removeMessages(messages);
		return InvokeResult.success();
	}
	
	public List<MessageDTO> findAllMessage() {
		return MessageAssembler.toDTOs(application.findAllMessage());
	}
	/**
	 * 条件查询
	 * 20180708pm
	 */
	public Page<MessageDTO> pageQueryMessage(MessageDTO queryVo, int currentPage, int pageSize) {
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select t from Message t where 1=1 ");
	   	if (queryVo.getCreateTime() != null) {
	   		jpql.append(" and t.createTime between ? and ? ");
	   		conditionVals.add(queryVo.getCreateTime());
	   		conditionVals.add(queryVo.getCreateTimeEnd());
	   	}		
	   	if (!DataUtils.isNullOrEmpty(queryVo.getName())) {
	   		jpql.append(" and t.name like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
	   	}
        Page<Message> pages = getQueryChannelService()
		   .createJpqlQuery(jpql.toString())
		   .setParameters(conditionVals)
		   .setPage(currentPage, pageSize)
		   .pagedList();
		   
        return new Page<MessageDTO>(pages.getStart(), pages.getResultCount(),pageSize, MessageAssembler.toDTOs(pages.getData()));
	}
	
}
