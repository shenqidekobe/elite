<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<form id="submitForm" method="post">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">
				推荐给CTO：<span id="selectedCTO"></span>
				<input id="selectedCTOId" type="hidden"/>
			</h4>
		</div>
		<div data-spy="scroll" data-target="#navbar-example" data-offset="0"
        style="height:600px;overflow:auto; position: relative;">
		<table class="project-table table" style="margin-left: 15px;margin-right: 15px; width:770">
			<thead>
				<tr>
				 <div class="box-header">
								<div class="input-group"
									style="width: 500px; margin-left: 15px; float: left;">
									<input style='display: none' /><input type="text"
										class="form-control" id="recommendKeywordId" placeholder="名称、昵称、手机号">
								</div>
								<button type="button" class="btn btn-primary" id="recommendSearch"
									style="margin-left: 10px; float: left;">搜索</button>
				</div>
				</tr>
			</thead>
			<input type="hidden" name="projectId"  id="RecommendProjectId" />
			<tbody id="ctolistdata">
			</tbody>
			<div id="ctoIds">
			</div>
		</table>
		</div>
		<div class="modal-footer">
			<button name="btn_sub" type="button" id="submit"class="btn btn-sm btn-info pull-right">
				<i class="ace-icon fa fa-check"></i> 发送
			</button>
			<button class="btn btn-sm btn-danger pull-right" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i> 取消
			</button>
		</div>
		
 
</form>
