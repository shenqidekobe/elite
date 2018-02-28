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
					人才中心 <small>精英管理</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">人才中心</li>
				</ol>
			</section>
			<!-- Main content -->
			<section class="content">
				<div class="col-md-12" style="min-width: 950px;">
					<div class="box">
						<div class="box-header">
							<button type="button" class="btn-custom" onclick='history.go(-1)'>返回列表</button>
							<h3 align="center">给精英方发送邀请码</h3>
						</div>
						<form id="listForm">
							<div class="box-header" align="center">
								<input style='display: none' />
								<!-- 类型 -->
								<%-- <input type="hidden" name="currentType" id="currentType" value="${currentType}"> --%>

								<%-- <div class="input-group"
									style="width: 80px; margin-left: 10px; float: left;">
									<input type="checkbox"   name="currentType" value="cto" id="eliteType" 
									<c:if test="${currentType=='cto'}"> checked </c:if>
									 >
										CTO身份
										</input>
									
								</div> --%>
								<div class="input-group" style="width: 150px; margin-left: 10px; float: left;">
									<%--  <input type="checkbox" id="eliteType"   name="currentType" value="elite"
									<c:if test="${currentType=='elite'}"> checked </c:if>
									 >
										非CTO精英
									</input> --%>
									<input type="hidden" name="currentType" id="currentTypeId" value="${currentType}">
									<button type="button"  data="cto"
									<c:if test="${currentType=='cto'}"> class="btn btn-info"</c:if>
									<c:if test="${currentType=='elite'}"> class="btn btn-default" </c:if>
									 id="eliteType">CTO</button>
									<button type="button" data="elite"
									<c:if test="${currentType=='cto'}"> class="btn btn-default"</c:if>
									<c:if test="${currentType=='elite'}"> class="btn btn-info" </c:if>
									 id="eliteType">精英</button>
								</div>
								<!-- 排序 -->
								<input type="hidden" value="desc" id="auditTimeOrderType" name="orderType">
								<div class="input-group" style="width: 190px; margin-left: 10px; float: left;">
									<input type="text" class="form-control" name="keyword" placeholder="用户名、姓名、手机号 ">
								</div>
								<button type="button" class="btn btn-primary" id="search" style="margin-left: 10px; float: left;">搜索</button>
								<button type="button" class="btn btn-primary" id="sendCodeBtn" style="margin-left: 10px; float: left;">发送</button>
								<label id="showSelectResult"></label> <label style="margin-left: 800px; float: left;"> <select class="form-control" id="addsearchType" name="changePageSize">
										<option value="10">10条</option>
										<option value="20" selected="selected">20条</option>
										<option value="30">30条</option>
										<option value="40">40条</option>
										<option value="50">50条</option>
										<option value="60">60条</option>
										<option value="70">70条</option>
										<option value="80">80条</option>
										<option value="90">90条</option>
										<option value="100">100条</option>
								</select>
								</label>
							</div>

							<div class="box-body" style="text-align: center;">
								<div id="listData"></div>
							</div>
						</form>
					</div>
			</section>
		</aside>
	</div>
	<!-- ./wrapper -->
	<script src="${_PATH}/res/js/admin/member/invitecode/list.js"></script>
	<div class="modal fade" id="myModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" id="auditData" style="width: 800px;"></div>
	</div>
</body>
</html>