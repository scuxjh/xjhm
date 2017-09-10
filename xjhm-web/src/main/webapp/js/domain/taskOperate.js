//
//    场景任务处理，公共JS代码部分
//    20170118pm
//
"use strict";
/*-------------------------------------------
	Dynamically load plugin scripts
---------------------------------------------*/
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//
//      MAIN DOCUMENT READY SCRIPT
//
//      In this script main logic of theme
//
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////

$.ajaxSetup({//from main.js 
	contentType : "application/x-www-form-urlencoded;charset=utf-8", 
	error : function(XMLHttpRequest, textStatus) { 
		if(XMLHttpRequest.status == 499){
			window.location.href = contextPath+"/j_spring_security_logout";
		}
	} 
});
//var taskTypeContents = new Array();//20160321pm
/**
 * @title  “任务表单”的初始化。
 * @author ywang
 * @param 
 * @return
 * @version
 * 20170118pm
 *     1.new.
 */
var initForm = function($form){
	//var curTime = getCurTime();
	initDatetimepicker($form);//20160913pm
	initSelect($form);
	initRadio($form);//20160822nt
	initCheckbox($form);
};
/**
 * 20160913pm
 */
var initDatetimepicker = function($form){
	$form.find('.form_datetime').datetimepicker({
        language: 'zh-CN',
        //format: "yyyy-mm-dd",
        //format: "yyyy-mm-dd hh:ii:ss",
        format: "yyyy-mm-dd hh:ii:ss",//20170119pm
        autoclose: true,
        todayBtn: true,
        //minView: 2,
        startView: 2,//'day'
        minView: 0,//'hour'
        pickerPosition: 'bottom-left'
	}).datetimepicker('setDate', new Date());//加载日期选择器
};
/**
 * @title  初始化form表单中的radio控件，绑定监听事件等。
 * @author ywang
 * @param 
 * @return
 * @version 
 * 20160822nt
 *     1.new.
 */
var initCheckbox = function($form){
	$form.find("input[type='checkbox']").change(function(){
		var $this = $(this);
		var id = $this.attr("id");
		var value = $this.next().next("label").html();
		//alert("change,id:"+id);
		//$this.after("<input type='hidden' class='customFormData' name='"+id+"' value=false >");
		var checked = $this.attr("checked");
		//alert("change,checked:"+checked);
		if(checked){
			//alert("this is checked.");
			$this.next().val(value);
		}else{
			$this.next().val(false);
		}
	});
};
/**
 * @title  初始化form表单中的radio控件，绑定监听事件等。
 * @author ywang
 * @param 
 * @return
 * @version 
 * 20160822nt
 *     1.new.
 */
var initRadio = function($form){
	//20160820nt
	$form.find("input[type='radio']").click(function(){
		var $this = $(this);
		var id = $this.attr("id");
		var name = $this.attr("name");
		var curValue = $this.val();
		$form.find("input[type='hidden'][name='"+name+"']").val(curValue);
	});
};
/**
 * 初始化表单中的select类型数据。  
 * @author ywang
 * @param 
 * @return
 * @version  
 * 20160821am
 *     1.new.
 */
var initSelect = function($form){
	var selectDomArray = $form.find(".btn-group.select");
	//alert("selectDomArray.length:"+selectDomArray.length);
	$.each(selectDomArray, function(index, element){
		initSingleSelect($(element));
	});
};//end of function initSelect.
/**
 * @title  called by initSelect。
 * @author ywang
 * @param 
 * @return
 * @version 
 * 20170118am
 *     1.new.
 */
var initSingleSelect = function($singleSelectDom){
	var selectKey = $singleSelectDom.attr("selectKey");
	var url = contextPath + "/DataDictionary/getDictItems.koala?dictId="+selectKey;
	$.ajax({
		url: url,
		async: false,
		//type: "GET",
		//timeout: 100000000,
		success: function(result){
			processResult($singleSelectDom, result);
		}
	});
	//$.get(url).done(function(result){processResult($singleSelectDom, result);});
};//end of function initSingleSelect.
/**
 * called by initSingleSelect。
 * @author ywang
 * @param 
 * @return
 * @version  
 * 20160821pm
 *     1.new.
 */
var processResult = function($singleSelectDom, result){
	var selectContents = new Array();
	var resultData = result.data;
	for(var curIndex in resultData){
		//console.log("curIndex:"+curIndex);
		var curObj = resultData[curIndex];
		//console.log("curIndex:"+curIndex+",curObj:"+curObj);
		var item = {title: curObj, value: curIndex};
		selectContents.push(item);
	}
	$singleSelectDom.select({
		title: "请选择",
		contents: selectContents
	}).on("change", function(){
		//console.log("select,.changed.selectKey:"+selectKey);
		var curValue = $(this).getValue();
		//console.log("curValue:"+curValue);
		$singleSelectDom.next(":hidden").val(curValue);
	});
};//end of function processResult
/**
 * @title  "实训任务"表单数据回显
 * @author ywang
 * @param 
 * @return
 * @version
 * 20160331pm  
 *     1.new.
 */
