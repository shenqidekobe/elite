<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
					项目中心 <small>我的股权项目</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">分配PM</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="col-md-12" style="min-width: 950px;">
					<div class="box">
					<button type="button" class="btn btn-primary"
							style="margin: 10px 0 10px 10px;" onclick="javascript:history.back(-1);">返回列表</button>
						<div class="box-header">
							<h3 class="box-title">分配PM</h3>
						</div>
						<form id="listForm" method="POST">
							<div class="box-header">
								<div class="input-group"
									style="width: 190px; margin-left: 10px; float: left;">
									<input style='display: none' />
									<input type="hidden" name="projectId" value=
									"${project.id}" id="projectId"/>
									<input type="hidden" name="userRoleType" value=
									"${userRoleType}" id="userRoleTypeId"/>
									 <span
										class="input-group-addon">名称</span> <input type="text"
										class="form-control" name="keyword" placeholder="名称">
								</div>
								<button type="button" class="btn btn-primary" id="search"
									style="margin-left: 10px; float: left;">搜索</button>
									
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label id="show_result">
									<c:if test="${project.pmId!=null }">
									已经分配：${project.pmName}
									</c:if>
									</label>
							</div>
							<div class="box-body" style="text-align: center;">
								<div id="listData"></div>
							</div>
						</form>
						</div>
					</div>
			</section>
		</aside>
		<form id="nextForm" action=""
			hidden="true" method="post">
			<input type="text" name="id" id="detailFormInputId" />
		</form>
		<input type="hidden" id="selectUserId"/>
		<input type="hidden" id="selectUserName" />
	</div>
	
	
	<script src="${_PATH}/res/js/admin/project/bm/allot.js"></script>
	
	
</body>
</html>