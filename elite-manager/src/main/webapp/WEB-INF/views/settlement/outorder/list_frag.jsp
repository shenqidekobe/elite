<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<table class="table table-bordered" id="tab" >
	<tbody id="tbody">

		<tr style="text-align: center;">
			<th><small>订单号</small></th>
			<th>项目名称</th>
			<th>用户名</th>
			<th>订单金额(元)</th>
			<th>到账金额(元)</th>
			<th>状态</th>
			<th>订单类型</th>
			<th style="width: 100px">操作</th>
		</tr>
		
	<c:forEach items="${list}" var="it">
			<tr>
				<td>${it.orderId}</td>
				<td>${it.project.name}</td>
				<td>${it.memberPassport.nickName}</td>
				<td>${it.orderAmount}</td>
				<td>${it.receiptAmount}</td>
				<td>${it.status.label}</td>
				<td>${it.type.label}</td>
				<td data="${it.id}">
			   <a id="viewDeatil" href="javascript:void(0);" >查看详情</a> 
				</td>
			</tr>
		</c:forEach> 
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>