var appendTaskFormData = function(localProcInstId, $procInstForm){
	//alert("1111,in appendTaskFormData(),localProcInstId:"+localProcInstId);
	$procInstForm.find("#idID").val(localProcInstId);
	var url = contextPath+"/KuWfRuProcInst/getWithFormdata/" + localProcInstId + ".koala";//20160613pm
	$.ajax({
		url: url,
		async: false,//20160816nt
		success: function(result){
			var resultData = result.data;
			//alert("resultData.procInstTitle:"+resultData.procInstTitle);
			//$procInstForm.find("#id").val(resultData.id);
			$procInstForm.find("#procInstTitleID").val(resultData.procInstTitle);//20160401pm 便于跟新formdata表
			$procInstForm.find("#procInstFormdataIdID").val(resultData.procInstFormdataId);//20160401pm 便于跟新formdata表
			
			var procInstFormdata = resultData.procInstFormdata;
			appendValue2EachDOM($procInstForm.find(".customFormData"), procInstFormdata);
			
			//20160913pm
			//console.log("procInstFormdata:"+procInstFormdata);
			var tablesData = $.parseJSON(procInstFormdata).Tables;
			appendTablesData(tablesData);
			
			var procInstTaskTaker = resultData.procInstTaskTaker;
			appendValue2EachDOM($procInstForm.find(".formDataTaskTaker"), procInstTaskTaker);
			
			var procInstTaskOpinion = resultData.procInstTaskOpinion;
			appendValue2EachDOM($procInstForm.find(".formDataTaskOpinion"), procInstTaskOpinion);
		 }
	});
};
/**
 * 20160913pm
 */
var appendTablesData = function(tablesData){
	console.log("appendTablesData,tablesData:"+JSON.stringify(tablesData));
	$(tablesData).each(function(index, curTable){
		var tableId = curTable.tableId;
		var $curTable = $("#"+tableId);
		var rowsData = curTable.rowsData;
		console.log("appendTablesData,tableId:"+tableId+",rowsData:"+rowsData);
		$(rowsData).each(function(index, curRow){
			var curRowStr = JSON.stringify(curRow);
			if(index == 0)  {appendValue2EachDOM($curTable.find("tr").eq(-2).find("input"), curRowStr);  return 0;}
			var $trObj = insertTableRow($curTable.find("tr:last"));
			//console.log("appendTablesData,index:"+index+",curRow:"+JSON.stringify(curRow)+",$trObj.find(input).length:"+$trObj.find('input').length);
			appendValue2EachDOM($trObj.find("input"), curRowStr);//20160913pm
		});
	});
};
/**
 * @title  "实训任务表单"单个form控件数据回显。  
 * @author ywang
 * @param 
 * @return
 * @version
 * 20160914nt
 *     1.增加“ID”属性识别。
 * 20160822am
 *     1.select类型控件数据回显。
 * 20160820am
 *     1.id属性改为name属性。
 *     2.checkbox类型数据回显.
 *     3.radio类型数据回显。
 * 20160401pm
 *     1.供方法appendTaskFormData()调用。
 */
var appendValue2EachDOM = function(customFormDataDomArray, customFormData){
	//console.log("1111,in appendValue2EachDOM, customFormData:"+customFormData);
	var jsonObject = $.parseJSON(customFormData);
	$.each(customFormDataDomArray, function(index, element){
		var $element = $(element);
		//20160914nt
		var id = $element.attr("id");
		var name = $(element).attr("name");
		if(!isNull(id))  name = id;
		if(isNull(name))  return 0;//20160913nt
		var curValue = $(jsonObject).attr(name);
		console.log("2222,in appendValue2EachDOM,name:"+name+",curValue:"+curValue);
		
		$(element).val(curValue);
		//20160820pm checkbox类型数据回显
		if(name.indexOf("-checkbox") > -1){
			var checkboxValue = $(jsonObject).attr(name);
			if(checkboxValue && checkboxValue != "false")  $(element).prev(":checkbox").attr("checked", "checked"); 
		}
		//20160820nt radio数据回显
		if(name.indexOf("-radio") > -1){
			var radioValue = $(jsonObject).attr(name);
			var radioId = name+"-"+radioValue;
			$(element).parent().find("input[type='radio'][id='"+radioId+"']").attr("checked", "checked");
		}
		//20160822am select数据回显
		if($element.attr("type")=="hidden" && $element.prev().hasClass("select")){
			$element.prev().setValue(curValue);
		}
	});
};
/**
 * 20160407pm
   	  查看任务书。
 */
var viewTaskbook = function(id, curTeachClassId){
	//alert("viewTaskbook,id:"+id);
	var url = contextPath+"/pages/domain/task/KuBizTaskbook-form.jsp";
	$.get(url).done(function(html){
		var $dialog = $(html);
		var $form = $dialog.find('form');
    	initForm($form);
    	$dialog.modal({
            keyboard:false
        }).on({
            'hidden.bs.modal': function(){$(this).remove();}
        });
      appendData2Form($dialog, id, true, curTeachClassId);
	});
};//End of function viewTaskbook.
/**
 * 一条记录表单的数据回显
   @param
    	  requestMapping:目标Controller的RequestMapping.如"news"
   		  $dialog: 对话模态框
   		  id:一条记录ID
          viewMode:为true时，为查看模式.
   @version
   20170119nt
   		1.new。
   20170817nt
   		1.增加单选按钮radio的数据回显功能。
   20170910pm
       1.数据回显时，回显summernote代码。
 */
