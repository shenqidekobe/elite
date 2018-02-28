<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>

<!-- 备案项目 -->
<table class="details-table">
	<c:forEach items="${list}" var="it">
	    <tr>
	    	<td>
	    		<c:if test="${it.memberBasic!=null }">${it.memberBasic.areaName}</c:if>
	    		<c:if test="${it.memberBasic==null }">${it.areaName}</c:if>
            </td>
	    	<td align="left">备案时间:<fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
	    	<td><c:if test="${it.memberId!=null }">注册时间:<fmt:formatDate value="${it.memberPassport.createTime}" pattern="yyyy-MM-dd HH:mm" /></c:if>
	    	<td>编号:<c:if test="${it.memberId!=null }">${it.memberPassport.memberNum }</c:if></td>
	    </tr>
		<tr>
			<td>
				<div>
				 	 ${it.memberPassport.credit.realName}
                </div>
				<div><c:if test="${it.memberId!=null}">(${it.memberPassport.nickName})</c:if><c:if test="${it.memberId==null}">(${it.name})</c:if></div>
				<div><c:if test="${it.memberId!=null}">${it.memberPassport.account}</c:if><c:if test="${it.memberId==null}">${it.memberPhone}</c:if></div>
			</td>
			<td colspan="2">
				<div>身份类型:${it.type.label}</div>
				<div>${it.companyName}</div>
			</td>
			<td>
				${it.status.label}
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>