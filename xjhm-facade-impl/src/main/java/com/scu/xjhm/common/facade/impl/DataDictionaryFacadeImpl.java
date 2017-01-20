package com.scu.xjhm.common.facade.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scu.xjhm.common.core.utils.DataBuffered;
import com.scu.xjhm.common.facade.DataDictionaryFacade;
import com.mysql.jdbc.StringUtils;

@Named
public class DataDictionaryFacadeImpl implements DataDictionaryFacade {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataDictionaryFacadeImpl.class);
	
	/**
	 * @title  根据数据字典ID查询该类字典的数据项。  
     * @author ywang
	 * @param dictId 
     * @return
     * @version  
     * 20160821am
     *     1.new.
	 */
	public LinkedHashMap<String, Object> getDictItems(String dictId){
		//20160821am
		LinkedHashMap<String, Object> dictItems = null;
		dictItems = DataBuffered.dataDictionaryMap.get(dictId);
		if(dictItems != null)  {
			LOGGER.info("1111,in getDictItems,dictItems != null,dictItems.size():"+dictItems.size());
			return dictItems;
		}
		dictItems = translateFromFile(dictId);
		DataBuffered.dataDictionaryMap.put(dictId, dictItems);
		LOGGER.info("2222,in getDictItems,dictItems.size():"+dictItems.size());
		return dictItems;
	}
	/**
	 * 20160321pm
	 * 从字典文件中，读取字典值
	 */
	@SuppressWarnings("finally")
	@Override
	public LinkedHashMap<String, Object> translateFromFile(String dictId){
		Properties pps = new OrderedProperties();//20160326pm
		LinkedHashMap<String, Object> dictItems = new LinkedHashMap<String, Object>();
		try{
			String URIPath = DataDictionaryFacadeImpl.class.getClassLoader().getResource("").toURI().getPath();
			String propName = "enums.properties";
			String propPath = URIPath + "/META-INF/props/"+propName;
			InputStream in = new BufferedInputStream (new FileInputStream(propPath));//20160326pm
			BufferedReader bf = new BufferedReader(new InputStreamReader(in, "utf-8"));//能够读取Properties文件中文。
			//pps.load(in);
			pps.load(bf);//20160326pm
			in.close();//20160326pm 关闭流
			String value = pps.getProperty(dictId);
			//System.out.println("2222,In DataDictionaryFacadeImpl,the value of "+dicId+" is:"+value);
			//如：task.TASK_TYPE=0:行政类,1:财务类,2:人资类
			if(StringUtils.isNullOrEmpty(value)) return dictItems;
			String[] valueArray = value.split(",");
			for(String keyValue: valueArray){
				String[] theKeyValue = keyValue.split(":");
				dictItems.put(theKeyValue[0], theKeyValue[1]);
			}
		}catch (URISyntaxException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			return dictItems;
		}
	}
	
	public static void main(String[] args){
		Properties pps = new Properties();
		try{
			InputStream in = new BufferedInputStream (new FileInputStream("src/main/resources/META-INF/props/enums.properties"));
			pps.load(in);
			String value = pps.getProperty("task.TASK_TYPE");
			System.out.println("In DataDictionaryFacadeImpl,the value of task_type is:"+value);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
}