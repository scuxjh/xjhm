package com.scu.xjhm.facade.impl.assembler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.scu.xjhm.common.core.utils.DataUtils;
import com.scu.xjhm.facade.dto.*;
import com.scu.xjhm.message.core.domain.*;

public class MessageAssembler {
	
	public static MessageDTO  toDTO(Message  message){
		if (message == null) {
			return null;
		}
    	MessageDTO result  = new MessageDTO();
	    	result.setId (message.getId());
     	    	result.setVersion (message.getVersion());
     	    	result.setCreateTime (message.getCreateTime());
     	    	result.setUpdateTime (message.getUpdateTime());
     	    	result.setIsAudio (message.getIsAudio());
     	    	result.setAudioFilepath (message.getAudioFilepath());
     	    	result.setName (message.getName());
     	    	result.setPhoneNum (message.getPhoneNum());
     	    	result.setAddr (message.getAddr());
     	    	result.setUnread (message.getUnread());
     	    	result.setCategoryId (message.getCategoryId());
     	    	result.setAudioSize (message.getAudioSize());
     	    	result.setIsDeal (message.getIsDeal());
     	    	result.setContent (message.getContent());
     	    	result.setBuildingId (message.getBuildingId());
     	    return result;
	 }
	
	public static List<MessageDTO>  toDTOs(Collection<Message>  messages){
		if (messages == null) {
			return null;
		}
		List<MessageDTO> results = new ArrayList<MessageDTO>();
		for (Message each : messages) {
			results.add(toDTO(each));
		}
		return results;
	}
	
	/**
	 * DTO 转换为  Entity。
	 * @param messageDTO
	 * @return
	 * @version
	 * 		1.new. 20180708pm
	 */
	 public static Message  toEntity(MessageDTO  messageDTO){
	 	if (messageDTO == null) {
			return null;
		}
	 	Message result  = null;
	 	Long id = messageDTO.getId();
	 	if(DataUtils.isNullOrEmpty(id)){
	 		result = new Message();
	 	}else{
	 		result = Message.get(Message.class, id);
	 	}
	 	//Message result  = new Message();
        result.setAudioFilepath (messageDTO.getAudioFilepath());
        result.setName (messageDTO.getName());
        result.setPhoneNum (messageDTO.getPhoneNum());
 	  	return result;
	 }
	
	public static List<Message> toEntities(Collection<MessageDTO> messageDTOs) {
		if (messageDTOs == null) {
			return null;
		}
		
		List<Message> results = new ArrayList<Message>();
		for (MessageDTO each : messageDTOs) {
			results.add(toEntity(each));
		}
		return results;
	}
}
