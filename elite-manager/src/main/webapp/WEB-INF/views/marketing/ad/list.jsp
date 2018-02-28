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
					营销管理<small>广告管理</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">广告管理</li>
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
												<li class="header">类型</li>
												<c:forEach items="${typeList}" var="typelist">
													<li name="listTypeLiName"><a id="showByType" href="javascript:void(0);"
														data="${typelist}">${typelist.label}</a></li>
												</c:forEach>
											</ul>
										</div>
									</div>
									<form id="listForm">
									<div class="col-md-9 col-sm-8">
										 <div id="serarchHeader">
											<button type="button" class="btn btn-primary"
												style="margin-left: 10px; float: left;" id="create">添加</button>

											<div class="input-group"
												style="width: 190px; margin-left: 10px; float: left;">
												 <input
													type="text" id="keyword" class="form-control"
													placeholder="标题，内容，连接" name="keyword">
											</div>
											<input style='display:none'/>
											<input id="typeId" type="hidden" name="type" value=" ${typeList[0]}" >
											<button type="button" class="btn btn-primary"
												style="margin-left: 10px; float: left;" id="search">搜索</button>
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
	
   <script src="${_PATH}/res/js/ueditor/ueditor.config.js"></script>
   <script src="${_PATH}/res/js/ueditor/ueditor.all.min.js"></script>
   	<script src="${_PATH}/res/js/plugins/jqueryupload/jquery.ui.widget.js"></script>
	<script src="${_PATH}/res/js/plugins/jqueryupload/jquery.fileupload.js"></script>
	<script src="${_PATH}/res/js/plugins/jqueryupload/webupload.js"></script>
	<script src="${_PATH}/res/js/admin/marketing/ad/list.js"></script>
		<div class="modal fade" id="myModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" id="showData" style="width: 800px;"></div>
	</div>
</body>
</html>