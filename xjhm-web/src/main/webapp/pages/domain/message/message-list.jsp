<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ks" uri="http://www.openkoala.org/security" %>
<!DOCTYPE html>
<html>
<head>
<title>留言列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
var _dialog;
var selectItems = {};
var contextPath = '${pageContext.request.contextPath}';
//$grid作为全局变量，方便使用。
var $grid_message;
$(function (){	
	var $searchForm_message;
	var nowNumber = $.now();//20150817am
    ($searchForm_message=$("#searchForm_message")).attr("id", "form_"+nowNumber);//20150817am
    ($grid_message=$("#divGrid_message")).attr("id", "grid_"+nowNumber);
    
	PageLoader = {
	    initSearchPanel:function(){},
	    initGridPanel: function(){
	         var self = this;
	         return $grid_message.grid({
	                identity: "id",
	                lockWidth: true,
	                buttons: [
	                        {content: '<ks:hasSecurityResource identifier="newsAdd"><button class="btn btn-primary" type="button" id="taskManagerAddBtn"><span class="glyphicon glyphicon-plus"><span>新增</button></ks:hasSecurityResource>', action: 'add'},
	                        {content: '<ks:hasSecurityResource identifier="newsUpdate"><button class="btn btn-success" type="button"><span class="glyphicon glyphicon-edit"><span>修改</button></ks:hasSecurityResource>', action: 'modify'},
	                        {content: '<ks:hasSecurityResource identifier="newsDelete"><button class="btn btn-danger" type="button"><span class="glyphicon glyphicon-remove"><span>删除</button></ks:hasSecurityResource>', action: 'delete'}
	                       ,{content: '<ks:hasSecurityResource identifier="newsQuery"><button class="btn btn-success" type="button"><span class="glyphicon glyphicon-search"></span>&nbsp;高级搜索&nbsp; <span class="caret"></span> </button></ks:hasSecurityResource>', action: 'newsQuery'}
	                    ],
	                url: contextPath+"/news/pageJson.action",
	                columns: [
	                     	    { title: '留言内容', name: 'audioFilepath', width: col_md}
                               ,{ title: '姓名', name: 'name', width: col_sm}
                               ,{ title: '联系方式', name: 'phoneNum', width: col_sm}
                               ,{ title: '留言时间', name: 'createTime', width: col_md}
	                           ,{ title: '操作', width: col_xs, render: function (rowdata, name, index)
	                                 {
	                                     var param = '"' + rowdata.id + '"';
	                                     var h = "<a href='javascript:viewNews(" + param + ")'>查看</a> ";
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
	                    },
	                   'delete': function(event, data){
	                        var indexs = data.data;
	                        var $this = $(this);
	                        if(indexs.length != 1){
	                            $this.message({type: 'warning', content: '请选择一条记录'});
	                            return;
	                        }
	                        var remove = function(){
	                            self.remove(indexs[0], $this);
	                        };
	                        $this.confirm({content: '确定要删除所选记录吗?', callBack: remove});
	                   }
	                   ,'newsQuery': function(event, data){
	                	   $searchForm_message.toggle(500);
	                   }
	                   ,'activate': function(event, data){//20160407pm
	                	   var indexs = data.data;
	                	   var $this = $(this);
	                       if(indexs.length != 1){
	                           $this.message({type: 'warning', content: '请选择一条记录'});
	                           return;
	                       }
	                	   alterNewsDisplay(indexs[0], 1);
	                   }
	                   ,'forbidden': function(event, data){
	                	   var indexs = data.data;
	                	   var $this = $(this);
	                       if(indexs.length != 1){
	                           $this.message({type: 'warning', content: '请选择一条记录'});
	                           return;
	                       }
	                	   alterNewsDisplay(indexs[0], 0);
	                   }
	         });
	    },//end of initGridPanel().
	    add: function($grid){
	    	addNews($grid);
	    }
	   ,modify: function(id, $grid){
		   addNews($grid, id);
	    }
	   ,remove: function(id, $grid){
		   var theGrid = $grid.getGrid();
		    var data = [{ name: 'ids', value: id}];
	    	$.post(contextPath+"/news/delete.action", data).done(function(result){
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
	$searchForm_message.find('#search').on('click', function(){
            //if(!Validator.Validate(document.getElementById(""),3)) return;
        //if(!Validator.Validate($searchForm, 3)) return;
            var params = {};
            $searchForm_message.find('input').each(function(){
                var $this = $(this);
                var name = $this.attr('name');
                if(name){
                    params[name] = $this.val();
                }
            });
            $grid_message.getGrid().search(params);
        });
});
/**
 	* 新增或修改 一条新闻。
   @param
       $grid  
       newsId 主键。
   @version
   	20170118am
       1.new.
    20170823nt
       1.保存时，增加对summernote内容的保存；
 */
var addNews = function($grid, newsId){
	//var url = contextPath + "/pages/common/template/ModalDialog-template.jsp";
	var url = contextPath + "/pages/common/template/ModalDialog-template-lg.jsp";
    $.get(url).done(function(html){
    	var $dialog = $(html);
    	$dialog.find(".modal-title").html("新增");
    	url = contextPath+"/pages/domain/news/news-form.jsp";
    	$.get(url).done(function(html){
    		$dialog.find("form").append($(html));
    		var $form = $dialog.find('form');
    		//调用taskOperate.js共用方法 ，初始化任务表单   20170118pm
    		initForm($form);
    		//20170910pm
    		$form.find('#contentID').hide();
    		
    		//20170816pm
    		var $content = $form.find("#summernoteDiv");
	    	//$content.summernote({height:200,lang:'zh-CN'});//20170816pm
	    	//var imageUrl = "https://imgsa.baidu.com/exp/pic/item/7a8a1446f21fbe09554416df62600c338644ada0.jpg";
	    	$content.summernote({height:200,lang:'zh-CN',
	    		callbacks: {
	    			onImageUpload: function(files) {//20170823nt
	    				var fileName = files[0].name;
	    				var fileType = files[0].type;
	    				var fileSize = files[0].size;
	    				console.log("onImageUpload....,fileName:"+fileName+",fileType:"+fileType+",fileSize:"+fileSize);
	    				if(fileType.indexOf('image') < 0) {$form.message({type: 'warning', content: '只能上传图片'}); return;}
	    				if(fileSize > 5*1024*1024) {$form.message({type: 'warning', content: '图片尺寸最大5M'}); return;}
	    				
	    				//使用FormData对象添加字段方式上传文件.20170910pm
	    				var formData = new FormData();
	    				formData.append("file", files[0]);
	    				var theUrl = contextPath + "/news/uploadImage.action";
	    				$.ajax({
	    				    url: theUrl,
	    				    type: 'POST',
	    				    datatype: 'json',
	    				    data: formData,
	    				    cache:false,
	    				    traditional: true,
	    				    contentType: false,
	    				    processData: false,
	    				    success: function (responseStr) {
	    				    	if(responseStr.success){
	    				    		console.log("上传文件成功。");
	    				    		var imageUrl = contextPath + responseStr.data.imageUrl;
	    				    		console.log("responseStr.data.imageUrl:"+imageUrl);
	    				    		console.log("responseStr.data.message:"+responseStr.data.message);
	    				    		$content.summernote('insertImage', imageUrl);
	    				    	}
	    				    },
	    				    error: function () {console.log("请求服务器失败。");}
	    				  });
	    			}
	    		}});//20170816pm
	    	
    		$dialog.modal({
                keyboard:false
            }).on({
                //'hidden.bs.modal': function(){$(this).remove();}
            });
    		//console.log("1111,in addNews,newsId:"+newsId);
    		if(newsId) appendData2Form("news", $dialog, newsId);
            $dialog.find('#saveBtn').on('click', function(e){
                  //if(!Validator.Validate($form))  return;//20160825nt
                  //console.log("1111,in add..,serialize:"+$dialog.find('form').serialize());
                  var contentSummernote = $content.summernote('code');//获取summernote中编辑的代码内容
                  console.log("1111,in save,contentSummernote:"+contentSummernote);
                  $dialog.find('#contentID').val(contentSummernote);
                  //return;
                  var theUrl = contextPath+"/news/add.action";
                  console.log($dialog.find('form').serialize());
                  //return;
                  $.post(theUrl, $dialog.find('form').serialize()).done(function(result){
                       if(result.success){
                    	   $dialog.modal('hide');
                           $grid.getGrid().refresh();
                           $grid.message({type: 'success', content: '操作成功'});
                        }else{
                           $dialog.find('.modal-content').message({type: 'error', content: result.errorMessage});
                        }
                  });//end of $.post.done().
            });
    	});
    });
};//end of function addNews.
/**
 * 查看一条新闻数据记录
   @param
       id:数据记录ID。
   @version
       20170119nt
       1.new.
 */
var viewNews = function(id){
	//var url = contextPath + "/pages/common/template/ModalDialog-template.jsp";
	var url = contextPath + "/pages/common/template/ModalDialog-template-lg.jsp";
    $.get(url).done(function(html){
    	var $dialog = $(html);
    	//$dialog.find(".modal-title").html("查看");
    	url = contextPath+"/pages/domain/news/news-form.jsp";
    	$.get(url).done(function(html){
    		$dialog.find("form").append($(html));
    	    var $form = $dialog.find('form');
    	    if($form.find('#summernoteDiv'))  $form.find('#summernoteDiv').hide();
    	    initForm($form);
    	    $dialog.modal({
    	    	keyboard:false
    	    }).on({
    	    	'hidden.bs.modal': function(){$(this).remove();}
    	    });
    	    appendData2Form("news", $dialog, id, true);
    	    
    	 });
	});
};//End of function viewNews.
/**
 * 更改新闻前端显示状态。
 * @version
 	20170117nt
 		1.new.
 */
var alterNewsDisplay = function(id, display){
	//20160730pm
	var theGrid = $grid_message.getGrid();
	var items = theGrid.selectedRows();
	var curDisplay = items[0].display;
	if(display == curDisplay)  return;
	var data = [{ name: 'id', value: id},{name:"display", value: display}];
	var url = contextPath+"/news/alterNewsDisplay.action";
	$.post(url, data).done(function(result){
    	if(result.success){
    		theGrid.refresh();//20160407pm
    		$grid_message.message({type: 'success', content: '操作成功'});
        }else{
            $grid_message.message({type: 'error', content: result.errorMessage});
        }
	});
};
</script>
</head>
<body>
<div style="width:98%;margin-right:auto; margin-left:auto; padding-top: 0px;">
<!-- search form -->
<form id="searchForm_message" target="_self" class="form-horizontal" style="display:none;">
<input type="hidden" name="page" value="0">
<input type="hidden" name="pagesize" value="10">
<table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
          <div class="form-group">
          	<label class="control-label" style="width:100px;float:left;">姓名:&nbsp;</label>
            <div style="margin-left:15px;float:left;">
            	<input name="name" class="form-control" type="text" style="width:180px;" id="nameID"  />
        	</div>
          </div>
    </td>
       <td style="vertical-align: bottom;"><button id="search" type="button" style="position:relative; margin-left:35px; top: -15px" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button></td>
  </tr>
</table>
</form>
<!-- grid -->
<div id="divGrid_message"></div><!-- 20150817am -->
</div>
</body>
</html>