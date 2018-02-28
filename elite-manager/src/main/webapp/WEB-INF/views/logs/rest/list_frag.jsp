<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<table class="table table-bordered" id="tab">
	<tbody id="tbody">
		<tr style="text-align: center;">
			<th style="width: 20%">请求IP</th>
			<th style="width: 20%">操作时间</th>
			<th style="width: 65%">请求接口</th>

		</tr>
		<c:forEach items="${list}" var="log">
			<tr onclick="list.viewDetail(${log.id})">
			     <%-- <th><c:if test="${empty log.member}">[未知]</c:if><c:if test="${not empty log.member}">${log.member.account}</c:if></th> --%>
				 <th>${log.reqIp}</th>
				 <th><fmt:formatDate value="${log.createTime}"
						pattern="yyyy-MM-dd HH:mm:ss" /></th>
				 <th>${log.classImpl}</th>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>