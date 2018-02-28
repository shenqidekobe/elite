<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<form id="auditForm" method="post">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">审核
			
			
			 <c:choose>
				<c:when test="${member.elite.status == 'normal'}">
				<small>:已经通过审核</small>  
				</c:when>
				<c:when test="${member.elite.status == 'auditNotPass'}">
				<small>:审核未通过</small>  
				</c:when>
			</c:choose>
			
			</h4>
		</div>
		<div class="modal-body">
			<div style="margin-left: 1px; list-style-type: none;"
				class="tab-pane fade in active" id="home">

				<label class="common-red">身份与技能<span>*</span></label>
			</div>
			<c:forEach items="${member.elite.eliteJobs}" var="jobs">
				<div>${jobs.getJobRoleVal()}:${jobs.jobAdept}</div>
			</c:forEach>
				<li style="list-style: none; margin-top: 5px; margin-bottom: 6px; clear: left;">
				<label>
				<c:if test="${member.elite.ctoSigned=='true'}">申请
									</c:if>
									<c:if test="${member.elite.ctoSigned=='false'}">未申请
									</c:if>
				</label> <a
				href="#heh">《云英汇CTO签约计划》</a>
			    </li>
				<div>相关工作年限：${member.elite.getJobAgeVal()}</div>
				<div>每周可分配时间：${member.elite.getAllotDurationVal()}</div>
				 <div>是否在职：<c:if test="${member.elite.onjobed=='true'}">在职
									</c:if>
									<c:if test="${member.elite.onjobed=='false'}">离职
									</c:if>
				</div> 
				<div>邀请码：${member.inviteCode }</div>
				<div>真实姓名：${member.credit.realName}</div>
				<div>
				生日年月：
				<fmt:formatDate value="${member.basic.birthday}"
					pattern="yyyy-MM-dd" />
			</div>
				<div>所在城市：${member.basic.areaName}</div>
				<div>邮箱：${member.basic.email}</div>
				<div>头衔：${member.basic.memberSign}</div>
				<div>
				<textarea class="form-control" rows="4" placeholder="请输入原因"
					name="auditReason" id="auditReasonId">${member.elite.auditCtoReason}</textarea>
			</div>
				<input type="text" hidden="true" name="status" id="auditStatusId">
				<input type="text" hidden="true" name="memberId" id="auditId">

			
					<button type="button" id="refuseAudit">不通过</button>
					<button type="button" id="passAudit">通过</button>
			
			</div>
		</div>


	
</form>
