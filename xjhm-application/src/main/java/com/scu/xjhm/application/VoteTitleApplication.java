package com.scu.xjhm.application;


import java.util.List;
import java.util.Set;

import  com.scu.xjhm.questionnaire.core.domain.VoteTitle;

public interface VoteTitleApplication {

	public VoteTitle getVoteTitle(Long id);
	
	public List<VoteTitle> getVoteTitleByqnId(Long qnid);
	
	public long getCurrenttitleid();
	
	public void creatVoteTitle(VoteTitle voteTitle);
	
	public void updateVoteTitle(VoteTitle voteTitle);
	
	public void removeVoteTitle(VoteTitle voteTitle);
	
	public void removeVoteTitles(Set<VoteTitle> voteTitles);
	
	public List<VoteTitle> findAllVoteTitle();
	
}

