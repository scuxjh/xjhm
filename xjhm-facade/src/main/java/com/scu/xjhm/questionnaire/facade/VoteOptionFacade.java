package com.scu.xjhm.questionnaire.facade;

import java.util.List;

import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import com.scu.xjhm.questionnaire.facade.dto.VoteOptionDTO;

public interface VoteOptionFacade {

	public InvokeResult getVoteOption(Long id);
	
	public InvokeResult getVoteOptionByvtId(Long vtid);
	
	public void creatVoteOption(VoteOptionDTO voteOption);
	
	public InvokeResult updateVoteOption(VoteOptionDTO voteOption);
	
	public InvokeResult removeVoteOption(Long id);
	
	public InvokeResult removeVoteOptions(Long[] ids);
	
	public List<VoteOptionDTO> findAllVoteOption();
	
	public Page<VoteOptionDTO> pageQueryVoteOption(VoteOptionDTO voteOption, int currentPage, int pageSize);
	

}

