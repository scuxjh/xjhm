<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- taglib可以自定义标签库 -->
<%@ taglib prefix="ks" uri="http://www.openkoala.org/security" %>
<!DOCTYPE html>
<html>
<head>
<title>问卷调查</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <script type="text/javascript">
var _dialog;
var selectItems = {};
//取得绝对路经
var contextPath = '${pageContext.request.contextPath}';
//$grid作为全局变量，方便使用。
var $grid_question;
$(function (){	
	var $searchForm_question;
	//$.now() 函数用于返回当前时间距1970年1月1日午夜所经过的毫秒数
	var nowNumber = $.now();//20150817am
    ($searchForm_question=$("#searchForm_question")).attr("id", "form_"+nowNumber);//20150817am
    
    ($grid_question=$("#divGrid_question")).attr("id", "grid_"+nowNumber);
    //设置pageloader的initGridPanel属性为一个function函数，PageLoader。initGridPanel可以调用此函数
	PageLoader = {
	    initSearchPanel:function(){},
	    initGridPanel: function(){
	         var self = this;
	         //？？
	         return $grid_question.grid({
	                identity: "id",
	                lockWidth: true,
	                buttons: [
	                          //&nbsp表示空格
	                        {content: '<ks:hasSecurityResource identifier="questionAdd"><button class="btn btn-primary" type="button" id="taskManagerAddBtn"><span class="glyphicon glyphicon-plus"><span>新增</button></ks:hasSecurityResource>', action: 'add'},
	                        {content: '<ks:hasSecurityResource identifier="questionUpdate"><button class="btn btn-success" type="button"><span class="glyphicon glyphicon-edit"><span>修改</button></ks:hasSecurityResource>', action: 'modify'},
	                        {content: '<ks:hasSecurityResource identifier="questionDelete"><button class="btn btn-danger" type="button"><span class="glyphicon glyphicon-remove"><span>删除</button></ks:hasSecurityResource>', action: 'delete'},
	                        {content: '<ks:hasSecurityResource identifier="questionQuery"><button class="btn btn-success" type="button"><span class="glyphicon glyphicon-search"></span>&nbsp;高级搜索&nbsp; <span class="caret"></span> </button></ks:hasSecurityResource>', action: 'questionQuery'}
	                    ],
	                url: contextPath+"/Questionnaire/pageJson.action",
	                //name属性与questionnaireDTO的字段对应
	                columns: [
	                     	    { title: '问卷标题', name: 'questionnaireTitle', width: col_md}
                               ,{ title: '所属类别', name: 'categoryName', width: col_sm}
                               ,{ title: '是否前台显示', name: 'display', width: col_md, render: function(rowdata, name, index){
	                         		//rowdata??
                            	   var display = rowdata.display;
	                         		var available = '<span class="glyphicon glyphicon-ok" style="color:#5CB85C;margin-left:15px;" title="是"></span>';
	                         		var forbidden = '<span class="glyphicon glyphicon-remove" style="color:#D9534F;margin-left:15px;" title="否"></span>';
	                         		if(display == "1"){
	                         			return available;
	                         		}else{
	                         			return forbidden;
	                         		}
	                         	}}
                               //col_sm是什么？index.jsp中的变量为什么可以直接使用
                               ,{ title: '创建时间', name: 'createTime', width: col_md}
	                           ,{ title: '发布时间', name: 'startTime', width: col_md}
	                           ,{ title: '操作', width: col_xs, render: function (rowdata, name, index)
	                                 {
	                                     var param = '"' + rowdata.id + '"';
	                                     //点击链接时返回js函数viewquestion
	                                     var h = "<a href='javascript:viewquestion(" + param + ")'>查看</a> ";
	                                     return h;                         
	                                 }
	                            }
	                           ,{ title: '问卷反馈', width: col_xs, render: function (rowdata, name, index)
	                                 {
	                                     var param = '"' + rowdata.id + '"';
	                                     //点击链接时返回js函数viewquestion
	                                     var h = "<a href='javascript:viewquestionanswer(" + param + ")'>查看</a> ";
	                                     return h;                         
	                                 }
	                            }
	                ]
	         }).on({
	                   'add': function(){
	                       self.add($(this));
	                   },
	                   //event,data怎么传递的
	                   'modify': function(event, data){
	                        var indexs = data.data;
	                        var $this = $(this);
	                        if(indexs.length == 0){
	                            $this.message({
	                                type: 'warning',
	                                content: '请选择一条记录进行修改'
	                            })
	                            return;
	                        }
	                        if(indexs.length > 1){
	                            $this.message({
	                                type: 'warning',
	                                content: '只能选择一条记录进行修改'
	                            })
	                            return;
	                        }
	                       self.modify(indexs[0], $this);
	                    },
	                   'delete': function(event, data){
	                        var indexs = data.data;
	                        var $this = $(this);
	                        if(indexs.length<1){
	                            $this.message({type: 'warning', content: '请选择一条记录'});
	                            return;
	                        }
	                        var remove = function(){
	                        	for(var i in indexs){
	                            self.remove(indexs[i], $this);
	                        	}
	                        };
	                        $this.confirm({content: '确定要删除所选记录吗?', callBack: remove});
	                   }
	                   ,'questionQuery': function(event, data){
	                	   $searchForm_question.toggle(500);
	                   }
	                   ,'activate': function(event, data){//20160407pm
	                	   var indexs = data.data;
	                	   var $this = $(this);
	                       if(indexs.length != 1){
	                           $this.message({type: 'warning', content: '请选择一条记录'});
	                           return;
	                       }
	                	   alterquestionDisplay(indexs[0], 1);
	                   }
	                   ,'forbidden': function(event, data){
	                	   var indexs = data.data;
	                	   var $this = $(this);
	                       if(indexs.length != 1){
	                           $this.message({type: 'warning', content: '请选择一条记录'});
	                           return;
	                       }
	                	   alterquestionDisplay(indexs[0], 0);
	                   }
	         });
	    },//end of initGridPanel().
	    add: function($grid){
	    	addquestion($grid);
	    }
	   ,modify: function(id, $grid){
		   addquestion($grid, id);
	    }
	   ,remove: function(id, $grid){
		   var theGrid = $grid.getGrid();
		    var data = [{ name: 'ids', value: id}];
	    	$.post(contextPath+"/Questionnaire/delete.action", data).done(function(result){
	    		if(result.success){
                	theGrid.refresh();
                	$grid.message({type: 'success', content: '操作成功'});
                }else{
                	$grid.message({type: 'error', content: result.errorMessage});
                }
	    	});
	    }
	};//end of PageLoader.
	//PageLoader.initDict();//20150704 am
	//PageLoader.initSearchPanel();
	PageLoader.initGridPanel();
	$searchForm_question.find('#search').on('click', function(){
            //if(!Validator.Validate(document.getElementById(""),3)) return;
        //if(!Validator.Validate($searchForm, 3)) return;
            var params = {};
            $searchForm_question.find('input').each(function(){
                var $this = $(this);
                var name = $this.attr('name');
                if(name){
                    params[name] = $this.val();
                }
            });
            $grid_question.getGrid().search(params);
        });
});
/**
 	* addquestion 新增或修改 一条新闻。
   @param
       $grid  
       questionId 主键。
   @version
   	20170118am
       1.new.
 */
