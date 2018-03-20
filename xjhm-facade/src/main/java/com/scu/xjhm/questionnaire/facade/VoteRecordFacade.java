package com.scu.xjhm.questionnaire.facade;

import java.util.List;

import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import com.scu.xjhm.questionnaire.facade.dto.VoteRecordDTO;

public interface VoteRecordFacade {

	public InvokeResult getVoteRecord(Long id);
	
	public InvokeResult getVoteRecordByqnid(Long id);
	
	public InvokeResult creatVoteRecord(VoteRecordDTO voteRecord);
	
	public InvokeResult updateVoteRecord(VoteRecordDTO voteRecord);
	
	public InvokeResult removeVoteRecord(Long id);
	
	public InvokeResult removeVoteRecords(Long[] ids);
	
	public List<VoteRecordDTO> findAllVoteRecord();
	
	public Page<VoteRecordDTO> pageQueryVoteRecord(VoteRecordDTO voteRecord, int currentPage, int pageSize);
	

}

