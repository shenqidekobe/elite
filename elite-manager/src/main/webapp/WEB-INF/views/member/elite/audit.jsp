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
			<h4 class="modal-title" id="myModalLabel">审核</h4>
		</div>
		<div class="modal-body">
			<div style="margin-left: 1px; list-style-type: none;"
				class="tab-pane fade in active" id="home">

				<label class="common-red">身份与技能<span></span></label>
			</div>
			<div style="margin-left: 15px; list-style-type: none;">
					<c:forEach items="${member.elite.eliteJobs}" var="jobs">
									<div>${jobs.getJobRoleVal()}:
									<c:forEach items="${jobs.jobAdeptValList}" var="adepts" varStatus="adeptStatus">
											<c:if test="${adeptStatus.index!=0}">
									     ,
									     </c:if>
									     ${adepts.value}
									  </c:forEach>
									</div>
								</c:forEach>
				    <li
					style="list-style: none; margin-top: 5px; margin-bottom: 6px; clear: left;">
					<label>
					
					<c:if test="${member.elite.ctoSigned=='true'}">
					<span style='color:#FF0000'>申请成为CTO</span>
									</c:if>
					<c:if test="${member.elite.ctoSigned=='false'}">
					未申请参与  <a href="#heh">《云英汇CTO签约计划》</a>
									</c:if>
					</label>
				</li>
				<div>相关工作年限：${member.elite.getJobAgeVal()}</div>
				<div>每周可分配时间：${member.elite.getAllotDurationVal()}</div>
				<div>
					是否在职：
					<c:if test="${member.elite.onjobed=='true'}">是
									</c:if>
					<c:if test="${member.elite.onjobed=='false'}">否
									</c:if>
				</div>
				<div>邀请码：${member.inviteCode}</div>
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
				<input type="text" hidden="true" name="noCto" id="noCto" value="Y">
                <input type="text" hidden="true" name="ctoSigned" value="${member.elite.ctoSigned}" id="ctoSigned"/>
				<c:choose>
					<c:when test="${member.elite.status == 'waitAduit'||member.elite.status == 'auditNotPass'||member.elite.status=='aduitIn'}">
						<button type="button" id="refuseAudit">不通过</button>
						<c:if test="${member.elite.ctoSigned=='false'}">
						 <button type="button" id="passAudit">通过</button>
						</c:if>
						<c:if test="${member.elite.ctoSigned=='true'}">
						    <button type="button" id="passAudit">CTO身份通过</button>
						    <button type="button" id="passEliteAudit">只通过精英身份</button>
						</c:if>
					</c:when>
					<c:when test="${member.elite.status == 'normal'}">
						<c:if test="${member.elite.ctoSigned=='true'}">
						 <button type="button" id="passEliteToCTOAgainAudit">不通过成为CTO</button>
						   <button type="button" id="passAudit">CTO身份通过</button>
						</c:if>
						<c:if test="${member.elite.ctoSigned=='false'}">
						<button type="button" id="refuseAudit">不通过</button>
						<button type="button" id="passAudit">只通过精英身份</button>
						</c:if>
					</c:when>
					<%-- <c:when test="${member.elite.status != 'waitAduit'&&member.elite.status!='normal'&&member.elite.status!='aduitIn'}">
						<button type="button" id="refuseAudit">不通过</button>
						<button type="button" id="passAudit">通过</button>
						<c:if test="${member.elite.ctoSigned=='true'}">
						    <button type="button" id="passEliteAudit">精英通过但不通过CTO</button>
						</c:if>
					</c:when> --%>
				</c:choose>
			</div>
		</div>


	</div>
	</div>
	</div>
</form>
