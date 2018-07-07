package com.scu.xjhm.questionnaire.facade;

import java.util.List;

import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import com.scu.xjhm.questionnaire.facade.dto.VoteTitleDTO;

public interface VoteTitleFacade {

	public InvokeResult getVoteTitle(Long id);
	
	public InvokeResult getVoteTitleByqnId(Long qnid);
	
	public InvokeResult creatVoteTitle(VoteTitleDTO voteTitle);
	
	public InvokeResult updateVoteTitle(VoteTitleDTO voteTitle);
	
	public InvokeResult removeVoteTitle(Long id);
	
	public InvokeResult removeVoteTitles(Long[] ids);
	
	public List<VoteTitleDTO> findAllVoteTitle();
	
	public Page<VoteTitleDTO> pageQueryVoteTitle(VoteTitleDTO voteTitle, int currentPage, int pageSize);
	

}

