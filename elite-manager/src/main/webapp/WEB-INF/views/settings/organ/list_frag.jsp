<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<table class="table table-bordered" id="tab">
	<tbody id="tbody">
		<tr style="text-align: center;">
		   <th>单位名称</th>
			<th>简介</th>
			<th style="width: 100px">操作</th>
		</tr>
		<c:forEach items="${list}" var="organ">
			<tr>
			<th>${organ.name}</th>
				<th>${organ.intro}</th>
				<td data="${organ.id}">
				<a id="remove" href="javascript:void(0);">删除</a>&nbsp;&nbsp;
				<a id="update" href="javascript:void(0);">修改</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>