var appendData2Form = function(requestMapping, $dialog, id, viewMode){
    var $form = $dialog.find('form');
    //initForm($form);
	//数据回显
	var url = contextPath+"/"+requestMapping+"/get/" + id + ".action";
	$.get(url).done(function(json){
		json = json.data;
	    var $elm;
		for(var index in json){
			$elm = $form.find('#'+ index + 'ID');
			if($elm.hasClass('select')){
				$elm.setValue(json[index]);
			}else{
				$elm.val(json[index]);
			}
			if($elm.hasClass('hiddenRadio')){//单选按钮radio的数据回显 20170817pm
				console.log("hiddenRadio,");
				var name = $elm.attr("name");
				var radioValue = $elm.val();
				var radioId = name+"-"+radioValue;
				console.log("radioId:"+radioId);
				$elm.parent().find("input[type='radio'][id='"+radioId+"']").attr("checked", "checked");
			}
		}
		//数据回显时，回显summernote代码。viewMode查看模式不回显summernote。20170910pm
		if(!viewMode && $form.find('#summernoteDiv'))  $form.find('#summernoteDiv').summernote('code', $form.find('#contentID').val());
		
		if(viewMode)  initViewMode($dialog);//20160802pm
	});
};

/**
 	 * @title  数据记录的查看模式  
     * @author ywang
     * @param 
     * @return
     * @version
     * 20170119nt
     *     1.new
 */
var initViewMode = function($dialog){
	console.log("1111,in initViewMode()");
	$dialog.find(".modal-title").html("查看");
	$dialog.find("#saveBtn").remove();
	$dialog.find("#cancelBtn").html("关闭");
	
	//$dialog.find(":hidden").remove();
	$dialog.find(":text,textarea").each(function(index, element){
		var $element = $(element);
		var name = $element.attr("name");
		//var id = $element.attr("id");
		var eleValue = $element.val();
		console.log("initViewMode, eleValue:"+eleValue);
		var classValue = $element.attr("class");
		//20160822pm 对于日期控件的处理
		if($element.parent().hasClass("form_datetime")){
			$element.next(".input-group-addon").remove();
		}
		$element.parent().children("a.glyphicon-user").remove();//20160911am
		$element.parent().children("span.required").remove();
		$element.after("<p class='form-control-static' name='"+name+"' style='display:inline-block;'>"+eleValue+"</p>");//20160822pm,inline-block.
		$element.after("<input type='hidden' class='"+classValue+"' name='"+name+"' value='"+eleValue+"'>").remove();//20160816nt
	});
	//对于select类型，获取对应的title并显示。20160407pm
	$dialog.find(".select").each(function(index, element){
		var $element = $(element);
		//var title = $element.children(":first").text();
		$element.parent().children("span.required").remove();//20160911am
		var title = $element.getItem();
		var name = $element.attr("name");
		$element.after("<p class='form-control-static' style='display:inline-block;'>"+title+"</p>").remove();//20160822pm
		//$element.after("<p class='form-control-static' name='"+name+"'>"+title+"</p>");//.remove();//20160816nt
	});
	//20160820nt
	$dialog.find(":radio").each(function(index, element){
		var $element = $(element);
		$element.parent().parent().children("span.required").remove();//20160911am
		$(element).attr("readonly", "readonly");
		$(element).attr("disabled", "disabled");
	});
	$dialog.find(":checkbox").each(function(index, element){
		var $element = $(element);
		$element.parent().children("span.required").remove();//20160911am
		$(element).attr("readonly", "readonly");
		$(element).attr("disabled", "disabled");
	});
	//$dialog.find("span.required").remove();
	//$dialog.find("a.glyphicon-user").remove();
};//end of initViewMode().
/**
 * 20160826am
 *     1.“任务标题发送时间”格式显示bug：个位数时间显示时也要显示2位
 * 20160624pm
 * 		返回当前时间，格式：“2016-6-24 15:53:08”
 */
var getCurTime = function(){
	var date = new Date();
	var month = date.getMonth()+1;
	if(month < 10)  month = "0"+month;
	var monthDate = date.getDate() > 9 ? date.getDate(): "0"+date.getDate();
	var hours = date.getHours() > 9 ? date.getHours(): "0"+date.getHours();
	var minutes = date.getMinutes() > 9 ? date.getMinutes(): "0"+date.getMinutes();
	var seconds = date.getSeconds() > 9 ? date.getSeconds(): "0"+date.getSeconds();
	//var dateString = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
	var dateString = date.getFullYear()+"-"+month+"-"+monthDate+" "+hours+":"+minutes+":"+seconds;
	return dateString;
};