
package com.scu.xjhm.questionnaire.facade.dto;


import java.io.Serializable;


/**
 * @author Administrator
 *
 */
public class QuestioncontentDTO implements Serializable {

	private Long id;

	private int version;
	private String createTime;
	private String updateTime;

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
	    
	    private Long questionnaireid;
	    
	   // private int order;
	    
	
	

    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public int getVersion() {
			return version;
		}

		public void setVersion(int version) {
			this.version = version;
		}

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public String getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(String updateTime) {
			this.updateTime = updateTime;
		}

		public Long getQuestionnaireid() {
			return questionnaireid;
		}

		public void setQuestionnaireid(Long questionnaireid) {
			this.questionnaireid = questionnaireid;
		}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/*public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}*/
}
	