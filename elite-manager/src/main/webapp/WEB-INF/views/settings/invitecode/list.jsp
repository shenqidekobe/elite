<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/inc/link.jsp"></c:import>
<link rel="stylesheet" href="${_PATH}/res/css/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
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
			 系统设置 <small>邀请码管理</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">邀请码管理</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="col-md-12" style="min-width: 950px;">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">邀请码管理</h3>
						</div>
						<!-- /.box-header -->
                        <form id="listForm" >
							<div class="box-header">
							<input style='display:none' />
								<div class="input-group"
									style="width: 250px; margin-left: 10px; float: left;">
									<input type="text" id="searchUserName" class="form-control" placeholder="邀请码，用户名" name="keyword">
								</div>
								<div style="float: left; margin-left: 20px; width: 180px">
									<!-- <span >角色: </span> -->
									<select class="form-control" id="searchType" name="type">
										<option value="">----请选择----</option>
										<c:forEach items="${types}" var="type">
											<option value="${type}">${type.label}</option>
										</c:forEach>
									</select>
								</div>
								<button type="button" class="btn btn-primary" style="margin-left: 10px; float: left;" id="search">搜索</button>
								<shiro:hasPermission name="elite:setting:invitecode:export">
									<button type="button" class="btn btn-primary" style="margin-left: 10px; float: left;" id="downExcelBtn">导出Excel文档</button>
								</shiro:hasPermission>	
								<shiro:hasPermission name="elite:setting:invitecode:add">
									<button type="button" class="btn btn-primary" style="margin-left: 10px; float: left;" id="addInviteCode">生成邀请码</button>
								</shiro:hasPermission>	
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
	</div>
	 <script type="text/javascript" src="${_PATH}/res/js/admin/settings/invitecode/list.js"></script>
	 <script type="text/javascript" src="${_PATH}/res/js/plugins/jqueryTableExpors/jquery.base64.js"></script>
	 <script type="text/javascript" src="${_PATH}/res/js/plugins/jqueryTableExpors/tableExport.js"></script> 
     <div class="modal fade" id="myModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" id="showData" style="width: 800px;"></div>
	</div>
	 
</body>
</html>