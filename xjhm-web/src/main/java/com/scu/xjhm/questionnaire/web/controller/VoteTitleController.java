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
import com.scu.xjhm.questionnaire.facade.VoteTitleFacade;
import com.scu.xjhm.questionnaire.facade.dto.VoteTitleDTO;

import org.openkoala.koala.commons.InvokeResult;

@Controller
@RequestMapping("/VoteTitle")
public class VoteTitleController {
		
	@Inject
	private VoteTitleFacade voteTitleFacade;
	
	@ResponseBody
	@RequestMapping("/add")
	public InvokeResult add(VoteTitleDTO voteTitleDTO) {
		return voteTitleFacade.creatVoteTitle(voteTitleDTO);
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public InvokeResult update(VoteTitleDTO voteTitleDTO) {
		return voteTitleFacade.updateVoteTitle(voteTitleDTO);
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(VoteTitleDTO voteTitleDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<VoteTitleDTO> all = voteTitleFacade.pageQueryVoteTitle(voteTitleDTO, page, pagesize);
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
        return voteTitleFacade.removeVoteTitles(idArrs);
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public InvokeResult get(@PathVariable Long id) {
		return voteTitleFacade.getVoteTitle(id);
	}
	@ResponseBody
	@RequestMapping("/getbyqnid/{id}")
	public InvokeResult getbyqnid(@PathVariable Long id) {
		return voteTitleFacade.getVoteTitleByqnId(id);
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
