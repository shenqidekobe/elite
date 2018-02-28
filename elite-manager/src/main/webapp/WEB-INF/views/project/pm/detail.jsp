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
					<li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
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
													<span class="logo-title">${it.firstName}</span> <span
														class="logo-bottom"></span>
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
																style="color: #000;">${it.deliveryDay}</span></span>个工作日
														</span>
													</div>
													<div>
														<c:if test="${it.ctoId!=null}">
															<span class="index"> CTO:${it.CTOName}
																${it.CTOPhone} </span>
														</c:if>
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
												<div class="deposit">已托管费用合计：
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
											<c:if test="${it.status!='unpass'&&it.status!='invalid'}">
											<shiro:hasPermission name="elite:project:pm:setting">
												<c:if
													test="${it.status=='wait_audit'||it.status=='audit_in'||it.status=='pass_wait'||it.status=='audited'}">
													<p>
														<a style="margin-bottom: 6px;" href="javascript:void(0);"
															id="setting_stage" data="${it.id}">设置服务阶段</a>

													</p>
												</c:if>
												</shiro:hasPermission>
												<p>
												   <shiro:hasPermission name="elite:project:pm:addTender">
													<c:if
														test="${it.status=='audit_in'||it.status=='pass_wait'||it.status=='pass_already'||it.status=='stage_course'}">
														<a href="javascript:void(0);" id="send_tender"
															data="${it.id}">发招标书</a>
													</c:if>
													</shiro:hasPermission>
													<shiro:hasPermission name="elite:project:pm:viewTender">
													<a href="javascript:void(0);" style="margin-left: 14px;"
														id="tender_list" data="${it.id}">投标列表</a>
														</shiro:hasPermission>
												</p>
												<shiro:hasPermission name="elite:project:pm:aduitProject"> 
												<p>

													<c:if
														test="${it.status!='unpass'&&it.status!='pass_already'&&it.status!='stage_course'&&it.status!='finish'}">
														<a href="javascript:void(0);" id="audit_project"
															data="${it.id}">审核项目</a>
													</c:if>
												</p>
												</shiro:hasPermission>
												<p>
												<shiro:hasPermission name="elite:project:pm:viewMaterial">
													<c:if
														test="${it.status=='stage_course'||it.status=='quality'||it.status=='finish'}">
														<a href="javascript:void(0);" id="view_material"
															data="${it.id}">查看新文件</a>
													</c:if>
													</shiro:hasPermission>
														<shiro:hasPermission name="elite:project:pm:viewWeekly">
													<c:if test="${it.status=='stage_course'}">
														<a href="javascript:void(0);" id="view_weekly"
															data="${it.id}">查看新周报</a>
													</c:if>
													</shiro:hasPermission>
												</p>
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
								<c:if test="${it.status!='unpass'&&it.status!='invalid'}">
								<li><a href="#tab_2" data-toggle="tab" id="logsShowBtn">
										招标等记录 </a></li>
								<!-- <li><a href="#tab_4" data-toggle="tab"> 需求修改 </a></li>
								<li><a href="#tab_5" data-toggle="tab"> 申述处理 </a></li>
								<li><a href="#tab_6" data-toggle="tab"> 延期处理 </a></li> -->
							
								<li><a href="#tab_3" data-toggle="tab" id="materialShowBtn">
										文件管理 </a></li>
								<li><a href="#tab_4" data-toggle="tab" id="weeklyShowBtn">
										周报管理 </a></li>
										</c:if>
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
												items="${it.getIndustryValList()}" var="industrys">
														${industrys}
														</c:forEach>

										</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">功能类型 : </label> <label
											class="detail-style2"> <c:forEach
												items="${it.functionValList}" var="industrys">
														${industrys}
														</c:forEach>

										</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">是否参与云英汇《明星开发团队持股开发计划》 : </label> <label
											class="detail-style2">是</label>
									</div>
								</div>
								<!-- 招标等记录 -->

								<div id="tab_2" class="tab-pane fade ">
									<form id="logsForm">
										<!-- 项目Id -->
										<input type="hidden" name="id" value="${it.id}" />
										<div id="logsData"></div>
									</form>
								</div>
								<!-- 文件管理 -->
								<div id="tab_3" class="tab-pane fade">
									<form id="materialForm"  name="materialForm" >
										<!-- list search 是否刷新页面判断 -->
										<input type="hidden" id="reloadPage" value="true" />
										<!-- 项目Id -->
										<input type="hidden" name="projectId" value="${it.id}"
											id="materialProjectId" />
										<!-- 查询方式 -->
										<input type="hidden" name="queryType" value="all" />

										<div class="input-group"
											style="width: 190px; margin-left: 10px; float: left;">
											<select class="form-control" id="searchRole" name="type">
												<c:forEach items="${it.projectDefine.stages}" var="stage">
													<option value="${stage.id}">${stage.stageCodeVal}</option>
												</c:forEach>
											</select>
										</div>

										<!-- 		<button type="button" class="btn-custom" id="myreceive">我收到的</button>
											<button type="button" class="btn-custom" id="search">我上传的</button> -->
										<!--<button type="button" class="btn-custom" id="search">未读</button>
											<button type="button" class="btn-custom" id="search">搜索</button> -->

										<br> <br>

										<div class="input-group"
											style="width: 190px; margin-left: 10px; float: left;">
											<select class="form-control" id="selectToType"
												name="receiveId">
												<option value="${it.companyId}">项目方</option>
												<c:if test="${it.ctoId!=null }">
												<option value="${it.ctoId}">CTO</option>
												</c:if>
											</select>
										</div>
										<span>
											<button type="button" onclick="document.materialForm.uploadMaterialFile.click()">上传文件</button>
											<input type="file" name="file" id="uploadMaterialFile"
											style="display: none;" /> 
											<input type="hidden" name="attaId" id="materialAttaId" />
											 <span id="materialNameId"></span>
											<button type="button" id="submitMaterial"
												style="display: none;">确认上传</button>
											<button type="button" id="cancleMaterial"
												style="display: none;">取消</button>
											<div class="channel-mag" id="listData"></div>
									</form>

								</div>
								<!-- 操作记录 -->
								<div id="tab_4" class="tab-pane fade ">
									<form id="weeklyForm" id="weeklyForm">
										<div class="input-group"
											style="width: 190px; margin-left: 10px; float: left;">
											<input type="hidden" name="projectId" value="${it.id}" /> <input
												type="hidden" name="month" value="0" id="selectMonthId" />
											<input type="hidden" name="status" id="searchStatusId" value='normal'>
											<input type="hidden" name="searchType" id="searchWeekTypeId"
												value="company" /> <select id="selectMonth">
												<option value="1" class="selectMonth">一月</option>
												<option value="2" class="selectMonth">二月</option>
												<option value="3" class="selectMonth">三月</option>
												<option value="4" class="selectMonth">四月</option>
												<option value="5" class="selectMonth">五月</option>
												<option value="6" class="selectMonth">六月</option>
												<option value="7" class="selectMonth">七月</option>
												<option value="8" class="selectMonth">八月</option>
												<option value="9" class="selectMonth">九月</option>
												<option value="10" class="selectMonth">十月</option>
												<option value="11" class="selectMonth">十一月</option>
												<option value="12" class="selectMonth">十二月</option>
											</select>
										</div>
										<br> <br>
										<ul class="nav nav-tabs">
											<li class="active"><a href="" data-toggle="tab"
												id="selCompanyWeek">给项目方的 </a></li>
											<li><a href="" data-toggle="tab" id="selCTOweek">来自CTO的周报</a></li>
										</ul>
										<div class="channel-mag" id="worklistData"></div>
									</form>
								</div>
							</div>
			</section>
			<form id="detailForm" action="" hidden="true" method="post">
				<input type="text" name="id" value="${it.id}" id="detailFormInputId" />
				<input type="text" name="projectId" value="${it.id}" id="detailFormProjectId" />
			</form>
			<form name="uploadfileform">
				<input type="file" name="file" id="defineFile"
					style="display: none;">
			</form>
		</aside>
	</div>
	<!-- ./wrapper -->
	<script src="${_PATH}/res/js/plugins/jqueryupload/jquery.ui.widget.js"></script>
	<script src="${_PATH}/res/js/plugins/jqueryupload/jquery.fileupload.js"></script>
	<script src="${_PATH}/res/js/plugins/jqueryupload/webupload.js"></script>
	<script src="${_PATH}/res/js/admin/project/bm/auditproject.js"></script>
	<script src="${_PATH}/res/js/admin/project/pm/settingstage.js"></script>
	<script src="${_PATH}/res/js/admin/member/company/prefect.js"></script>
	<script src="${_PATH}/res/js/admin/project/bm/prefect.js"></script>
	<script src="${_PATH}/res/js/admin/project/bm/business.js"></script>
	<script src="${_PATH}/res/js/admin/member/cto/audit.js"></script>
	<script src="${_PATH}/res/js/admin/project/pm/detail.js"></script>
	<div class="modal fade" id="myModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" id="showData"
			style="width: 800px;"></div>
	</div>
</body>
</html>