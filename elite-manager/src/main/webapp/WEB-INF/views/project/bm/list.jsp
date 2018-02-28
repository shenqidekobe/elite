<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/inc/link.jsp"></c:import>
<title>云英汇 | 系统首页</title>
<link href="${_PATH}/res/css/daterangepicker/bootstrap-datepicker.css"
	rel="stylesheet" type="text/css" />
<link
	href="${_PATH}/res/css/daterangepicker/bootstrap-datepicker.min.css"
	rel="stylesheet" type="text/css" />
<script src="${_PATH}/res/js/datetimepicker/bootstrap-datepicker.js"></script>
<script
	src="${_PATH}/res/js/datetimepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
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
					项目中心 <small>我的股权项目</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">项目中心</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="col-md-12" style="min-width: 950px;">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">我的股权项目</h3>
						</div>
						<form id="listForm" method="POST">
							<div class="box-header">
								<div class="input-group" style="width: 190px; margin-left: 10px; float: left;">
									<input style='display: none' /> <span class="input-group-addon">模糊搜索</span> <input
										type="text" class="form-control" id="keywordId"name="keyword" placeholder="名称、编号">
								</div>
								<div class="input-group" style="width: 220px; float: left;">
									<input style='display: none' /> <span class="input-group-addon">状态</span>
									<select
											class="form-control" id="projectStatus" name="status">
											<option value="">----请选择----</option>
											<c:forEach items="${list}" var="type">
											<c:if test="${type!='audited'}">
											<option value="${type}">${type.label}</option>
											</c:if>
											</c:forEach>
								   </select>
								</div>
								<div style="float: left; margin-left: 0px; width: 230px">
									<div class="input-group" style="width: 230px; margin-left: 10px; float: left;">
										<span class="input-group-addon">创建时间</span> 
										<input type="text"  class="form-control" name="startTime" id="startTimeId"
										placeholder="开始时间">
									</div>
									
								</div>
								<div style="float: left; margin-left:0px; width:200px">
									<div class="input-group" style="width: 190px; margin-left: 10px; float: left;">
									   <input type="text"  class="form-control" name="endTime" id="endTimeId"
										  placeholder="结束时间"> 
									</div>
								</div>
								<div id="initPageth" hidden></div>
								<button type="button" class="btn btn-primary" id="search"
									style="margin-left: 10px; float: left;">搜索</button>
									<shiro:hasPermission name="elite:project:bm:createProjectBtn">  
									<button type="button" class="btn btn-primary" id="create"
									style="margin-right: 100px; float: right;" data="293">新建项目</button></shiro:hasPermission>
							</div>
							<div class="box-body" style="text-align: center;">
								<div id="listData"></div>
							</div>
						</form>
						
					</div>
				</div>
			</section>
		</aside>
		<form id="detailForm" action="" hidden="true" method="post">
			<input type="text" name="id" id="detailFormInputId" />
		</form>
	</div>

	<!-- ./wrapper -->
		<script src="${_PATH}/res/js/plugins/jqueryupload/jquery.ui.widget.js"></script>
	<script src="${_PATH}/res/js/plugins/jqueryupload/jquery.fileupload.js"></script>
	<script src="${_PATH}/res/js/plugins/jqueryupload/webupload.js"></script>
	<script src="${_PATH}/res/js/admin/project/bm/auditproject.js"></script>
	<script src="${_PATH}/res/js/admin/member/company/prefect.js"></script>
	<script src="${_PATH}/res/js/admin/project/bm/prefect.js"></script>
	<script src="${_PATH}/res/js/admin/project/bm/createproject.js"></script>
	<script src="${_PATH}/res/js/admin/project/bm/audit.js"></script>
	<script src="${_PATH}/res/js/admin/project/bm/business.js"></script>
	<script src="${_PATH}/res/js/admin/project/bm/list.js"></script>
	<script src="${_PATH}/res/js/ueditor/ueditor.config.js"></script>
   <script src="${_PATH}/res/js/ueditor/ueditor.all.min.js"></script>

	<div class="modal fade" id="myModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" id="auditData" style="width: 800px;"></div>
	</div>

</body>
</html>