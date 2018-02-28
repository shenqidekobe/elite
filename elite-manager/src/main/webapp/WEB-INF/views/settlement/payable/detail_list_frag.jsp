<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<table class="table table-bordered" id="tab" >
	<tbody id="tbody">

		<tr style="text-align: center;">
			<th><small>付款时间</small></th>
			<th>用户名</th>
			<th>姓名</th>
			<th>身份</th>
			<th>税前金额（元）</th>
			<th>税后金额（元）</th>
			<th>付款类型</th>
			<th>项目或者任务名称</th>
		</tr>
		
	<c:forEach items="${list}" var="it">
			<tr>
			<td><fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd" /></td>
				<td>${it.memberPassport.nickName}</td>
				<td>${it.credit.realName}</td>
				<td>
				     <c:if test="${it.memberPassport.currentType=='elite'}">
				                 精英
				     </c:if>
				      <c:if test="${it.memberPassport.currentType=='cto'}">
				       CTO
				     </c:if>
				      <c:if test="${it.memberPassport.currentType=='partnerElite'}">
				                 人才渠道方
				     </c:if>
				      <c:if test="${it.memberPassport.currentType=='partnerCompany'}">
				                  项目渠道方
				     </c:if>
				</td>
				<td>${it.orderAmount}</td>
				<td>${it.receiptAmount}</td>
				<td>${it.type.label}</td>
				<td>${it.project.name}
				<c:if test="${it.task.name!=null}">
				,
				</c:if>
				${it.task.name}</td>
				
			</tr>
		</c:forEach> 
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>
