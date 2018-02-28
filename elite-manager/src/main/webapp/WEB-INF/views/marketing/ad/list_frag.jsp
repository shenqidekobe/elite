<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<table class="table table-bordered" id="tab">
	<tbody id="tbody">
		<tr>
			<th style="width: 60px">序号</th>
			<th style="width: 200px">标题</th>
			<th style="width: 120px">类型</th>
			<th style="width: 100px">平台</th>
			<th style="width: 100px">角色</th>
			<th style="width: 100px">创建者</th>
			<th style="width: 100px">创建时间</th>
			<th style="width: 120px">详情</th>
			<th style="width: 300px">操作</th>
		</tr>
		<c:forEach items="${list}" var="it">
			<tr>
				<td>${it.orders}</td>
				<td>${it.title}</td>
				<td>${it.type.label}</td>
				<td>${it.usePlatform}</td>
				<td>${it.channel.label}</td>
				<td>${it.createName}</td>
				<td><fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><a id="detail" href="javascript:void(0);" data="${it.id}">查看详情</a></td>
				<td data="${it.id}">
				<a id="upmove" href="javascript:void(0);">上移</a>&nbsp;&nbsp;
				<a id="downmove" href="javascript:void(0);">下移</a>&nbsp;&nbsp;
				<a id="remove" href="javascript:void(0);">删除</a>&nbsp;&nbsp;
				<a id="update" href="javascript:void(0);">修改</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>