<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<table class="table table-bordered" id="tab">
	<tbody id="tbody">
		<tr style="text-align: center;">
			<th>角色名</th>
			<th>角色描述</th>
			<th style="width: 100px">操作</th>
		</tr>
		<c:forEach items="${list}" var="role">
			<tr>
				<th>${role.name}</th>
				<th>${role.intro}</th>
				<td data="${role.id}">
				<shiro:hasPermission name="elite:setting:role:del">	
					<a id="remove" href="javascript:void(0);">删除</a>&nbsp;&nbsp;
				</shiro:hasPermission>	
				<shiro:hasPermission name="elite:setting:role:modify">
					<a id="update" href="javascript:void(0);">修改</a>
				</shiro:hasPermission>	
				<shiro:hasPermission name="elite:setting:role:add">
					 <a id="right-1" href="javascript:void(0);">设置权限</a></td>
				</shiro:hasPermission>	
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>