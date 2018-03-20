package com.scu.xjhm.questionnaire.facade.impl.assembler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.scu.xjhm.common.core.utils.DataUtils;
import com.scu.xjhm.common.core.utils.DateUtils;
import com.scu.xjhm.questionnaire.core.domain.*;
import com.scu.xjhm.questionnaire.facade.dto.VoteTitleDTO;

public class VoteTitleAssembler {
	
	public static VoteTitleDTO  toDTO(VoteTitle  voteTitle){
		if (voteTitle == null) {
			return null;
		}
    	VoteTitleDTO result  = new VoteTitleDTO();
	    	result.setId (voteTitle.getId());
     	    	result.setVersion (voteTitle.getVersion());
     	    	result.setCreateTime (DateUtils.convertDateToStr(voteTitle.getCreateTime(), DateUtils.DATE_TIME_PATTERN));
     	    	result.setUpdateTime (DateUtils.convertDateToStr(voteTitle.getUpdateTime(), DateUtils.DATE_TIME_PATTERN));
     	    	result.setQuestionnaireId (voteTitle.getQuestionnaireId());
     	    	result.setQuestionTitle (voteTitle.getQuestionTitle());
     	    	result.setQuestionNum (voteTitle.getQuestionNum());
     	    	result.setQuestionType (voteTitle.getQuestionType());
     	    return result;
	 }
	
	public static List<VoteTitleDTO>  toDTOs(List<VoteTitle>  voteTitles){
		if (voteTitles == null) {
			return null;
		}
		List<VoteTitleDTO> results = new ArrayList<VoteTitleDTO>();
		for (VoteTitle each : voteTitles) {
			results.add(toDTO(each));
		}
		return results;
	}
	
	 public static VoteTitle  toEntity(VoteTitleDTO  voteTitleDTO){
	 	if (voteTitleDTO == null) {
			return null;
		}
	 	VoteTitle result = null;
	 	Long id = voteTitleDTO.getId();
	 	if(DataUtils.isNullOrEmpty(id)){
	 		//LOGGER.info("1111,in toEntity, isNullOrEmpty...");
	 		result = new VoteTitle();
	 	}else{
	 		result = VoteTitle.get(VoteTitle.class, id); 
	 	}
        //result.setId (voteTitleDTO.getId());
        // result.setVersion (voteTitleDTO.getVersion());
        // result.setCreateTime (voteTitleDTO.getCreateTime());
         //result.setUpdateTime (voteTitleDTO.getUpdateTime());
         result.setQuestionTitle (voteTitleDTO.getQuestionTitle());
        result.setQuestionNum (voteTitleDTO.getQuestionNum());
         result.setQuestionType (voteTitleDTO.getQuestionType());
 	  	return result;
	 }
	
	public static List<VoteTitle> toEntities(Collection<VoteTitleDTO> voteTitleDTOs) {
		if (voteTitleDTOs == null) {
			return null;
		}
		
		List<VoteTitle> results = new ArrayList<VoteTitle>();
		for (VoteTitleDTO each : voteTitleDTOs) {
			results.add(toEntity(each));
		}
		return results;
	}
}