var addquestion = function($grid, questionId){
	
	var url = contextPath + "/pages/common/template/ModalDialog-template-lg.jsp";
	// $.get(url)请求载入信息,function(html)??html是什么？
    $.get(url).done(function(html){
    	var $dialog = $(html);
    	$dialog.find(".modal-title").html("<b>新增</b>");
    	url = contextPath+"/pages/domain/questionnaire/Questionnaire-form.jsp";
    	$.get(url).done(function(html){
    		$dialog.find('form').append($(html));
    		var $form = $dialog.find('form'); 
    		 //20170118pm
    		initForm($form);
    		 //初始化问卷
    		initQuestionnaire($dialog);
    		//bootstrap模态框，backdrop使点击背景时不自动关闭
    		$dialog.modal({
    			backdrop:'static',
                keyboard:false
                //on()函数用于为指定元素的一个或多个事件绑定事件处理函数。
            })
            
    		.on(
    			
            	//remove() 方法删除被选元素及其子元素。
                "hidden.bs.modal", function(){$dialog.remove();}
            );
    		
    		//console.log("1111,in addquestion,questionId:"+questionId);
    		if(questionId) {
    			$dialog.find(".modal-title").html("<b>修改</b>");
    			appendData2Form("Questionnaire", $dialog, questionId);
    			showquestionnaire($dialog,questionId,false);
    			
    		}
            $dialog.find('#saveBtn').on('click', function(e){
                  var theUrl = contextPath+"/Questionnaire/add.action";
                  console.log($dialog.find('form').serialize());
                  $.post(theUrl, $dialog.find('form').serialize()).done(function(result){
                       if(result.success ){
                    	   addvotetitle($dialog );
                    	   $dialog.modal('hide');
                    	  // $dialog.refresh();
                           $grid.getGrid().refresh();
                           $grid.message({type: 'success', content: '操作成功'});
                        }else{
                           $dialog.find('.modal-content').message({type: 'error', content: result.errorMessage});
                        }
                  });//end of $.post.done().
                 
            });
    	});
    });
};//end of function addquestion.
/**
 * 查看一条新闻数据记录
   @param
       id:数据记录ID。
   @version
       20170119nt
       1.new.
 */
 var countxx;
 var addvotetitle=function($dialog){
	       countxx=0;
    	   $dialog.find('.btwenzi').each(function(counttime,currentbt){
    		   //添加延迟，保证each中的内容依此执行
    		   setTimeout(function(){addtimeout($dialog,currentbt);},counttime*2000);
         	 }); 
       }
       var addtimeout=function($dialog,currentbt){
    	   var $bt= $dialog.find('.btwenzi');
           var thecontentUrl=contextPath+"/VoteTitle/add.action";
    	   $.post(thecontentUrl,
            		{
    		         id:$(currentbt).parents(".movie_box").attr("id"),
    		         questionTitle:$(currentbt).text(),
            		 questionType:$(currentbt).parents(".movie_box").children(".dx_box").attr("data-t"),
            		 questionNum:$(currentbt).siblings(".nmb").text(),
            		  }	  
            	  ).done(function(result){
            		 if(result.success ){
                       $currentbt=$bt.eq(countxx++);
            			$currentbt.parents(".movie_box").find(".xxwenzi").each(function(num,currentxx){
            				setTimeout(function(){addvoteoption(num,currentxx);},num*400);
            			
            			});
            			
            	  }
            		else{
                       $dialog.find('.modal-content').message({type: 'error', content: result.errorMessage});
                    }
            	  });
       }
