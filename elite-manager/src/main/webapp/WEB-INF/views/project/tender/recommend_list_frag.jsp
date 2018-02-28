<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<c:forEach items="${list}" var="it">
	<tr>
		<td>
			<div style="padding: 5px 10px; text-align: left;" id="selctCtoItem" data="${it.id}" text="${it.nickName}" realName="${it.credit.realName}">
				<p>
					<span>${it.nickName}</span><span>&nbsp;${it.credit.realName }</span> <span style="margin-left: 20px;">&nbsp;${it.account}</span> <span style="text-align: right; margin-left: 50px;">${it.basic.areaName}</span>
				</p>
				<hr>
			</div>
		</td>
	</tr>

</c:forEach>