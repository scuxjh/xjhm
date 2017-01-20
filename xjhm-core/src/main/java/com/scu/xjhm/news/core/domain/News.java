package com.scu.xjhm.news.core.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.scu.xjhm.common.domain.BaseAbstractEntity;

/**
 * Auto Generated Entity
 * 
 * @author ywang 
 * 
 */
@Entity
@Table(name="news")
public class News extends BaseAbstractEntity {

	private static final long serialVersionUID = -799599758762856738L;

@Column(name="NEWS_TITLE")
    private String newsTitle;
  

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

  
  public String getNewsTitle() {
		return newsTitle;
  }
  
  public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
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