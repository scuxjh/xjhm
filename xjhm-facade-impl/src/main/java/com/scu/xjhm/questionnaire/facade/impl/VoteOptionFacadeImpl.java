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
import com.scu.xjhm.application.VoteOptionApplication;
import com.scu.xjhm.questionnaire.core.domain.*;
import com.scu.xjhm.questionnaire.facade.VoteOptionFacade;
import com.scu.xjhm.questionnaire.facade.dto.VoteOptionDTO;
import com.scu.xjhm.questionnaire.facade.impl.assembler.VoteOptionAssembler;

@Named
public class VoteOptionFacadeImpl implements VoteOptionFacade {

	@Inject
	private VoteOptionApplication  application;

	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	public InvokeResult getVoteOption(Long id) {
		return InvokeResult.success(VoteOptionAssembler.toDTO(application.getVoteOption(id)));
	}
	public InvokeResult getVoteOptionByvtId(Long vtid) {
		return InvokeResult.success(VoteOptionAssembler.toDTOs(application.getVoteOptionByvtId(vtid)));
	}
	public void creatVoteOption(VoteOptionDTO voteOptionDTO) {
		application.creatVoteOption(VoteOptionAssembler.toEntity(voteOptionDTO));
	}
	
	public InvokeResult updateVoteOption(VoteOptionDTO voteOptionDTO) {
		application.updateVoteOption(VoteOptionAssembler.toEntity(voteOptionDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult removeVoteOption(Long id) {
		application.removeVoteOption(application.getVoteOption(id));
		return InvokeResult.success();
	}
	
	public InvokeResult removeVoteOptions(Long[] ids) {
		Set<VoteOption> voteOptions= new HashSet<VoteOption>();
		for (Long id : ids) {
			voteOptions.add(application.getVoteOption(id));
		}
		application.removeVoteOptions(voteOptions);
		return InvokeResult.success();
	}
	
	public List<VoteOptionDTO> findAllVoteOption() {
		return VoteOptionAssembler.toDTOs(application.findAllVoteOption());
	}
	
	public Page<VoteOptionDTO> pageQueryVoteOption(VoteOptionDTO queryVo, int currentPage, int pageSize) {
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _voteOption from VoteOption _voteOption   where 1=1 ");
	   	if (queryVo.getCreateTime() != null) {
	   		jpql.append(" and _voteOption.createTime between ? and ? ");
	   		conditionVals.add(queryVo.getCreateTime());
	   		//conditionVals.add(queryVo.getCreateTimeEnd());
	   	}	
	   	if (queryVo.getUpdateTime() != null) {
	   		jpql.append(" and _voteOption.updateTime between ? and ? ");
	   		conditionVals.add(queryVo.getUpdateTime());
	   		//conditionVals.add(queryVo.getUpdateTimeEnd());
	   	}	
	   	if (queryVo.getQuestionOption() != null && !"".equals(queryVo.getQuestionOption())) {
	   		jpql.append(" and _voteOption.questionOption like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getQuestionOption()));
	   	}		
	   	if (queryVo.getOptionNum()!= null && !"".equals(queryVo.getOptionNum())) {
	   		jpql.append(" and _voteOption.optionNum like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getOptionNum()));
	   	}		
        Page<VoteOption> pages = getQueryChannelService()
		   .createJpqlQuery(jpql.toString())
		   .setParameters(conditionVals)
		   .setPage(currentPage, pageSize)
		   .pagedList();
		   
        return new Page<VoteOptionDTO>(pages.getStart(), pages.getResultCount(),pageSize, VoteOptionAssembler.toDTOs(pages.getData()));
	}
	
	
}
