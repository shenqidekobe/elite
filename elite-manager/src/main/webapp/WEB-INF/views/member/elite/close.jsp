<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<form id="closeForm" method="post">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4 class="modal-title" id="myModalLabel" id="titleId">关停人员</h4>
		</div>
		<div class="modal-body">

			<div style="margin-left: 25px; list-style-type: none;">
				<div id="controltitleId">你将要关停此用户</div>
				<div>请输入原因</div>
				<div>
					<textarea class="form-control" rows="6" placeholder="请输入关停原因"
						name="closeReason" id="closeReasonId">${member.elite.auditCtoReason}</textarea>
				</div>
				<!-- <div id="stoptime" >剩余时间20日</div> -->
				<div>
					<label >关停时长</label><label><input type="text" name="closeTime" id="closeTime"/>日</label> <label><button
							type="button" id="createCloseBtn">提交</button><button type="button" data-dismiss="modal"
				aria-label="Close">取消</button></label>
				</div>
				<input type="text" hidden="true" name="status" id="closeStatusId">
				<input type="text" hidden="true" name="memberId" id="closeId"/>
			</div>
			<br>&nbsp;
            <br>
			<div>关停记录：</div>
			<hr>
			
			<c:forEach items="${list}" var="logs">
			<div>
				<div>
					<label><fmt:formatDate value="${logs.createTime}"
					pattern="yyyy-MM-dd" /></label> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;${logs.userId}${logs.ext1}了此人
					
					<c:choose>
								<c:when test="${logs.status == 'disabled'}">
										${logs.ext2}日
								</c:when>
					</c:choose>
					
					
				
				</div>
				<div>理由:${logs.content}</div>
				<hr>
			</div>
           </c:forEach>

			</div>
		</div>
</form>
