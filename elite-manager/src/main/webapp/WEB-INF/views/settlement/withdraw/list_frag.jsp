<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<table class="table table-bordered" id="tab" >
	<tbody id="tbody">

		<tr style="text-align: center;">
			<th><small>姓名</small></th>
			<th>提现编号</th>
			<th>申请时间</th>
			<th>提现金额</th>
			<th>税后金额</th>
			<th>状态</th>
			<th>负责财务</th>
			<th style="width: 100px">操作</th>
		</tr>
		
	<c:forEach items="${list}" var="it">
			<tr>
				<th>${it.memberPassport.credit.realName}</th>
				<th>${it.orderId}</th>
				<th><fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></th>
				<th>${it.amount}</th>
				<th>${it.receiptAmount}</th>
				<th>${it.status.label}</th>
				<th>${it.sysUser.loginName}</th>
				<td data="${it.id}">
			  <%--   <c:if test="${it.status=='wait_process'||it.status=='confirmed'||it.status=='processing'||it.status=='refuse'||it.status=='failure'}">
			      <a id="viewInvoice" href="javascript:void(0);">发票管理</a> 
			    </c:if> --%>
			    <c:if test="${it.status=='wait_process' }">
			  	<shiro:hasPermission name="elite:settlement:withdraw:controll">
			     <a id="viewPay" href="javascript:void(0);" >处理提现<input type="button" style="background-color:red;height:12px; width:12px;border:1px;border-radius:6px;"></input></a> 
			    </shiro:hasPermission>
			    </c:if>
			    <shiro:hasPermission name="elite:settlement:withdraw:viewDetail">
			    <c:if test="${it.status=='confirmed' }">
				  <a id="viewLog" href="javascript:void(0);">财务已确认</a> 
				</c:if>
			    <c:if test="${it.status=='processing' }">
				  <a id="viewLog" href="javascript:void(0);">处理中</a> 
				</c:if>
				<c:if test="${it.status=='refuse' }">
				  <a id="viewLog" href="javascript:void(0);">已拒绝</a> 
				</c:if>
				<c:if test="${it.status=='failure' }">
				  <a id="viewLog" href="javascript:void(0);">提现失败</a> 
				</c:if>
			    <c:if test="${it.status=='success' }">
				  <a id="viewLog" href="javascript:void(0);">提现记录</a> 
				</c:if>
				</shiro:hasPermission>
				
				</td>
			</tr>
		</c:forEach> 
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>