<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>病人相关问卷列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
var _dialog;
var selectItems = {};
//$grid作为全局变量，方便使用。
var $grid_bbquestionnaire;
$(function (){	
	var $searchForm_kuBizTaskbook;
	var nowNumber = $.now();//20150817am
    ($searchForm_kuBizTaskbook=$("#searchForm_kuBizTaskbook")).attr("id", "form_"+nowNumber);//20150817am
    ($grid_bbquestionnaire=$("#divGrid_bbquestionnaire")).attr("id", "grid_"+nowNumber);
    
	PageLoader = {
	    initSearchPanel:function(){},
	    initGridPanel: function(){
	         var self = this;
	         return $grid_bbquestionnaire.grid({
	                identity:"id"
	               ,lockWidth: true
	               ,searchCondition: {"patient_id": "123456"}//默认查询条件。20160904am
	               ,resultData: "questionnaire_list"
	               ,buttons: [
	                        {content: '<button class="btn btn-primary" type="button" id="taskManagerAddBtn"><span class="glyphicon glyphicon-plus"><span>添加</button>', action: 'add'}
	                       //,{content: '<button class="btn btn-success" type="button"><span class="glyphicon glyphicon-edit"><span>修改</button>', action: 'modify'}
	                       ,{content: '<button class="btn btn-danger" type="button"><span class="glyphicon glyphicon-remove"><span>删除</button>', action: 'delete'}
	                    ]
	               ,url: "/api/questionnaire-list"
	               ,columns: [
	                     	    { title: '问卷ID', name: 'id', width: col_xs}
	                           ,{ title: '问卷标题', name: 'title', width: col_sm, render: function(rowdata, name, index){
	                         		var title = rowdata.title;
	                         		if(title.length > 10)  title = diagnose.title(0, 10)+"..";
	                         		return title;
	                         	}}
	                           ,{ title: '日期', name: 'date', width: col_xs}
	                           ,{ title: '操作', width: col_xs, render: function (rowdata, name, index)
	                                 {
	                                     var param = '"' + rowdata.id + '"';
	                                     var h = "<a href='javascript:viewTaskbook(" + param + ")'>查看</a> ";
	                                     return h;
	                                 }
	                            }
	                ]
	         }).on({
	                   'add': function(){
	                       self.add($(this));
	                   },
	                   'modify': function(event, data){
	                        var indexs = data.data;
	                        var $this = $(this);
	                        if(indexs.length != 1){
	                            $this.message({type: 'warning', content: '请选择一条记录'});
	                            return;
	                        }
	                       self.modify(indexs[0], $this);
	                    }
	         });
	    },//end of initGridPanel().
	    add: function($grid){
	    	addQuestionnaire($grid);
	    }
	   ,modify: function(id, $grid){
		    addQuestionnaire($grid, id);
	    }
	};//end of PageLoader.
	//PageLoader.initDict();//20150704 am
	//PageLoader.initSearchPanel();
	PageLoader.initGridPanel();
});
/**
 * 20160909pm
 */
var addQuestionnaire = function($grid){
	var url = contextPath + "/pages/common/template/ModalDialog-template-lg.jsp";
	$.get(url).done(function(dialog){
		var $dialog = $(dialog);
		$dialog.find(".modal-title").html("问卷调查");
		url = contextPath + "/pages/domain/questionnaire/questionnaire-form.jsp";
		$.get(url).done(function(html){
			$dialog.find("form").append($(html));
			var $form = $dialog.find('form');
	    	initForm($form);
			$dialog.modal({
				keyboard: false
	           ,backdrop: false
	         }).on({
	        	 'hidden.bs.modal' : function() {$(this).remove();}
	         });
			$dialog.find(".form-group.chapter").find("a.glyphicon.glyphicon-plus").on("click", function(e){
	        	addChapter($dialog);
	        });
			$dialog.find("#saveBtn").on("click", function(e){
				if(!Validator.Validate($dialog.find('form')[0]))  return;
				var $form = $dialog.find('form');
				var type = $form.find(":hidden[id='taskTypeID']").val();
				var title = $form.find("#titleID").val();
				var chapterDomArray = $form.find(".chapter-group>.chapter-default");
	            console.log("chapterDomArray.length:"+chapterDomArray.length);
	            var questionnaire_detail = {};
	            $.each(chapterDomArray, function(index, curChapterDom){
	            	var $curChapterDom = $(curChapterDom);
	            	var chapterName = $curChapterDom.find("input[name='question']").val();
	            	console.log("index:"+index+",chapterName:"+chapterName);
	            	var chapterItemsArray = new Array();
	            	var chapterItemsDomArray = $curChapterDom.find("div.form-group.chapterItem>.row").find("input[name='option']");
	            	$.each(chapterItemsDomArray, function(index, curChapterItemDom){
	            		var $curChapterItemDom = $(curChapterItemDom);
	            		chapterItemsArray.push($curChapterItemDom.val()+"");
	            	});
	            	questionnaire_detail[chapterName] = chapterItemsArray;
	            });
	            var questionnaire_detail_str = JSON.stringify(questionnaire_detail);
	            console.log("after saveBtn,title:"+title+"type:"+type+",questionnaire_detail_str:"+questionnaire_detail_str);
	            //调用ajaxAPI保存问卷。。。。
			});
		});
	});
};//end of function addQuestionnaire().
/**
 * 20150809pm
   20160412pm
 */
