<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<div class="modal-body" style="padding-left:45px; padding-right:65px;padding-top:10px;">
	<form class="form-horizontal page_form">
    	<div class="form-group">
        	<div class="row">
            	<label class="col-md-2 control-label" for="name">新闻标题:</label>
                <div class="col-md-10">
                	<input type="text" class="form-control" id="newsTitleID" name="newsTitle" value="" dataType="Require" placeholder="新闻标题"><span class="required">*</span>
                    <input type="hidden" id="idID" name="id" />
				</div>
            </div>
		</div>
		<!-- 第2行 -->
		<div class="form-group">
			<div class="row">
				<label class="col-md-2 control-label" for="name">新闻类别:</label>
                <div class="col-md-4">
					<div class="input-group" style="width:100%;">
                    	<div class="btn-group select" id="categoryIdID" selectKey="news.CATEGORY" ></div>
                        <input type="hidden" id="categoryIdID" name="categoryId" dataType="Require" />
                        <span class="required">*</span>
                    </div>
                </div>
				<label class="col-md-2 control-label" for="name">是否前台显示:</label><!-- 默认为否 value=0 -->
                <div class="col-md-2">
					<label class="radio-inline">
                    	<input type="radio" id="display-1" name="display" value="1">是
                    </label>
		            <label class="radio-inline">
        		    	<input type="radio" id="display-0" name="display" value="0" checked="checked">否
                	</label>
                    <input type='hidden' class="hiddenRadio" name='display' id='displayID' value="0" >
                    <span class="required">*</span>
                 </div>
			</div>
		</div>
		<!-- 第3行 -->
		<div class="form-group">
			<div class="row">
				<label class="col-md-2 control-label" for="name">发布时间:</label>
            	<div class="col-md-4">
					<div class="input-group date form_datetime">
						<input type="text" class="form-control" name="startTime" id="startTimeID" size="16" value="" style="width:100%;">
						<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
					</div>
            	</div>
			</div>
		</div>
                    <!-- 第4行 -->
                    <div class="form-group">
                    	<div class="row">
                    		<label class="col-md-2 control-label" for="name">新闻内容:</label>
                        	<div class="col-md-10">
                            	<textarea class="form-control" name="content" id="contentID" rows="5" value="" placeholder="新闻内容" style="display:none;"></textarea>
                            	<!-- 
                            	<span class="required">*</span>
                            	 -->
                            	 <!-- 加载summernote组件的Div，name和id必须为“summernoteDiv” 20170910pm -->
                            	<div name="summernoteDiv" id="summernoteDiv"></div>
                        	</div>
                    	</div>
                    </div>
                </form>
            </div><!-- End of model-body. -->