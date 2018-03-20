package com.scu.xjhm.questionnaire.facade.impl.assembler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.scu.xjhm.common.core.utils.DataUtils;
import com.scu.xjhm.common.core.utils.DateUtils;
import com.scu.xjhm.questionnaire.core.domain.*;
import com.scu.xjhm.questionnaire.facade.dto.VoteOptionDTO;

public class VoteOptionAssembler {
	
	public static VoteOptionDTO  toDTO(VoteOption  voteOption){
		if (voteOption == null) {
			return null;
		}
    	VoteOptionDTO result  = new VoteOptionDTO();
	    	result.setId (voteOption.getId());
     	    	result.setVersion (voteOption.getVersion());
     	    	result.setCreateTime (DateUtils.convertDateToStr(voteOption.getCreateTime(), DateUtils.DATE_TIME_PATTERN));
     	    	result.setUpdateTime (DateUtils.convertDateToStr(voteOption.getUpdateTime(), DateUtils.DATE_TIME_PATTERN));
     	    	result.setQuestionId (voteOption.getQuestionId());
     	    	result.setQuestionOption (voteOption.getQuestionOption());
     	    	result.setOptionNum (voteOption.getOptionNum());
     	    return result;
	 }
	
	public static List<VoteOptionDTO>  toDTOs(List<VoteOption>  voteOptions){
		if (voteOptions == null) {
			return null;
		}
		List<VoteOptionDTO> results = new ArrayList<VoteOptionDTO>();
		for (VoteOption each : voteOptions) {
			results.add(toDTO(each));
		}
		return results;
	}
	
	 public static VoteOption  toEntity(VoteOptionDTO  voteOptionDTO){
	 	if (voteOptionDTO == null) {
			return null;
		}
	 	VoteOption result = null;
	 	Long id = voteOptionDTO.getId();
	 	if(DataUtils.isNullOrEmpty(id)){
	 		//LOGGER.info("1111,in toEntity, isNullOrEmpty...");
	 		result = new VoteOption();
	 	}else{
	 		result = VoteOption.get(VoteOption.class, id); 
	 	}
        //result.setId (voteOptionDTO.getId());
         //result.setVersion (voteOptionDTO.getVersion());
         //result.setCreateTime (voteOptionDTO.getCreateTime());
         //result.setUpdateTime (voteOptionDTO.getUpdateTime());
         //result.setQuestionId (voteOptionDTO.getQuestionId());
         result.setQuestionOption (voteOptionDTO.getQuestionOption());
         result.setOptionNum (voteOptionDTO.getOptionNum());
 	  	return result;
	 }
	
	public static List<VoteOption> toEntities(Collection<VoteOptionDTO> voteOptionDTOs) {
		if (voteOptionDTOs == null) {
			return null;
		}
		
		List<VoteOption> results = new ArrayList<VoteOption>();
		for (VoteOptionDTO each : voteOptionDTOs) {
			results.add(toEntity(each));
		}
		return results;
	}
}
