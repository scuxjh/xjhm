package com.scu.xjhm.questionnaire.facade.impl.assembler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.scu.xjhm.common.core.utils.DataBuffered;
import com.scu.xjhm.common.core.utils.DataUtils;
import com.scu.xjhm.common.core.utils.DateUtils;
import com.scu.xjhm.common.core.utils.SysConstants;
import com.scu.xjhm.questionnaire.core.domain.*;
import com.scu.xjhm.questionnaire.facade.dto.QuestioncontentDTO;
import com.scu.xjhm.questionnaire.facade.dto.QuestionnaireDTO;


public class QuestionnaireAssembler {
	public static int test;
	public static QuestionnaireDTO  toDTO(Questionnaire  questionnaire){
		if (questionnaire == null) {
			return null;
		}
		QuestionnaireDTO result  = new QuestionnaireDTO();
	    	result.setId (questionnaire.getId());
     	    	result.setVersion (questionnaire.getVersion());
     	    	result.setQuestionnaireTitle (questionnaire.getQuestionnaireTitle());
     	    	result.setQuestionNum (questionnaire.getQuestionNum());
     	    	result.setQuestionContent (questionnaire.getQuestionContent());
     	    	result.setStartTime (DateUtils.convertDateToStr(questionnaire.getStartTime(), DateUtils.DATE_TIME_PATTERN));
     	    	result.setEndTime (DateUtils.convertDateToStr(questionnaire.getEndTime(), DateUtils.DATE_TIME_PATTERN));
     	    	result.setCommunityId (questionnaire.getCommunityId());
     	    	result.setPosition (questionnaire.getPosition());
     	    	result.setUnread (questionnaire.getUnread());
     	    	result.setPagevoew (questionnaire.getPagevoew());
     	    	result.setCategoryId (questionnaire.getCategoryId());
     	    	String categoryName = String.valueOf(DataBuffered.dataDictionaryMap.get(SysConstants.DICT_QUESTIONNAIRE_CATETORY).get(String.valueOf(questionnaire.getCategoryId())));
     		    result.setCategoryName(categoryName);
     	    	result.setDisplay (questionnaire.getDisplay());
     	    	result.setBuildingId (questionnaire.getBuildingId());
     	    	result.setCreateTime (DateUtils.convertDateToStr(questionnaire.getCreateTime(), DateUtils.DATE_TIME_PATTERN));
     	    	result.setUpdateTime (DateUtils.convertDateToStr(questionnaire.getUpdateTime(), DateUtils.DATE_TIME_PATTERN));
     	    return result;
	 }
	
	public static List<QuestionnaireDTO>  toDTOs(Collection<Questionnaire>  questionnaires){
		if (questionnaires == null) {
			return null;
		}
		List<QuestionnaireDTO> results = new ArrayList<QuestionnaireDTO>();
		for (Questionnaire each : questionnaires) {
			results.add(toDTO(each));
		}
		return results;
	}
	
	 public static Questionnaire  toEntity(QuestionnaireDTO  questionnaireDTO){
	 	if (questionnaireDTO == null) {
			return null;
		}
	 	Questionnaire result = null;
	 	Long id = questionnaireDTO.getId();
	 	if(DataUtils.isNullOrEmpty(id)){
	 		//LOGGER.info("1111,in toEntity, isNullOrEmpty...");
	 		result = new Questionnaire();
	 	}else{
	 		result = Questionnaire.get(Questionnaire.class, id); 
	 	}
	 	//Questionnaire result  = new Questionnaire();
        //result.setId (questionnaireDTO.getId());
         //result.setVersion (questionnaireDTO.getVersion());
         result.setQuestionnaireTitle (questionnaireDTO.getQuestionnaireTitle());
        // result.setQuestionnaireTitle (test);
	 	// result.setQuestionNum (questionnaireDTO.getQuestionNum());
         result.setQuestionContent (questionnaireDTO.getQuestionContent());
         String startTime=questionnaireDTO.getStartTime();
        		 
         result.setStartTime (DateUtils.convertStrToDate(startTime, DateUtils.DATE_TIME_PATTERN));
        // String endTime=questionnaireDTO.getStartTime();
         //result.setEndTime (DateUtils.convertStrToDate(endTime, DateUtils.DATE_TIME_PATTERN));
         //result.setCommunityId (questionnaireDTO.getCommunityId());
       //result.setPosition (questionnaireDTO.getPosition());
         //result.setUnread (questionnaireDTO.getUnread());
         //result.setPagevoew (questionnaireDTO.getPagevoew());
         result.setCategoryId (questionnaireDTO.getCategoryId());
         result.setDisplay (questionnaireDTO.getDisplay());
         //result.setBuildingId (questionnaireDTO.getBuildingId());
         
         //result.setCreateTime (DateUtils.convertStrToDate(startTime, DateUtils.DATE_TIME_PATTERN));
         //result.setUpdateTime (DateUtils.convertStrToDate(startTime, DateUtils.DATE_TIME_PATTERN));
 	  	return result;
	 }
	
	public static List<Questionnaire> toEntities(Collection<QuestionnaireDTO> questionnaireDTOs) {
		if (questionnaireDTOs == null) {
			return null;
		}
		
		List<Questionnaire> results = new ArrayList<Questionnaire>();
		for (QuestionnaireDTO each : questionnaireDTOs) {
			results.add(toEntity(each));
		}
		return results;
	}
	 public static QuestionContent  toqcEntity(QuestioncontentDTO  questioncontentDTO){
		 	if (questioncontentDTO == null) {
				return null;
			}
		 	
		 	QuestionContent qcresult= new QuestionContent();
		 	qcresult.setQuestionTitle(questioncontentDTO.getQuestionTitle());
		 	qcresult.setQuestionType(questioncontentDTO.getQuestionType());
		 	//test=qcresult.getQuestionType();
	 	  	return qcresult;
		 }
}
