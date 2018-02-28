<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<form id="createForm" name="createForm">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">广告详情</h4>
		</div>
		<div class="modal-body">
			<div>
				<div class="">
					<label class="title-style2">标题:${it.title} </label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
				</div>
				<div class="">
					<label class="title-style2">营销类型:${it.type.label} </label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
				</div>
				<div class="">
					<label class="title-style2">使用平台:${it.usePlatform} </label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
				</div>
				<div class="">
					<label class="title-style2">链接:${it.href}</label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
				</div>
				<div class="">
					<label class="title-style2">创建者:${it.createName}</label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
				</div>
				<div class="">
					<label class="title-style2">创建时间:<fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
				</div>
				<div class="">
					<label class="title-style2">内容</label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
				</div>
				<script id="content" type="text/plain" style="width: 95%;"
					name="content" id="contentId">${it.content}</script>
		       <div class="">
				</div>
				<label class="title-style2">
				
						<div class="papers" >
							<div>
								<img src="${it.atta.getPath()}" id="logoIdImgId"  >
							</div>
						</div>
						
					 </label>
					  <input type="file" name="file"
						    id="logoIdFile" style="display: none;"> 
							<input type="hidden" name="logoId"  id="logoId">
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>取消
					</button>

				</div>
			</div>
</form>
