package com.scu.xjhm.feed.core.domain;

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
@Table(name="feed")
public class Feed extends BaseAbstractEntity {



  /**
	 * 
	 */
	private static final long serialVersionUID = -799599758762856738L;


@Column(name="FEED_TITLE")
    private String feedTitle;
  

  @Column(name="START_TIME")
    private Date startTime;
  

  @Column(name="END_TIME")
    private Date endTime;
  

  @Column(name="CONTENT")
    private String content;
  

  @Column(name="COUNT_NUM")
    private int countNum;
  

  @Column(name="POSITION")
    private int position;
  

  @Column(name="PAGE_VIEW")
    private int pageView;
  

  @Column(name="CATEGORY_ID")
    private int categoryId;
  

  @Column(name="DISPLAY")
    private int display;
  

  @Column(name="ADMIN_ID")
    private Long adminId;
  

  

  
    
  public String getFeedTitle() {
		return feedTitle;
  }
  
  public void setFeedTitle(String feedTitle) {
		this.feedTitle = feedTitle;
  }
  
  
    
  public Date getStartTime() {
		return startTime;
  }
  
  public void setStartTime(Date startTime) {
		this.startTime = startTime;
  }
  
  
    
  public Date getEndTime() {
		return endTime;
  }
  
  public void setEndTime(Date endTime) {
		this.endTime = endTime;
  }
  
  
    
  public String getContent() {
		return content;
  }
  
  public void setContent(String content) {
		this.content = content;
  }
  
  
    
  public int getCountNum() {
		return countNum;
  }
  
  public void setCountNum(int countNum) {
		this.countNum = countNum;
  }
  
  
    
  public int getPosition() {
		return position;
  }
  
  public void setPosition(int position) {
		this.position = position;
  }
  
  
    
  public int getPageView() {
		return pageView;
  }
  
  public void setPageView(int pageView) {
		this.pageView = pageView;
  }
  
  
    
  public int getCategoryId() {
		return categoryId;
  }
  
  public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
  }
  
  
    
  public int getDisplay() {
		return display;
  }
  
  public void setDisplay(int display) {
		this.display = display;
  }
  
  
    
  public Long getAdminId() {
		return adminId;
  }
  
  public void setAdminId(Long adminId) {
		this.adminId = adminId;
  }
  
  
    
  
  

   @Override
   public String[] businessKeys() {
     // TODO Auto-generated method stub
    return null;
   }

}