var addvoteoption=function(i,currentxx){
	var theoptionUrl=contextPath+"/VoteOption/add.action";
	$.post(theoptionUrl,{
		id:$(currentxx).attr("id"),
		questionOption:$(currentxx).text(),
			optionNum:i+1,
	});
}       
var viewquestion = function(id){
	var url = contextPath + "/pages/common/template/ModalDialog-template-lg.jsp";
    $.get(url).done(function(html){
    	var $dialog = $(html);
    	//$dialog.find(".modal-title").html("查看");
    	url = contextPath+"/pages/domain/questionnaire/Questionnaire-form.jsp";
    	$.get(url).done(function(html){
    		$dialog.find("form").append($(html));
    	    var $form = $dialog.find('form');
    	    initForm($form);
    	    $dialog.modal({
    	    	keyboard:false
    	    }).on({
    	    	'hidden.bs.modal': function(){$(this).remove();}
    	    });
    	    $dialog.find(".xxk_box").remove();
    	    appendData2Form("Questionnaire", $dialog, id, true);
    	    showquestionnaire($dialog,id,true);
    	    
    		
    	 });
	});
};//End of function viewquestion.
/**
 * 查看问卷反馈答案
 */
var viewquestionanswer=function(id){
	var url = contextPath + "/pages/common/template/ModalDialog-template-lg.jsp";
	$.get(url).done(function(html){
    	var $dialog = $(html);
    	//$dialog.find(".modal-title").html("查看");
    	url = contextPath+"/pages/domain/questionnaire/Questionnaire-answer.jsp";
    	$.get(url).done(function(html){
    		$dialog.find("form").append($(html));
    	    var $form = $dialog.find('form');
    	    $dialog.modal({
    	    	keyboard:false
    	    }).on({
    	    	'hidden.bs.modal': function(){$(this).remove();}
    	    });
    	    
    	    showquestionanswer($dialog,id);
    	    
    		
    	 });
	});
	
}
var showquestionanswer=function($dialog,questionnaireid){
	var biaoti="<li><div class='tm_btitlt'><i class='nmb'></i>. <i class='btwenzi'></i>[<i class='type'> </i>]</div></li>";
	var title="<li><div class='title'>反馈<i class='anmb'></i>&nbsp&nbsp&nbsp&nbsp<i class='votetime'></i></div></li>"
	var recordurl = contextPath+"/VoteRecord/getbyqnid/" + questionnaireid + ".action";
	var votetitleurl=contextPath+"/VoteTitle/getbyqnid/" + questionnaireid + ".action";
	var startt="<div class='movie_box' id=''><ul class='wjdc_list'></ul></div>";
	var questionstartt="<div class='question_box' id=''><ul class='question_list'></ul></div>";
	var xxiang="<li><label><span class='xxwenzi' id=''></span></label></li>";
	//定位第几个反馈者
	var feedNum = -1;
	//将投票答案存入a数组
	var a =new Array();
	$.get(recordurl).done(function(json){
		json=json.data;
		for(var i in json){
			var voterecord=json[i];
			var recordanswer=voterecord.problemChoice;
			//alert(recordanswer);
			
			a[i]=recordanswer;
			
			//answer[i] = a;
			$dialog.find(".all_answer").append(startt);
            $dialog.find(".wjdc_list").last().append(title);
            $dialog.find(".anmb").last().text(Number(i)+1);
            $dialog.find(".votetime").last().text(voterecord.voteTime);
            //执行votetitleurl之前出现上面的循环操作已经执行完的情况
         $.get(votetitleurl).done(function(votejson){
        	 feedNum++;
        	 var b =new Array();
        	 b=a[feedNum].split(";");
        	 votejson=votejson.data;
        	 for(var index in votejson){
        		 var votetitle=votejson[index];
        		 var questiontype=votetitle.questionType;
        		 $dialog.find(".wjdc_list").eq(feedNum).append(questionstartt);
        		 $dialog.find(".question_list").last().append(biaoti).append(xxiang);
        		 $dialog.find(".nmb").last().text(votetitle.questionNum);
        		 $dialog.find(".btwenzi").last().text(votetitle.questionTitle);
        		 $dialog.find(".xxwenzi").last().text(b[index]);
        		 if(questiontype==0)$dialog.find(".type").last().text("单选");
        		 if(questiontype==1)$dialog.find(".type").last().text("多选");
        		 if(questiontype==2)$dialog.find(".type").last().text("填空");
        	 }
         });
         
		}
	});
}
/**
 * 更改新闻前端显示状态。
 * @version
 	20170117nt
 		1.new.
 */
