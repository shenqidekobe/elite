<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
 <table  class="table table-bordered">  
 
            <thead>  
               	<tr style="text-align: center;">
			<th>邀请码</th>
			<th>类型</th>
			<th>所属用户</th>
			<th>邀请数量</th>
			<th>最大邀请数量</th>
			<th>连接</th>
			<th>创建日期</th>
			<th>过期日期</th>
		      </tr>
            </thead>  
           <tbody>  
           <c:forEach items="${list}" var="it">
              <tr>
				<td>${it.inviteCode}</td>
				<td>${it.type.label}</td>
				<td>${it.memberPassport.nickName}</td>
				<td>${it.inviteCount}</td>
				<td>${it.maxCount}</td>
				<td>${it.href}</td>
				<td><fmt:formatDate value="${it.createTime}"
						pattern="yyyy年MM月dd" /></td>
				<td><fmt:formatDate value="${it.expireTime}"
						pattern="yyyy年MM月dd " /></td>
				
			</tr>
            </c:forEach>    
            </tbody>  
        </table>
<c:import url="/inc/pager.jsp"></c:import>