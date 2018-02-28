<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>用户列表</title>
	<script type="text/javascript" src="${_PATH}/res/js/jquery.min.js"></script>
	<script type="text/javascript" src="${_PATH}/res/js/admin/test/list.js"></script>
</head>
<body>
<button type="button" id="add">添加</button>
<div>
<ul>
<c:forEach items="${list}" var="us">
  <li>${us.phone}---${us.password}</li>
</c:forEach>
</ul>
</div>
</body>
</html>