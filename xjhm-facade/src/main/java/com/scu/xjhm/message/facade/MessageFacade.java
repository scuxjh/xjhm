package com.scu.xjhm.message.facade;

import java.util.List;

import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import com.scu.xjhm.message.facade.dto.MessageDTO;

public interface MessageFacade {

	public InvokeResult getMessage(Long id);
	
	public InvokeResult createMessage(MessageDTO message);
	
	public InvokeResult updateMessage(MessageDTO message);
	
	public InvokeResult removeMessage(Long id);
	
	public InvokeResult removeMessages(Long[] ids);
	
	public List<MessageDTO> findAllMessage();
	
	public Page<MessageDTO> pageQueryMessage(MessageDTO message, int currentPage, int pageSize);
	

}

