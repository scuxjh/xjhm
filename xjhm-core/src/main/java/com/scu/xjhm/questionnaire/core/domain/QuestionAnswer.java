package com.scu.xjhm.questionnaire.core.domain;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name="question_answer")
public class QuestionAnswer extends KoalaLegacyEntity {

 private static final long serialVersionUID = 1L;
 
/**
*
* 主键
*
**/
      
       @Id
       @Column(name="answer_id")
	   //@GeneratedValue(strategy = GenerationType.AUTO)
       private Long answerId;
   
   

    @Column(name="option_id")
  private QuestionOption optionId;
  

    @Column(name="user_id")
  private Long userId;
  

      
         
       public void setAnswerId(Long answerId) {
		  this.answerId = answerId;
       }
   
   

  
    @ManyToOne(cascade={CascadeType.ALL})
    public QuestionOption getOptionId() {
		return optionId;
  }
    public void setOptionId(QuestionOption optionId) {
		this.optionId = optionId;
  }
  
  
    
    public Long getUserId() {
		return userId;
  } 
    public void setUserId(Long userId) {
		this.userId = userId;
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