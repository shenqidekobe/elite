<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>

<!-- 入驻项目 -->
<table class="details-table">
	<tbody>
	<tr>
	<td>阶段</td>
	<td>创建时间</td>
	<td>发送人</td>
	<td>接收人类型</td>
	<td>附件</td>
	<td>操作</td>
	</tr>
	<c:forEach items="${list}" var="it">
		<tr>
			<td>${it.stage.stageCodeVal}</td>
			<td><fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td><c:if test="${it.uploadMember.nickName==null}">
			      pm
			     </c:if>
			      <c:if test="${it.uploadMember.nickName!=null}">
			      ${it.uploadMember.nickName}
			     </c:if>
			  </td>
			<td>
			   <c:if test="${it.receiveMember.currentType=='company' }">
			        项目方
			   </c:if>
			   <c:if test="${it.receiveMember.currentType!='company' }">
			       CTO
			   </c:if>
			  
			</td>
			<td>${it.atta.fileName}<a href="${it.atta.downPath}" target="_blank">点击下载</a></td>
		    <td data="${it.id}">
		      <c:if test="${it.status=='unpass'}">
			    审核未通过
			  </c:if>
			  <c:if test="${it.status=='pass'}">
			     已发送给 <c:if test="${it.receiveMember.currentType=='company' }">
			           项目方
			   </c:if>
			   <c:if test="${it.receiveMember.currentType!='company' }">
			       CTO
			   </c:if>
			  </c:if>
			  <c:if test="${it.status=='wait_audit'}">
	<%-- 		 <a href="javascript:void(0);" id="audit_material" data="pass">审核,转发给
			 
			  <c:if test="${it.receiveMember.currentType=='company' }">
			           项目方
			   </c:if>
			   <c:if test="${it.receiveMember.currentType=='cto' }">
			       cto
			   </c:if>
			 
			 </a> --%>
			 <a href="javascript:void(0);" id="audit_material" >审核</a>
			  </c:if>
		    </td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>