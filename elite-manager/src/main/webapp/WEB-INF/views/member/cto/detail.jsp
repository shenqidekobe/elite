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
					人才中心 <small>CTO详情</small>
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
																<div class="class-label">CTO</div>
																<div class="class-label">√</div>
																<!-- <div class="class-label">Star</div> -->
															</div>
															<c:forEach items="${member.elite.eliteJobs}" var="eej">
																<div style="margin-bottom: 6px;width:115px" class="title-label">
																	${eej.getJobRoleVal()}
																	<div class="title-grade"
																		style="background-color: #7FCACB;width:27px">L${eej.level}</div>
																</div>
															</c:forEach>
															<div
																style="margin-bottom: 6px; text-align: right; padding-top: 15px;">
																<div style="margin-bottom: 6px; text-align: right;">
																	<c:if test="${member.elite.taked=='true'}">接活
									                               </c:if>
																	<c:if test="${member.elite.taked=='false'}">不接活
									                               </c:if>
																</div>
																<div style="margin-bottom: 6px; text-align: right;">
																	<span style="text-align: right;" class="index">诚信度:
																		<span style="color: #000; text-align: right;">${member.basic.integrity}</span>
																	</span>
																</div>
															</div>
														</div>
													</div>
												</div>
											</td>
											<td>
												<div style="margin-top: 25px;">
													<div style="margin-bottom: 6px;">${member.elite.status.label}</div>
													<div style="margin-bottom: 6px;">来源：${member.channel.label}</div>
												</div>
											</td>
											<td>
												<div style="padding-top: 15px;">
													<div style="margin-bottom: 6px; text-align: gray;">我的收益${member.memberAccount.totalIncome}
														&nbsp;元</div>
													<!-- <div style="margin-bottom: 6px; text-align: gray;">已接任务数
														&nbsp;</div> -->
												</div>
											</td>
											<td>
												<div style="margin-top: 25px;" class="opt">
												<shiro:hasPermission name="elite:member:cto:aduit">
													<c:choose>
														<c:when test="${member.elite.status == 'waitAduit'}">
															<p>
																<a style="margin-bottom: 6px;"
																	href="javascript:void(0);" id="audit" data="${member.id}">审核</a>
																	<input type="button" style="background-color:red;height:12px; width:12px;border:1px;border-radius:6px;"></input>
															</p>
														</c:when>

													</c:choose>
													</shiro:hasPermission>
													<shiro:hasPermission name="elite:member:cto:viewLevel">
													<p>
														<a style="margin-bottom: 6px;" href="javascript:void(0);"
															id="level" data="${member.id}">重新定级</a>
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
								<div>用户名：${member.nickName}</div>
								<div>手机号：${member.accountOffSuffix}</div>
								<div style="margin-left: 1px; list-style-type: none;"
									class="tab-pane fade in active" id="home">

									<label class="common-red">身份与技能<span>*</span></label>
								</div>
								<c:forEach items="${member.elite.eliteJobs}" var="jobs">
									<div>${jobs.getJobRoleVal()}:
									  <c:forEach items="${jobs.jobAdeptValList}" var="adepts" varStatus="adeptStatus">
									     <c:if test="${adeptStatus.index!=0}">
									     ,
									     </c:if>
									     ${adepts.value}
									  </c:forEach>
									</div>
								</c:forEach>
								<li
									style="list-style: none; margin-top: 5px; margin-bottom: 6px; clear: left;">
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
								<div>
									生日年月：
									<fmt:formatDate value="${member.basic.birthday}"
										pattern="yyyy-MM-dd" />
								</div>
								<div>所在城市：${member.basic.areaName}</div>
								<div>邮箱：${member.basic.email}</div>
								<div>头衔：${member.basic.memberSign}</div>
								<div><label >支付宝账号:</label>
										<c:forEach items="${alipays}" var="it">
										<div> <label class="detail-style2">${it.bankCard}</label></div>
										</c:forEach></div>
									<div>
										<label>银行卡号:</label> 
										<c:forEach items="${banks}" var="it">
										<div> <label class="detail-style2">${it.bankCard}</label></div>
										</c:forEach>
									</div>
								<div>
									<label>自我描述:</label>
								</div>
								<div>
									<label>${member.elite.intro}</label>
								</div>
								<label>头像</label>
								<div>
									<img width="50px" height="50px" src="${member.basic.memberPhoto.path}" />
									
								</div>

								<div style="margin-bottom: 6px;" class="common-red">
									关注行业:<c:forEach items="${member.elite.attentionIndustryListVal}" var="eej">
										${eej}
								</c:forEach><span></span>
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
								<br>
								<label class="common-red">教育经历<span>*</span></label>
								<c:forEach items="${member.educations}" var="education">
									<div>机构名称：${education.organ}</div>
									<div>专业：${education.major}</div>
									<div>学历：${education.educationVal}</div>
									<div>
										毕业时间：
										<fmt:formatDate value="${education.graduateTime}" pattern="yyyy-MM-dd" />
									</div>
									<hr>
								</c:forEach>
								<br>
								<label style="margin-bottom: 6px;" class="common-red">附件简历<span>*</span></label>
								<div>
									${member.elite.resumeAtta.fileName}
									    <c:if test="${member.elite.resumeAttaId!=null }">
												<a href="${member.elite.resumeAtta.downPath}" target="_blank">
										<span class="glyphicon glyphicon-download custom-icon"></span>
										</a>
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
										<label class="title-style2">真实姓名: </label> <label
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
													<span>其他照片</span>
												</div>
												<div>
													<img src="${member.credit.businessCertPhoto.path}">
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
	 <script src="${_PATH}/res/js/admin/member/cto/audit.js"></script>
	<script src="${_PATH}/res/js/admin/member/cto/detail.js"></script>
	<script src="${_PATH}/res/js/admin/member/elite/level.js"></script>
	<!-- ./wrapper -->
	<div class="modal fade" id="myModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" id="auditData"
			style="width: 800px;"></div>
	</div>
</body>
</html>