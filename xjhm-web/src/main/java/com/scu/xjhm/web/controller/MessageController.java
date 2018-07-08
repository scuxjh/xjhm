package com.scu.xjhm.web.controller;

import javax.inject.Inject;
import org.springframework.web.bind.WebDataBinder;
import java.text.SimpleDateFormat;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.dayatang.utils.Page;
import com.scu.xjhm.facade.dto.MessageDTO;
import com.scu.xjhm.facade.MessageFacade;
import org.openkoala.koala.commons.InvokeResult;

@Controller
@RequestMapping("/Message")
public class MessageController {
		
	@Inject
	private MessageFacade messageFacade;
	
	@ResponseBody
	@RequestMapping("/add")
	public InvokeResult add(MessageDTO messageDTO) {
		return messageFacade.createMessage(messageDTO);
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public InvokeResult update(MessageDTO messageDTO) {
		return messageFacade.updateMessage(messageDTO);
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(MessageDTO messageDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<MessageDTO> all = messageFacade.pageQueryMessage(messageDTO, page, pagesize);
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
        return messageFacade.removeMessages(idArrs);
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public InvokeResult get(@PathVariable Long id) {
		return messageFacade.getMessage(id);
	}
	
		
//    @InitBinder    
//    public void initBinder(WebDataBinder binder) {  
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
//        dateFormat.setLenient(false);    
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
//        //CustomDateEditor 可以换成自己定义的编辑器。  
//        //注册一个Date 类型的绑定器 。
//        binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);
//    }
	
}
