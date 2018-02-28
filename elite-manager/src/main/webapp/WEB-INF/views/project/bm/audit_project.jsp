<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<form id="prefectForm">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">审核项目</h4>
		</div>
		<div class="modal-body">
			<div>
			<div class="">
					<label class="title-style2">项目名称:${project.name}</label>
				</div>
				<input type="hidden" id="projectId" value="${project.id}"/>
				<div class="">
					<label class="title-style2">解决方案类型：
					
					<c:forEach items="${project.solutionListVal}" var="types">
									  ${types}
									</c:forEach>
					</label>
				</div>
				<div class="">
					<label class="title-style2">行业分类：
					
					<c:forEach items="${project.industryValList}" var="types">
									  ${types}
									</c:forEach>
					</label>
				</div>
				<div class="">
					<label class="title-style2">功能分类：
					
					<c:forEach items="${project.functionValList}" var="types">
									  ${types}
									</c:forEach>
					</label>
				</div>
				<div class="">
					<label class="title-style2">项目预算:${project.projectBudget}</label>
				</div>
				<div class="">
					<label class="title-style2">期望交付时间：${project.expectTime}</label>
				</div>
				<div class="">
					<label class="title-style2">项目所在城市：${project.areaName}</label>
						
				</div>
				<div class="">
					<label class="title-style2">项目简介：</label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
				</div>
			    <textarea class="form-control" rows="4" placeholder=""
					>${project.intro}</textarea>
			   </div>
			    <hr>
			   <div class="">
					<label class="title-style2">审核原因：</label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
				</div>
				<textarea class="form-control" rows="4" placeholder="请输入审核不同过原因"
					name="intro" id="auditReasonId" ></textarea>
		</div>
		<div class="modal-footer">
						<button type="button" class="btn btn-default"
							data-dismiss="modal">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
						</button>
						<button type="button" id="btn_submitAudit" class="btn btn-primary">
							<span class="glyphicon glyphicon-floppy-disk"
								aria-hidden="true"></span>不通过
						</button>
					</div>
	</div>
</form>
