package com.scu.xjhm.application;


import java.util.List;
import java.util.Set;

import  com.scu.xjhm.questionnaire.core.domain.VoteRecord;

public interface VoteRecordApplication {

	public VoteRecord getVoteRecord(Long id);
	
	public List<VoteRecord> getVoteRecordByqnid(Long id);
	
	public void creatVoteRecord(VoteRecord voteRecord);
	
	public void updateVoteRecord(VoteRecord voteRecord);
	
	public void removeVoteRecord(VoteRecord voteRecord);
	
	public void removeVoteRecords(Set<VoteRecord> voteRecords);
	
	public List<VoteRecord> findAllVoteRecord();
	
}

