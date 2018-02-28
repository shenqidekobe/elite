<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/inc/link.jsp"></c:import>
<title></title>
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
			 渠道设置 <small>人才渠道管理</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">人才渠道管理</li>
				</ol>
			</section>
			<!-- Main content -->
			<section class="content">
				<div class="col-md-12" style="min-width: 950px;">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">人才渠道</h3>
						</div>
						<!-- /.box-header -->
                        <form id="listForm" >
							<div class="box-header">
								<div class="input-group"
									style="width: 220px; margin-left: 10px; float: left;">
								 <input type="text"
										name="keyword"  class="form-control" id="keywordId" placeholder="用户名/姓名/渠道编号/手机号">
								</div>
								<div style="float: left; margin-left: 0px; width: 150px">
									<div class="input-group"
										style="width: 190px; margin-left: 10px; float: left;">
										<span class="input-group-addon">类别</span>
										<select
											class="form-control" id="searchRole" name="type" >
											<option value="">----请选择----</option>
											<c:forEach items="${list}" var="type">
												<option value="${type}">${type.label}</option>
											</c:forEach>
										</select>
									</div>
								</div>
									<div id="initPageth" hidden>
								    </div>
									<div class="input-group"   style="width: 220px; float: left;margin-left: 50px;">
									<input style='display: none' /> <span class="input-group-addon">状态</span>
									<select
											class="form-control" name="status" id="partnerStatus">
											<option value="">----请选择----</option>
											<c:forEach items="${status}" var="type">
											<option value="${type}">${type.label}</option>
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
								<button type="button" class="btn btn-primary" style="margin-left: 50px; float: left;" id="search">搜索</button>
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
     <form id="detailForm" 
			hidden="true" method="post">
			<input type="text" name="id" id="detailFormInputId" />
		</form>
	<script src="${_PATH}/res/js/admin/channel/elite/aduit.js"></script>
	<script src="${_PATH}/res/js/admin/channel/elite/list.js"></script>
	<script src="${_PATH}/res/js/admin/channel/elite/remarks.js"></script>
	
     <div class="modal fade" id="myModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" id="aduitDate"
			style="width: 800px;"></div>
	</div>
	<div class="modal fade" id="workLogsModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
			
	</div>
	<div id="remarkModel" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;" aria-hidden="true">
		<div id="showDate" class="modal-dialog" role="document" style="width: 800px;">
		</div>
	</div>
	
	<c:import url="/inc/affirm.jsp"></c:import>
</body>
</html>