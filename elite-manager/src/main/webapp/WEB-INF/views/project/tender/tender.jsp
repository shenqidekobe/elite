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
					项目中心 <small>招标书</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">招标书</li>
				</ol>
			</section>
			<form id="form" name="submitForm">
				<!-- Main content -->
				<section class="content">
					<div class="box" style="padding-bottom: 30px;">
						<button type="button" class="btn btn-primary" style="margin: 10px 0 10px 10px;" id="back"
							onclick="javascript:history.back(-1);">返回列表</button>
						<div class="title-h4" style="border-bottom: 1px solid #C1C1C1;">
							<h4>发招标书:</h4>
						</div>
						<div class="send-tender">
							<input type="hidden" value="${it.id}" name="projectId">
							<div>
								<label class="title-style1"><<项目招标书>></label>
							</div>
							<!-- 	<textarea class="form-control textarea-sp" rows="3" name="content">
							</textarea> -->
							<div>
								<hr>
								<script id="content" type="text/plain" style="width: 95%; height: 380px;"></script>
							</div>
							<input type="hidden" name="content" id="content_inputId" />

							<div class="sp-item">
								<label class="title-style2">项目名称 : </label> <input class="short-input6" type="text"
									placeholder="简洁且能概括产品的项目标题" name="name" value="${it.name}" readonly></input>
							</div>
							<div class="sp-item">
								<label class="title-style2">行业分类 : <label> <c:forEach
											items="${it.getIndustryValList()}" var="industrys">
									          ${industrys}
									    </c:forEach>


								</label></label>
							</div>
							<div class="sp-item">
								<label class="title-style2">功能类型 : <c:forEach items="${it.functionValList}"
										var="industrys">
									          ${industrys}
									    </c:forEach>

								</label> </label>
							</div>
							<div class="sp-item">
								<label class="title-style2">解决方案类型 : <c:forEach items="${it.solutionListVal}"
										var="types">
									${types}
									</c:forEach>

								</label> </label>
								<div class="selector-box"></div>
							</div>
							<div class="sp-item">
								<label class="title-style2">期望交付时间 : </label>
							</div>
							<div class="sp-item">
								<label class="title-style2">
									<div class="col-xs-30">
										<input type="text" id="startTimeId" name="startTime"  placeholder="请选择开始时间"
											 /> <span><input type="text" id="endTimeId"  placeholder="请选择结束时间"
											name="endTime"  /></span><label>共<span
											id="expecTimeDayId">0</span></label>个工作日
							      </div>
							</label>
						</div>
						<div class="sp-item">
							<label class="title-style2">参考项目 : </label> <input class="short-input3" type="text"
								placeholder="天猫/淘宝" value="${it.referProject}" name="referProject"></input>
						</div>
						<div class="sp-item">
							<div class="sp-item">
								<label class="title-style2">初步需求文档:<span id="fileName"></span></label>
								<div class="ID-pic-add" style="margin-left: 10px;">

									<span class="glyphicon glyphicon-upload custom-icon"
										onclick="document.submitForm.defineFile.click()"></span> <a target="_blank"
										id="uploadSpan" style="display: none"><span
										class="glyphicon glyphicon-download custom-icon"></span></a> <span
										class="glyphicon glyphicon-trash custom-icon" id="removeSpan" style="display: none"></span>

								</div>
								<div>
									<input type="file" name="file" id="defineFile" style="display: none;"> <input
										type="hidden" name="attaId" id="defineFileId" />
								</div>
							</div>

						</div>
						<div class="sp-item">
							<label class="title-style2">研发计划 * </label>
							<table class="sp-table" id="selectTableId">
								<tbody>
									<tr style="text-align: center;">
										<td style="width: 130px;">项目阶段</td>

									</tr>
									<c:forEach items="${list}" var="type" varStatus="order">
										<tr>

											<td style="width: 150px;"><span class="custom-bg circle" id="defineNameId"
												data="input_${order.index}">${type.title}</span></td>
											<td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>
						<div class="sp-item">
							<label class="title-style2">愿意付出股权 : </label> <input type="text" class="short-input1"
								name="stockRate"> <span>%</span>
						</div>
						<!-- <div class="sp-item">
								<label class="title-style2">平台初步估值 : </label> <input type="text"
									class="short-input1"> <span>万</span>
							</div> -->
						<div class="sp-item">
							<label class="title-style2">质保期 : </label> <input type="text" class="short-input1"
								name="qualitTyMonth"> <span>月</span>
						</div>
						<div class="sp-item">
							<label class="title-style2">项目简介 : </label>
							<script id="contentIntro" type="text/plain" style="width:95%;height:380px;" name="intro">${it.intro}</script>
						</div>
						<div class="sp-item">
							<label class="title-style2">平台PM说明 : </label>
							<textarea class="form-control textarea-sp" rows="3" name="platformIntro"></textarea>
						</div>
						<div class="sp-item">
							<label class="title-style2">截止时间:<span style="z-index:999;position: relative;"><input type="text" placeholder="请选择截止时间"
								id="tenderoverTime" name="tenderoverTime" /></span></label>

						</div>
						<div style="width: 100%; height: 50px;">
							<div style="margin: 20px auto; width: 75%; height: 40px;">
								<button style="float: left; padding: 5px 25px;" type="submit" class="btn btn-primary"
									id="submitBtn">提交</button>
								<button style="float: right; padding: 5px 25px;" type="button" id="cancel"
									class="btn btn-primary">取消</button>
							</div>
						</div>
					</div>
	</div>
	</section>
	</form>
	</aside>
	<form id="detailForm" action="" hidden="true" method="post">
		<input type="text" name="id" value="${it.id}" id="detailFormInputId" />
	</form>
	<!-- /.right-side -->
	</div>
	<script src="${_PATH}/res/js/plugins/jqueryupload/jquery.ui.widget.js"></script>
	<script src="${_PATH}/res/js/plugins/jqueryupload/jquery.fileupload.js"></script>
	<script src="${_PATH}/res/js/plugins/jqueryupload/webupload.js"></script>
	<script src="${_PATH}/res/js/datetimepicker/bootstrap-datepicker.js"></script>
	<script src="${_PATH}/res/js/datetimepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
	<script src="${_PATH}/res/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${_PATH}/res/js/plugins/validate/jquery.validate.messages_cn.js"></script>
	<script src="${_PATH}/res/js/admin/project/tender/tender.js"></script>
	<!-- ./wrapper -->
	<div class="modal fade" id="myModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" id="auditData" style="width: 800px;"></div>
	</div>
</body>
</html>