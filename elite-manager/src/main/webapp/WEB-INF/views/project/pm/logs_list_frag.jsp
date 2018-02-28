<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>


<label class="title-style2">1、设置服务阶段</label>
<table class="project-table table box-body">
	<tr>
		<td width="100px">时间</td>
		<td><span style="float: left"><fmt:formatDate
					value="${settingTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span></td>
	</tr>
	<tr>
		<td>操作人</td>
		<td><span style="float: left">${settingName}</span></td>
	</tr>
	<tr>
		<td>结果</td>
		<td><span style="float: left"> <c:forEach
					items="${settingStages}" var="stage">
		${stage.title}&nbsp;
		</c:forEach>
		</span></td>
	</tr>
</table>
<div></div>
<label class="title-style2">招标书列表</label>
<br>
<c:forEach items="${list}" var="it" varStatus="status">
	<label class="title-style2">&nbsp;招标书 ${status.index+1}</label>
	<table class="project-table table box-body">
		<tr>
			<td width="100px">时间</td>
			<td><span style="float: left"><fmt:formatDate
						value="${it.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span></td>
		</tr>
		<tr>
			<td>操作人</td>
			<td><span style="float: left">${project.pmName }</span></td>
		</tr>
		<tr>
			<td>详情</td>
			<td><span style="float: left"><a
					href="javascript:void(0);" id="logsViewTender" data="${it.id}">查看招标书</a></span></td>
		</tr>
	</table>
	<label class="title-style2">&nbsp;竞标记录</label>
	<table class="project-table table box-body">
		<tr>
			<td>CTO姓名</td>
			<td>已完成项目数</td>
			<td>运行中项目数</td>
			<td>整体报价（元）</td>
			<td>整体运行时间</td>
			<td>结果</td>
			<td>投标时间</td>
			<td>竞标书</td>
		</tr>
		<c:forEach items="${it.tenders}" var="tender">
			<tr>
				<td>${tender.memberName}</td>
				<td>${tender.projectDoneCount}</td>
				<td>${tender.projectInCount}</td>
				<td>${tender.amount}</td>
				<td>${tender.workingStart}/${tender.workingEnd}共${tender.workingDay}天</td>
				<td>${tender.status.label}</td>
				<td><fmt:formatDate value="${tender.createTime}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><a href='javascript:void(0);' id="logsViewCTOTender"
					data="${tender.id}">查看</a></td>
			</tr>
		</c:forEach>
	</table>
	<c:forEach items="${it.tenders}" var="tender">
		<c:if
			test="${tender.status=='tender_win'||tender.status=='tender_normal'}">
			<label class="title-style2">&nbsp;定标</label>
			<table class="project-table table box-body">
				<tr>
					<td width="200px">时间</td>
					<td><span style="float: left"><fmt:formatDate
								value="${tender.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span></td>
				</tr>
				<tr>
					<td>操作人</td>
					<td><span style="float: left">${project.pmName}</span></td>
				</tr>
				<tr>
					<td>定标CTO及定标书</td>
					<td><span style="float: left">${tender.memberName}&nbsp;<a
							href="javascript:void(0);" id="logsViewDefine"
							data="${project.id}">定标书</a></span></td>
				</tr>
			</table>
		</c:if>
	</c:forEach>
	<hr>
</c:forEach>
<c:import url="/inc/pager.jsp"></c:import>

