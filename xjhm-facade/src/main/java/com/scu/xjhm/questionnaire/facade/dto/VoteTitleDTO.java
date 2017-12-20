package com.scu.xjhm.questionnaire.facade.dto;

import java.util.Date;

import java.io.Serializable;

public class VoteTitleDTO implements Serializable {

	private Long id;

	private int version;

			
		private String createTime;
		
				
		private Long questionnaireId;
		
								
		private Integer questionNum;
		
				
		private String updateTime;
				
		private String questionTitle;
		
								
		private Integer questionType;
		
			
	
	public void setCreateTime(String createTime) { 
		this.createTime = createTime;
	}

	public String getCreateTime() {
		return this.createTime;
	}
		
	
			
	
	public void setQuestionnaireId(Long questionnaireId) { 
		this.questionnaireId = questionnaireId;
	}

	public Long getQuestionnaireId() {
		return this.questionnaireId;
	}
		
							
	
	public void setQuestionNum(Integer questionNum) { 
		this.questionNum = questionNum;
	}

	public Integer getQuestionNum() {
		return this.questionNum;
	}
		
			
	
	public void setUpdateTime(String updateTime) { 
		this.updateTime = updateTime;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}
		
		
			
	
	public void setQuestionTitle(String questionTitle) { 
		this.questionTitle = questionTitle;
	}

	public String getQuestionTitle() {
		return this.questionTitle;
	}
		
							
	
	public void setQuestionType(Integer questionType) { 
		this.questionType = questionType;
	}

	public Integer getQuestionType() {
		return this.questionType;
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
		VoteTitleDTO other = (VoteTitleDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}