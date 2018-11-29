package com.scu.xjhm.message.facade.dto;

import java.util.Date;

import java.io.Serializable;

public class MessageDTO implements Serializable {

	private static final long serialVersionUID = -2825499231069981678L;

	private Long id;

	private int version;

			
		private String createTime;
		
		private Date createTimeEnd;
				
		private String updateTime;
		
		private Date updateTimeEnd;
				
		private Long categoryId;
		
				
		private Long audioSize;
		
				
		private String unread;
		
				
		private String addr;
		
				
		private String audioFilepath;
		
				
		private String isDeal;
		
				
		private String content;
		
				
		private String phoneNum;
		
				
		private String name;
		
								
		private Integer isAudio;
		
				
		private Long buildingId;
		
			
	
	public void setCreateTime(String createTime) { 
		this.createTime = createTime;
	}

	public String getCreateTime() {
		return this.createTime;
	}
		
		public void setCreateTimeEnd(Date createTimeEnd) { 
		this.createTimeEnd = createTimeEnd;
	}

	public Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}
			
	
	public void setUpdateTime(String updateTime) { 
		this.updateTime = updateTime;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}
		
		public void setUpdateTimeEnd(Date updateTimeEnd) { 
		this.updateTimeEnd = updateTimeEnd;
	}

	public Date getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}
			
	
	public void setCategoryId(Long categoryId) { 
		this.categoryId = categoryId;
	}

	public Long getCategoryId() {
		return this.categoryId;
	}
		
			
	
	public void setAudioSize(Long audioSize) { 
		this.audioSize = audioSize;
	}

	public Long getAudioSize() {
		return this.audioSize;
	}
		
			
	
	public void setUnread(String unread) { 
		this.unread = unread;
	}

	public String getUnread() {
		return this.unread;
	}
		
			
	
	public void setAddr(String addr) { 
		this.addr = addr;
	}

	public String getAddr() {
		return this.addr;
	}
		
			
	
	public void setAudioFilepath(String audioFilepath) { 
		this.audioFilepath = audioFilepath;
	}

	public String getAudioFilepath() {
		return this.audioFilepath;
	}
		
			
	
	public void setIsDeal(String isDeal) { 
		this.isDeal = isDeal;
	}

	public String getIsDeal() {
		return this.isDeal;
	}
		
			
	
	public void setContent(String content) { 
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}
		
			
	
	public void setPhoneNum(String phoneNum) { 
		this.phoneNum = phoneNum;
	}

	public String getPhoneNum() {
		return this.phoneNum;
	}
		
			
	
	public void setName(String name) { 
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
		
							
	
	public void setIsAudio(Integer isAudio) { 
		this.isAudio = isAudio;
	}

	public Integer getIsAudio() {
		return this.isAudio;
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
		MessageDTO other = (MessageDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}