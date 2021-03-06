package com.scu.xjhm.application;


import java.util.List;
import java.util.Set;

import  com.scu.xjhm.questionnaire.core.domain.Questionnaire;

public interface QuestionnaireApplication {

	public Questionnaire getQuestionnaire(Long id);
	
	public  long getCurrentquestionnaireid();
	
	public void creatQuestionnaire(Questionnaire questionnaire);
	
	public void updateQuestionnaire(Questionnaire questionnaire);
	
	public void removeQuestionnaire(Questionnaire questionnaire);
	
	public void removeQuestionnaires(Set<Questionnaire> questionnaires);
	
	public List<Questionnaire> findAllQuestionnaire();
	
}

