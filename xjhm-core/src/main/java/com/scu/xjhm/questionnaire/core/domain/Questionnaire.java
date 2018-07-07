package com.scu.xjhm.questionnaire.core.domain;

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
 * @author LiHan 
 * 
 */
@Entity
@Table(name="questionnaire")
public class Questionnaire extends BaseAbstractEntity {



  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


@Column(name="QUESTIONNAIRE_TITLE")
    private String questionnaireTitle;
  

  @Column(name="QUESTION_NUM")
    private int questionNum;
  

  @Column(name="QUESTION_CONTENT")
    private String questionContent;
  

  @Column(name="START_TIME")
    private Date startTime;
  

  @Column(name="END_TIME")
    private Date endTime;
  

  @Column(name="COMMUNITY_ID")
    private Long communityId;
  

  @Column(name="POSITION")
    private String position;
  

  @Column(name="UNREAD")
    private int unread;
  

  @Column(name="PAGEVOEW")
    private int pagevoew;
  

  @Column(name="CATEGORY_ID")
    private Long categoryId;
  

  @Column(name="DISPLAY")
    private int display;
  

  @Column(name="BUILDING_ID")
    private Long buildingId;
 
    
  public String getQuestionnaireTitle() {
		return questionnaireTitle;
  }
  
  public void setQuestionnaireTitle(String questionnaireTitle) {
		this.questionnaireTitle = questionnaireTitle;
  }
  
  
    
  public int getQuestionNum() {
		return questionNum;
  }
  
  public void setQuestionNum(int questionNum) {
		this.questionNum = questionNum;
  }
  
  
    
  public String getQuestionContent() {
		return questionContent;
  }
  
  public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
  }
  
  
    
  public Date getStartTime() {
		return startTime;
  }
  
  public void setStartTime(Date date) {
		this.startTime = date;
  }
  
  
    
  public Date getEndTime() {
		return endTime;
  }
  
  public void setEndTime(Date endTime) {
		this.endTime = endTime;
  }
  
  
    
  public Long getCommunityId() {
		return communityId;
  }
  
  public void setCommunityId(Long communityId) {
		this.communityId = communityId;
  }
  
  
    
  public String getPosition() {
		return position;
  }
  
  public void setPosition(String position) {
		this.position = position;
  }
  
  
    
  public int getUnread() {
		return unread;
  }
  
  public void setUnread(int unread) {
		this.unread = unread;
  }
  
  
    
  public int getPagevoew() {
		return pagevoew;
  }
  
  public void setPagevoew(int pagevoew) {
		this.pagevoew = pagevoew;
  }
  
  
    
  public Long getCategoryId() {
		return categoryId;
  }
  
  public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
  }
  
  
    
  public int getDisplay() {
		return display;
  }
  
  public void setDisplay(int display) {
		this.display = display;
  }
  
  
    
  public Long getBuildingId() {
		return buildingId;
  }
  
  public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
  }
  
  
    
 
  

   @Override
   public String[] businessKeys() {
     // TODO Auto-generated method stub
    return null;
   }

}