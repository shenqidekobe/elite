<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/inc/link.jsp"></c:import>
<link rel="stylesheet"
	href="${_PATH}/res/js/jstree/dist/themes/default/style.min.css" />
<title>云英汇 | 系统首页</title>
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
					系统设置 <small>角色管理</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">角色管理</li>
				</ol>
			</section>

			<section class="content">
				<div class="col-md-12" style="min-width: 950px;">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title"></h3>
						</div>
						<!-- /.box-header -->
						<form id="listForm" action="${_PATH}/settings/user/listData">
							<div class="box-header">
								<button type="button" id="create" class="btn btn-primary"
									style="margin-left: 10px; float: left;">+添加角色</button>
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

	</aside>
	<!-- /.right-side -->
	</div>
	<!-- ./wrapper -->
	<jsp:include page="menu_modal.jsp"></jsp:include>
	<script type="text/javascript"
		src="${_PATH}/res/js/jstree/dist/jstree.min.js"></script>
		<script type="text/javascript"
		src="${_PATH}/res/js/admin/settings/role/menu.js"></script> 
	<script type="text/javascript"
		src="${_PATH}/res/js/admin/settings/role/list.js"></script>
	

	<div class="modal fade" id="myModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<form  id="userForm"
				method="post">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<input id="roleId" type="hidden" name="id">

						<h4 class="modal-title" id="myModalLabel">添加角色</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="txt_departmentlevel">角色名称</label> <input type="text"
								name="name" class="form-control" id="roleName"
								placeholder="输入角色名称"> <label for="txt_departmentlevel">角色简介</label>
							<input type="text" name="intro" class="form-control"
								id="roleIntro" placeholder="请输入简介">
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
							</button>
							<button type="submit" id="btn_submit" class="btn btn-primary">
								<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	

</body>
</html>