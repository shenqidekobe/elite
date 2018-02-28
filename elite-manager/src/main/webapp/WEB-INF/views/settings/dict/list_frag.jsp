<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<table class="table table-bordered" id="tab">
	<tbody id="tbody">
		<tr>
			<th style="width: 60px">序号</th>
			<th style="width: 120px">Key</th>
			<th style="width: 240px">Value</th>
			<th style="width: 120px">类型</th>
			<th style="width: 300px">描述信息</th>
			<th style="width: 300px">操作</th>
		</tr>
		<c:forEach items="${list}" var="dict">
			<tr>
				<th>${dict.orders}</th>
				<th>${dict.dictKey}</th>
				<th>${dict.dictVal}</th>
				<th>${dict.dictType}</th>
				<th>${dict.dictDesc}</th>
				<td data="${dict.id}">
				<a id="upmove" href="javascript:void(0);">上移</a>&nbsp;&nbsp;
				<a id="downmove" href="javascript:void(0);">下移</a>&nbsp;&nbsp;
				<a id="remove" href="javascript:void(0);">删除</a>&nbsp;&nbsp;
				<a id="update" href="javascript:void(0);">修改</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>