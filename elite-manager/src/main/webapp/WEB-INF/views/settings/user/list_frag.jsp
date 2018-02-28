<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<table class="table table-bordered" id="tab">
	<tbody id="tbody">
		<tr style="text-align: center;">
			<th>ID</th>
			<th>用户名</th>
			<th>姓名</th>
			<th>部门</th>
			<th>角色</th>
			<th>创建日期</th>
			<th>上次登录时间</th>
			<th>电话</th>
			<th>邮箱</th>
			<th style="width: 100px">操作</th>
		</tr>
		<c:forEach items="${list}" var="user">
			<tr>
				<th>${user.id}</th>
				<th>${user.loginName}</th>
				<th>${user.userName}</th>
				<th>${user.sysDept.name}</th>
				<th><c:forEach items="${user.roles}" var="roles">${roles.sysRole.name}&nbsp;&nbsp</c:forEach></th>
				<th><fmt:formatDate value="${user.createTime}"
						pattern="yyyy-MM-dd HH:mm:ss" /></th>
				<th><fmt:formatDate value="${user.lastLoginTime}"
						pattern="yyyy-MM-dd HH:mm:ss" /></th>
				<th>${user.phone}</th>
				<th>${user.email}</th>
				<td data="${user.id}">
				<c:if test="${user.status eq 'disabled'}">
					<shiro:hasPermission name="elite:setting:user:enable">
				    	<a id="enable" href="javascript:void(0);">启用</a>&nbsp;&nbsp;
				    </shiro:hasPermission>	
				</c:if>
				<c:if test="${user.status eq 'normal'}">
					<shiro:hasPermission name="elite:setting:user:disable">
				    	<a id="disable" href="javascript:void(0);">禁用</a>&nbsp;&nbsp;
				    </shiro:hasPermission>	
				</c:if>
				<shiro:hasPermission name="elite:setting:user:del">
					<a id="remove" href="javascript:void(0);">删除</a>&nbsp;&nbsp;
				</shiro:hasPermission>
				<shiro:hasPermission name="elite:setting:user:modify">
					<a id="update" href="javascript:void(0);">修改</a></td>
				</shiro:hasPermission>	
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>