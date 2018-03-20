package com.scu.xjhm.questionnaire.facade.impl.assembler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.scu.xjhm.common.core.utils.DateUtils;
import com.scu.xjhm.questionnaire.core.domain.*;
import com.scu.xjhm.questionnaire.facade.dto.VoteRecordDTO;

public class VoteRecordAssembler {
	
	public static VoteRecordDTO  toDTO(VoteRecord  voteRecord){
		if (voteRecord == null) {
			return null;
		}
    	VoteRecordDTO result  = new VoteRecordDTO();
	    	result.setId (voteRecord.getId());
     	    	result.setVersion (voteRecord.getVersion());
     	    	result.setProblemId (voteRecord.getProblemId());
     	    	result.setProblemChoice (voteRecord.getProblemChoice());
     	    	result.setVoteTime (DateUtils.convertDateToStr(voteRecord.getVoteTime(), DateUtils.DATE_TIME_PATTERN));
     	    	result.setBuildingId (voteRecord.getBuildingId());
     	    	result.setQuestionId (voteRecord.getQuestionId());
     	    	result.setQuestionnaireId (voteRecord.getQuestionnaireId());
     	    return result;
	 }
	
	public static List<VoteRecordDTO>  toDTOs(Collection<VoteRecord>  voteRecords){
		if (voteRecords == null) {
			return null;
		}
		List<VoteRecordDTO> results = new ArrayList<VoteRecordDTO>();
		for (VoteRecord each : voteRecords) {
			results.add(toDTO(each));
		}
		return results;
	}
	
	 public static VoteRecord  toEntity(VoteRecordDTO  voteRecordDTO){
	 	if (voteRecordDTO == null) {
			return null;
		}
	 	VoteRecord result  = new VoteRecord();
        result.setId (voteRecordDTO.getId());
         result.setVersion (voteRecordDTO.getVersion());
         result.setProblemId (voteRecordDTO.getProblemId());
         result.setProblemChoice (voteRecordDTO.getProblemChoice());
         //result.setVoteTime (voteRecordDTO.getVoteTime());
         result.setBuildingId (voteRecordDTO.getBuildingId());
         result.setQuestionId (voteRecordDTO.getQuestionId());
         result.setQuestionnaireId (voteRecordDTO.getQuestionnaireId());
 	  	return result;
	 }
	
	public static List<VoteRecord> toEntities(Collection<VoteRecordDTO> voteRecordDTOs) {
		if (voteRecordDTOs == null) {
			return null;
		}
		
		List<VoteRecord> results = new ArrayList<VoteRecord>();
		for (VoteRecordDTO each : voteRecordDTOs) {
			results.add(toEntity(each));
		}
		return results;
	}
}
