package com.scu.xjhm.application;


import java.util.List;
import java.util.Set;
import  com.scu.xjhm.message.core.domain.Message;

public interface MessageApplication {

	public Message getMessage(Long id);
	
	public void createMessage(Message message);
	
	public void updateMessage(Message message);
	
	public void removeMessage(Message message);
	
	public void removeMessages(Set<Message> messages);
	
	public List<Message> findAllMessage();
	
}

