<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
					人才中心 <small>CTO管理</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">人才中心</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="col-md-12" style="min-width: 950px;">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">CTO管理</h3>
						</div>
						 <form id="listForm" action="${_PATH}/member/cto/listData" >
							<div class="box-header">
							<input style='display:none' />
							    <div class="input-group" style="width: 260px; margin-left: 10px; float: left;">
									<span class="input-group-addon">模糊搜索</span>
									<input type="text" class="form-control" name="keyword" id="keywordId" placeholder="名称、编号，会员账号">
								</div>
									<div class="input-group" style="width: 220px; float: left;">
									<input style='display: none' /> <span class="input-group-addon">状态</span>
									<select
											class="form-control" name="status" id="eliteStatus">
											<option value="">----请选择----</option>
											<c:forEach items="${list}" var="type">
											<option value="${type}">${type.label}</option>
											</c:forEach>
								   </select>
								</div>
								<div class="input-group" style="width: 220px; float: left;">
									<input style='display: none' /> <span class="input-group-addon">身份</span>
									<select
											class="form-control" name="role" id="eliteRole">
											<option value="">----请选择----</option>
											<c:forEach items="${jobs}" var="job">
											<option value="${job.dictKey}">${job.dictVal}</option>
											</c:forEach>
								   </select>
								</div>
								
									<div id="initPageth" hidden>
								</div>
								  <div class="input-group"
									style="width: 150px; float: left;">
								 <input type="text"
										name="inviteCode" id="inviteCodeId" class="form-control" placeholder="邀请码">
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
								<button type="button" class="btn btn-primary" id="search" style="margin-left: 10px; float: left;">搜索</button>
							    <button type="button" class="btn btn-primary" id="inviteCodeBtn"
									style="margin-left: 10px;">发送邀请码</button>
							</div>
							<div class="box-body" style="text-align: center;">
							    <div id="listData"></div>
							</div>
					    </form>
					</div>
			</section>
		</aside>
		<!-- /.right-side -->
		<form id="detailForm" 
			hidden="true" method="post">
			<input type="text" name="id" id="detailFormInputId" />
			<input type="text" name="currentType" id="detailFormCurrentId" />
		</form>
	</div>
	<!-- ./wrapper -->
    <script src="${_PATH}/res/js/admin/member/cto/audit.js"></script>
    <script src="${_PATH}/res/js/admin/member/cto/close.js"></script>
    	<script src="${_PATH}/res/js/admin/member/elite/level.js"></script>
	<script src="${_PATH}/res/js/admin/member/cto/list.js"></script>
    <div class="modal fade" id="myModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" id="auditData"
			style="width: 800px;"></div>
	</div>
	
	<c:import url="/inc/affirm.jsp"></c:import>
</body>
</html>