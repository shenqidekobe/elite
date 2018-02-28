<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/inc/link.jsp"></c:import>
<title>云英汇 | 系统首页</title>
</head>
<link href="${_PATH}/res/css/daterangepicker/bootstrap-datepicker.css"
	rel="stylesheet" type="text/css" />
<link
	href="${_PATH}/res/css/daterangepicker/bootstrap-datepicker.min.css"
	rel="stylesheet" type="text/css" />
<script src="${_PATH}/res/js/datetimepicker/bootstrap-datepicker.js"></script>
<script
	src="${_PATH}/res/js/datetimepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
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
					结算中心 <small>提现管理</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">结算中心</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="col-md-12" style="min-width: 950px;">
					<div class="box">

						<form id="listForm" method="POST">


							<div class="box-header">
							<br>
							<div></div>
								<div class="input-group" style="width: 220px; margin-left: 10px; float: left;">
								<span class="input-group-addon">模糊查询</span>
									<input type="text" name="keyword" id="searchUserName" class="form-control"
										placeholder="姓名,编号,财务">
								</div>
								<div style="float: left; margin-left: 0px; width: 230px">
									<div class="input-group" style="width: 230px; margin-left: 10px; float: left;">
										<span class="input-group-addon">申请时间</span> 
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
								<button type="button" class="btn btn-primary" style="margin-left: 0px; float: left;"
									id="search">搜索</button>
							</div>
							<div class="box-body" style="text-align: center;">
								<div id="listData"></div>
							</div>
						</form>
					</div>
				</div>
			</section>
		</aside>
	</div>
	<form id="detailForm" hidden="true" method="post">
		<input type="text" name="id" id="detailFormInputId" />
	</form>
	<!-- ./wrapper -->
	<script src="${_PATH}/res/js/plugins/jqueryupload/jquery.ui.widget.js"></script>
	<script src="${_PATH}/res/js/plugins/jqueryupload/jquery.fileupload.js"></script>
	<script src="${_PATH}/res/js/plugins/jqueryupload/webupload.js"></script>
	<script src="${_PATH}/res/js/admin/settlement/withdraw/invoice.js"></script>
	<script src="${_PATH}/res/js/admin/settlement/withdraw/list.js"></script>
	<div class="modal fade" id="myModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" id="showDate" style="width: 600px;"></div>
	</div>
</body>
</html>