<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/inc/link.jsp"></c:import>
<title>云英汇 | 系统首页</title>
</head>
<link rel="stylesheet" type="text/css" media="all"
	href="${_PATH}/res/css/daterangepicker/daterangepicker-bs3.css" />
<script src="${_PATH}/res/js/ueditor/ueditor.config.js"></script>
<script src="${_PATH}/res/js/ueditor/ueditor.all.min.js"></script>
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
					项目中心 <small>立项书</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i>
							首页</a></li>
					<li class="active">立项书</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<form id="defineForm" method="post" name="submitForm">
					<div class="box">
						<button type="button" class="btn btn-primary"
							style="margin: 10px 0 10px 10px;" id="back">返回列表</button>
						<div class="box-header">
							<h3 class="box-title">
								发立项书
								<c:if test="${company.status!='normal'}">
									<small> <font color="red"> 项目方的资料未审核，请先审核项目方</font></small>
								</c:if>

							</h3>
						</div>
						<!-- /.box-header -->
						<input type="hidden" name="defineType" value="company" />
						<table
							style="margin: 0 10px 10px 10px; width: 70%; padding-bottom: 30px;"
							class="project-table box-body">
							<tbody>
								<tr>
									<td>
										<div style="padding: 5px 10px; text-align: left;">
											<p class="time-code">
												<span>项目创建时间 &nbsp;${it.createTime}</span> <span
													style="margin-left: 20px;">项目编号:&nbsp;${it.projectNum}</span>
											</p>

											<div>
												<div class="project-logo">
													<span class="logo-title">${it.firstName}</span> <span
														class="logo-bottom">neitui</span>
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
														<span class="index">开发类型:</span>&nbsp;<span class="value">
															<c:forEach items="${it.getSolutionListVal()}" var="types">
																<span class="value">${types}</span>
															</c:forEach>


														</span>
													</div>
													<div>
														<span class="index">预算:</span>&nbsp;<span class="value">${it.projectBudget}</span>
													</div>
													<div>
														<span class="index">期望交付日期:</span>&nbsp;<span
															class="value"><fmt:formatDate
																value="${it.expectTime}" pattern="yyyy-MM-dd" /></span>
													</div>
													<div>
														<span class="index">共<span style="color: #000;">${it.deliveryDay}</span>个工作日
														</span>
													</div>
													<div>
														<span class="index">
															联系人:${it.contactName}&nbsp;${it.contactPhone} </span>
													</div>
													<input type="hidden" id="projectId" value="${it.id}" />
												</div>
											</div>
										</div>
									</td>
									<td>
									<div class="deposit">
						                                    已交意向金：${it.intentionAmount} 元
				                     </div>
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
											<c:if test="${it.getTrustStage()!=null}">
					                          ${it.getTrustStage().amount}
					                    	</c:if>
											<c:if test="${it.getTrustStage()==null}">0
									                               </c:if>
									                               元
										</div>
										<div class="deposit-item">
											股权托管：
											<c:if test="${it.trustStocked=='true'}">
						                           有
						      </c:if>
											<c:if test="${it.trustStocked=='false'}">
					          	     无
						      </c:if>

										</div>
									</td>

								</tr>
							</tbody>
						</table>
						<div style="margin: 0 10px 10px 10px; width: 86.5%">
							<input type="text" hidden="true" name="projectId"
								value="${it.id}" />
							<div class="send-project box-body">
								<label class="title-style1" style="text-align: center;">
									<< &nbsp;${it.name}立项确认书&nbsp;>> </label>
								<hr>
								<script id="content" type="text/plain"
									style="width: 95%; height: 380px;" name="clause"></script>
								<hr>
								<div class="sp-item">
									<label class="title-style2">研发费用 : </label> <input type="text"
										class="short-input1" style="width: 170px;"
										value="${define.totalAmount}" id="totalAmountId"
										name="totalAmount"></input> <span>元</span>
								</div>
								<div class="sp-item">
									<label class="title-style2">股权 : </label> <input type="text"
										class="short-input1" value="${define.stock}" name="stock"></input>
									<span>%</span>
								</div>
								<div class="sp-item">
									<label class="title-style2">研发计划 * </label>
									<table class="sp-table" id="selectTableId">
										<tr style="text-align: center;">
											<td style="width: 150px;">项目阶段</td>
											<td>阶段时间规划</td>
											<td>阶段所需费用 (元)</td>
										</tr>
										<c:forEach items="${list}" var="type" varStatus="order">
											<tr>
											
												<td><span class="custom-bg circle" id="defineNameId"
													data="input_${order.index}">${type.title}</span> <input
													type="hidden" id="input_${order.index}"
													name="stage['${type.stageCode}'].stageCode"
													value="${type.stageCode}" /> <input type="hidden"
													id="input_${order.index}"
													name="stage['${type.stageCode}'].projectId"
													value="${it.id}" /> <input type="hidden"
													id="input_${order.index}"
													name="stage['${type.stageCode}'].title"
													value="${type.title}" /> <input type="hidden"
													id="input_${order.index}"
													name="stage['${type.stageCode}'].orders"
													value="${type.orders}" /></td>
												<td><input type="text" class="short-input2"
													style="width: 200px" id="input_${order.index}"
													name="stage['${type.stageCode}'].period" checked="false"></input></td>
												<td><input type="text" class="short-input2"
													style="width: 100px" id="input_${order.index}"
													name="stage['${type.stageCode}'].amount"></input></td>
											</tr>
										</c:forEach>
									</table>
								</div>
								<div class="sp-item">
									<label class="title-style2">期望交付时间 : </label> <input
										type="text" class="short-input2"
										value="<fmt:formatDate value='${it.deliveryTime}' pattern='yyyy-MM-dd' />"
										name="deliveryTime" id="deliveryTimeId"></input>

								</div>
								<div class="sp-item">
									<label class="title-style2">其它说明 : </label>
									<textarea class="form-control textarea-sp" rows="3"
										name="otherDesc"></textarea>
								</div>
								<div class="sp-item">
									<div style="margin-top: 10px;">
										<%-- 	<c:forEach items="${it.attas}" var="fileup">
											<div style="margin-top: 10px;">
												<span style="font-size: 25px;" class="glyphicon glyphicon-file"></span>
												${fileup.atta.fileName} <a href="${fileup.atta.downPath}" target="_blank">点击下载</a>
											</div>
										</c:forEach> --%>
										<label class="title-style2">需求确认文档:<span id="fileName"></span></label>
										<div class="ID-pic-add" style="margin-left: 10px;">

											<span class="glyphicon glyphicon-upload custom-icon"
												onclick="document.submitForm.defineFile.click()"></span> <a
												target="_blank" id="uploadSpan" style="display: none"><span
												class="glyphicon glyphicon-download custom-icon"></span></a> <span
												class="glyphicon glyphicon-trash custom-icon"
												id="removeSpan" style="display: none"></span>

										</div>
										<div>
											<input type="file" name="file" id="defineFile"
												style="display: none;"> <input type="hidden"
												name="attaId" id="defineFileId" />
										</div>
									</div>
								</div>
							</div>
							<c:if test="${company.status=='normal'}">
								<div style="width: 80%; height: 50px;">
									<button
										style="padding: 5px 20px !important; float: left; margin-left: 150px;"
										type="submit" class="btn btn-primary" id="submitBtn">提交</button>
								</div>
							</c:if>
						</div>
					</div>
				</form>
				<form id="detailForm" action="" hidden="true" method="post">
					<input type="text" name="id" value="${it.id}" />
				</form>
			</section>
		</aside>
		<!-- /.right-side -->
	</div>
	<script src="${_PATH}/res/js/plugins/jqueryupload/jquery.ui.widget.js"></script>
	<script src="${_PATH}/res/js/plugins/jqueryupload/jquery.fileupload.js"></script>
	<script src="${_PATH}/res/js/plugins/jqueryupload/webupload.js"></script>
	<script src="${_PATH}/res/js/datetimepicker/bootstrap-datepicker.js"></script>
	<script
		src="${_PATH}/res/js/datetimepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
	<script src="${_PATH}/res/js/plugins/validate/jquery.validate.min.js"></script>
	<script
		src="${_PATH}/res/js/plugins/validate/jquery.validate.messages_cn.js"></script>
	<script src="${_PATH}/res/js/admin/project/bm/define.js"></script>
	<script src="${_PATH}/res/js/daterangepicker/moment.js"></script>
	<script src="${_PATH}/res/js/daterangepicker/daterangepicker.js"></script>

	<!-- ./wrapper -->
	<div class="modal fade" id="myModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" id="auditData"
			style="width: 800px;"></div>
	</div>
</body>
</html>