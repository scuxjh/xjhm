<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
            <div class="modal-body tab-content" style="padding-left:45px; padding-right:65px;padding-top:0px;display:block;">
		    	<div class="form-group">
                	<div class="row">
                		<label class="col-md-2 control-label" for="name">标题:</label>
                        <div class="col-md-4">
                        	<input type="text" class="form-control" id="titleID" name="title" value="" dataType="Require" placeholder="问卷标题"><span class="required">*</span>
                        	<!-- <input type="hidden" id="idID" name="id" />20160822pm -->
                        </div>
                		<label class="col-md-2 control-label" for="name">分类:</label>
                        <div class="col-md-2">
                            	<div class="input-group" style="width:100%;">
                            		<div class="btn-group select" selectKey="task.TASK_TYPE" id="taskTypeID" ></div>
                            		<input type="hidden" id="taskTypeID" name="taskType" dataType="Require" />
                            		<span class="required">*</span>
                            	</div>
                        </div>
                	</div>
                </div>
                <div class="chapter-group">
				<!-- 该div作为动态获取DOM元素的数据源模板 -->
		    	<div class="chapter-default-template" style="display:none;">
		    		<div class="form-group chapter">
		    			<div class="row" style="cursor:pointer;background-color:#f5f5f5;">
		    				<a class="glyphicon glyphicon-plus" style="padding-top:10px;float:left;" onclick="javascript:addChapter($(this));"></a>
		    				<label class="col-md-2 control-label" for="name">第<mark>0</mark>题:</label>
                    		<div class="col-md-6">
                        		<input type="text" class="form-control" name="question" id="questionID" value="" placeholder="问题名称">
                        		<!-- <span class="required">*</span> -->
                    		</div>
                        	<a class="glyphicon glyphicon-minus" style="padding-top:10px;float:right;" onclick="javascript:minusChapter(this);"></a>
		    			</div>
		    		</div>
		    		<div class="form-group chapterItem">
		    			<div class="row">
		    				<label class="col-md-2 control-label" style="width:10%;" for="name">选项<mark>1</mark>:</label>
		    				<div class="col-md-4">
                        		<input type="text" class="form-control" name="option" value="" placeholder="选项内容">
                    		</div>
                    		<label class="col-md-2 control-label" style="width:10%;" for="name">选项<mark>2</mark>:</label>
		    				<div class="col-md-4">
                        		<input type="text" class="form-control" name="option" value="" placeholder="选项内容">
                    		</div>
		    			</div>
		    			<div class="row">
		    				<label class="col-md-2 control-label" style="width:10%;" for="name">选项<mark>3</mark>:</label>
		    				<div class="col-md-4">
                        		<input type="text" class="form-control" name="option" value="" placeholder="选项内容">
                    		</div>
                    		<label class="col-md-2 control-label" style="width:10%;" for="name">选项<mark>4</mark>:</label>
		    				<div class="col-md-4">
                        		<input type="text" class="form-control" name="option" value="" placeholder="选项内容">
                    		</div>
		    			</div>
		    		</div>
		    	</div><!-- End of chapter-default-template -->
		    	<div class="chapter-default">
		    		<div class="form-group chapter">
		    			<div class="row" style="cursor:pointer;background-color:#f5f5f5;">
		    				<a class="glyphicon glyphicon-plus" style="padding-top:10px;float:left;"></a>
		    				<label class="col-md-2 control-label" for="name">第<mark>1</mark>题:</label>
                    		<div class="col-md-6">
                        		<input type="text" class="form-control" name="question" value="" placeholder="问题名称">
                    		</div>
		    			</div>
		    		</div>
		    		<div class="form-group chapterItem">
		    			<div class="row">
		    				<label class="col-md-2 control-label" style="width:10%;" for="name">选项<mark>1</mark>:</label>
		    				<div class="col-md-4">
                        		<input type="text" class="form-control" name="option" value="" placeholder="选项内容">
                    		</div>
                    		<label class="col-md-2 control-label" style="width:10%;" for="name">选项<mark>2</mark>:</label>
		    				<div class="col-md-4">
                        		<input type="text" class="form-control" name="option" value="" placeholder="选项内容">
                    		</div>
		    			</div>
		    			<div class="row">
		    				<label class="col-md-2 control-label" style="width:10%;" for="name">选项<mark>3</mark>:</label>
		    				<div class="col-md-4">
                        		<input type="text" class="form-control" name="option" value="" placeholder="选项内容">
                    		</div>
                    		<label class="col-md-2 control-label" style="width:10%;" for="name">选项<mark>4</mark>:</label>
		    				<div class="col-md-4">
                        		<input type="text" class="form-control" name="option" value="" placeholder="选项内容">
                    		</div>
		    			</div>
		    		</div>
		    	</div>
		    </div>
		</div>