<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/inc/link.jsp"></c:import>
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
					结算中心 <small>出账管理</small>
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
						<div class="box-header">
							<h3 class="box-title">出账管理</h3>
						</div>
						<form id="listForm" method="POST">
						  <div style="float: left; margin-left: 0px; width: 205px">
									<div class="input-group"
										style="width: 190px; margin-left: 15px; float: left;">
										<input type="text"
										name="keyword" id="keyword" class="form-control" placeholder="订单号，项目名称，用户名">
									</div>
								</div>
								<div class="input-group" style="width: 200px; float: left;">
									<input style='display: none' /> <span class="input-group-addon">状态</span>
									<select
											class="form-control"  id="status" name="status">
											<option value="">----请选择----</option>
											<c:forEach items="${status}" var="type">
											<option value="${type}">${type.label}</option>
											</c:forEach>
								   </select>
								</div><div class="input-group" style="width: 200px; float: left;">
									<input style='display: none' /> <span class="input-group-addon">订单类型</span>
									<select
											class="form-control" id="type"  name="type">
											<option value="">----请选择----</option>
											<c:forEach items="${type}" var="type">
											<option value="${type}">${type.label}</option>
											</c:forEach>
								   </select>
								</div>
								<div id="initPageth" hidden>
								</div>
								<button type="button" class="btn btn-primary"
									style="margin-left: 55px; float: left;" id="search">搜索</button>
									<input style='display:none' /> 
						    <br>
						    <br>
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
	<script src="${_PATH}/res/js/admin/settlement/outorder/list.js"></script>
</body>
</html>