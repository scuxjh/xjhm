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
@Table(name="vote_option")
public class VoteOption extends BaseAbstractEntity {



  @Column(name="QUESTION_ID")
    private Long questionId;
  

  @Column(name="QUESTION_OPTION")
    private String questionOption;
  

  @Column(name="OPTION_NUM")
    private int optionNum;
  
   
  public Long getQuestionId() {
		return questionId;
  }
  
  public void setQuestionId(Long questionId) {
		this.questionId = questionId;
  }
  
  
    
  public String getQuestionOption() {
		return questionOption;
  }
  
  public void setQuestionOption(String questionOption) {
		this.questionOption = questionOption;
  }
  
  
    
  public int getOptionNum() {
		return optionNum;
  }
  
  public void setOptionNum(int optionNum) {
		this.optionNum = optionNum;
  }
  
 

   @Override
   public String[] businessKeys() {
     // TODO Auto-generated method stub
    return null;
   }

}