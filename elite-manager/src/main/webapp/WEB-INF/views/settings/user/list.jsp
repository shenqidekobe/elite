<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/inc/link.jsp"></c:import>
<link rel="stylesheet" href="${_PATH}/res/css/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<title></title>
</head>
<body class="skin-blue">
	<!-- header logo: style can be found in header.less -->
	<c:import url="/inc/header.jsp"></c:import>
	<div class="wrapper row-offcanvas row-offcanvas-left">
		<!-- Left side column. contains the logo and sidebar -->
		<c:import url="/inc/left.jsp"></c:import>

		<!-- Right side column. Contains the navbar and content of the page -->
		<aside class="right-side">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
			 系统设置 <small>成员管理</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">成员管理</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="col-md-12" style="min-width: 950px;">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">成员管理</h3>
						</div>
						<!-- /.box-header -->
                        <form id="listForm" action="${_PATH}/settings/user/listData" >
							<div class="box-header">
							<input style='display:none' />
								<shiro:hasPermission name="elite:setting:user:add">	
									<button type="button" id="createUser" class="btn btn-primary" style="margin-left: 10px; float: left;" >+添加成员</button>
								</shiro:hasPermission>
								<div class="input-group"
									style="width: 190px; margin-left: 10px; float: left;width: 250px" >
									<span class="input-group-addon">搜索</span> 
									<input type="text" id="searchUserName" class="form-control" placeholder="用户名、姓名、电话" name="keyword">
								</div>
								<div style="float: left; margin-left: 20px; width: 150px">
									<!-- <span >角色: </span> -->
									<select class="form-control" id="searchRole" name="roleId">
										<option value="">----角色选择----</option>
										<c:forEach items="${roleList}" var="role">
											<option value="${role.id}">${role.name}</option>
										</c:forEach>
									</select>
								</div>
								<div style="float: left; margin-left: 20px; width: 150px">
									<!-- <span >角色: </span> -->
									<select class="form-control" id="status" name="status">
										<option value="normal">正常状态</option>
										<option value="disabled">已禁用</option>
									</select>
								</div>
								<button type="button" class="btn btn-primary" style="margin-left: 10px; float: left;" id="search">搜索</button>
							</div>
							<div class="box-body" style="text-align: center;">
							    <div id="listData"></div>
							</div>
					    </form>
				    </div>
					<!-- /.box-body -->

				</div>
				<!-- /.box -->
	</div>
	</section>
	<!--section content-->
	</aside>
	<!-- /.right-side -->
	</div>
	<!-- ./wrapper -->
	<script src="${_PATH}/res/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${_PATH}/res/js/plugins/validate/jquery.validate.messages_cn.js"></script>
	<script src="${_PATH}/res/js/crypto/core-min.js"></script>
	<script src="${_PATH}/res/js/crypto/cipher-core-min.js"></script>
	<script src="${_PATH}/res/js/crypto/enc-base64-min.js"></script>
	<script src="${_PATH}/res/js/crypto/mode-ecb-min.js"></script>
	<script src="${_PATH}/res/js/crypto/tripledes-min.js"></script>
	<script src="${_PATH}/res/js/common/encrypt.js"></script>
	<script type="text/javascript" src="${_PATH}/res/js/ztree/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${_PATH}/res/js/admin/settings/dept/selectDept.js"></script>
    <script src="${_PATH}/res/js/plugins/jqueryupload/jquery.ui.widget.js"></script>
	<script src="${_PATH}/res/js/plugins/jqueryupload/jquery.fileupload.js"></script>
	<script src="${_PATH}/res/js/plugins/jqueryupload/webupload.js"></script>
	<script src="${_PATH}/res/js/admin/settings/user/list.js"></script>
	<div id="menuContent" class="dropdown-menu" style="display:none; position: absolute;">
		<ul id="deptTree" class="ztree" style="margin-top:0; width:160px;"></ul>
	</div>
	
	<div class="modal fade" id="myModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<form action="${_PATH}/settings/user/save" id="userForm" name="userForm" method="post">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<input style='display:none' />
						<input id="sysUserId" type="hidden" name="id">
						<input id="deptId" type="hidden" name="deptId">
						<h4 class="modal-title" id="myModalLabel">成员管理</h4>
						<input id="changePassWord" type="hidden" name="changePassWord">
					</div>
					<div class="modal-body">
						<div class="form-group">
							<div class="col-xs-6">
								<label for="txt_departmentlevel">用户名</label> <input
									type="text" name="loginName" class="form-control"
									id="loginNameId" placeholder="输入用户名">
							</div>
							<div class="col-xs-6">
								<label for="txt_departmentlevel">密码</label> <input
									type="password" name="password" class="form-control"
									id="passWordId" placeholder="输入登录密码">
							</div>
							<div class="col-xs-6">
								<label for="txt_parentdepartment">部门</label> <input
									type="text" name="deptName" class="form-control" id="deptName"
									placeholder="选择部门">
							</div>
							<div class="col-xs-6">
								<label for="txt_departmentlevel">角色</label> <select
									class="form-control" id="roleId" name="roleId">
									<option value="">----请选择角色----</option>
									<c:forEach items="${roleList}" var="role">
										<option value="${role.id}">${role.name}</option>
									</c:forEach>

								</select>
							</div>
							<div class="col-xs-6">
								<label for="txt_departmentname">姓名</label> <input
									type="text" name="userName" class="form-control"id="userNameId"
									placeholder="输入登录姓名">
							</div>
							<div class="col-xs-6">
								<label for="txt_departmentlevel">电话</label> <input
									type="text" name="phone" class="form-control" id="phoneId"
									placeholder="输入电话">
							</div>
							<div class="col-xs-6">
								<label for="txt_departmentlevel">邮箱</label> <input
									type="text" name="email" class="form-control" id="emailId"
									placeholder="输入邮箱">
							</div>
							<div class="col-xs-6">
								<label for="txt_departmentlevel">头像</label>
								<input type="hidden" name="userPhoto" id="userPhoto">
								<input type="file" name="userPhotoFile" id="userPhotoFile" style="display:none;">
								<img style="width:150px;height:60px;" name="userPhotoImg" id="userPhotoImg" onclick="document.userForm.userPhotoFile.click()">
							</div>
							<div class="col-xs-6">
								<label for="txt_departmentlevel"></label>
							</div>
                        
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default"
							data-dismiss="modal">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
						</button>
						<button type="submit" id="btn_submit" class="btn btn-primary">
							<span class="glyphicon glyphicon-floppy-disk"
								aria-hidden="true"></span>保存
						</button>
					</div>
				</div>
				
			</form>
		</div>
	</div>
</body>
</html>