<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<script>
 function back(){
	 window.location.href = ctx + '/settlement/platformInOrder'
 }
</script>
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
					结算中心<small>订单管理详情</small>
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
							<button type="button" class="btn-custom" onclick="back()">返回列表</button>
						</div>
						<div class="box-body" style="text-align: left;">
							<div id="listData">
                                  <div>订单号：${it.orderId}</div>
                                  <div>项目名称：${it.project.name}</div>
                                  <div>项目编号：${it.project.projectNum}</div>
                                  <div>阶段名称：${it.stage.title}</div>
                                  <div>任务名称：${it.task.name }</div>
                                  <div>会员姓名：${it.memberName }</div>
                                  <div>订单状态：${it.status.label}</div>
                                  <div>订单类型：${it.type.label}</div>
                                  <div>订单金额：${it.orderAmount}</div>
                                  <div>待付款金额：${it.payAmount }</div>
                                  <div>实付金额：${it.receiptAmount}</div>
                                  <div>下单时间：<fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
                                  <br/>
                                  <div>支付类型：${it.payType.label }</div>
                                  <div>支付者账号：${it.payAccount }</div>
                                  <div>支付时间：<fmt:formatDate value="${it.payTime}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
                                  <div>第三方交易流水号：${it.thirdSerial}</div>
                                  <div>备注：${it.intro }</div>
                                  <div>发票抬头：${it.invoiceRise }</div>
							</div>
						</div>
					</div>

				</div>
			</section>
		</aside>
		<!-- /.right-side -->
	</div>
</body>
</html>