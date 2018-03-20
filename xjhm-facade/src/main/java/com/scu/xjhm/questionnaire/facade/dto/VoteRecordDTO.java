package com.scu.xjhm.questionnaire.facade.dto;

import java.util.Date;

import java.io.Serializable;

public class VoteRecordDTO implements Serializable {

	private Long id;

	private int version;

			
		private String problemChoice;
		
				
		private Long questionnaireId;
		
				
		private Long questionId;
		
				
		private Long problemId;
		
				
		private Long buildingId;
		
				
		private String voteTime;
			
	
	public void setProblemChoice(String problemChoice) { 
		this.problemChoice = problemChoice;
	}

	public String getProblemChoice() {
		return this.problemChoice;
	}
		
			
	
	public void setQuestionnaireId(Long questionnaireId) { 
		this.questionnaireId = questionnaireId;
	}

	public Long getQuestionnaireId() {
		return this.questionnaireId;
	}
		
			
	
	public void setQuestionId(Long questionId) { 
		this.questionId = questionId;
	}

	public Long getQuestionId() {
		return this.questionId;
	}
		
			
	
	public void setProblemId(Long problemId) { 
		this.problemId = problemId;
	}

	public Long getProblemId() {
		return this.problemId;
	}
		
			
	
	public void setBuildingId(Long buildingId) { 
		this.buildingId = buildingId;
	}

	public Long getBuildingId() {
		return this.buildingId;
	}
		
			
	
	public void setVoteTime(String voteTime) { 
		this.voteTime = voteTime;
	}

	public String getVoteTime() {
		return this.voteTime;
	}
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VoteRecordDTO other = (VoteRecordDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}