package com.scu.xjhm.questionnaire.facade;

import java.util.List;

import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import com.scu.xjhm.questionnaire.facade.dto.QuestioncontentDTO;
import com.scu.xjhm.questionnaire.facade.dto.QuestionnaireDTO;

/*import java.util.List;

import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import com.scu.xjhm.questionnaire.facade.dto.QuestionnaireDTO;
*/
//import com.scu.xjhm.questionnaire.facade.dto.*;

public interface QuestionnaireFacade {
	
   
    
	public InvokeResult getQuestionnaire(Long id);
	
	public InvokeResult creatQuestionnaire(QuestionnaireDTO questionnaire);
	
	public  void creatQuestioncontent(QuestioncontentDTO questioncontentDTO);
	
	public InvokeResult updateQuestionnaire(QuestionnaireDTO questionnaire);
	
	public InvokeResult removeQuestionnaire(Long id);
	
	public InvokeResult removeQuestionnaires(Long[] ids);
	
	public List<QuestionnaireDTO> findAllQuestionnaire();
	
	public Page<QuestionnaireDTO> pageQueryQuestionnaire(QuestionnaireDTO questionnaire, int currentPage, int pageSize);
	

}

