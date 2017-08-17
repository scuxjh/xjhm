<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<div class="modal-body" style="padding-left:45px; padding-right:65px;padding-top:10px;">
	<form class="form-horizontal page_form">
    	<div class="form-group">
        	<div class="row">
            	<label class="col-md-2 control-label" for="name">反馈标题:</label>
                <div class="col-md-6">
                	<input type="text" class="form-control" id="feedTitleID" name="feedTitle" value="" dataType="Require" placeholder="反馈标题"><span class="required">*</span>
                    <input type="hidden" id="idID" name="id" />
				</div>
            </div>
		</div>
		<!-- 第2行 -->
		<div class="form-group">
			<div class="row">
			<label class="col-md-2 control-label" for="name">反馈类别:</label>
                <div class="col-md-4">
					<div class="input-group" style="width:100%;">
                    	<div class="btn-group select" id="categoryIdID" selectKey="feed.CATEGORY" ></div>
                        <input type="hidden" id="categoryIdID" name="categoryId" dataType="Require" />
                        <span class="required">*</span>
                    </div>
                </div>
			<label class="col-md-2 control-label" for="name">发布时间:</label>
            <div class="col-md-4">
				<div class="input-group date form_datetime">
					<input type="text" class="form-control" name="startTime" id="startTimeID" size="16" value="" style="width:100%;">
					<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
				</div>
            </div>
			</div>
		</div>
                    <!-- 第3行 -->
                    <div class="form-group">
                    	<div class="row">
                    		<label class="col-md-2 control-label" for="name">反馈内容:</label>
                        	<div class="col-md-10">
                            	<textarea class="form-control" name="content" id="contentID" rows="5" value="" placeholder="反馈内容" dataType="Require"></textarea>
                            	<span class="required">*</span>
                        	</div>
                    	</div>
                    </div>
                </form>
            </div><!-- End of model-body. -->