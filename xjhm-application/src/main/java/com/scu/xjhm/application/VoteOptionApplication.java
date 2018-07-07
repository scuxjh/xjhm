package com.scu.xjhm.application;


import java.util.List;
import java.util.Set;

import  com.scu.xjhm.questionnaire.core.domain.VoteOption;

public interface VoteOptionApplication {

	public VoteOption getVoteOption(Long id);
	
	public List<VoteOption> getVoteOptionByvtId(Long vtid);
	
	public void creatVoteOption(VoteOption voteOption);
	
	public void updateVoteOption(VoteOption voteOption);
	
	public void removeVoteOption(VoteOption voteOption);
	
	public void removeVoteOptions(Set<VoteOption> voteOptions);
	
	public List<VoteOption> findAllVoteOption();
	
}

