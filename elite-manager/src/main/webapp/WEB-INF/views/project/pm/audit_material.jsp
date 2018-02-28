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
			<h4 class="modal-title" id="myModalLabel">审核文件</h4>
		</div>
		<div class="modal-body">
			<div>
			   <div class="">
					<label class="title-style2">审核原因：</label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
				</div>
				<textarea class="form-control" rows="4" placeholder="请输入审核原因"
					name="auditReason" id="auditReasonId" ></textarea>
		</div>
		<div class="modal-footer">
		               <input type="hidden" id="auditMaterialId" value="${materialId}"/>
						<button type="button" class="btn btn-default"
							data-dismiss="modal">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
						</button>
						<button type="button" id="audit_material_submit" class="btn btn-primary" data="pass">
							<span class="glyphicon glyphicon-floppy-disk"
								aria-hidden="true"></span>通过
						</button>
						<button type="button" id="audit_material_cannel" class="btn btn-primary" data="unpass">
							<span class="glyphicon glyphicon-floppy-disk"
								aria-hidden="true"></span>不通过
						</button>
					</div>
	</div>
</form>
