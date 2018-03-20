package com.scu.xjhm.questionnaire.facade.impl;

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

import com.scu.xjhm.application.QuestionnaireApplication;
import com.scu.xjhm.common.core.utils.SysConstants;
import com.scu.xjhm.common.facade.DataDictionaryFacade;
import com.scu.xjhm.questionnaire.core.domain.*;
import com.scu.xjhm.questionnaire.facade.QuestionnaireFacade;
import com.scu.xjhm.questionnaire.facade.dto.QuestionnaireDTO;
import com.scu.xjhm.questionnaire.facade.impl.assembler.QuestionnaireAssembler;

@Named
public class QuestionnaireFacadeImpl implements QuestionnaireFacade {

	@Inject
	private QuestionnaireApplication  application;

	private QueryChannelService queryChannel;
	@Inject
	private DataDictionaryFacade dicFacade;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
    
	
	public InvokeResult getQuestionnaire(Long id) {
		return InvokeResult.success(QuestionnaireAssembler.toDTO(application.getQuestionnaire(id)));
	}
	
	public InvokeResult creatQuestionnaire(QuestionnaireDTO questionnaireDTO) {
		application.creatQuestionnaire(QuestionnaireAssembler.toEntity(questionnaireDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult updateQuestionnaire(QuestionnaireDTO questionnaireDTO) {
		application.updateQuestionnaire(QuestionnaireAssembler.toEntity(questionnaireDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult removeQuestionnaire(Long id) {
		application.removeQuestionnaire(application.getQuestionnaire(id));
		return InvokeResult.success();
	}
	
	public InvokeResult removeQuestionnaires(Long[] ids) {
		Set<Questionnaire> questionnaires= new HashSet<Questionnaire>();
		for (Long id : ids) {
			questionnaires.add(application.getQuestionnaire(id));
		}
		application.removeQuestionnaires(questionnaires);
		return InvokeResult.success();
	}
	
	public List<QuestionnaireDTO> findAllQuestionnaire() {
		return QuestionnaireAssembler.toDTOs(application.findAllQuestionnaire());
	}
	
	public Page<QuestionnaireDTO> pageQueryQuestionnaire(QuestionnaireDTO queryVo, int currentPage, int pageSize) {
		//获取并设置"feed.CATEGORY"字典项。20170119nt
		dicFacade.getDictItems(SysConstants.DICT_QUESTIONNAIRE_CATETORY);
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _questionnaire from Questionnaire _questionnaire   where 1=1 ");
	   	if (queryVo.getQuestionnaireTitle() != null && !"".equals(queryVo.getQuestionnaireTitle())) {
	   		jpql.append(" and _questionnaire.questionnaireTitle like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getQuestionnaireTitle()));
	   	}		
	   	if (queryVo.getQuestionNum() != null) {
	   		jpql.append(" and _questionnaire.questionNum=?");
	   		conditionVals.add(queryVo.getQuestionNum());
	   	}	
	   	if (queryVo.getQuestionContent() != null && !"".equals(queryVo.getQuestionContent())) {
	   		jpql.append(" and _questionnaire.questionContent like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getQuestionContent()));
	   	}		
	   	if (queryVo.getPosition() != null && !"".equals(queryVo.getPosition())) {
	   		jpql.append(" and _questionnaire.position like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getPosition()));
	   	}		
	   	if (queryVo.getUnread() != null) {
	   		jpql.append(" and _questionnaire.unread=?");
	   		conditionVals.add(queryVo.getUnread());
	   	}	
	   	if (queryVo.getPagevoew() != null) {
	   		jpql.append(" and _questionnaire.pagevoew=?");
	   		conditionVals.add(queryVo.getPagevoew());
	   	}	
	   	if (queryVo.getDisplay() != null) {
	   		jpql.append(" and _questionnaire.display=?");
	   		conditionVals.add(queryVo.getDisplay());
	   	}	
        Page<Questionnaire> pages = getQueryChannelService()
		   .createJpqlQuery(jpql.toString())
		   .setParameters(conditionVals)
		   .setPage(currentPage, pageSize)
		   .pagedList();
		   
        return new Page<QuestionnaireDTO>(pages.getStart(), pages.getResultCount(),pageSize, QuestionnaireAssembler.toDTOs(pages.getData()));
	}
	
	
}
