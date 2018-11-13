
var showquestionnaire=function($dialog ,id,mode){
	var Titleurl = contextPath+"/VoteTitle/getbyqnid/" + id + ".action";
	var biaoti="<li><div class='tm_btitlt'><i class='nmb' id='revise'></i>. <i class='btwenzi'></i></div></li>";
	var danxxiang="<li><label><input name='a' type='radio' ><span class='xxwenzi' id=''></span></label></li>";
	var duoxxiang="<li><label><input name='a' type='checkbox' ><span class='xxwenzi' id=''></span></label></li>";
	var danxdata="<div class='dx_box' data-t='0'></div>";
	var duoxdata="<div class='dx_box' data-t='1'></div>";
	var tkdata="<div class='dx_box' data-t='2'></div>";
	var startt;
	if(mode)  startt="<div class='movie_box_view'><ul class='wjdc_list'></ul></div>";
	else      startt="<div class='movie_box' id=''><ul class='wjdc_list'></ul></div>";//修改模式可编辑
	$.get(Titleurl).done(function(json){
		json=json.data;
		for(var i in json){
			var votetitle=json[i];
            $dialog.find(".yd_box").append(startt);
            $dialog.find(".movie_box").eq(i).attr("id",votetitle.id);
            $dialog.find(".wjdc_list").last().append(biaoti);
            $dialog.find(".nmb").last().text(votetitle.questionNum);
            $dialog.find(".btwenzi").last().text(votetitle.questionTitle); 
            initoption(votetitle,i);
            
		}
	});
	
var initoption=function(question,a){
		var xxtype;
		if(question.questionType==0) xxtype=danxxiang;
		else if(question.questionType==1) xxtype=duoxxiang;
		else if(question.questionType==2) $dialog.find(".wjdc_list").eq(a).append("<li><label><textarea class='input_wenbk btwen_text btwen_text_dx'> </textarea></label></li>");
	var Optionurl=contextPath+"/VoteOption/getbyvtid/" + question.id + ".action";
	setTimeout(function(){$.get(Optionurl).done(function(json){
		json=json.data;
		for(var i in json){
			var voteoption=json[i];
			$dialog.find(".wjdc_list").eq(a).append(xxtype);
			$dialog.find(".xxwenzi").last().text(voteoption.questionOption);
			$dialog.find(".xxwenzi").last().attr("id",voteoption.id);
		}
		if(!mode){
        	if(question.questionType==0) $dialog.find(".movie_box").eq(a).append(danxdata);
        	else if(question.questionType==1) $dialog.find(".movie_box").eq(a).append(duoxdata);
        	else if(question.questionType==2) $dialog.find(".movie_box").eq(a).append(tkdata);
        }
});},100*a);
	}
}



