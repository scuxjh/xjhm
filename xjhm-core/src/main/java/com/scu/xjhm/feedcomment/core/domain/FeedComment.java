package com.scu.xjhm.feedcomment.core.domain;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.openkoala.koala.commons.domain.KoalaIDEntity;



import com.scu.xjhm.common.domain.BaseAbstractEntity;

import java.io.Serializable;

/**
 * Auto Generated Entity
 * 
 * @author Koala 
 * 
 */
@Entity
@Table(name="feed_comment")
public class FeedComment extends BaseAbstractEntity {



  @Column(name="IC")
    private Long ic;
  

  @Column(name="FEED_ID")
    private Long feedId;
  

  @Column(name="FEED_CONTENT")
    private String feedContent;
  

  @Column(name="COMMUNITY_ID")
    private Long communityId;
  

  @Column(name="YARD_ID")
    private Long yardId;
  

  @Column(name="CATEGORY_ID")
    private Long categoryId;
  

  @Column(name="COMMENT_TIME")
    private Date commentTime;
  

  @Column(name="UNREAD")
    private String unread;
  

  @Column(name="BUILDING_ID")
    private Long buildingId;
  

  @Column(name="COMMENTER_NAME")
    private String commenterName;
  

  @Column(name="COMMENTER_TEL")
    private String commenterTel;
  


  
    
  public Long getIc() {
		return ic;
  }
  
  public void setIc(Long ic) {
		this.ic = ic;
  }
  
  
    
  public Long getFeedId() {
		return feedId;
  }
  
  public void setFeedId(Long feedId) {
		this.feedId = feedId;
  }
  
  
    
  public String getFeedContent() {
		return feedContent;
  }
  
  public void setFeedContent(String feedContent) {
		this.feedContent = feedContent;
  }
  
  
    
  public Long getCommunityId() {
		return communityId;
  }
  
  public void setCommunityId(Long communityId) {
		this.communityId = communityId;
  }
  
  
    
  public Long getYardId() {
		return yardId;
  }
  
  public void setYardId(Long yardId) {
		this.yardId = yardId;
  }
  
  
    
  public Long getCategoryId() {
		return categoryId;
  }
  
  public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
  }
  
  
    
  public Date getCommentTime() {
		return commentTime;
  }
  
  public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
  }
  
  
    
  public String getUnread() {
		return unread;
  }
  
  public void setUnread(String unread) {
		this.unread = unread;
  }
  
  
    
  public Long getBuildingId() {
		return buildingId;
  }
  
  public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
  }
  
  
    
  public String getCommenterName() {
		return commenterName;
  }
  
  public void setCommenterName(String commenterName) {
		this.commenterName = commenterName;
  }
  
  
    
  public String getCommenterTel() {
		return commenterTel;
  }
  
  public void setCommenterTel(String commenterTel) {
		this.commenterTel = commenterTel;
  }
  
  
    

  

   @Override
   public String[] businessKeys() {
     // TODO Auto-generated method stub
    return null;
   }

}