<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/inc/link.jsp"></c:import>
<link rel="stylesheet"
	href="${_PATH}/res/css/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
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
					系统设置 <small>数据字典管理</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">数据字典管理</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<!-- MAILBOX BEGIN -->
				<div class="mailbox row">
					<div class="col-xs-12">
						<div class="box box-solid">
							<div class="box-body">
								<div class="row">
									<div class="col-md-3 col-sm-4">
										<!-- BOXES are complex enough to move the .box-header around.
                                                 This is an example of having the box header within the box body -->
										<!-- Navigation - folders-->
										<div style="margin-top: 15px;" >
											<ul class="nav nav-pills nav-stacked">
												<li class="header">字典类型</li>
												<c:forEach items="${typeList}" var="dict">
													<li name="listTypeLiName"><a id="showByType" href="javascript:void(0);"
														data="${dict.dictKey}">${dict.dictDesc}</a></li>
												</c:forEach>
											</ul>
										</div>
									</div>
									<!-- /.col (LEFT) -->
									<form id="listForm">
									<div class="col-md-9 col-sm-8">
										 <div id="serarchHeader">
											<button type="button" class="btn btn-primary"
												style="margin-left: 10px; float: left;" id="create">+添加字典</button>

											<!-- <div class="input-group" hidden="true"
												style="width: 190px; margin-left: 10px; float: left;">
												<span class="input-group-addon">Key</span> <input
													type="text" id="searchUserName" class="form-control"
													placeholder="" name="dictKey">
											</div> -->
											<div class="input-group"
												style="width: 190px; margin-left: 10px; float: left;">
												<span class="input-group-addon">Value</span> <input
													type="text" id="searchUserName" class="form-control"
													placeholder="" name="dictVal">
											</div>
											<input style='display:none' />
											<input id="TypeId" type="hidden" name="dictType" value="">
											<button type="button" class="btn btn-primary"
												style="margin-left: 10px; float: left;" id="searchDict">搜索</button>
											<div class="row pad">
												<div class="col-sm-6"></div>
												<div class="col-sm-6 search-form"></div>
											</div>
										  </div>
										<!-- /.row -->
										<div id="listData"></div>
									</div></form>
									<!-- /.col (RIGHT) -->
								</div>
								<!-- /.row -->
							</div>
							<!-- /.box-body -->
							<div class="box-footer clearfix"></div>
							<!-- box-footer -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col (MAIN) -->
				</div>
				<!-- MAILBOX END -->

			</section>
			<!--section content-->
		</aside>
		<!-- /.right-side -->
	</div>
	<!-- ./wrapper -->
	<script src="${_PATH}/res/js/plugins/validate/jquery.validate.min.js"></script>
	<script
		src="${_PATH}/res/js/plugins/validate/jquery.validate.messages_cn.js"></script>
    <script type="text/javascript" src="${_PATH}/res/js/ztree/jquery.ztree.core.js"></script>
	<script type="text/javascript"
		src="${_PATH}/res/js/jstree/dist/jstree.min.js"></script>
	 <script type="text/javascript" src="${_PATH}/res/js/admin/settings/dict/selectJobs.js"></script>
	<script src="${_PATH}/res/js/admin/settings/dict/list.js"></script>
	<div id="menuContent" class="dropdown-menu"
		style="display: none; position: absolute;">
		<ul id="deptTree" class="ztree" style="margin-top: 0; width: 160px;"></ul>
	</div>

	<div class="modal fade" id="myModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<form id="userForm"
				method="post">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">添加字典</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">

							 <input id="dictId" type="hidden" name="id"> <label
								for="txt_departmentlevel">Key</label> <input type="text"
								name="dictKey" class="form-control" id="dictKeyId"
								placeholder="输入Key">
						</div>
						<div class="form-group">
							<label for="txt_departmentlevel">Val</label> <input type="text"
								name="dictVal" class="form-control" id="dictValId"
								placeholder="dictVal">
						</div>
					<div class="form-group" id="typeDivId" hidden="true">
							<label for="txt_parentdepartment">类型</label> <input type="text"
								name="dictType" class="form-control" id="dictTypeId"
								placeholder="dictType">
						</div> 
						<div class="form-group">
							<label for="txt_departmentname">描述信息</label> <input type="text"
								name="dictDesc" class="form-control" id="dictDescId"
								placeholder="输入描述信息">
						</div>
						<div class="form-group" id="jobDivId">
						    <input id="parentId" type="hidden" name="parentId">
							<label for="txt_parentdepartment">职业</label> <input type="text"
								name="abc" class="form-control" id="parentName" 
								placeholder="职业">
						</div>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
						</button>
						<button type="submit" id="btn_submit" class="btn btn-primary">
							<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
						</button>
					</div>
				</div>

			</form>
		</div>
	</div>
</body>
</html>