var initQuestionnaire=function($dialog){
	//$dialog.find('.btwen_text_dx').val("danxiang");
	 $dialog.find(".btwen_text").val("题目");
	 $dialog.find(".btwen_text_dx").val("单选题目");
	 $dialog.find(".btwen_text_duox").val("多选题目");
	 $dialog.find(".btwen_text_tk").val("填空题目");  
	 $dialog.find(".xxk_conn").children(".xxk_xzqh_box").eq(0).show().siblings().hide();
	 $dialog.find(".leftbtwen_text").val("例子：CCTV1，CCTV2，CCTV3");
	
	$dialog.find(".xxk_title li").click(function(){
		
		var xxkjs = $(this).index(); 
		
		$(this).addClass("on").siblings().removeClass("on");
		$(".xxk_conn").children(".xxk_xzqh_box").eq(xxkjs).show().siblings().hide();
		});
		
	//鼠标移上去显示按钮
	$(".movie_box").live("hover",function(event){ 
		if(event.type=="mouseenter"){
			var	html_cz = "<div class='kzqy_czbut'><a href='javascript:void(0)' class='sy'>上移</a><a href='javascript:void(0)'  class='xy'>下移</a><a href='javascript:void(0)'  class='bianji'>编辑</a><a href='javascript:void(0)' class='del' >删除</a></div>" 
 		    $(this).css({"border":"1px solid #0099ff"}); 
		    $(this).children(".wjdc_list").after(html_cz);
		 }
		else{
			$(this).css({"border":"1px solid #fff"}); 
		    $(this).children(".kzqy_czbut").remove();
		//$(this).children(".dx_box").hide();
		}
	});	
		
		//下移
		$(".xy").live("click", function() {  
			//文字的长度 
			var leng = $(".yd_box").children(".movie_box").length;
			var dqgs = $(this).parent(".kzqy_czbut").parent(".movie_box").index();  
			 
			if(dqgs < leng-1){
				var czxx = $(this).parent(".kzqy_czbut").parent(".movie_box");  
				var xyghtml = czxx.next().html();
				var syghtml = czxx.html(); 
				czxx.next().html(syghtml);
				czxx.html(xyghtml);
				//序号
				czxx.children(".wjdc_list").find(".nmb").text(dqgs+1);
				czxx.next().children(".wjdc_list").find(".nmb").text(dqgs+2);
				}else{
					alert("到底了");
					}  
		});
		//上移
		$(".sy").live("click", function() {
			//文字的长度 
		    var leng = $(".yd_box").children(".movie_box").length;
			var dqgs = $(this).parent(".kzqy_czbut").parent(".movie_box").index(); 
			if(dqgs > 0){
			var czxx = $(this).parent(".kzqy_czbut").parent(".movie_box");  
			var xyghtml = czxx.prev().html();
  			var syghtml = czxx.html();   
			czxx.prev().html(syghtml);
			czxx.html(xyghtml);
			//序号
			czxx.children(".wjdc_list").find(".nmb").text(dqgs+1);
			czxx.prev().children(".wjdc_list").find(".nmb").text(dqgs);
			
				}else{
					alert("到头了");
					}  
		});
		//删除
		$(".del").live("click", function() {  
			var czxx = $(this).parent(".kzqy_czbut").parent(".movie_box"); 
			var zgtitle_gs = czxx.parent(".yd_box").find(".movie_box").length;
			var xh_num = 0; 
			//重新编号
			czxx.parent(".yd_box").find(".movie_box").each(function() { 
				$(".yd_box").children(".movie_box").eq(xh_num).find(".nmb").text(xh_num);
				 xh_num++;
				//alert(xh_num);
            }); 
			 czxx.remove(); 	  		 
		});
		
		//编辑
		$(".bianji").live("click", function() {  
		    //编辑的时候禁止其他操作	
		    $(this).siblings().hide();
			//$(this).parent(".kzqy_czbut").parent(".movie_box").unbind("hover"); 
			var dxtm = $(".dxuan").html();
			var duoxtm = $(".duoxuan").html();
			var tktm = $(".tktm").html();
			var jztm = $(".jztm").html();
			//接受编辑内容的容器
			var dx_rq = $(this).parent(".kzqy_czbut").parent(".movie_box").find(".dx_box");  
		    var title = dx_rq.attr("data-t");
			//alert(title);
			//题目选项的个数
			var timlrxm = $(this).parent(".kzqy_czbut").parent(".movie_box").children(".wjdc_list").children("li").length; 
			
			//单选题目
			if(title==0){
				dx_rq.show().html(dxtm);
				//模具题目选项的个数
				var bjxm_length = dx_rq.find(".title_itram").children(".kzjxx_iteam").length;
			    var dxtxx_html = dx_rq.find(".title_itram").children(".kzjxx_iteam").html(); 
				//添加选项题目
				for (var i_tmxx = bjxm_length; i_tmxx < timlrxm-1 ; i_tmxx++) {
                	 dx_rq.find(".title_itram").append("<div class='kzjxx_iteam'>"+dxtxx_html+"</div>"); 
                 }
				 //赋值文本框 
				 //题目标题
					var texte_bt_val = $(this).parent(".kzqy_czbut").parent(".movie_box").find(".wjdc_list").children("li").eq(0).find(".tm_btitlt").children(".btwenzi").text();  
					dx_rq.find(".btwen_text").val(texte_bt_val);  
				 //遍历题目项目的文字
				 var  bjjs=0;
				 $(this).parent(".kzqy_czbut").parent(".movie_box").find(".wjdc_list").children("li").each(function() { 
					//可选框框
					 var ktksfcz = $(this).find("input").hasClass("wenb_input");
				    if(ktksfcz){
					  var jsxz_kk = $(this).index(); 
					 dx_rq.find(".title_itram").children(".kzjxx_iteam").eq(jsxz_kk-1).find("label").remove();
					 } 
					 //题目选项
					var texte_val = $(this).find("span").text(); 
					dx_rq.find(".title_itram").children(".kzjxx_iteam").eq(bjjs-1).find(".input_wenbk").val(texte_val);
					bjjs++
					 
                }); 
				}
			//多选题目	
			 if(title==1){
				dx_rq.show().html(duoxtm);
				//模具题目选项的个数
				var bjxm_length = dx_rq.find(".title_itram").children(".kzjxx_iteam").length;
			    var dxtxx_html = dx_rq.find(".title_itram").children(".kzjxx_iteam").html(); 
				//添加选项题目
				for (var i_tmxx = bjxm_length; i_tmxx < timlrxm-1 ; i_tmxx++) {
                	 dx_rq.find(".title_itram").append("<div class='kzjxx_iteam'>"+dxtxx_html+"</div>");
					 //alert(i_tmxx);
                 }
				 //赋值文本框 
				 //题目标题
					var texte_bt_val = $(this).parent(".kzqy_czbut").parent(".movie_box").find(".wjdc_list").children("li").eq(0).find(".tm_btitlt").children(".btwenzi").text(); 
					dx_rq.find(".btwen_text").val(texte_bt_val); 
				 //遍历题目项目的文字
				 var  bjjs=0;
				 $(this).parent(".kzqy_czbut").parent(".movie_box").find(".wjdc_list").children("li").each(function() {  
					//可选框框
					 var ktksfcz = $(this).find("input").hasClass("wenb_input");
				    if(ktksfcz){
					  var jsxz_kk = $(this).index(); 
					 dx_rq.find(".title_itram").children(".kzjxx_iteam").eq(jsxz_kk-1).find("label").remove();
					 } 
					//题目选项
					var texte_val = $(this).find("span").text(); 
					dx_rq.find(".title_itram").children(".kzjxx_iteam").eq(bjjs-1).find(".input_wenbk").val(texte_val);
					bjjs++
					 
                });
			 }
			 //填空题目
			 if(title==2){
			 	dx_rq.show().html(tktm);
				 //赋值文本框 
				 //题目标题
					var texte_bt_val = $(this).parent(".kzqy_czbut").parent(".movie_box").find(".wjdc_list").children("li").eq(0).find(".tm_btitlt").children(".btwenzi").text(); 
					dx_rq.find(".btwen_text").val(texte_bt_val);   
			 }
			 //矩阵题目
			 if(title==3){
				dx_rq.show().html(jztm);
				}     		 
		}); 
		
		//增加选项  
		$(".zjxx").live("click", function() {  
			//$(".btwen_text_dx").val("单选题目hsha");
			//$(".input_wenbk").val("单选题目hsha");
			 var zjxx_html =  $(this).prev(".title_itram").children(".kzjxx_iteam").html();
			 $(this).prev(".title_itram").append("<div class='kzjxx_iteam'>"+zjxx_html+"</div>"); 
		});
		
		//删除一行 
		$(".del_xm").live("click", function() {  
			//获取编辑题目的个数
			var zuxxs_num = $(this).parent(".kzjxx_iteam").parent(".title_itram").children(".kzjxx_iteam").length;
			 if(zuxxs_num > 1){
				  $(this).parent(".kzjxx_iteam").remove();
				 }else{
					alert("手下留情");
					 } 
		});
		//取消编辑
		$(".dx_box .qxbj_but").live("click", function() {  
			 $(this).parent(".bjqxwc_box").parent(".dx_box").empty().hide();
			  $(".movie_box").css({"border":"1px solid #fff"});
			  $(".kzqy_czbut").remove(); 
			  // 			  
		});

		
		//完成编辑（编辑）
		var x=0;
		$(".swcbj_but").live("click", function() { 
			var bianji=$(this).parent(".bjqxwc_box");
			//编辑修改
		var jcxxxx = bianji.parent(".dx_box");
		//初次完成编辑
		
		if(jcxxxx.length<=0){
			x++;
			var sz=new Array();
			var htmldx=bianji.parent(".xxk_xzqh_box");
			
			htmldx.find(".input_wenbk").each(function(){
				
				sz.push($(this).val());
				//alert($(this).children(".input_wenbk").val());
			});
			
			var biaoti="<li><div class='tm_btitlt'><i class='nmb'></i>. <i class='btwenzi'></i></div></li>";
			var danxxiang="<li><label><input name='a' type='radio' ><span class='xxwenzi'></span></label></li>";
			var duoxxiang="<li><label><input name='a' type='checkbox' ><span class='xxwenzi'></span></label></li>";
			var danxdata="<div class='dx_box' data-t='0'></div>";
			var duoxdata="<div class='dx_box' data-t='1'></div>";
			var tkdata="<div class='dx_box' data-t='2'></div>";
			var startt="<div class='movie_box'><ul class='wjdc_list'></ul></div>";
			var revisenum=$(".yd_box").find(".nmb").last().text();
			$(".yd_box").append(startt);
			//$("#first").prepend(biaoti);
			for(var i=0;i<sz.length;i++){
				//添加标题
				if(!i){
					$(".yd_box").find(".wjdc_list").last().append(biaoti);
					var biaoti = $(".yd_box").find(".nmb");
					biaoti.last().text(biaoti.eq(-2).text()*1+1);
				    $(".yd_box").find(".btwenzi").last().text(sz[i]);
				    if(sz.length==1) 
				    	$(".yd_box").find(".wjdc_list").last().append("<li><label><textarea class='input_wenbk btwen_text btwen_text_dx'> </textarea></label></li>");
				}
				else{//添加选项
					if(bianji.parent(".dxuan").length>0)
						$(".yd_box").find(".wjdc_list").last().append(danxxiang);
				    else 
				    	$(".yd_box").find(".wjdc_list").last().append(duoxxiang);
				    $(".yd_box").find("span").last().text(sz[i]);
			}
			}
			if(bianji.parent(".dxuan").length>0)
				$(".yd_box").find(".movie_box").last().append(danxdata);
			else if(bianji.parent(".duoxuan").length>0)
				 $(".yd_box").find(".movie_box").last().append(duoxdata);
			 else if (bianji.parent(".tktm").length>0) 
			    	$(".yd_box").find(".movie_box").last().append(tkdata);
				
			
		}
		//编辑题目选项的个数
		  var bjtm_xm_length = jcxxxx.find(".title_itram").children(".kzjxx_iteam").length;
		  var xmtit_length = jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").length-1;
		 
		 //添加选项题目
		 //添加选项
		 if(bjtm_xm_length > xmtit_length){
			var fzll = jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").eq(1).html(); 
			  for(var toljs_add = 0 ; toljs_add < bjtm_xm_length - xmtit_length ; toljs_add++){
					 jcxxxx.parent(".movie_box").children(".wjdc_list").append("<li>"+fzll+"</li>") 
				  } 
			}
			//删除选项
			if(bjtm_xm_length < xmtit_length&&jcxxxx.attr("data-t")!=2) {  
				 for(var toljs = xmtit_length ; toljs > bjtm_xm_length ; toljs--){
					 jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").eq(toljs).remove();
					 }  
				}
			 
			 //赋值文本框 
			 //题目标题
			  var texte_bt_val_bj = jcxxxx.find(".btwen_text").val();  
			  jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").eq(0).find(".tm_btitlt").children(".btwenzi").text(texte_bt_val_bj); 
			 //遍历题目项目的文字
			  var  bjjs_bj=0;
			  jcxxxx.children(".title_itram").children(".kzjxx_iteam").each(function() { 
				 //题目选项
				 var texte_val_bj = $(this).find(".input_wenbk").val(); 
				 jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").eq(bjjs_bj+1).find("span").text(texte_val_bj);
				 bjjs_bj++
				 //可填空  
				 var kxtk_yf = $(this).find(".fxk").is(':checked');	 
				 if(kxtk_yf){
					 //第几个被勾选
					 var jsxz = $(this).index();
					 //alert(jsxz);
					 jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").eq(jsxz+1).find("span").after("<input name='' type='text' class='wenb_input'>");
					  
					 }  
				 
                });  
				
			//清除	 
			 $(this).parent(".bjqxwc_box").parent(".dx_box").empty().hide();	
		
		});
		
}