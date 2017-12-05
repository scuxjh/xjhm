
package com.scu.xjhm.questionnaire.facade.dto;


import java.io.Serializable;


/**
 * @author Administrator
 *
 */
public class QuestioncontentDTO implements Serializable {

	    private Long questionId;
	
	    public Long getQuestionId() {
			return questionId;
		}

		public void setQuestionId(Long questionId) {
			this.questionId = questionId;
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

		private String questionTitle;
		
	    private int questionType;
	    
	    
	
	

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((questionId == null) ? 0 : questionId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestioncontentDTO other = (QuestioncontentDTO) obj;
		if (questionId == null) {
			if (other.questionId != null)
				return false;
		} else if (!questionId.equals(other.questionId))
			return false;
		return true;
	}
}
	