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
			</h4>
		</div>
		<div class="modal-body">
				<div>用户名：${it.nickName}</div>
				<div>性别 :<c:if test="${it.basic.sex =='F'}">女</c:if><c:if test="${it.basic.sex=='M'}">男</c:if></div>
				<div>编号 :${it.partnerCompany.channelNum}</div>
				<div>手机号码：${it.account}</div>
				<div>所在城市：${it.basic.areaName}</div>
				<div>机构名称：${it.partnerCompany.companyName}</div>
				<div>邮箱：${it.basic.email}</div>
				<div>注册时间：<fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:ss" />
				</div>
				<div>身份类型:${it.partnerCompany.type.label}</div>
				<div>姓名:${it.credit.realName}&nbsp;&nbsp;&nbsp;&nbsp;身份证号:${it.credit.idCard}</div>
				<div>身份证:</div>
				<div><img src="${it.credit.cardJustPhoto.path}" height="105px" width="105px">&nbsp;&nbsp;&nbsp;<img src="${it.credit.cardReversePhoto.path}" height="105px" width="105px"></div>
				<div style="margin-top:15px;">
				<textarea class="form-control" rows="4" placeholder="请输入原因"
					name="auditReason" id="auditReasonId">${member.elite.auditCtoReason}</textarea>
			    </div>
				<input type="text" hidden="true" id="aduitMemberId" value="${it.id}">
				<div style="margin-top:15px;">
				   <button type="button" id="refuseAduit">不通过</button>
			       <button type="button" id="passAduit">通过</button>
		       </div>
			</div>
		</div>
</form>
