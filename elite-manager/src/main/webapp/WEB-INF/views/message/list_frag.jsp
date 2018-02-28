<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<table class="table table-hover">
  <thead>
  <tr>
    <th>
      <input type="checkbox" id="markAll" />
    </th>
    <th><i class="iconfont">&#xe622;</i></th>
    <th>发件方</th>
    <th>主题</th>
    <th>发送时间</th>
  </tr>
  </thead>
  <tbody id="messageBox">
    <c:forEach items="${list}" var="message">
      <c:set var="readStatus" value="iconfont" />
      <c:if test="${message.read eq false}"><c:set var="readStatus" value="iconfont unread" /></c:if>
      <tr style="cursor: hand" data="${message.id }">
        <th>
           <input type="checkbox" name="messageId" value="${message.id}"/>
        </th>
        <td><i class="${readStatus}">&#xe622;</i></td>
        <td>云英汇小秘书</td>
        <td>${message.title}</td>
        <td><fmt:formatDate value="${message.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>