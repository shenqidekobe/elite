<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<table class="table table-bordered" id="tab" >
	<tbody id="tbody">

		<tr style="text-align: center;">
			<th><small>项目名</small></th>
			<th>项目编号</th>
			<th>所处阶段</th>
			<th>意向金</th>
			<th>项目总额</th>
			<th>已收款合计</th>
			<th style="width: 100px">操作</th>
		</tr>
		
    	<c:forEach items="${list}" var="it">
			<tr>
				<th>${it.name}</th>
				<th>${it.projectNum}</th>
				<th>${it.trustStage.stageCodeVal}</th>
				<th>${it.intentionAmount}</th>
				<th>${it.projectDefine.totalAmount}</th>
				<th>
				<c:if test="${it.payedAmount==0}">
				${it.intentionAmount}
				</c:if>
				<c:if test="${it.payedAmount!=0}">
				${it.payedAmount}
				</c:if>
				</th>
				<td data="${it.id}">
					<shiro:hasPermission name="elite:settlement:receivable:viewDetail">
			   <a id="viewDeatil" href="javascript:void(0);">查看详情并操作</a> 
			   </shiro:hasPermission>
				</td>
			</tr>
		</c:forEach> 
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>