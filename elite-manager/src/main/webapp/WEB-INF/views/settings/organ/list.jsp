<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/inc/link.jsp"></c:import>
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
			 系统设置 <small>单位管理</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">单位管理</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="col-md-12" style="min-width: 950px;">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">单位管理</h3>
						</div>
						<!-- /.box-header -->
                        <form id="listForm" action="${_PATH}/settings/user/listData" >
							<div class="box-header">
						         <input id="status" type="hidden" name="status" value="normal">
								<button type="button" id="createUser" class="btn btn-primary" style="margin-left: 10px; float: left;" >+添加单位</button>
								<div class="input-group"
									style="width: 190px; margin-left: 10px; float: left;">
									<span class="input-group-addon">单位名称</span> 
									<input type="text" id="searchUserName" class="form-control" placeholder="" name="name">
								</div>
							<div style="float: left; margin-left: 20px; width: 150px">
									<!-- <span >角色: </span> -->
								<%-- 	<select class="form-control" id="searchRole" name="roleId">
										<option value="">----请选择状态----</option>
										<c:forEach items="${roleList}" var="role">
											<option value="${role.id}">${role.name}</option>
										</c:forEach>
									</select> --%>
								</div>
								<button type="button" class="btn btn-primary" style="margin-left: 10px; float: left;" id="search">搜索</button>
							<div>
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
	<script src="${_PATH}/res/js/admin/settings/organ/list.js"></script>
	
	<div class="modal fade" id="myModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<form  id="organForm" method="post">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<input id="organId" type="hidden" name="id">
						
						<h4 class="modal-title" id="myModalLabel">添加单位</h4>
					</div>
					<div class="modal-body">
						  <div class="form-group">
							           <label for="txt_departmentlevel">单位名称</label> <input
									type="text" name="name" class="form-control" id="organName"
									placeholder="输入单位名称">
							      <label for="txt_departmentlevel">单位简介</label> <input
									type="text" name="intro" class="form-control" id="organIntro"
									placeholder="请输入简介">
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