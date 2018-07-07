package com.scu.xjhm.application.impl;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.scu.xjhm.application.QuestionnaireApplication;
import com.scu.xjhm.application.VoteTitleApplication;
import com.scu.xjhm.questionnaire.core.domain.VoteTitle;

@Named
@Transactional
public class VoteTitleApplicationImpl implements VoteTitleApplication {
	private static long currenttitleid;
	@Inject
	QuestionnaireApplication questionnaireApplication;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VoteTitleApplicationImpl.class);

	public VoteTitle getVoteTitle(Long id) {
		return VoteTitle.get(VoteTitle.class, id);
	}
	public List<VoteTitle> getVoteTitleByqnId(Long qnid) {
		//return VoteTitle.get(VoteTitle.class, qnid);
		List<VoteTitle> resultList =  VoteTitle.findByProperty(VoteTitle.class,"questionnaireId",qnid);
		LOGGER.info("resultList.size():"+resultList.size());
		return resultList;
		//return VoteTitle.findByProperty(VoteTitle.class,"questionnaireId",qnid);
	}
	
	public void creatVoteTitle(VoteTitle voteTitle) {
		voteTitle.setQuestionnaireId(questionnaireApplication.getCurrentquestionnaireid());
		voteTitle.save();
		setCurrenttitleid(voteTitle.getId());
	}
	public  long getCurrenttitleid() {
		return currenttitleid;
	}

	public static void setCurrenttitleid(long currenttitleid) {
		VoteTitleApplicationImpl.currenttitleid = currenttitleid;
	}
	
	public void updateVoteTitle(VoteTitle voteTitle) {
		voteTitle .save();
	}
	
	public void removeVoteTitle(VoteTitle voteTitle) {
		if(voteTitle != null){
			voteTitle.remove();
		}
	}
	
	public void removeVoteTitles(Set<VoteTitle> voteTitles) {
		for (VoteTitle voteTitle : voteTitles) {
			voteTitle.remove();
		}
	}
	
	public List<VoteTitle> findAllVoteTitle() {
		return VoteTitle.findAll(VoteTitle.class);
	}
	
}
