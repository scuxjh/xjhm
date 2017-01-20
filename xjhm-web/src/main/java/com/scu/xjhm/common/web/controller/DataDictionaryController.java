package com.scu.xjhm.common.web.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

import com.scu.xjhm.common.facade.DataDictionaryFacade;

import org.openkoala.koala.commons.InvokeResult;

@Controller
@RequestMapping("/DataDictionary")
public class DataDictionaryController {

    @Inject
	private DataDictionaryFacade dicFacade;//20160321pm
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DataDictionaryController.class);

    /**
     * @title  根据字典key查询数据字典数据项。
     * @author ywang
     * @param dictId 
     * @return
     * @version  
     * 20160821
     *     1.new。
     */
    @ResponseBody
	@RequestMapping("/getDictItems")
    public InvokeResult getDictItems(String dictId){
    	LOGGER.info("1111,in getDictItems,dictId:"+dictId);
    	return InvokeResult.success(dicFacade.getDictItems(dictId));
    }
    /**
     * 20160326pm
     * 根据任务书编号，获取任务书对应的JSP资源文件(从Properties文件中读取映射关系。)
     * @param taskNo
     * @return
     */
    @ResponseBody
	@RequestMapping("/getTaskFormTaskNoDict/{taskNo}")
    public InvokeResult getTaskFormTaskNoDict(@PathVariable String taskNo){
    	String dictId = "task.TASK_NO_TASK_FORM";
    	return InvokeResult.success(dicFacade.translateFromFile(dictId));
    }
    /**
     * 20160321pm
     * 返回“任务类型”字典数据。
     * @return
     */
    @ResponseBody
	@RequestMapping("/getTaskTypeDict")
	public InvokeResult getTaskTypeDict() {
    	LOGGER.info("1111,in TaskbookController.getTaskTypeDict().");
		String dictId = "task.TASK_TYPE";
		return InvokeResult.success(dicFacade.translateFromFile(dictId));
		//return kuBizTaskbookFacade.creatKuBizTaskbook(kuBizTaskbookDTO);
	}
}