var addChapter = function($html){
	var chapterTemplate = $html.find(".chapter-group>.chapter-default-template");
	var chapterRowClone = chapterTemplate.clone();
	chapterRowClone.removeClass("chapter-default-template").addClass("chapter-default").show();//20150812pm
	
	var chapterRowCloneChapter = chapterRowClone.find(".form-group.chapter").find(".row");
	$html.find(".chapter-group").append(chapterRowClone);//20150812pm
	var prevChapter = chapterRowClone.prev();
	var prevChapterNoMark = prevChapter.find("div.form-group.chapter>.row>label>mark");
	var prevChapterNo = prevChapterNoMark.html();
	chapterRowClone.find("div.form-group.chapter>.row>label>mark").html(parseInt(prevChapterNo)+1);
	
    chapterRowCloneChapter.find("a.glyphicon.glyphicon-plus").on("click", function(e){
    	addChapter($html);
    });
    chapterRowClone.find(".form-group.chapterItem>.row>a.glyphicon.glyphicon-minus").remove();
	return chapterRowClone;//20160412am
};//End of function addChapter.
/**
 * minusChapter
   20150810am
 */
var minusChapter = function(athis){//20150812pm
	var chapterObj = $(athis).parent().parent().parent();
    var nextAll = chapterObj.nextAll();
	nextAll.each(function(index, element){
		var chapterRowChapterNoMark = $(element).find(".form-group.chapter").find(".row").find("label>mark");
		var chapterNo = chapterRowChapterNoMark.html();
		chapterRowChapterNoMark.html(parseInt(chapterNo)-1);
	});
	chapterObj.remove();
};
/**
 * 20160821am
       1.精简参数，去掉isUpdate.根据taskbookId判断是否为更新。
 * 20160711am
 		新增或修改 任务书实体。
   @param
       isUpdate true为更新任务书；
       taskbookId 主键。
   @version
       1.当前若是教师修改“任务书情景描述”时，需要更新“教学班级任务书关系”数据。20160802pm
 */
var addTaskbook = function($grid, taskbookId){
    var url = contextPath+"/pages/domain/task/KuBizTaskbook-form.jsp";
    $.get(url).done(function(html){
    	var $dialog = $(html);
    	var $form = $dialog.find('form');
    	initForm($form);
        $dialog.modal({
            keyboard:true
        }).on({
            'hidden.bs.modal': function(){$(this).remove();}
        });//.find('.modal-body').html(html);
        /*
        $dialog.find("span.input-group-addon>a").on("click", function(event, data){//选择岗位名称点击事件绑定 20150818am
        	selectPostName($(this));
        });
        */
        if(taskbookId) appendData2Form($dialog, taskbookId);
        
        $dialog.find('#saveTaskbook').on('click', function(e){
              if(!Validator.Validate($dialog.find('form')[0]))  return;//20160825nt
              //alert("add");
              var theUrl = contextPath+"/KuBizTaskbook/add.koala";
              //当前若是教师修改“任务书情景描述”时，需要更新“教学班级任务书关系”数据。20160802pm
              var curUserRoleName = $("#roles").html();
              var teachClassId = globalStudentParam.teachClassId;
           	  if(curUserRoleName == globalData.roleName_Teacher && teachClassId){
          		  theUrl = contextPath+"/KuBizTeachclassTaskbookRelation/update.koala?teachClassId="+teachClassId+"&&taskbookId="+taskbookId;
          	  }
              $.post(theUrl, $dialog.find('form').serialize()).done(function(result){
                   if(result.success ){
                	   $dialog.modal('hide');
                        $grid.getGrid().refresh();
                        $grid.message({type: 'success', content: '保存成功'});
                        
                    }else{
                        $dialog.find('.modal-content').message({type: 'error', content: result.actionError});
                     }
              });//end of $.post.done().
        });
    });
};//end of function addTaskbook.
</script>
</head>
<body>
<div style="width:98%;margin-right:auto; margin-left:auto; padding-top: 0px;">
<form id="searchForm_kuBizTaskbook" target="_self" class="form-horizontal" style="display:none;">
<input type="hidden" name="page" value="0"><input type="hidden" name="pagesize" value="10">
</form>
<div id="divGrid_bbquestionnaire"></div>
</div>
</body>
</html>