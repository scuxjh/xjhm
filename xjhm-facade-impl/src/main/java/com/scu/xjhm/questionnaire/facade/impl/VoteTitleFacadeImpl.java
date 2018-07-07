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
import com.scu.xjhm.application.VoteTitleApplication;
import com.scu.xjhm.questionnaire.core.domain.*;
import com.scu.xjhm.questionnaire.facade.VoteTitleFacade;
import com.scu.xjhm.questionnaire.facade.dto.VoteTitleDTO;
import com.scu.xjhm.questionnaire.facade.impl.assembler.VoteTitleAssembler;

@Named
public class VoteTitleFacadeImpl implements VoteTitleFacade {

	@Inject
	private VoteTitleApplication  application;
    
	private QuestionnaireApplication questionnaireApplication;
	
	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	public InvokeResult getVoteTitle(Long id) {
		return InvokeResult.success(VoteTitleAssembler.toDTO(application.getVoteTitle(id)));
	}
	public InvokeResult getVoteTitleByqnId(Long qnid) {
		return InvokeResult.success(VoteTitleAssembler.toDTOs(application.getVoteTitleByqnId(qnid)));
	}
	
	public InvokeResult creatVoteTitle(VoteTitleDTO voteTitleDTO) {
		//voteTitleDTO.setQuestionnaireId(questionnaireApplication.getCurrentquestionnaireid());
		application.creatVoteTitle(VoteTitleAssembler.toEntity(voteTitleDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult updateVoteTitle(VoteTitleDTO voteTitleDTO) {
		application.updateVoteTitle(VoteTitleAssembler.toEntity(voteTitleDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult removeVoteTitle(Long id) {
		application.removeVoteTitle(application.getVoteTitle(id));
		return InvokeResult.success();
	}
	
	public InvokeResult removeVoteTitles(Long[] ids) {
		Set<VoteTitle> voteTitles= new HashSet<VoteTitle>();
		for (Long id : ids) {
			voteTitles.add(application.getVoteTitle(id));
		}
		application.removeVoteTitles(voteTitles);
		return InvokeResult.success();
	}
	
	public List<VoteTitleDTO> findAllVoteTitle() {
		return VoteTitleAssembler.toDTOs(application.findAllVoteTitle());
	}
	
	public Page<VoteTitleDTO> pageQueryVoteTitle(VoteTitleDTO queryVo, int currentPage, int pageSize) {
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _voteTitle from VoteTitle _voteTitle   where 1=1 ");
	   	if (queryVo.getCreateTime() != null) {
	   		jpql.append(" and _voteTitle.createTime between ? and ? ");
	   		conditionVals.add(queryVo.getCreateTime());
	   		//conditionVals.add(queryVo.getCreateTimeEnd());
	   	}	
	   	if (queryVo.getUpdateTime() != null) {
	   		jpql.append(" and _voteTitle.updateTime between ? and ? ");
	   		conditionVals.add(queryVo.getUpdateTime());
	   		//conditionVals.add(queryVo.getUpdateTimeEnd());
	   	}	
	   	if (queryVo.getQuestionTitle() != null && !"".equals(queryVo.getQuestionTitle())) {
	   		jpql.append(" and _voteTitle.questionTitle like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getQuestionTitle()));
	   	}		
	   	if (queryVo.getQuestionNum() != null) {
	   		jpql.append(" and _voteTitle.questionNum=?");
	   		conditionVals.add(queryVo.getQuestionNum());
	   	}	
	   	if (queryVo.getQuestionType() != null) {
	   		jpql.append(" and _voteTitle.questionType=?");
	   		conditionVals.add(queryVo.getQuestionType());
	   	}	
        Page<VoteTitle> pages = getQueryChannelService()
		   .createJpqlQuery(jpql.toString())
		   .setParameters(conditionVals)
		   .setPage(currentPage, pageSize)
		   .pagedList();
		   
        return new Page<VoteTitleDTO>(pages.getStart(), pages.getResultCount(),pageSize, VoteTitleAssembler.toDTOs(pages.getData()));
	}
	
	
}
