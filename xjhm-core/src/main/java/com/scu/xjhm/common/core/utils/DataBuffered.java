package com.scu.xjhm.common.core.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @title  数据缓存类，用于保存系统缓存数据。
 * @author ywang
 * @param 
 * @return
 * @version  
 * 20170119nt
 *     1.new。
 */
public class DataBuffered {
	//数据字典数据项缓存值。20170119nt
	public static HashMap<String, LinkedHashMap<String, Object>> dataDictionaryMap = new HashMap<String, LinkedHashMap<String, Object>>();
	
	static{
		/*Set<String> roleNames = new HashSet<String>();
		roleNames.add(KuConstants.ROLE_NAME_SYSADMIN);
		roleNames.add(KuConstants.ROLE_NAME_TEACHADMIN);
		roleNames.add(KuConstants.ROLE_NAME_TEACHER);
		roleNames.add(KuConstants.ROLE_NAME_STUDENT);
		roleNameMap.put(KuConstants.ROLE_NAME_ADMIN, roleNames);
		roleNames = new HashSet<String>();
		roleNames.add(KuConstants.ROLE_NAME_TEACHADMIN);
		roleNameMap.put(KuConstants.ROLE_NAME_SYSADMIN, roleNames);
		roleNames = new HashSet<String>();
		roleNames.add(KuConstants.ROLE_NAME_TEACHER);
		roleNames.add(KuConstants.ROLE_NAME_STUDENT);
		roleNameMap.put(KuConstants.ROLE_NAME_TEACHADMIN, roleNames);
		*/
	}
}