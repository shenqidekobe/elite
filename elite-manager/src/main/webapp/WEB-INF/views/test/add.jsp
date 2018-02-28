<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>添加用户</title>
</head>
<body>
	<form action="${_PATH}/user/save" method="post" id="form1">
		<div>
			<ul>
			   <li>电话：<input type="text" name="phone" id="phone" maxlength="11"></li>
			   <li>密码：<input type="password" name="password" id="password" maxlength="16"></li>
			   <li><button type="button" id="save">保存</button></li>
			</ul>
		</div>
	</form>
	<script type="text/javascript" src="${_PATH}/res/js/jquery.min.js"></script>
	<script type="text/javascript" src="${_PATH}/res/js/admin/test/edit.js"></script>
</body>
</html>