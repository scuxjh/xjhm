package com.scu.xjhm.questionnaire.web.controller;

import javax.inject.Inject;

import org.springframework.web.bind.WebDataBinder;

import java.text.SimpleDateFormat;             
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.dayatang.utils.Page;


import org.openkoala.koala.commons.InvokeResult;

import com.scu.xjhm.questionnaire.core.domain.QuestionContent;
import com.scu.xjhm.questionnaire.facade.QuestionnaireFacade;
import com.scu.xjhm.questionnaire.facade.dto.QuestioncontentDTO;
import com.scu.xjhm.questionnaire.facade.dto.QuestionnaireDTO;
//import com.scu.xjhm.questionnaire.facade.QuestionnaireFacade;

//import com.scu.xjhm.questionnaire.facade.impl.QuestionnaireFacadeImpl;


@Controller
@RequestMapping("/Questionnaire")
public class QuestionnaireController {
		
	@Inject
	private QuestionnaireFacade questionnaireFacade;
	
	@ResponseBody
	@RequestMapping("/add")
	public InvokeResult add(QuestionnaireDTO questionnaireDTO) {
		 
		//xx= questionnaireFacadeimpl.getCurrentQuestionnaire(questionnaireDTO);
		return questionnaireFacade.creatQuestionnaire(questionnaireDTO);
		
	}
	@ResponseBody
	@RequestMapping("/addcontent")
	public void addcontent(QuestioncontentDTO questioncontentDTO) {
		//questioncontent.setQuestionnaireId(application.getCurrentquestionnaire());
		//questioncontent.save();
		 questionnaireFacade.creatQuestioncontent(questioncontentDTO);
		
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public InvokeResult update(QuestionnaireDTO questionnaireDTO) {
		return questionnaireFacade.updateQuestionnaire(questionnaireDTO);
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(QuestionnaireDTO questionnaireDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<QuestionnaireDTO> all = questionnaireFacade.pageQueryQuestionnaire(questionnaireDTO, page, pagesize);
		return all;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public InvokeResult remove(@RequestParam String ids) {
		String[] value = ids.split(",");
        Long[] idArrs = new Long[value.length];
        for (int i = 0; i < value.length; i ++) {
        	        					idArrs[i] = Long.parseLong(value[i]);
						        }
        return questionnaireFacade.removeQuestionnaires(idArrs);
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public InvokeResult get(@PathVariable Long id) {
		return questionnaireFacade.getQuestionnaire(id);
	}
	
		
    @InitBinder    
    public void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
        dateFormat.setLenient(false);    
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
        //CustomDateEditor 可以换成自己定义的编辑器。  
        //注册一个Date 类型的绑定器 。
        binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);
    }
	
}
