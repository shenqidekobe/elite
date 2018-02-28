<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>

<!-- 备案人才 -->
	<div id="myTabContent" class="tab-content">
		<div id="tab" class="tab-pane fade in active" style="padding: 10px;">
			<table class="details-table">
				<c:forEach items="${list}" var="it">
				    <tr>
				    	<td colspan="3" align="left">备案时间:<fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
				    </tr>
					<tr>
						<td>
							<div>${it.name}</div>
							<div>${it.memberPhone}</div>
						</td>
						<td>
							<div>身份类型:${it.companyType.label}</div>
							<div>${it.companyName}</div>
						</td>
						<td>
							${it.status.label }
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
</div>
<c:import url="/inc/pager.jsp"></c:import>