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
					日志管理 <small>会员日志管理</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">会员日志管理</li>
				</ol>
			</section>

			<!-- Main content -->
			<ul class="nav nav-tabs">
				<li class="active"><a href="#tab_1" data-toggle="tab"
					id="logsSel">操作日志</a></li>
				<li><a href="#tab_2" data-toggle="tab" id="loginLogsSel">登录日志</a></li>
			</ul>
			<form id="listForm">
				<section class="content">
					<div class="col-md-12" style="min-width: 950px;">
						<div class="box">
							<div class="box-header">
								<!-- <button type="button" class="btn btn-primary" style="margin-left: 10px;float: left;
                                    margin-top:10px;">导出</button> -->
								<div class="input-group"
									style="width: 190px; margin-left: 10px; float: left; margin-top: 10px;">
									<span class="input-group-addon">会员名</span> <input type="text"
										class="form-control" placeholder="会员名" name="account">
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
	<script src="${_PATH}/res/js/admin/logs/member/list.js"></script>
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
						<input id="selectType" type="hidden" name="selectType">

						<h4 class="modal-title" id="myModalLabel">日志详情</h4>
					</div>
					<div class="modal-body">
						<table class="table table-bordered">
							<tbody>
								<tr style="text-align: center;">
									<th style="width: 15%">会员名人</th>
									<th style="width: 65%" id="detailMemberName"></th>
								</tr>
								<tr style="text-align: center;">
									<th style="width: 15%">操作类型</th>
									<th style="width: 65%" id="detailOperType"></th>
								</tr>
								<tr style="text-align: center;">
									<th style="width: 15%">操作IP</th>
									<th style="width: 65%;word-break:break-all" id="detailReqIp"></th>
								</tr>
								<tr style="text-align: center;">
									<th style="width: 15%">操作结果</th>
									<th style="width: 65%;word-break:break-all" id="detailResult"></th>
								</tr>

								<tr>
								<tr style="text-align: center;" id="classImpl">
									<th style="width: 15%">请求类</th>
									<th style="width: 65%;word-break:break-all" id="detailclassImpl"></th>
								</tr>
								<tr style="text-align: center;" id="reqMethod">
									<th style="width: 15%">请求方法</th>
									<th style="width: 65%;word-break:break-all" id="detailreqMethod"></th>
								</tr>
								<tr style="text-align: center;" id="rspParam">
									<th style="width: 15%">返回参数</th>
									<th style="width: 65%;word-break:break-all" id="detailrspParam"></th>
								</tr>
								</tr>

								<tr style="text-align: center;">
									<th style="width: 15%">请求参数</th>
									<th style="width: 65%;word-break:break-all" id="detailReqParam"></th>
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