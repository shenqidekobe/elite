<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/inc/link.jsp"></c:import>


<title></title>
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
					渠道中心 <small>项目渠道管理</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">项目渠道</li>
				</ol>
			</section>
			
			<!-- Main content -->

			<section class="content">
				<div class="col-md-12" style="min-width: 950px;">
					<div class="box" style="padding-bottom: 30px;">
						<div class="title-h4" style="margin: 20px 0 10px 0; height: 30px;">
							<button type="button" class="btn-custom" id="backBtn">返回列表</button>
						</div>
						<form id="detailForm"  hidden="true" method="post">
							<input type="text" name="id" id="detailFormInputId" />
						</form>
							<div id="remarkModel" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;" aria-hidden="true">
								<div id="showDate" class="modal-dialog" role="document" style="width: 800px;">
								</div>
							</div>	
						<div class="channel-mag">
							<table class="table table-bordered" id="tab">
								<tbody id="tbody">
										<tr style="text-align: center;">
											<td colspan="2"><small>注册时间:<fmt:formatDate value="${member.partnerCompany.createTime}" pattern="yyyy-MM-dd HH:mm" /></small></td>
											<td colspan="2"><small>渠道编号:${member.partnerCompany.channelNum}</small></td>
											<td><small>Ta的推荐人:${member.partnerCompany.parentName}</small></td>
											<td><small>Ta的负责人:${member.partnerCompany.chargeName}</small></td>
										</tr>
										<tr>
											<td width="15%" align="center">
												<div>
													<c:if test="${member.basic.photoId!=null }"><img src="${member.basic.memberPhoto.path}" height="105px" width="105px"></c:if>
													<c:if test="${member.basic.photoId==null }"><img src="${_PATH}/res/img/default.jpg" height="105px" width="105px" /></c:if>
												</div>
											</td>
											<td>
												<div>${member.nickName}</div>
												<div>${member.accountOffSuffix}</div>
											</td>
											<td>
												<div>姓名:${member.credit.realName}</div>
												<div>机构:${member.partnerCompany.companyName}</div>
												<div>类别:${member.partnerCompany.type.label}</div>
											</td>
											<td>
												<div>收益总额:${member.memberAccount.totalIncome}</div>
												<div>备案及入驻项目数:${member.partnerCompany.putCount}(${member.partnerCompany.enterCount})</div>
												<div>备案及入驻项目渠道数:${member.partnerCompany.putParnterCount}(${member.partnerCompany.enterParnterCount})</div>
											</td>
											<td>
											　　	<div>
													<div>
														<c:if test="${member.partnerCompany.status=='waitAduit'}">
															待完善
														</c:if>
														<c:if test="${member.partnerCompany.status=='aduitIn'}">
															待审核
														</c:if>
														<c:if test="${member.partnerCompany.status=='auditNotPass'}">
															审核不通过
														</c:if>
														<c:if test="${member.partnerCompany.status=='normal'}">
															审核通过
														</c:if>
													</div>
												</div>
											</td>
											<td>
												<div style="margin-top: 25px;" class="opt">
													<div>
														<p><a style="margin-bottom: 6px;" href="javascript:void(0);" id="allot" data="${member.id}">分配负责人</a></p>
														<c:if test="${member.partnerCompany.status=='aduitIn'}">
															<p><a style="margin-bottom: 6px;" href="javascript:void(0);" id="aduit" data="${member.id}">审核</a></p>
														</c:if>
														<c:if test="${member.partnerCompany.status!='aduitIn'}">
															<p><a style="margin-bottom: 6px;" href="javascript:void(0);" id="aduit" data="${member.id}">强审核</a></p>
														</c:if>
														<p><a style="margin-bottom: 6px;" href="javascript:void(0);" id="addRemarks" data="${member.id}">备注</a></p>
														<!--<p><a style="margin-bottom: 6px;" href="javascript:void(0);" id="" data="${it.id}">协助编辑资料</a></p>-->
													</div>
												</div>					
											</td>
										</tr>
								</tbody>
							</table>						</div>
                         <input type="hidden" value="${member.id}" id="partnerCompanyId"/>
						<div style="margin-top: 35px;">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#tab_1" data-toggle="tab">基本资料 </a></li>
								<li><a href="#tab_2" data-toggle="tab" id="recordProject">他推荐的项目 </a></li>
								<li><a href="#tab_3" data-toggle="tab" id="recordPartner">他推荐的渠道 </a></li>
								<li><a href="#tab_4" data-toggle="tab">审核信息</a></li>
