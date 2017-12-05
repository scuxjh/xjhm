package com.scu.xjhm.application.impl;

import java.util.List;
import java.util.Set;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.scu.xjhm.application.QuestionnaireApplication;
import com.scu.xjhm.questionnaire.core.domain.QuestionContent;
import com.scu.xjhm.questionnaire.core.domain.Questionnaire;

@Named
@Transactional
public class QuestionnaireApplicationImpl implements QuestionnaireApplication {
	public static Questionnaire currentquestionnaire;
	
	

	public  Questionnaire getCurrentquestionnaire() {
		return currentquestionnaire;
	}


	public static void setCurrentquestionnaire(Questionnaire currentquestionnaire) {
		QuestionnaireApplicationImpl.currentquestionnaire = currentquestionnaire;
	}


	public Questionnaire getQuestionnaire(Long id) {
		return Questionnaire.get(Questionnaire.class, id);
	}
	
	
	public void creatQuestionnaire(Questionnaire questionnaire) {
		questionnaire.save();
		setCurrentquestionnaire(questionnaire);
	}
	public void creatQuestioncontent(QuestionContent questionContent){
		questionContent.save();
		//questionContent.setQuestionnaireId(currentquestionnaire);
		
	}
	
	public void updateQuestionnaire(Questionnaire questionnaire) {
		questionnaire.save();
	}
	
	public void removeQuestionnaire(Questionnaire questionnaire) {
		if(questionnaire != null){
			questionnaire.remove();
		}
	}
	
	public void removeQuestionnaires(Set<Questionnaire> questionnaires) {
		for (Questionnaire questionnaire : questionnaires) {
			questionnaire.remove();
		}
	}
	
	public List<Questionnaire> findAllQuestionnaire() {
		return Questionnaire.findAll(Questionnaire.class);
	}
	
}
