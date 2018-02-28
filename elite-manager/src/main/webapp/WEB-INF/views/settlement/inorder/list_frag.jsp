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
			<th>状态</th>
			<th>订单类型</th>
			<th>支付类型</th>
			<th>下单时间</th>
			<th style="width: 100px">操作</th>
		</tr>
		
	<c:forEach items="${list}" var="it">
			<tr>
				<td>${it.orderId}</td>
				<td>${it.project.name}</td>
				<td>${it.memberName}</td>
				<td>${it.orderAmount}</td>
				<td>${it.status.label}</td>
				<td>${it.type.label}</td>
				<td>${it.payType.label}</td>
				<td><fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
				<td data="${it.id}">
			   <a id="viewDeatil" href="javascript:void(0);" >查看详情</a> 
				</td>
			</tr>
		</c:forEach> 
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>