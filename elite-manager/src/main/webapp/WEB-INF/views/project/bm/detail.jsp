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
					我的股权项目 <small>我的股权项目详情</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i>
							首页</a></li>
					<li class="active">项目中心</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="col-md-12" style="min-width: 950px;">
					<div class="box">

						<button type="button" class="btn btn-primary"
							style="margin: 10px 0 10px 10px;" id="back">返回列表</button>

						<table class="project-table table box-body">
							<tbody>
								<tr>
									<td>
										<div style="padding: 5px 10px; text-align: left;">
											<p class="time-code">
												<span>项目创建时间 &nbsp;<fmt:formatDate
														value="${it.createTime}" pattern="yyyy-MM-dd HH:mm" /></span> <span
													style="margin-left: 20px;">项目编号:&nbsp;${it.projectNum}</span>
											</p>
											<div>
												<div class="project-logo">
													<span class="logo-title">${it.firstName}</span>
												</div>
												<div class="project-info">
													<div>
														<span class="project-name">${it.name}</span>
														<c:forEach items="${it.getIndustryValList()}"
															var="industrys">
															<div class="class-label">${industrys}</div>
														</c:forEach>
													</div>
													<div>
														<span class="index">开发类型:</span>&nbsp;
														<c:forEach items="${it.getSolutionListVal()}" var="types">
															<span class="value">${types}</span>
														</c:forEach>
													</div>
													<div>
														<c:if test="${it.status=='pass_wait'||it.status=='wait_audit'||it.status=='audit_in'||it.status=='unpass'||it.status=='invalid'}">
									<span class="index">预算:</span>&nbsp;<span class="value">
									${it.projectBudget}&nbsp;
									</span>
									</c:if>
									<c:if test="${it.status=='pass_already'||it.status=='stage_course'||it.status=='quality'}">
									<span class="index">费用:</span>&nbsp;<span class="value">
									${it.totalAmount}&nbsp;
									</span>
									</c:if>
													</div>
													<div>
														<span class="index">期望交付日期:</span>&nbsp;<span
															class="value"><fmt:formatDate
																value="${it.expectTime}" pattern="yyyy-MM-dd" /></span>
													</div>
													<div>
														<span class="index">共<span style="color: #000;"><span
																style="color: #000;">${it.deliveryDay} </span></span>个工作日
														</span>
													</div>
													<div>
														<span class="index"> 联系人:${it.contactName}
															${it.contactPhone} </span>
													</div>
												</div>
											</div>
										</div>
									</td>
									<c:if
										test="${it.status!='wait_audit'&&it.status!='audit_in'&&it.status!='unpass'&&it.status!='invalid'}">
										<td>
										<c:if test="${it.trustedAmount==0 }">
					   <div class="deposit">
						已交意向金：${it.intentionAmount} 元
					  </div>
							</c:if>
											<div class="deposit">
												已托管费用合计：
												<c:if test="${it.trustedAmount==0 }">
						                       ${it.intentionAmount} 元
						                   </c:if>
												<c:if test="${it.trustedAmount!=0 }">
						                      ${it.trustedAmount} 元
						                   </c:if>


											</div>
											<div class="deposit-item">
												本阶段已托管费用：
												<c:if test="${it.trustStage.trusted=='true'}">
					                          ${it.trustStage.amount}
					                       </c:if>
												<c:if test="${it.trustStage.trusted=='false'}">
					                             0
					                             </c:if>
					                                                                                    元
											</div>
											<div class="deposit-item">

												股权托管：
												<c:if test="${it.isStock=='true'}">
						                           有
						      </c:if>
												<c:if test="${it.isStock=='false'}">
					          	     无
						      </c:if>
											</div>
										</td>
									</c:if>
									<td>
										<div style="padding-top: 5px;">
											<p style="font-size: 14px; color: #2CB7C9;">
												<c:if test="${it.status!='stage_course'}">
						                        ${it.status.label}
						                       </c:if>
												<c:if test="${it.status=='stage_course'}">
					                          	${it.forStage.title}
						                      </c:if>

											</p>
											<p style="font-size: 12px;"></p>
											<p style="font-size: 12px; color: #9B9B9B;">PM:&nbsp;${it.pmName}</p>
											<p style="font-size: 12px; color: #9B9B9B;">BM:&nbsp;${it.bmName}</p>
										</div>
									</td>
									<td>
										<div class="opt">
									  	<c:if test="${it.status!='invalid'}">
										 <shiro:hasPermission name="elite:project:bm:projectPrefect"> 
											<p>
												<a style="margin-bottom: 6px;" href="javascript:void(0);"
													id="assist_perfect" data="${it.id}">协助完善项目资料</a>
											</p>
											</shiro:hasPermission>
											<shiro:hasPermission name="elite:project:bm:projectCompanyPrefect">
											<p>
												<a style="margin-bottom: 6px;" href="javascript:void(0);"
													id="assistelite_prefect" data="${it.companyId}">协助完善项目方资料</a>
											</p>
											</shiro:hasPermission>
											<shiro:hasPermission name="elite:project:bm:aduitCompany">
											<p>
												<c:if test="${it.auditedMemberCompany=='waitAduit'}">
													<a href="javascript:void(0);" id="audit_company"
														data="${it.companyId}">审核项目方资料</a>
												</c:if>
											</p>
											</shiro:hasPermission>
											<c:if test="${it.status!='unpass'}">
												<p></p>
												<shiro:hasPermission name="elite:project:bm:assignPM">
												<p>
													<c:if test="${it.status!='wait_audit'}">
														<a href="javascript:void(0);" id="allot_pm"
															data="${it.id }">分配PM</a>
													</c:if>
												</p>
												</shiro:hasPermission>
												<shiro:hasPermission name="elite:project:bm:addBussines">
												<p>
													<a href="javascript:void(0);" id="add_business"
														data="${it.id}">添加商务记录</a>
												</p>
												</shiro:hasPermission>
												<p>
											     	<shiro:hasPermission name="elite:project:bm:findDefine">
													<c:if test="${it.tendered=='true'}">
														<a href="javascript:void(0);" id="search_define"
															data="${it.id}">查看立项书</a>
													</c:if>
													</shiro:hasPermission>
													 <shiro:hasPermission name="elite:project:bm:addDefine">
													<c:if test="${it.intentionStatus=='success'}">
														<c:if test="${it.tendered=='false'}">
																<c:if test="${fn:length(it.projectSettingStages) >0 }">
							                                   	<a href="javascript:void(0);" id="add_define" data="${it.id}">发立项书</a>
						                                   </c:if>
														</c:if>
													</c:if>
													</shiro:hasPermission>
												</p>
											</c:if>
                                           </c:if>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
						<div>
							<ul class="nav nav-tabs">
								<li class="active"><a href="#tab_1" data-toggle="tab">
										项目详情 </a></li>
								<li><a href="#tab_2" data-toggle="tab"> 项目方详情</a></li>
								<!-- <li><a href="#tab_3" data-toggle="tab"> 操作记录</a></li> -->

							</ul>
							<div id="myTabContent" class="tab-content">
								<!-- 项目详情 -->
								<div id="tab_1" class="tab-pane fade in active">
									<div class="tab1-item">
										<label class="title-style2">项目编号 : </label> <label
											class="detail-style2">${it.projectNum}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">项目名称 : </label> <label
											class="detail-style2">${it.name}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">解决方案类型 : </label> <label
											class="detail-style2"> <c:forEach
												items="${it.getSolutionListVal()}" var="types">
												<span class="value">${types}</span>
											</c:forEach>

										</label>
									</div>
									<div class="tab1-item">
									
												<c:if test="${it.status=='wait_audit'||it.status=='audit_in'||it.status=='unpass'||it.status=='invalid'}">
									<label class="title-style2">预算 : </label> <label
											class="detail-style2">
									${it.projectBudget}&nbsp;
									</c:if>
									<c:if test="${it.status=='pass_wait'||it.status=='pass_already'||it.status=='stage_course'||it.status=='quality'}">
									<label class="title-style2">费用 : </label> <label
											class="detail-style2">
									${it.totalAmount}&nbsp;
									</c:if>
											</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">项目简介 : </label>
										<p>${it.intro}</p>
									</div>
									<div class="tab1-item">
										<label class="title-style2">其他描述类文档 : </label>
										<div style="margin-top: 10px;">
											<c:forEach items="${it.attas}" var="fileup">
												<span style="font-size: 25px;"
													class="glyphicon glyphicon-file"></span> ${fileup.atta.fileName}
												<a href="${fileup.atta.downPath}" target="_blank">点击下载</a>
											</c:forEach>
										</div>
									</div>
									<div class="tab1-item">
										<label class="title-style2">推荐人相关信息 : </label> <label
											class="detail-style2">${it.recommendUser}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">期望交付时间 : </label> <label
											class="detail-style2">${it.expectTime}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">项目紧急联系人 : </label> <label
											class="detail-style2">${it.contactName}
											${it.contactPhone}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">参考项目 : </label> <label
											class="detail-style2">${it.referProject}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">所在城市 : </label> <label
											class="detail-style2">${it.areaName}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">行业分类 : </label> <label
											class="detail-style2"> <c:forEach
												items="${it.getIndustryValList()}" var="types">
												<span class="value">${types}</span>
											</c:forEach>

										</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">功能类型 : </label> <label
											class="detail-style2"> <c:forEach
												items="${it.getFunctionValList()}" var="types">
												<span class="value">${types}</span>
											</c:forEach>

										</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">是否参与云英汇《明星开发团队持股开发计划》 : </label>
										<c:if test="${it.planed=='true'}">
											<label class="detail-style2">是</label>
										</c:if>
										<c:if test="${it.planed=='false'}">
											<label class="detail-style2">否</label>
										</c:if>
									</div>
									<c:if test="${it.planed=='true'}">
										<div class="tab1-item">
											<label class="title-style2">商务计划书 : <c:if
													test="${it.planBook!=null}"> 
									          ${it.planBookAtta.fileName}
												<a href="${it.planBookAtta.downPath}" target="_blank">点击下载</a>
												</c:if>
											</label>
										</div>
									</c:if>
								</div>
								<!-- 项目方信息 -->
								<div id="tab_2" class="tab-pane fade">
									<div>
										<div class="">
											<label class="title-style3">1. 基本资料</label>
										</div>
										<div class="tab1-item">
											<label class="title-style2">真实姓名 : </label> <label
												class="detail-style2">${member.credit.realName}</label>
										</div>
										<div class="tab1-item">
											<label class="title-style2">手机号码 : </label> <label
												class="detail-style2">${member.account}</label>
										</div>
										<div class="tab1-item">
											<label class="title-style2">编号 : </label> <label
												class="detail-style2">${member.memberNum}</label>
										</div>
										<div class="tab1-item">
											<label class="title-style2">生日年月 : </label> <label
												class="detail-style2"> <fmt:formatDate
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
												<div>
													<label class="detail-style2">${it.bankCard}</label>
												</div>
											</c:forEach>

										</div>
										<div class="tab1-item">
											<label class="title-style2">银行卡号</label>
											<c:forEach items="${banks}" var="it">
												<div>
													<label class="detail-style2">${it.bankCard}</label>
												</div>
											</c:forEach>
										</div>
										<div class="tab1-item">
											<label class="title-style2">头像 : </label>
											<div>
												<img src="${member.basic.memberPhoto.path}"
													class="head-icon">
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
									<div>
										<div class="">
											<label class="title-style3">3. 征信信息</label>
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
														<img src="${member.credit.cardJustPhoto.path}">
													</div>
												</div>
												<div class="papers">
													<div>
														<span>身份证反面</span>
													</div>
													<div>
														<img src="${member.credit.cardReversePhoto.path}">
													</div>
												</div>
											</div>
											<div style="clear: left;">
												<div class="papers">
													<div>
														<span>工作证</span>
													</div>
													<div>
														<img src="${member.credit.jobCertPhoto.path}">
													</div>
												</div>
												<div class="papers">
													<div>
														<span>名片</span>
													</div>
													<div>
														<img src="${member.credit.visitingCertPhoto.path}">
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- 操作记录 -->
								<div id="tab_3" class="tab-pane fade "></div>
								<!-- 需求修改 -->
								<div id="tab_4" class="tab-pane fade"
									style="padding-bottom: 30px;"></div>
								<!-- 操作记录 -->
								<div id="tab_5" class="tab-pane fade" style="padding: 0 10px;">
									<!-- 项目周报 -->
								</div>
								<div id="tab_6" class="tab-pane fade"></div>
								<!-- 文件管理 -->
								<div id="tab_7" class="tab-pane fade"></div>
								<!-- 客服反馈 -->
								<div id="tab_8" class="tab-pane fade"></div>
							</div>
						</div>
						<div style="width: 100%; height: 0px;"></div>
					</div>
			</section>
		</aside>
	</div>
	<!-- ./wrapper -->
	<script src="${_PATH}/res/js/admin/member/company/prefect.js"></script>
	<script src="${_PATH}/res/js/admin/project/bm/prefect.js"></script>
	<script src="${_PATH}/res/js/admin/project/bm/business.js"></script>
	<script src="${_PATH}/res/js/admin/project/bm/audit.js"></script>
	<script src="${_PATH}/res/js/admin/project/bm/detail.js"></script>
	<div class="modal fade" id="myModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" id="auditData"
			style="width: 800px;"></div>
	</div>
	<form id="detailForm" action="" hidden="true" method="post">
		<input type="text" name="id" id="detailFormInputId" />
	</form>
</body>
</html>