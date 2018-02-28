<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<form id="removeForm" method="post">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">取消招标书</h4>
		</div>
		<div class="modal-body">
			<div>
				<div class="">
					<label class="title-style2">取消原因</label>
				</div>
				<div>
				<textarea class="form-control" rows="4" placeholder="请输入原因"
					id="reasonId"></textarea>
			    <input type="hidden" id="removeTenderId" value="${tender.id}"/>
			</div>
			</div>
			<div>
				<button type="button" id="removeTenderBtn">取消标书</button>
		     </div>
		</div>
	</div>
</form>
