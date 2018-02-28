<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<table class="project-table table box-body">
	<thead>
		<tr>
			<th>姓名</th>
			<th>联系方式</th>
			<th>昵称</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="it">
			<tr>
				<td>
				<div style="padding-top: 5px;">
						
							${it.userName}
					</div>
					</td>
				<td>
					<div style="padding-top: 5px;">
						
							${it.phone}
					</div>
				</td>
				<td>
				<div style="padding-top: 5px;">
						
						${it.loginName}
					</div>
				</td>
				<td>
				<div style="padding-top: 5px;">
						
							<a href="javascript:void(0);" target="_blank" id="allot"
								data="${it.id}" value="${it.userName}">分配</a>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>