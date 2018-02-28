<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<table class="project-table table">
	<thead>
		<tr>
			
			<th>用户名</td>
			<th>姓名</input></th>
			<th>账号</input></th>
			<th>审核时间
			<input type="button" value="升" id="ascBtn"
			<c:if test="${orderType=='asc'}">
			style="background-color: #3c8dbc" 
			</c:if>
			></input >
			<input type="button" value="降" 
			<c:if test="${orderType!='asc'}">
			style="background-color: #3c8dbc" 
			</c:if>
			id="descBtn"></input>
			</th>
			<th>全选<input type="checkbox" id="headSelect" data="left"></input></th>
			<!-- <th>点选此列<input type="checkbox" id="headSelect" data="middle"></input></th>
			<th>点选此列<input type="checkbox" id="headSelect" data="right"></input></th> -->
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="it" varStatus="status">
			<%--  <c:if test="${status.index%3==0}">
			
			 </c:if> --%>
			  <tr>
				
              <td> <label >${it.nickName} </label></td>
              <td> <label >${it.credit.realName} </label></td>
              <td> <label  >${it.account} </label></td>
             <td>  <label >
              <fmt:formatDate value="${it.elite.auditTime}"
						pattern="yyyy-MM-dd" />
               
                </label></td>
            <%--     <c:if test="${status.index%3==0}"> --%>
			<td>	<input style="width: 50px" type="checkbox" id="left" data="${it.id}"></input></td>
			<%-- 	</c:if> --%>
           <%--      <c:if test="${status.index%3==1}">
				<input style="width: 50px" type="checkbox" id="middle" data="${it.id}"></input>
				</c:if>
                <c:if test="${status.index%3==2}">
				<input style="width: 50px" type="checkbox" id="right" data="${it.id}"></input>
				</c:if> --%>
				</th>
				 </tr>
			<%--  <c:if test="${status.index%3==2}">
			
			 </c:if> --%>
		</c:forEach>
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>