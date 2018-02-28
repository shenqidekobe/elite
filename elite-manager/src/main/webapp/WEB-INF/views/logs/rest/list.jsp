<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/inc/link.jsp"></c:import>
<title></title>
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
					接口请求日志<small>系统日志管理</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">接口请求日志管理</li>
				</ol>
			</section>

			<!-- Main content -->
			<form id="listForm">
				<section class="content">
					<div class="col-md-12" style="min-width: 950px;">
						<div class="box">
							<!-- /.box-header -->
							<div class="box-header">
								<!-- <button type="button" class="btn btn-primary" style="margin-left: 10px;float: left;
                                    margin-top:10px;">导出</button> -->
								<div class="input-group"
									style="width: 190px; margin-left: 10px; float: left; margin-top: 10px;">
									<span class="input-group-addon">操作人</span> <input type="text"
										class="form-control" placeholder="操作人" name="userName">
								</div>

								<div class="input-group"
									style="width: 250px; margin-left: 10px; float: left; margin-top: 10px;">
									<span class="input-group-addon">起始时间</span> <input type="text"
										class="form-control" placeholder=""
										data-format="dd/MM/yyyy hh:mm:ss" id="startTimeId"
										name="startTime">
								</div>
								<div class="input-group"
									style="width: 250px; margin-left: 10px; float: left; margin-top: 10px;">
									<span class="input-group-addon">结束时间</span> <input type="text"
										class="form-control" placeholder="" id="endTimeId"
										name="endTime">
								</div>
								<button type="button" class="btn btn-primary"
									style="margin-left: 10px; float: left; margin-top: 10px;"
									id="search">搜索</button>
							</div>
							<div class="box-body" style="text-align: center;">
								<div id="listData"></div>

							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>

				</section>
			</form>
			<!--section content-->
		</aside>
		<!-- /.right-side -->
		<!--  -->
	</div>
	<!-- ./wrapper -->
	<script src="${_PATH}/res/js/admin/logs/rest/list.js"></script>
	<div class="modal fade" id="myModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<form id="organForm" method="post">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<input id="organId" type="hidden" name="id">

						<h4 class="modal-title" id="myModalLabel">日志详情</h4>
					</div>
					<div class="modal-body">
						<table class="table table-bordered">
							<tbody>
								<tr style="text-align: center;">
									<th style="width: 15%">操作人</th>
									<th style="width: 65%" id="detailuserId"></th>
								</tr>
								<tr style="text-align: center;">
									<th style="width: 15%">操作类型</th>
									<th style="width: 65%" id="detailoperType"></th>
								</tr>
								
								<tr style="text-align: center;">
									<th style="width: 15%">请求接口</th>
									<th style="width: 65%;word-break:break-all" id="classImpl"></th>
								</tr>
								<tr style="text-align: center;">
									<th style="width: 15%">请求方法</th>
									<th style="width: 65%;word-break:break-all" id="reqMethod"></th>
								</tr>
								<tr style="text-align: center;">
									<th style="width: 15%">请求参数</th>
									<th style="width: 65%;word-break:break-all" id="reqParam"></th>
								</tr>
								<tr style="text-align: center;">
									<th style="width: 15%">返回参数</th>
									<th style="width: 65%;word-break:break-all" id="rsqParam"></th>
								</tr>
								<tr style="text-align: center;">
									<th style="width: 15%">请求IP</th>
									<th style="width: 65%;word-break:break-all" id="detailreqIp"></th>
								</tr>
								<tr style="text-align: center;">
									<th style="width: 15%">操作结果</th>
									<th style="width: 65%;word-break:break-all" id="detailresult"></th>
								</tr>

							</tbody>
						</table>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
						</button>
					</div>
				</div>

			</form>
		</div>
	</div>

</body>
</html>