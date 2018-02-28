<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
					渠道中心 <small>人才渠道管理</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">人才渠道</li>
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
									<td colspan="2"><small>注册时间:<fmt:formatDate value="${member.partnerElite.createTime}" pattern="yyyy-MM-dd HH:mm" /></small></td>
									<td colspan="2"><small>渠道编号:${member.partnerElite.channelNum}</small></td>
									<td><small>Ta的推荐人:${member.partnerElite.parentName}</small></td>
									<td><small>负责人:${member.partnerElite.chargeName}</small></td>
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
										<!-- <div>机构:${member.partnerElite.companyName}</div> -->
										<div>类别:${member.partnerElite.type.label}</div>
									</td>
									<td>
										<div>收益总额:${member.memberAccount.totalIncome}</div>
										<div>备案及入驻人才数:${member.partnerElite.putCount}(${member.partnerElite.enterCount})</div>
										<div>备案及入驻人才渠道数:${member.partnerElite.putParnterCount}(${member.partnerElite.enterParnterCount})</div>
									</td>
									<td>
									　　	<div>
											<div>
												<c:if test="${member.partnerElite.status=='waitAduit'}">
													待完善
												</c:if>
												<c:if test="${member.partnerElite.status=='aduitIn'}">
													待审核
												</c:if>
												<c:if test="${member.partnerElite.status=='auditNotPass'}">
													审核不通过
												</c:if>
												<c:if test="${member.partnerElite.status=='normal'}">
													审核通过
												</c:if>
											</div>
										</div>
									</td>
									<td>
										<div style="margin-top: 25px;" class="opt">
											<div>
												<p><a style="margin-bottom: 6px;" href="javascript:void(0);" id="allot" data="${member.id}">分配负责人</a></p>
												<c:if test="${member.partnerElite.status=='aduitIn'}">
													<p><a style="margin-bottom: 6px;" href="javascript:void(0);" id="aduit" data="${member.id}">审核</a></p>
												</c:if>
												<c:if test="${member.partnerElite.status!='aduitIn'}">
													<p><a style="margin-bottom: 6px;" href="javascript:void(0);" id="aduit" data="${member.id}">强审核</a></p>
												</c:if>
												<p><a style="margin-bottom: 6px;" href="javascript:void(0);" id="addRemarks" data="${member.id}">备注</a></p>
												<!--<p><a style="margin-bottom: 6px;" href="javascript:void(0);" id="" data="${member.id}">协助编辑资料</a></p>-->
											</div>
										</div>					
									</td>
								</tr>
						</tbody>
					</table>

						</div>

						<div style="margin-top: 35px;">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#tab_1" data-toggle="tab"> 基本资料 </a></li>
								<li><a href="#tab_2" data-toggle="tab" id="recordElite"> 他推荐的人才 </a></li>
								<li><a href="#tab_3" data-toggle="tab" id="checkedElite">他推荐的渠道</a></li>
								<li><a href="#tab_4" data-toggle="tab">审核信息</a></li>
