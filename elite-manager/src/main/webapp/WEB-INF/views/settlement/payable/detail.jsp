<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/inc/link.jsp"></c:import>
<link href="${_PATH}/res/css/daterangepicker/bootstrap-datepicker.css"
	rel="stylesheet" type="text/css" />
<link
	href="${_PATH}/res/css/daterangepicker/bootstrap-datepicker.min.css"
	rel="stylesheet" type="text/css" />
<script src="${_PATH}/res/js/datetimepicker/bootstrap-datepicker.js"></script>
<script
	src="${_PATH}/res/js/datetimepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
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
					结算中心<small>应付管理详情</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">结算中心</li>
				</ol>
			</section>

			<!-- Main content -->
			<form id="listForm">
			<section class="content">
				<div class="col-md-12" style="min-width: 950px;">
					<div class="box">
						<div class="box-header">
							<button type="button" class="btn-custom" id="back">返回列表</button>
						</div>
						<div class="box-body" style="text-align: center;">
							<div id="listData">

								<table class="project-table table">

									<tbody>
										<tr>
											<td>概况</td>
											<td>付款情况</td>
											<td>分阶段应付和时间</td>
											<td>其他操作</td>
										</tr>
										<tr>
											<td>
												<div style="padding: 5px 10px; text-align: left;">

													<div>
														<p>
															<span>项目签约时间 ：<fmt:formatDate value="${it.createTime}"
																	pattern="yyyy-MM-dd HH:mm" />
															</span> <span style="margin-left: 92px;">项目编号：${it.projectNum}</span>
														</p>
														<div class="project-logo">
															<span class="logo-title">${it.firstName}</span> <span class="logo-bottom"></span>
														</div>
														<div class="project-info">
															<div style="margin-bottom: 6px;">
																<span class="project-name" style="width: 200px">${it.name}</span> <span
																	class="project-name">${it.trustStage.stageCodeVal}</span>
															</div>
															<div style="margin-bottom: 6px;">
																<span class="project-name" style="width: 200px">CTO：${it.CTOName}</span> <span
																	class="project-name">总费用：${it.projectDefine.totalAmount}</span>
															</div>
																<div style="margin-bottom: 6px;">
																<span class="project-name">联系电话：${it.ctoPhone}</span>
															</div>
														</div>
													</div>
												</div>
											</td>
											<td>
												<div style="margin-top: 15px;">
													<div style="margin-bottom: 6px;">付款合计：${it.allPayAbleAmount}元</div>
													<div style="margin-bottom: 6px;">尚欠款项：${it.projectDefine.totalAmount-it.trustedAmount}元</div>
												<!-- 	<div style="margin-bottom: 6px;">超期未付：</div> -->
												</div>
											</td>
											<td><c:forEach items="${it.projectDefine.stages}" var="stage">
													<div style="margin-top: 15px;">
														<span>${stage.stageCodeVal}&nbsp;</span> <span>${stage.amount}元
															&nbsp;&nbsp;&nbsp;</span> <span><fmt:formatDate value="${stage.originalStopTime}"
																pattern="yyyy-MM-dd" />&nbsp;</span>
														<c:if test="${stage.status=='quality'||stage.status=='finish'}">
															<span> 系统已经付&nbsp;
															</span>
														</c:if>
														<c:if test="${stage.status!='quality'&&stage.status!='finish'}">
															<span> 未支付&nbsp;
															</span>
														</c:if>
													</div>
												</c:forEach></td>
											<td>
												<div style="margin-top: 25px;" class="opt">
												 <shiro:hasPermission name="elite:settlement:payable:remarks">
													<p>
														<a style="margin-bottom: 6px;" href="javascript:void(0);" id="addRemarks"
															data="${it.id}" type="projectPayable">添加备注</a>
													</p>
													</shiro:hasPermission>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							
							
							</div>
							   
						</div>
						<br>
						          <div> <big> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;详细资金走向</big></div>
								 <div class="box-body" style="text-align: center;">
								 		<div class="box-header">
								<div class="input-group"
									style="width: 250px;  float: left; margin-top: 10px;">
									<span class="input-group-addon">起始时间</span> <input type="text"
										class="form-control" placeholder=""
										data-format="dd/MM/yyyy hh:mm:ss" id="startTimeId"
										name="startTime">
								</div>
								<div class="input-group"
									style="width: 250px; float: left; margin-top: 10px;">
									<span class="input-group-addon">结束时间</span> <input type="text"
										class="form-control" placeholder="" id="endTimeId"
										name="endTime">
								</div>
								<div class="input-group"
									style="width: 190px; float: left; margin-top: 10px;">
									 <input type="text"
										class="form-control" placeholder="用户名/姓名" name="keyword">
								</div>
								<button type="button" class="btn btn-primary"
									style="margin-left: 10px; float: left; margin-top: 10px;"
									id="searchOutOrder">搜索</button>
							</div>
							 	<div id="detailListData"></div>
							 	<input type="hidden" name="id"  value="${it.id}"/>
								</div>
					</div>

				</div>
				</form>
			</section>
		</aside>
		<!-- /.right-side -->
	</div>
	<!-- ./wrapper -->
	<div class="modal fade" id="myModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" id="showDate" style="width: 800px;"></div>
	</div>
	<script src="${_PATH}/res/js/admin/settlement/receivable/remarks.js"></script>
	<script src="${_PATH}/res/js/admin/settlement/payable/detail.js"></script>
</body>
</html>