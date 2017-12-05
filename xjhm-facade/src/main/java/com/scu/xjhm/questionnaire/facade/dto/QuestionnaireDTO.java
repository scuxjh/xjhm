package com.scu.xjhm.questionnaire.facade.dto;

import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class QuestionnaireDTO implements Serializable {

	private Long id;

	private int version;

			
		private String position;
		
				
		private String startTime;
		
				
		private String createTime;
		
								
		private Integer questionNum;
		
				
		private String questionnaireTitle;
		
				
		private String updateTime;
		
				
		private Long categoryId;
		
		//新闻分类显示名称
		private String categoryName;
		
				
		private String questionContent;
		
								
		private Integer unread;
		
								
		private Integer display;
		
				
		private String endTime;
		
								
		private Integer pagevoew;
		
				
		private Long buildingId;
		
				
		private Long communityId;
		
			
	
	public void setPosition(String position) { 
		this.position = position;
	}

	public String getPosition() {
		return this.position;
	}
		
			
	
	public void setStartTime(String startTime) { 
		this.startTime = startTime;
	}

	public String getStartTime() {
		return this.startTime;
	}
		
			
	
	public void setCreateTime(String createTime) { 
		this.createTime = createTime;
	}

	public String getCreateTime() {
		return this.createTime;
	}
		
							
	
	public void setQuestionNum(Integer questionNum) { 
		this.questionNum = questionNum;
	}

	public Integer getQuestionNum() {
		return this.questionNum;
	}
		
			
	
	public void setQuestionnaireTitle(String questionnaireTitle) { 
		this.questionnaireTitle = questionnaireTitle;
	}

	public String getQuestionnaireTitle() {
		return this.questionnaireTitle;
	}
		
			
	
	public void setUpdateTime(String updateTime) { 
		this.updateTime = updateTime;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}
		
			
	
	public void setCategoryId(Long categoryId) { 
		this.categoryId = categoryId;
	}

	public Long getCategoryId() {
		return this.categoryId;
	}
		
			
	
	public void setQuestionContent(String questionContent) { 
		this.questionContent = questionContent;
	}

	public String getQuestionContent() {
		return this.questionContent;
	}
		
							
	
	public void setUnread(Integer unread) { 
		this.unread = unread;
	}

	public Integer getUnread() {
		return this.unread;
	}
		
							
	
	public void setDisplay(Integer display) { 
		this.display = display;
	}

	public Integer getDisplay() {
		return this.display;
	}
		
			
	
	public void setEndTime(String endTime) { 
		this.endTime = endTime;
	}

	public String getEndTime() {
		return this.endTime;
	}
		
							
	
	public void setPagevoew(Integer pagevoew) { 
		this.pagevoew = pagevoew;
	}

	public Integer getPagevoew() {
		return this.pagevoew;
	}
		
			
	
	public void setBuildingId(Long buildingId) { 
		this.buildingId = buildingId;
	}

	public Long getBuildingId() {
		return this.buildingId;
	}
		
			
	
	public void setCommunityId(Long communityId) { 
		this.communityId = communityId;
	}

	public Long getCommunityId() {
		return this.communityId;
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
		QuestionnaireDTO other = (QuestionnaireDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}