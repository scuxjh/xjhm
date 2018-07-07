package com.scu.xjhm.questionnaire.core.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.scu.xjhm.common.domain.BaseAbstractEntity;


/**
 * Auto Generated Entity
 * 
 * @author Koala 
 * 
 */
@Entity
@Table(name="vote_title")
public class VoteTitle extends BaseAbstractEntity {



  @Column(name="QUESTIONNAIRE_ID")
    private Long questionnaireId;
  

  @Column(name="QUESTION_TITLE")
    private String questionTitle;
  

  @Column(name="QUESTION_NUM")
    private int questionNum;
  


  @Column(name="QUESTION_TYPE")
    private int questionType;
  

  
    
  public Long getQuestionnaireId() {
		return questionnaireId;
  }
  
  public void setQuestionnaireId(Long questionnaireId) {
		this.questionnaireId = questionnaireId;
  }
  
  
    
  public String getQuestionTitle() {
		return questionTitle;
  }
  
  public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
  }
  
  
    
  public int getQuestionNum() {
		return questionNum;
  }
  
  public void setQuestionNum(int questionNum) {
		this.questionNum = questionNum;
  }
  
    
  public int getQuestionType() {
		return questionType;
  }
  
  public void setQuestionType(int questionType) {
		this.questionType = questionType;
  }
  

   @Override
   public String[] businessKeys() {
     // TODO Auto-generated method stub
    return null;
   }

}