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
					项目中心 <small>项目方管理</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">人才中心</li>
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
											<td>
												<div style="padding: 5px 10px; text-align: left;">

													<div>
														<p>
															<span>注册时间 ：<fmt:formatDate
																	value="${member.createTime}" pattern="yyyy-MM-dd HH:mm" /></span>
															<span style="margin-left: 20px;">编号:${member.memberNum}</span>
															<span style="text-align: right; margin-left: 50px;">${member.basic.areaName}</span>
														</p>
														<div class="project-logo">
															<img src="${member.basic.memberPhoto.getPath()}" height="105px" width="105px" />
														</div>
														<div class="project-info">
															<div style="margin-bottom: 6px;">
																<span class="project-name">${member.nickName}</span>
																<div class="class-label">项目方</div>
																<div class="class-label">√</div>
																<!-- <div class="class-label">Star</div> -->
															</div>

															<div style="margin-bottom: 6px;">${member.basic.memberSign}</div>
															<div
																style="margin-bottom: 6px; text-align: right; padding-top: 15px;">

															</div>
														</div>
													</div>
												</div>
											</td>
											<%-- <td>
												<div style="margin-top: 25px;">
													<p style="margin-bottom: 6px;">
														<a href="javascript:void(0);" target="_blank" id="detail"
															data="${member.id}">查看详情</a>
													</p>
												</div>
											</td> --%>
											<td>
												<div style="padding-top: 15px;">
													<div style="margin-bottom: 6px; text-align: gray;">进行中项目 &nbsp;${member.projectDoCount}</div>
						                            <div style="margin-bottom: 6px; text-align: gray;">已托管费用 &nbsp; ${member.memberAccount.trustAmount}元</div>
												</div>
											</td>
											<td>
												<div style="margin-top: 25px;" class="opt">
												  <shiro:hasPermission name="elite:project:company:projectCompanyPrefect">
													<p>
														<a style="margin-bottom: 6px;" href="javascript:void(0);"
															id="assist_perfect" data="${member.id}">协助完善项目方资料</a>
													</p>
													</shiro:hasPermission>
													<shiro:hasPermission name="elite:project:company:aduit">
													<p>
													
													 <c:if test="${member.company.status=='waitAduit'}">
												           <a style="margin-bottom: 6px;" href="javascript:void(0);"
															id="audit" data="${member.id}">审核项目方资料</a>
															<input type="button" style="background-color:red;height:12px; width:12px;border:1px;border-radius:6px;"></input>
							                             </c:if>
													</p>
													</shiro:hasPermission>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>

					<div>
						<ul class="nav nav-tabs">
							<li class="active"><a href="#tab_1" data-toggle="tab">基本信息</a></li>
							<li><a href="#tab_2" data-toggle="tab">认证资料 </a></li>


						</ul>
						<div id="myTabContent" class="tab-content">
							<!-- 项目方信息 -->
							<div id="tab_1" class="tab-pane fade in active">
								<div>
									<div class="">
										<label class="title-style3">1. 基本资料</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">编号 : </label> <label
											class="detail-style2">${member.memberNum}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">生日年月 : </label> <label
											class="detail-style2"><fmt:formatDate
												value="${member.basic.birthday}" pattern="yyyy-MM-dd" /></label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">所在城市 : </label> <label
											class="detail-style2">${member.basic.areaName}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">头衔 : </label> <label
											class="detail-style2">${member.basic.memberSign}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">绑定邮箱 : </label> <label
											class="detail-style2">${member.basic.email}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">支付宝账号</label>
										<c:forEach items="${alipays}" var="it">
										<div> <label class="detail-style2">${it.bankCard}</label></div>
										</c:forEach>
											
									</div>
									<div class="tab1-item">
										<label class="title-style2">银行卡号</label> 
										<c:forEach items="${banks}" var="it">
										<div> <label class="detail-style2">${it.bankCard}</label></div>
										</c:forEach>
									</div>
									
									<div class="tab1-item">
										<label class="title-style2">头像 : </label>
										<div>
											<img src="${member.basic.memberPhoto.getPath()}" class="head-icon">
										</div>
									</div>
								</div>
								<div>
									<div class="">
										<label class="title-style3">2. 创业属性</label>
									</div>
									<c:if test="${member.company.companyed=='true'}">
									<div class="tab1-item">
										<label class="title-style2">你的职位 : </label> <label
											class="detail-style2">${member.company.companyPosition}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">公司名称 : </label> <label
											class="detail-style2">${member.company.companyName}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">公司规模 : </label> <label
											class="detail-style2">${member.company.companyScaleVal}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">公司简介 : </label>
										<p>${member.company.companyIntro}</p>
									</div>
									</c:if>
									<c:if test="${member.company.companyed=='false'}">
									<div class="tab1-item">
										<label class="title-style2">你的职位 : </label> <label
											class="detail-style2">${member.company.companyPosition}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">团队人数 : </label> <label
											class="detail-style2">${member.company.teamNumVal}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">团队介绍 : </label> <label
											class="detail-style2">${member.company.teamIntro}</label>
									</div>
									</c:if>
								</div>

							</div>
							<!-- 认证信息 -->
							<div id="tab_2" class="tab-pane fade ">
								<div>
									<div class="">
										<label class="title-style3">1. 征信信息</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">真实姓名 : </label> <label
											class="detail-style2">${member.credit.realName}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">身份证号 : </label> <label
											class="detail-style2">${member.credit.idCard}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">身份证照片 : </label>
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
													<span>营业执照</span>
												</div>
												<div>
													<img src="${member.credit.businessCertPhoto.getPath()}">
												</div>
											</div>
											<div class="papers">
												<div>
													<span>工作证或名片</span>
												</div>
												<div>
													<img src="${member.credit.jobCertPhoto.getPath()}">
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

						</div>
					</div>
					<div style="width: 100%; height: 0px;"></div>
				</div>
			</section>
		</aside>
		<!-- /.right-side -->
	</div>
	<script src="${_PATH}/res/js/plugins/validate/jquery.validate.min.js"></script>
	<script
		src="${_PATH}/res/js/plugins/validate/jquery.validate.messages_cn.js"></script>
	<script src="${_PATH}/res/js/admin/member/company/detail.js"></script>
	<script src="${_PATH}/res/js/admin/member/company/audit.js"></script>
	<script src="${_PATH}/res/js/admin/member/company/prefect.js"></script>
	<!-- ./wrapper -->
	<div class="modal fade" id="myModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" id="auditData"
			style="width: 800px;"></div>
	</div>
</body>
</html>