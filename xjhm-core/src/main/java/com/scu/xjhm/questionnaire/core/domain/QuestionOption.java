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
@Table(name="question_option")
public class QuestionOption extends KoalaLegacyEntity {

 private static final long serialVersionUID = 1L;
 
/**
*
* 主键
*
**/
      
       @Id
       @Column(name="option_id")
	   //@GeneratedValue(strategy = GenerationType.AUTO)
       private Long optionId;
   
   

    @Column(name="option_content")
  private String optionContent;
  

    @Column(name="question_id")
  private QuestionContent questionId;
  

      
         
       public void setOptionId(Long optionId) {
		  this.optionId = optionId;
       }
   
   

  
    
    public String getOptionContent() {
		return optionContent;
  }
    public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
  }
  
  
    @ManyToOne(cascade={CascadeType.ALL})
    public QuestionContent getQuestionId() {
		return questionId;
  }
    public void setQuestionId(QuestionContent questionId) {
		this.questionId = questionId;
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