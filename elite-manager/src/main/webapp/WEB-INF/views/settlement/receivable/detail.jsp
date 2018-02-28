<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
					结算中心<small>应收管理详情</small>
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
						<div class="box-header"></div>
						<div class="box-header">
							<button type="button" class="btn-custom" id="back">返回列表</button>
						</div>
						<div class="box-body" style="text-align: center;">
							<div id="listData">

								<table class="project-table table">

									<tbody>
										<tr>
											<td>概况</td>
											<td>收款情况</td>
											<td>分阶段应收和时间</td>
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
																<span class="project-name" style="width: 200px">BM：${it.bmName}</span> <span
																	class="project-name">总项目款：${it.projectDefine.totalAmount}</span>
															</div>
															<div style="margin-bottom: 6px;">
																<span class="project-name">联系电话：${it.bmPhone}</span>
															</div>
														</div>
													</div>
												</div>
											</td>
											<td>
												<div style="margin-top: 15px;">
													<div style="margin-bottom: 6px;">已收款合计：
													<c:if test="${it.payedAmount==0}">
			                                    	${it.intentionAmount}
				                                  </c:if>
				                                    <c:if test="${it.payedAmount!=0}">
				                                    ${it.payedAmount}
				                                    </c:if>
													
													元</div>
													<div style="margin-bottom: 6px;">尚欠款项：${it.projectDefine.totalAmount-it.trustedAmount}元</div>
													<!-- <div style="margin-bottom: 6px;">超期未收：</div> -->
													<div style="margin-bottom: 6px;">已开发票：${invoiceAmount}元</div>
													<div style="margin-bottom: 6px;">未开发票：${it.projectDefine.totalAmount-invoiceAmount}元</div>
												</div>
											</td>
										    <!-- 项目方ID -->
											<td><c:forEach items="${it.projectDefine.stages}" var="stage">
													<div style="margin-top: 15px;">
													    <!-- 线上支付 -->
													    <!-- 只显示支付成功的 -->
													    	<span>${stage.title}&nbsp;
													    	<c:if test="${stage.payWay =='offline'}">
													    	<input type="button" style="background-color:red;height:12px; width:12px;border:1px;border-radius:6px;"></input>
													    	</c:if>
													    	</span>
															<span>${stage.amount}元 &nbsp;&nbsp;&nbsp;</span>
															<span><fmt:formatDate value="${stage.originalStopTime}" pattern="yyyy-MM-dd" />&nbsp;</span>
													    <c:if test="${stage.payWay!='offline'&&stage.payStatus=='success'}">
													   	<shiro:hasPermission name="elite:settlement:receivable:invoice">
														<c:if test="${stage.invoiced=='true'}">
															<span> <a id="viewInvoice" href="javascript:void(0);" data="${stage.id}">已开票</a>&nbsp;
															</span>
														</c:if>
														<c:if test="${stage.invoiced=='false'}">
															<span> <a id="addInvoice" href="javascript:void(0);" data="${stage.id}">开发票</a>&nbsp;
															      <c:if test="${stage.askInvoice=='true'}">
															      <input type="button" style="background-color:red;height:12px; width:12px;border:1px;border-radius:6px;"></input>
															      </c:if>
															</span>
														</c:if>
														</shiro:hasPermission>
														<shiro:hasPermission name="elite:settlement:receivable:inOrder">
														<span> <a id="viewInOrder" href="javascript:void(0);" data="${stage.id}">已收款</a>&nbsp;
																</span>
														</shiro:hasPermission>
													    </c:if>
													    <!-- 线下支付 -->
														<c:if test="${stage.payWay =='offline'}">
													         
														<shiro:hasPermission name="elite:settlement:receivable:inOrder">
															<c:if test="${stage.payStatus!='success'}">
															
																<span><a id="viewConfirmReceipt" href="javascript:void(0);"
																	data="${stage.id}">确认收款</a>&nbsp;</span>
															</c:if>
															</shiro:hasPermission>
															<c:if test="${stage.payStatus=='success'}">
															<c:if test="${stage.invoiced=='true'}">
																<shiro:hasPermission name="elite:settlement:receivable:invoice">
															<span> <a id="viewInvoice" href="javascript:void(0);" data="${stage.id}">已开票</a>&nbsp;
															</span>
															</shiro:hasPermission>
														   </c:if>
														  <c:if test="${stage.invoiced=='false'}">
														  	<shiro:hasPermission name="elite:settlement:receivable:invoice">
															<span> <a id="addInvoice" href="javascript:void(0);" data="${stage.id}">开发票</a>&nbsp;
															</span>
															</shiro:hasPermission>
														    </c:if>
														    <shiro:hasPermission name="elite:settlement:receivable:inOrder">
																<span> <a id="viewInOrder" href="javascript:void(0);" data="${stage.id}">已收款</a>&nbsp;
																</span>
															</shiro:hasPermission>
															</c:if>
														</c:if>
													</div>
												</c:forEach></td>
											<td>
												<shiro:hasPermission name="elite:settlement:receivable:remarks">
												<div style="margin-top: 25px;" class="opt">
													<p>
														<a style="margin-bottom: 6px;" href="javascript:void(0);" id="addRemarks"
															data="${it.id}" type="projectReceivable">添加备注</a>
													</p>
												</div>
												</shiro:hasPermission>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>

				</div>
			</section>
		</aside>
		<!-- /.right-side -->
	</div>
	<!-- ./wrapper -->
	<div class="modal fade" id="myModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" id="showDate" style="width: 800px;"></div>
	</div>
	<script src="${_PATH}/res/js/plugins/jqueryupload/jquery.ui.widget.js"></script>
	<script src="${_PATH}/res/js/plugins/jqueryupload/jquery.fileupload.js"></script>
	<script src="${_PATH}/res/js/plugins/jqueryupload/webupload.js"></script>
	<script src="${_PATH}/res/js/admin/settlement/receivable/remarks.js"></script>
	<script src="${_PATH}/res/js/admin/settlement/receivable/detail.js"></script>
</body>
</html>