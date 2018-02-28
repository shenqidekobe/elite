<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/inc/link.jsp"></c:import>
<title>云英汇 | 系统首页</title>
</head>
<link href="${_PATH}/res/css/daterangepicker/bootstrap-datepicker.css" rel="stylesheet"
	type="text/css" />
<link href="${_PATH}/res/css/daterangepicker/bootstrap-datepicker.min.css" rel="stylesheet"
	type="text/css" />

<script src="${_PATH}/res/js/ueditor/ueditor.config.js"></script>
<script src="${_PATH}/res/js/ueditor/ueditor.all.min.js"></script>
<script>
	$(function() {
		ueEditor = UE.getEditor('content', {
			initialFrameWidth : "100%", //初始化选项
			readonly : true
		});
	});
</script>

<!-- 竞标书详情 -->
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
					项目中心 <small>竞标书</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">竞标书</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="box">
					<button type="button" class="btn btn-primary" style="margin: 10px 0 10px 10px;" id="back"
						onclick="javascript:history.back(-1);">返回列表</button>
					<form id="defineForm">
						<input type="text" hidden="true" name="projectId" value="${it.id}" />
						<div class="send-project box-body">
							<label class="title-style1" style="text-align: center;"> << ${it.project.name}竞标书 >></label>
							<script id="content" type="text/plain" style="width: 95%; height: 380px;" name="clause">${it.tender.content}</script>
							<hr>
							<div class="sp-item">
								<label class="title-style2">研发费用 : &nbsp; </label> ${it.amount} &nbsp;元+
								${it.tender.stockRate}% &nbsp;股权
							</div>
							<div class="sp-item">
								<label class="title-style2">研发计划 * </label>
								<table class="sp-table">
									<tr style="text-align: center;">
										<td style="width: 130px;">项目阶段</td>
										<td>阶段时间规划</input></td>
										<td>阶段所需费用 (元)</td>
									</tr>
									<c:forEach items="${it.tenderStages}" var="type" varStatus="order">
										<tr>
											<td style="width: 150px;"><span class="custom-bg circle" id="defineNameId"
												data="input_${order.index}">${type.stageCodeVal}</span></td>
											<td><input type="text" class="short-input2" value="${type.startTime}"></input> <input
												type="text" class="short-input2" value="${type.endTime}"></input></td>
											<td><input type="text" class="short-input2" value="${type.amount}"></input></td>
										</tr>
									</c:forEach>
								</table>
							</div>
							<div class="sp-item">
								<label class="title-style2">您对此项目的优势 : </label>
								<textarea class="form-control textarea-sp" rows="3" readonly>${it.advantage}</textarea>
							</div>
							<div class="sp-item">
								<label class="title-style2">相关附件 : </label>
								<div style="margin-top: 10px;">
									
									<c:if test="${it.attaId!=null}">
									<span style="font-size: 28px;" class="glyphicon glyphicon-file"></span>
									${it.atta.fileName}
												<a href="${it.atta.downPath}" target="_blank">
										<span class="glyphicon glyphicon-download custom-icon"></span>
									</a>
								</c:if>

								</div>
							</div>
						</div>
						<div class="send-project box-body">
							<label class="title-style1" style="text-align: center;"> <small><small>
										竞标CTO信息</small> </small>
							</label>
							<div class="sp-item">
								<label class="title-style2" name="showCTOInfo">基本信息 :>> </label>
								<div style="margin-left:30px;display:none">
								<div style="margin-left: 1px; list-style-type: none;"
									class="tab-pane fade in active" id="home">

									<label class="display:none">身份与技能<span>*</span></label>
								</div>
								<c:forEach items="${member.elite.eliteJobs}" var="jobs">
									<div>${jobs.getJobRoleVal()}:${jobs.jobAdept}</div>
								</c:forEach>
									<label> <c:if test="${member.elite.ctoSigned=='true'}">
					申请
									</c:if> <c:if test="${member.elite.ctoSigned=='false'}">
					未申请参与 
									</c:if>
								</label> <a href="#heh">《云英汇CTO签约计划》</a>
								</li>
								<div>相关工作年限：${member.elite.getJobAgeVal()}</div>
								<div>每周可分配时间：${member.elite.getAllotDurationVal()}</div>
								<div>
									是否在职：
									<c:if test="${member.elite.onjobed=='true'}">在职
									</c:if>
									<c:if test="${member.elite.onjobed=='false'}">离职
									</c:if>
								</div>
								<div>邀请码：${member.inviteCode}</div>
								<div>真实姓名：${member.credit.realName}</div>
								<div>电话：${member.account}</div>
								<div>
									生日年月：
									<fmt:formatDate value="${member.basic.birthday}"
										pattern="yyyy-MM-dd" />
								</div>
								<div>所在城市：${member.basic.areaName}</div>
								<div>邮箱：${member.basic.email}</div>
								<div>头衔：${member.basic.memberSign}</div>
								<label>头像</label>
								<div>
									<img width="50px" height="50px" src="${member.basic.memberPhoto.getPath()}" />
									
								</div>

								<div style="margin-bottom: 6px;" class="common-red">
									关注行业:${member.elite.attentionIndustryListVal}<span></span>
								</div>
								<label class="common-red">项目经历<span>*</span></label>
								<c:forEach items="${member.projects}" var="project">
									<div>项目名称：${project.project}</div>
									<div>担当职位：${project.position}</div>
									<div>
										起止时间：
										<fmt:formatDate value="${project.startTime}"
											pattern="yyyy-MM-dd" />
										——
										<fmt:formatDate value="${project.endTime}"
											pattern="yyyy-MM-dd" />
									</div>
									<p>项目描述：${project.intro}</p>
									<hr>
								</c:forEach>
								<div>
								<label class="common-red">教育经历<span>*</span></label>
								<c:forEach items="${member.educations}" var="education">
									<div>机构名称：${education.organ}</div>
									<div>专业：${education.major}</div>
									<div>学历：${education.education}</div>
									<div>
										起止时间：
										<fmt:formatDate value="${education.startTime}"
											pattern="yyyy-MM-dd" />
										——
										<fmt:formatDate value="${education.endTime}"
											pattern="yyyy-MM-dd" />
									</div>
									<hr>
								</c:forEach>
								<div>
								<label style="margin-bottom: 6px;" class="common-red">附件简历<span>*</span></label>
								
								${member.elite.resumeAtta.fileName}
												<a href="${member.elite.resumeAtta.downPath}" target="_blank">
										<span class="glyphicon glyphicon-download custom-icon"></span>
										</a>
								</div>

							</div>
								</div>
							</div>
							<div class="sp-item">
								<label class="title-style2" name="showCTOInfo">征信信息 :>> </label>
								<div style="display:none">
									<div class="tab1-item">
										<label class="title-style2">身份证号 : </label> <label
											class="detail-style2">${member.credit.idCard}</label>
									</div>
									<div class="tab1-item">
										<div>
											<div class="papers">
												<div>
													<span>身份证正面</span>
												</div>
												<div>
													<img src="${member.credit.cardJustPhoto.getPath()}">
												</div>
											</div>
											<div class="papers">
												<div>
													<span>身份证反面</span>
												</div>
												<div>
													<img src="${member.credit.cardReversePhoto.getPath()}">
												</div>
											</div>
										</div>
										<div style="clear: left;">
											<div class="papers">
												<div>
													<span>工作证</span>
												</div>
												<div>
													<img src="${member.credit.jobCertPhoto.getPath()}">
												</div>
											</div>
											<div class="papers">
												<div>
													<span>名片</span>
												</div>
												<div>
													<img src="${member.credit.visitingCertPhoto.getPath()}">
												</div>
											</div>
										</div>
									</div>
								
							<div style="clear: left;">
								<%-- 	<div class="">
										<label class="title-style2"> 支付宝账号</label>
									</div>
									<div class="tab1-item">
										<label
											class="detail-style2" style="margin-bottom: 0 15px 0 15px;">${member.credit.alipayAccount}</label>
										<span
											style="color: #7BD2DE; font-size: 15px; margin-left: 15px;"></span>
									</div> --%>
								</div>
								<div style="clear: left;">
									<%-- <div class="">
										<label class="title-style2">银行支付账户绑定</label>
									</div>
									<div class="tab1-item">
									
										</label> <label class="detail-style2">${member.credit.bankCard}</label>
									</div> --%>

								</div>
								 </div> 
							</div>
							<!-- <div class="sp-item">
								<label class="title-style2">评价信息 :>> </label>
							</div> -->
						</div>
						<div>
							<div class="sp-item">
								<label class="title-style2"><a href="javascript:void(0)" data="${it.memberId}"
									id="showWorkLogs">CTO工作记录</a></label>
							</div>
						</div>
					</form>


				</div>
				<form id="detailForm" action="" hidden="true" method="post">
					<input type="text" name="id" value="${it.id}" />
				</form>
			</section>
		</aside>
		<!-- /.right-side -->
	</div>
	<script src="${_PATH}/res/js/admin/project/tender/detail.js"></script>
	<!-- ./wrapper -->
	<div class="modal fade" id="workLogsModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" id="auditData" style="width: 800px;"></div>
	</div>
</body>
</html>