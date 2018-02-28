<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/inc/link.jsp"></c:import>
<title>云英汇 | 系统首页</title>
</head>
<link href="${_PATH}/res/css/daterangepicker/bootstrap-datepicker.css"
	rel="stylesheet" type="text/css" />
<link
	href="${_PATH}/res/css/daterangepicker/bootstrap-datepicker.min.css"
	rel="stylesheet" type="text/css" />
	<script src="${_PATH}/res/js/ueditor/ueditor.config.js"></script>
<script src="${_PATH}/res/js/ueditor/ueditor.all.min.js"></script>
<script>
    $(function(){ 
    ueEditor = UE.getEditor('content',{
   		 initialFrameWidth:"100%", //初始化选项
   			readonly:true
   	        });
    ueEditor = UE.getEditor('contentIntro',{
  		   initialFrameWidth:"100%", //初始化选项
  			readonly:true,
  			 toolbars:[]
  	        });
    $("#back").click(function(){
    	 var  backPage=sessionStorage.getItem('defineBackPage');
 			if (backPage != null && backPage != "") {
 				if (backPage == "pmlist") {
 					window.location.href = ctx + '/project/pm'
 				}
 				sessionStorage.removeItem("defineBackPage")
 			} else {
 				window.history.back(-1);
 			}
         }) 
    }); 
</script> 
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
			<form id="form">
				<!-- Main content -->
				<section class="content">
					<div class="box" style="padding-bottom: 30px;">
					<button type="button" class="btn btn-primary"
						style="margin: 10px 0 10px 10px;" id="back" >返回列表</button>
						<div class="send-tender">
							<input type="hidden" value="${it.id}" name="projectId">
							<div>
								<label class="title-style1"><<项目招标书>></label>
							</div>
							<script id="content" type="text/plain" style="width:95%;height:380px;">${it.content}</script>
							<div class="sp-item">
								<label class="title-style2">项目编号:${it.project.projectNum}</label> 
								<br>
								<label class="title-style2">项目名称:${it.project.name} </label> 
								<br>
								<label class="title-style2">行业分类:
								
									<c:forEach items="${it.project.getIndustryValList()}" var="item" >
					                   ${item}
										</c:forEach>
								
								</label> 
								<br>
								<label class="title-style2">功能类型 :
								<c:forEach items="${it.project.functionValList}" var="industrys">
									          ${industrys}
									    </c:forEach>
								
								 </label>
								<br>
								<label class="title-style2">解决方案类型 :
								<c:forEach items="${it.project.getSolutionListVal()}" var="types">
									        <span class="value">${types}</span>
									       </c:forEach>	
								</label>
								<br>
								<label class="title-style2">期望交付时间: </label>
								<div class="sp-item">
								<label class="title-style2">
									<div class="col-xs-30">
										<input type="text" id="startTimeId" name="startTime" value="<fmt:formatDate value='${it.startTime}' pattern='yyyy-MM-dd '/>"
											  readonly="readonly"/> <span><input type="text" id="endTimeId"  value="<fmt:formatDate value='${it.endTime}' pattern='yyyy-MM-dd '  />"
											name="endTime" readonly="readonly" /></span><label>共<span
											id="expecTimeDayId">${it.deliveryDay}</span></label>个工作日
							      </div>
							    </label>
								<br>
								<label class="title-style2">参考项目:${it.project.referProject} </label>
								<br>
								<label class="title-style2">初步需求说明 : </label>
									${it.atta.fileName}
												<a href="${it.atta.downPath}" target="_blank"><span class="glyphicon glyphicon-download custom-icon"></span></a>
									</span>
							</div>
								
							<div class="sp-item">
								<label class="title-style2">研发计划 * </label>
								<table class="sp-table">
									<tbody>
										<tr style="text-align: center;">
											<td style="width: 130px;"></td>
										 	<td></td>
											<td></td> 
										</tr>
										<c:forEach items="${stages}" var="stage" >
											<tr>

												<td style="width: 150px;"><span
													class="custom-bg circle"
													>${stage.title}</span></td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>
							<div class="sp-item">
								<label class="title-style2">愿意付出股权 : ${it.stockRate}%</label>
								<br>
								<!-- <label class="title-style2">平台初步估值: </label>
								<br> -->
								<label class="title-style2">质保期到期时间 : </label> <fmt:formatDate value="${it.qualityTime}" pattern="yyyy-MM-dd"/> <span></span>
							</div>
							<div class="sp-item">
								<label class="title-style2">项目简介 : </label>
								<script id="contentIntro" type="text/plain" style="width:95%;height:380px;">${it.project.intro}</script>
							</div>
							<div class="sp-item">
								<label class="title-style2">平台PM说明 : </label>
								<textarea class="form-control textarea-sp" rows="3"
									name="platformIntro">${it.platformIntro}</textarea>
							</div>
							<div class="sp-item">
								<label class="title-style2">招标截止时间 :
								<fmt:formatDate value="${it.tenderoverTime}" pattern="yyyy-MM-dd " />
								</label>
							</div>
						</div>
					</div>
				</section>
			</form>

		</aside>
		<!-- /.right-side -->
	</div>
	<!-- ./wrapper -->
	<div class="modal fade" id="myModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" id="auditData"
			style="width: 800px;"></div>
	</div>
</body>
</html>