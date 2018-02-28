<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<form id="levelForm" method="post">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">重新定级</h4>
		</div>
		<div class="modal-body">
			<div style="margin-left: 1px; list-style-type: none;"
				class="tab-pane fade in active" id="home">
				<input type="text" id="jobsSize" hidden="true" value="${fn:length(jobs)} "/>
				<c:forEach items="${jobs}" var="job" varStatus="status">

					<div class="input-group"
						style="width: 190px; margin-left: 10px; float: left;">
						<span class="input-group-addon">${job.getJobRoleVal()}</span>
						<input
							type="text" class="form-control" name="jobs[${status.index}].level" maxlength="2"
							value="${job.level}">
							<input type="text" name="jobs[${status.index}].id" hidden="true" value="${job.id}"/>
					</div>
				</c:forEach>
			</div>

			<div class="modal-footer">
				<button type="Button" id="levelUpdate" class="btn btn-primary">
					<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>定级
				</button>
			</div>
		</div>
	</div>
</form>
