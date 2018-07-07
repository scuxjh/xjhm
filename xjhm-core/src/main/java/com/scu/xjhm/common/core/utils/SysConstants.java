package com.scu.xjhm.common.core.utils;

public class SysConstants {
	//教学组织类型：教学班级
	public static final int TEACH_CLASS_CATEGORY_CLASS = 0;
	//教学组织类型：教学团队
	public static final int TEACH_CLASS_CATEGORY_TEAM = 1;
	//真值，false的整型表示0
	public static final int FALSE_INT = 0;
	//真值，true的整型表示1
	public static final int TRUE_INT = 1;
	//“学生考勤签到”签到状态
	public static final int CLASS_ANSWER_STATUS_INIT = 0,CLASS_ANSWER_STATUS_ONTIME=1,CLASS_ANSWER_STATUS_HOLIDAY=2,CLASS_ANSWER_STATUS_LATE=3,CLASS_ANSWER_STATUS_ABSENCE=4;
	//“任务书”启用状态
	public static final int TASKBOOK_STATUS_FORBIDDEN = 0,TASKBOOK_STATUS_ACTIVATE = 1;
	//“课程资源”启用状态
	public static final int COURSE_STATUS_FORBIDDEN = 0,COURSE_STATUS_ACTIVATE = 1;
	//系统角色名称
	public static final String ROLE_NAME_ADMIN = "超级管理员",ROLE_NAME_SYSADMIN="系统管理员",ROLE_NAME_TEACHADMIN="教学平台管理员",ROLE_NAME_TEACHER="教师",ROLE_NAME_STUDENT="学生";
	//数据字典key值 .20170119nt
	public static final String DICT_NEWS_CATETORY = "news.CATEGORY";
	public static final String DICT_FEED_CATETORY = "feed.CATEGORY";
	public static final String DICT_QUESTIONNAIRE_CATETORY = "questionnaire.CATEGORY";
}