　								<li><a href="#tab_5" data-toggle="tab">备注信息</a></li>
								<li><a href="#tab_6" data-toggle="tab" id="income">他的收益结构 </a></li>
							</ul>
							<div id="myTabContent" class="tab-content">
								<!-- 项目详情 -->
								<div id="tab_1" class="tab-pane fade in active"
									style="padding: 10px;">
									<div class="tab1-item">
										<label class="title-style2">用户名 :</label> <label
											class="detail-style2">${member.nickName}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">性别 :</label> <label
											class="detail-style2"><c:if test="${member.basic.sex =='F'}">女</c:if><c:if test="${member.basic.sex=='M'}">男</c:if></label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">编号 :</label> <label
											class="detail-style2">${member.partnerCompany.channelNum}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">手机号 :</label> <label
											class="detail-style2">${member.accountOffSuffix}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">邮箱:</label> <label
											class="detail-style2">${member.basic.email}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">身份类型:</label> <label
											class="detail-style2">${member.partnerCompany.type.label}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">机构名称:</label> <label
											class="detail-style2">${member.partnerCompany.companyName}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">城市:</label> <label
											class="detail-style2">${member.basic.areaName}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">推荐人:</label> <label
											class="detail-style2">${parent.memberPassport.nickName}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">征信:</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">姓名:</label>
										<label　class="detail-style2">${member.credit.realName}</label>&nbsp;&nbsp;&nbsp;&nbsp;
										<label class="title-style2">身份证号:</label>
										<label　class="detail-style2">${member.credit.idCard}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2"><img src="${member.credit.cardJustPhoto.path}" height="105px" width="105px"></label>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<label class="title-style2"><img src="${member.credit.cardReversePhoto.path}" height="105px" width="105px"></label>
									</div>
								</div>
								<div id="tab_2" class="tab-pane fade ">
									<form id="projectListForm">
										<input type="hidden" value="registered" name="status" id="status"/>
										<input type="hidden" value="${member.partnerCompany.id}" name="partnerId" />
										<div class="channel-mag" id="projectListData"></div>
									</form>
								</div>
								<div id="tab_3" class="tab-pane fade">
									<form id="partnerListForm">
										<input type="hidden" value="${member.partnerCompany.id}" name="partnerId" />
										<div class="search-option">
											<div class="item" style="display: inline-block;">
												<label class="title-nomal"></label> <input style="padding: 2px 5px;"
													class="input-style2" placeholder="姓名/手机" name="keyword"></input>
											</div>
											<div class="item" style="display: inline-block;">
												<select class="form-control" name="status" id="partnerStatus">
													<option value="">----请选择----</option>
													<option value="no_register">已备案项目渠道</option>
													<option value="registered">已注册项目渠道</option>
													<option value="audit_pass">已入驻项目渠道</option>
										   		</select>
											</div>

											<button type="button" class="btn-custom" id="search">搜索</button>
										</div>
										<div class="channel-mag" id="partnerListData"></div>
									</form>
								</div>
								<div id="tab_4" class="tab-pane fade">
									<div class="channel-mag">
										<span><fmt:formatDate value="${member.partnerCompany.auditTime}" pattern="yyyy-MM-dd HH:mm" /></span>&nbsp;&nbsp;
										<span>${user.userName}</span>&nbsp;&nbsp;
										<span><c:if test="${member.partnerCompany.status=='normal'}">审核通过</c:if><c:if test="${member.partnerCompany.status!='normal'}">审核不通过</c:if></span>&nbsp;&nbsp;
										<span>${member.partnerCompany.auditReason}</span>
									</div>
								</div>
								<div id="tab_5" class="tab-pane fade">
								 <c:forEach items="${list}" var="record" varStatus="statu">
									<div class="channel-mag">
										<span><fmt:formatDate value="${record.createTime}" pattern="yyyy-MM-dd HH:mm" /></span>&nbsp;&nbsp;
										<span>${record.realName}</span>&nbsp;&nbsp;
										<span>${record.content}</span>
									</div>
								</c:forEach>
								</div>
								<div id="tab_6" class="tab-pane fade">
									<form id="incomeForm">
										<input type="hidden" value="${member.id}" name="id" />
										<div class="channel-mag" id="incomeData"></div>
									</form>
								</div>
							</div>
						</div>

					</div>
			</section>
			<!--section content-->

		</aside>
	
	</div>
	<!-- ./wrapper -->
	<script src="${_PATH}/res/js/plugins/validate/jquery.validate.min.js"></script>
	<script
		src="${_PATH}/res/js/plugins/validate/jquery.validate.messages_cn.js"></script>

	<script src="${_PATH}/res/js/admin/channel/company/aduit.js"></script>
	<script src="${_PATH}/res/js/admin/channel/company/detail.js"></script>
	<script src="${_PATH}/res/js/admin/channel/company/remarks.js"></script>
	<script src="${_PATH}/res/js/admin/channel/company/enterproject.js"></script>
	 <div class="modal fade" id="myModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" id="aduitDate"
			style="width: 800px;"></div>
	</div>
	<div class="modal fade" id="workLogsModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
			
	</div>
</body>
</html>