　								<li><a href="#tab_5" data-toggle="tab" id="">备注信息</a></li>
								<li><a href="#tab_6" data-toggle="tab" id="income">他的收益结构 </a></li>
							</ul>
							<div id="myTabContent" class="tab-content">
								<!-- 项目详情 -->
								<div id="tab_1" class="tab-pane fade in active" style="padding: 10px;">
									<div class="tab1-item">
										<label class="title-style2">用户名 : </label> <label class="detail-style2">${member.nickName}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">渠道编号: </label> <label class="detail-style2">${member.partnerElite.channelNum}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">邮箱: </label> <label class="detail-style2">${member.basic.email}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">性别 :</label> <label
											class="detail-style2"><c:if test="${member.basic.sex =='F'}">女</c:if><c:if test="${member.basic.sex=='M'}">男</c:if></label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">出生年月 : </label> <label class="detail-style2"><fmt:formatDate value="${member.basic.birthday}" pattern="yyyy.MM.dd" /></label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">聚焦行业 : </label> <label class="detail-style2">${member.partnerElite.attentionIndustryVal}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">公司名 : </label> <label class="detail-style2">${member.partnerElite.companyName}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">擅长职位: </label> <label class="detail-style2">${member.partnerElite.goodatJobVal}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">从业年限: </label> <label class="detail-style2">${member.partnerElite.jobAgeVal}</label>
									</div>

									<div class="tab1-item">
										<label class="title-style2">城市 : </label> <label class="detail-style2">${member.basic.areaName}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">身份类型 : </label> <label class="detail-style2">${member.partnerElite.type.label}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">ta的推荐人 : </label> <label class="detail-style2">${elite.memberPassport.nickName}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">征信信息</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">姓名 : </label> <label class="detail-style2">${member.credit.realName}</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">身份证号: </label> <label class="detail-style2">${member.credit.idCard}</label>
									</div>
									<div class="tab1-item">
										<c:if test="${member.credit.cardJust!=null }">
											<label class="title-style2"><img src="${member.credit.cardJustPhoto.path}" height="105px" width="105px"></label>
											&nbsp;&nbsp;&nbsp;&nbsp;
										</c:if>
										<c:if test="${member.credit.cardReverse!=null }">
											<label class="title-style2"><img src="${member.credit.cardReversePhoto.path}" height="105px" width="105px"></label>
											&nbsp;&nbsp;&nbsp;&nbsp;
										</c:if>
										<c:if test="${member.credit.jobCert!=null }">
											<label class="title-style2"><img src="${member.credit.jobCertPhoto.path}" height="105px" width="105px"></label>
											&nbsp;&nbsp;&nbsp;&nbsp;
										</c:if>
										<c:if test="${member.credit.businessCert!=null }">	
											<label class="title-style2"><img src="${member.credit.businessCertPhoto.path}" height="105px" width="105px"></label>
											&nbsp;&nbsp;&nbsp;&nbsp;
										</c:if>
										<c:if test="${member.credit.pmpCret!=null }">	
											<label class="title-style2"><img src="${member.credit.pmpCretPhoto.path}" height="105px" width="105px"></label>
										</c:if>	
									</div>
									<div class="tab1-item">
										<label class="title-style2">帐号信息</label>
									</div>
									<div class="tab1-item">
										<label class="title-style2">手机号 : </label> <label class="detail-style2">${member.accountOffSuffix}</label>
									</div>
								</div>
								<div id="tab_2" class="tab-pane fade ">
									<form id="eliteListForm">
										<div class="search-option">
											<div class="item" style="display: inline-block;">
												<input type="text" hidden="true" name="memberId" id="auditMemberId" value="${member.id}"></input>
												<input type="hidden" name="partnerId" value="${member.partnerElite.id}" />
											</div>
											<div class="item" style="display: inline-block;">
												<label class="title-nomal"></label> <input style="padding: 2px 5px;"
													class="input-style2" placeholder="姓名/手机" name="keyword"></input>
											</div>
											<div class="item" style="display: inline-block;">
												<select class="form-control" name="status" id="partnerStatus">
													<option value="">----请选择----</option>
													<option value="noRegister">已备案精英</option>
													<option value="registered">已注册精英</option>
													<option value="audit_pass">已入驻精英</option>
										   		</select>
											</div>
											<button type="button" class="btn-custom" id="search" data="elite">搜索</button>
										</div>

										<div class="channel-mag" id="eliteListData"></div>
									</form>
								</div>
								<div id="tab_3" class="tab-pane fade">
								  <form id="partnerListForm">
									<div class="search-option">
										<div class="item" style="display: inline-block;">
											<input type="text" hidden="true" name="memberId" id="auditMemberId" value="${member.id}"></input>
											<input type="hidden" name="partnerId" value="${member.partnerElite.id}" />
										</div>
										<div class="item" style="display: inline-block;">
											<label class="title-nomal"></label> <input style="padding: 2px 5px;"
													class="input-style2" placeholder="姓名/手机" name="keyword"></input>
										</div>
										<div class="item" style="display: inline-block;">
											<select class="form-control" name="status" id="partnerStatus">
												<option value="">----请选择----</option>
												<option value="no_register">已备案渠道</option>
												<option value="registered">已注册渠道</option>
												<option value="audit_pass">已入驻渠道</option>
									   		</select>
										</div>
										<button type="button" class="btn-custom" id="search" data="partner">搜索</button>
									</div>
									
									<div class="channel-mag" id="partnerListData">
									</div>
									</form>
								</div>
								<div id="tab_4" class="tab-pane fade">
									<div class="channel-mag">
										<span><fmt:formatDate value="${member.partnerElite.auditTime}" pattern="yyyy-MM-dd HH:mm" /></span>&nbsp;&nbsp;
										<span>${user.userName}</span>&nbsp;&nbsp;
										<span><c:if test="${member.partnerElite.status=='normal'}">审核通过</c:if><c:if test="${member.partnerElite.status!='normal'}">审核不通过</c:if></span>&nbsp;&nbsp;
										<span>${member.partnerElite.auditReason}</span>
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


		<!-- /.right-side -->
	</div>
	<!-- ./wrapper -->
	<script src="${_PATH}/res/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${_PATH}/res/js/plugins/validate/jquery.validate.messages_cn.js"></script>

	<script src="${_PATH}/res/js/admin/channel/elite/aduit.js"></script>
	<script src="${_PATH}/res/js/admin/channel/elite/detail.js"></script>
	<script src="${_PATH}/res/js/admin/channel/elite/remarks.js"></script>
	<script src="${_PATH}/res/js/plugins/jqueryupload/jquery.fileupload.js"></script>
	<script src="${_PATH}/res/js/plugins/jqueryupload/webupload.js"></script>
	<div class="modal fade" id="myModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" id="aduitDate" style="width: 800px;"></div>
	</div>

	<div class="modal fade" id="workLogsModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel"></div>
</body>
</html>
