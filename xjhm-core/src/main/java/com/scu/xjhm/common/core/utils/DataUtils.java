package com.scu.xjhm.common.core.utils;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 20150825pm
 * @author ywang
 *
 */
public class DataUtils {
	private DataUtils() {
	}
	//计数器，用于getTimeUID()方法去重复处理。取值范围：0-99.
	private static Byte counter = 0;
	
	private static DataUtils dataUtils = null;
	
	public static DataUtils getInstance() {
		if(dataUtils == null){
			dataUtils = new DataUtils();
		}
		return dataUtils;
	}
	/**
	 * @title  
     * @author ywang
     * @param 
     * @return
     * @version  
     * 20160813am
     *     1.new.
	 */
	public static int getCounter(){
		if(counter == 99) counter = 0;
		return counter++;
	}
	/**
	 * @title  
     * @author ywang
     * @param stringValue 
     * @return
     * @version  
     * 20160814pm
     *     1.new.
	 */
	public static boolean isNullOrEmpty(String stringValue){
		return (stringValue == null || stringValue.length()<1 || "null".equalsIgnoreCase(stringValue));
	}
	public static boolean isNullorEmpty(Set<?> set){
		return (set == null || set.size() < 1);
	}
	public static boolean isNullorEmpty(List<?> set){
		return (set == null || set.size() < 1);
	}
	/**
	 * 20160725pm
	 * @param intValue
	 * @return
	 */
	public static boolean isNullOrEmpty(Integer intValue){
		return (intValue == null || intValue.intValue() < 1);
	}
	/**
	 * 20160725pm
	 * @param longValue
	 * @return
	 */
	public static boolean isNullOrEmpty(Long longValue){
		return (longValue == null || longValue.longValue() < 1);
	}
	/**
	 * @title 返回唯一ID数值（时间形式）
     * @author ywang
     * @param 
     * @return
     * @version
     * 20160813am
     *     1.修改，完善。timeUID类型为：time后加两位自增数值，以防止同一时间持久化操作的ID重复问题。
     * 20150825pm
     *     1.new
	 
	 */
	public static Long getTimeUID(){
		return DateUtils.getTimeInMillis()*100 + getCounter();
	}
	/**
	 * 20150825pm
	 * @return
	 */
	public static Long getRandomLong(){
		double random = Math.random();
		double weight = Math.pow(10, 8);
		Long randomLong = Math.round(random*weight);
		
		System.out.println("random:"+random*weight);
		System.out.println("random.randomLong:"+randomLong);
		return randomLong;
	}
	public static void main(String[] args){
		//DataUtils.getRandom();
	}
}