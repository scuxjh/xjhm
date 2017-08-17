package com.scu.xjhm.feed.facade.dto;

import java.sql.Timestamp;
import java.io.Serializable;

public class FeedDTO implements Serializable {

	private Long id;

	private int version;

							
		private Integer position;
		
				
		private String content;
		
				
		private String startTime;
		
				
		private String createTime;
		
				
		private Long adminId;
		
				
		private String feedTitle;
		
								
		private Integer pageView;
		
				
		private String updateTime;
		
								
		private Integer categoryId;
		
		
		private String categoryName;
		
	    
		private Integer display;
		
				
		private String endTime;
		
								
		private Integer countNum;
		
							
	
	public void setPosition(Integer position) { 
		this.position = position;
	}

	public Integer getPosition() {
		return this.position;
	}
		
			
	
	public void setContent(String content) { 
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}
		
			
	
	public void setStartTime(String startTime) { 
		this.startTime =startTime;
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
		
			
	
	public void setAdminId(Long adminId) { 
		this.adminId = adminId;
	}

	public Long getAdminId() {
		return this.adminId;
	}
		
			
	
	public void setFeedTitle(String feedTitle) { 
		this.feedTitle = feedTitle;
	}

	public String getFeedTitle() {
		return this.feedTitle;
	}
		
							
	
	public void setPageView(Integer pageView) { 
		this.pageView = pageView;
	}

	public Integer getPageView() {
		return this.pageView;
	}
		
			
	
	public void setUpdateTime(String updateTime) { 
		this.updateTime = updateTime;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}
		
							
	
	public void setCategoryId(Integer categoryId) { 
		this.categoryId = categoryId;
	}

	public Integer getCategoryId() {
		return this.categoryId;
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
		
							
	
	public void setCountNum(Integer countNum) { 
		this.countNum = countNum;
	}

	public Integer getCountNum() {
		return this.countNum;
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
		FeedDTO other = (FeedDTO) obj;
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