var alterquestionDisplay = function(id, display){
	//20160730pm
	var theGrid = $grid_question.getGrid();
	var items = theGrid.selectedRows();
	var curDisplay = items[0].display;
	if(display == curDisplay)  return;
	var data = [{ name: 'id', value: id},{name:"display", value: display}];
	var url = contextPath+"/Questionnaire/alterQuestionnaireDisplay.action";
	$.post(url, data).done(function(result){
    	if(result.success){
    		theGrid.refresh();//20160407pm
    		$grid_question.message({type: 'success', content: '操作成功'});
        }else{
            $grid_question.message({type: 'error', content: result.errorMessage});
        }
	});
};
</script>
</head>
<body>
<div style="width:98%;margin-right:auto; margin-left:auto; padding-top: 0px;">
<!-- search form -->
<form id="searchForm_question" target="_self" class="form-horizontal" style="display:none;">
<input type="hidden" name="page" value="0">
<input type="hidden" name="pagesize" value="10">
<table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
          <div class="form-group">
          	<label class="control-label" style="width:100px;float:left;">标题:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            	<input name="questionTitle" class="form-control" type="text" style="width:180px;" id="questionTitleID"  />
        	</div>
          </div>
    </td>
       <td style="vertical-align: bottom;"><button id="search" type="button" style="position:relative; margin-left:35px; top: -15px" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button></td>
  </tr>
</table>
</form>
<!-- grid -->
<div id="divGrid_question"></div><!-- 20150817am -->
</div>
</body>
</html>