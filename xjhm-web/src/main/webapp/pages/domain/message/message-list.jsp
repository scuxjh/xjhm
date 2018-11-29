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
	                        {content: '<ks:hasSecurityResource identifier="messageDelete"><button class="btn btn-danger" type="button"><span class="glyphicon glyphicon-remove"><span>删除</button></ks:hasSecurityResource>', action: 'delete'}
	                       ,{content: '<ks:hasSecurityResource identifier="messageQuery"><button class="btn btn-success" type="button"><span class="glyphicon glyphicon-search"></span>&nbsp;高级搜索&nbsp; <span class="caret"></span> </button></ks:hasSecurityResource>', action: 'messageQuery'}
	                    ],
	                url: contextPath+"/message/pageJson.action",
	                columns: [
	                     	    { title: '留言内容', name: 'audioFilepath', width: col_lg, render: function(rowdata, name, index){
	                     	    	var filePath = rowdata.audioFilepath;
	                     	    	var h = "<audio src='"+filePath+"' controls='controls' style='width:200px'></audio>";
	                     	    	//var h = "<audio src='/uploadMessage/孔德仕1520181108212528.mp3' controls='controls' style='width:200px'></audio>";
	                     	    	return h;
	                     	    	}
	                     	    }
                               ,{ title: '姓名', name: 'name', width: col_sm}
                               ,{ title: '联系方式', name: 'phoneNum', width: col_sm}
                               ,{ title: '留言时间', name: 'createTime', width: col_md}
                               /*
	                           ,{ title: '操作', width: col_xs, render: function (rowdata, name, index)
	                                 {
	                                     var param = '"' + rowdata.id + '"';
	                                     var h = "<a href='javascript:viewmessage(" + param + ")'>查看</a> ";
	                                     return h;
	                                 }
	                            }*/
	                ]
	         }).on({
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
	                   ,'messageQuery': function(event, data){
	                	   $searchForm_message.toggle(500);
	                   }
	         });
	    },//end of initGridPanel().
	    add: function($grid){
	    	addmessage($grid);
	    }
	   ,modify: function(id, $grid){
		   addmessage($grid, id);
	    }
	   ,remove: function(id, $grid){
		   var theGrid = $grid.getGrid();
		    var data = [{ name: 'ids', value: id}];
	    	$.post(contextPath+"/message/delete.action", data).done(function(result){
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