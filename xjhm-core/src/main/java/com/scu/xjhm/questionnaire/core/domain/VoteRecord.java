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
 * @author Koala 
 * 
 */
@Entity
@Table(name="vote_record")
public class VoteRecord extends BaseAbstractEntity {



  @Column(name="PROBLEM_ID")
    private Long problemId;
  

  @Column(name="PROBLEM_CHOICE")
    private String problemChoice;
  

  @Column(name="VOTE_TIME")
    private Date voteTime;
  

  @Column(name="BUILDING_ID")
    private Long buildingId;
  

  

  @Column(name="QUESTION_ID")
    private Long questionId;
  

  @Column(name="QUESTIONNAIRE_ID")
    private Long questionnaireId;
  

  
    
  public Long getProblemId() {
		return problemId;
  }
  
  public void setProblemId(Long problemId) {
		this.problemId = problemId;
  }
  
  
    
  public String getProblemChoice() {
		return problemChoice;
  }
  
  public void setProblemChoice(String problemChoice) {
		this.problemChoice = problemChoice;
  }
  
  
    
  public Date getVoteTime() {
		return voteTime;
  }
  
  public void setVoteTime(Date date) {
		this.voteTime = date;
  }
  
  
    
  public Long getBuildingId() {
		return buildingId;
  }
  
  public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
  }
  
    
  public Long getQuestionId() {
		return questionId;
  }
  
  public void setQuestionId(Long questionId) {
		this.questionId = questionId;
  }
  
  
    
  public Long getQuestionnaireId() {
		return questionnaireId;
  }
  
  public void setQuestionnaireId(Long questionnaireId) {
		this.questionnaireId = questionnaireId;
  }
  

   @Override
   public String[] businessKeys() {
     // TODO Auto-generated method stub
    return null;
   }

}