package com.scu.xjhm.feed.facade.dto;

import java.sql.Timestamp;

import java.util.Date;

import java.io.Serializable;

public class FeedCommentDTO implements Serializable {

	private Long id;

	private int version;

			
		private Long yardId;
		
				
		private String createTime;
		
		private String createTimeEnd;
				
		private Long feedId;
		
				
		private Long ic;
		
				
		private String commentTime;
		
				
		private String updateTime;
		
		private String updateTimeEnd;
				
		private Long categoryId;
		
				
		private String commenterTel;
		
				
		private String commenterName;
		
				
		private String unread;
		
				
		private String feedContent;
		
				
		private Long communityId;
		
				
		private Long buildingId;
		
			
	
	public void setYardId(Long yardId) { 
		this.yardId = yardId;
	}

	public Long getYardId() {
		return this.yardId;
	}
		
			
	
	public void setCreateTime(String createTime) { 
		this.createTime = createTime;
	}

	public String getCreateTime() {
		return this.createTime;
	}
		
		public void setCreateTimeEnd(String createTimeEnd) { 
		this.createTimeEnd = createTimeEnd;
	}

	public String getCreateTimeEnd() {
		return this.createTimeEnd;
	}
			
	
	public void setFeedId(Long feedId) { 
		this.feedId = feedId;
	}

	public Long getFeedId() {
		return this.feedId;
	}
		
			
	
	public void setIc(Long ic) { 
		this.ic = ic;
	}

	public Long getIc() {
		return this.ic;
	}
		
			
	
	public void setCommentTime(String commentTime) { 
		this.commentTime = commentTime;
	}

	public String getCommentTime() {
		return this.commentTime;
	}
		
			
	
	public void setUpdateTime(String updateTime) { 
		this.updateTime = updateTime;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}
		
		public void setUpdateTimeEnd(String updateTimeEnd) { 
		this.updateTimeEnd = updateTimeEnd;
	}

	public String getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}
			
	
	public void setCategoryId(Long categoryId) { 
		this.categoryId = categoryId;
	}

	public Long getCategoryId() {
		return this.categoryId;
	}
		
			
	
	public void setCommenterTel(String commenterTel) { 
		this.commenterTel = commenterTel;
	}

	public String getCommenterTel() {
		return this.commenterTel;
	}
		
			
	
	public void setCommenterName(String commenterName) { 
		this.commenterName = commenterName;
	}

	public String getCommenterName() {
		return this.commenterName;
	}
		
			
	
	public void setUnread(String unread) { 
		this.unread = unread;
	}

	public String getUnread() {
		return this.unread;
	}
		
			
	
	public void setFeedContent(String feedContent) { 
		this.feedContent = feedContent;
	}

	public String getFeedContent() {
		return this.feedContent;
	}
		
			
	
	public void setCommunityId(Long communityId) { 
		this.communityId = communityId;
	}

	public Long getCommunityId() {
		return this.communityId;
	}
		
			
	
	public void setBuildingId(Long buildingId) { 
		this.buildingId = buildingId;
	}

	public Long getBuildingId() {
		return this.buildingId;
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
		FeedCommentDTO other = (FeedCommentDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}