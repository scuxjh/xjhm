package com.scu.xjhm.application.impl;

import java.util.List;
import java.util.Set;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;
import com.scu.xjhm.application.MessageApplication;
import com.scu.xjhm.message.core.domain.Message;

@Named
@Transactional
public class MessageApplicationImpl implements MessageApplication {

	public Message getMessage(Long id) {
		return Message.get(Message.class, id);
	}
	
	public void createMessage(Message message) {
		message.save();
	}
	
	public void updateMessage(Message message) {
		message.save();
	}
	
	public void removeMessage(Message message) {
		if(message != null){
			message.remove();
		}
	}
	
	public void removeMessages(Set<Message> messages) {
		for (Message message : messages) {
			message.remove();
		}
	}
	
	public List<Message> findAllMessage() {
		return Message.findAll(Message.class);
	}
	
}
