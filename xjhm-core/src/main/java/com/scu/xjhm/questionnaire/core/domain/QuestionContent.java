package com.scu.xjhm.questionnaire.core.domain;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.openkoala.koala.commons.domain.KoalaLegacyEntity;

import java.io.Serializable;

/**
 * Auto Generated Entity
 * 
 * @author Koala 
 * 
 */
@Entity
@Table(name="question_content")
public class QuestionContent extends KoalaLegacyEntity {

 private static final long serialVersionUID = 1L;
 
/**
*
* 主键
*
**/
      @GeneratedValue
       @Id
       @Column(name="question_id")
	   //@GeneratedValue(strategy = GenerationType.AUTO)
       private Long id;
   
   

	public void setId(Long id) {
	this.id = id;
}

	@Column(name="question_title")
  private String questionTitle;
  

    @Column(name="question_type")
  private int questionType;
  

    @Column(name="questionnaire_id")
  private Questionnaire questionnaireId;
  

   
   @ManyToOne(cascade={CascadeType.ALL})
       public Questionnaire getQuestionnaireId() {
   		return questionnaireId;
     }
       public void setQuestionnaireId(Questionnaire questionnaireId) {
   		this.questionnaireId = questionnaireId;
     }
     

  
    
    public String getQuestionTitle() {
		return questionTitle;
  }
    public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
  }
  
  
    
    public int getQuestionType() {
		return questionType;
  }
    public void setQuestionType(int questionType) {
		this.questionType = questionType;
  }
  
  
    
   
	
	public Serializable getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean existed() {
		// TODO Auto-generated method stub
		return false;
	}



	public boolean notExisted() {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean existed(String propertyName, Object propertyValue) {
		// TODO Auto-generated method stub
		return false;
	}

   @Override
    public String[] businessKeys() {
     // TODO Auto-generated method stub
     return null;
    }
	
}