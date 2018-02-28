<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>

<script src="${_PATH}/res/js/city/kuCity.js"></script>
<link href="${_PATH}/res/css/daterangepicker/bootstrap-datepicker.css"
	rel="stylesheet" type="text/css" />
<link
	href="${_PATH}/res/css/daterangepicker/bootstrap-datepicker.min.css"
	rel="stylesheet" type="text/css" />
<script src="${_PATH}/res/js/datetimepicker/bootstrap-datepicker.js"></script>
<script
	src="${_PATH}/res/js/datetimepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<body class="skin-blue">
	<script src="${_PATH}/res/js/plugins/jqueryupload/jquery.ui.widget.js"></script>
	<script src="${_PATH}/res/js/plugins/jqueryupload/jquery.fileupload.js"></script>
	<script src="${_PATH}/res/js/plugins/jqueryupload/webupload.js"></script>
	<link
	href="${_PATH}/res/css/kuCity.css"
	rel="stylesheet" type="text/css" />
<form id="prefectForm" name="prefectForm">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">协助完善项目方资料</h4>
		</div>
		<div class="modal-body">
			<div>
				<div class="">
					<label class="title-style3">1.基本资料</label>
				</div>
			
				<div class="tab1-item">
					<label class="title-style2">生日年月日: </label> <label
						class="title-style2">
						<div
							style="background:url('${_PATH}/res/img/round.png') no-repeat;height:25px">
							&nbsp;<small>隐</small> &nbsp;&nbsp;
						</div>
					</label>
				</div>
				<div class="tab1-item">
					<label class="detail-style2"><input type="text"
						class="form-control" name="basic.birthday" id="startTimeId"
						value="<fmt:formatDate
							value="${member.basic.birthday}" pattern="yyyy-MM-dd" />"></label>
				</div>
				 <div class="tab1-item">
					<label class="title-style2">所在城市 *</label>
				</div>
				<div class="tab1-item">
					<label class="detail-style2"><input class="form-control" id="areaName"
						name="basic.areaName" type="text" value="${member.basic.areaName}"></label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">职位/头衔&nbsp;&nbsp;<small><small>少于15字</small></small></label>
				</div>
				<div class="tab1-item">
					<label class="detail-style2"><input class="form-control"
						name="basic.memberSign" type="text"
						value="${member.basic.memberSign}" /></label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">绑定邮箱* &nbsp;&nbsp;<small><small>用于账户登录及项目进展的消息推送</small></small></label>
				</div>
				<div class="tab1-item">
					<label class="detail-style2"><input class="form-control"
						name="basic.email" type="text" value="${member.basic.email}" /></label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">头像 : </label>
					<div>
						<img src="${member.basic.memberPhoto.getPath()}" id="photoImgId" class="head-icon"  onclick="document.prefectForm.photoIdFile.click()">
					    <input type="file" name="file"
						id="photoIdFile" style="display: none;"> <input type="hidden" name="basic.photoId"
						id="photoId" value="${member.basic.photoId}" />
				   </div>
				</div>
			</div>
			<div>
				<div class="">
					<label class="title-style3">2. 创业属性</label>
				</div>
				<!--  注册公司-->
				<c:if test="${member.company.companyed=='true'}">
				<div class="tab1-item">
					<label class="title-style2">你的职位* </label>
				</div>
				<div class="tab1-item">
					<label class="detail-style2"><input class="form-control"
						name="company.companyPosition" type="text"
						value="${member.company.companyPosition}" /></label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">公司名称 *</label>
				</div>
				<div class="tab1-item">
					<label class="detail-style2"><input class="form-control"
						name="company.companyName" type="text"
						value="${member.company.companyName}" /></label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">公司规模 *</label>
				</div>
				<div class="tab1-item">
					<%-- <label class="detail-style2"><input class="form-control"
						name="company.companyScale" type="text"
						value="${member.company.companyScale}" /></label> --%>
							<div class="">
					<label class="title-style2">

						<div class="btn-group" role="group" aria-label="...">

							 <div class="btn-group" role="group">
								<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">
									<div id="bugetShowId">
									<c:if test="${member.company.companyScale==''|| member.company.companyScale==null}">
									     请选择
									</c:if>
									 ${member.company.companyScaleVal}
									</div>
									<span class="caret"></span>
								</button>
								<input type="hidden" name="company.companyScale" value="${member.company.companyScale}" id="companyScaleId"/>
								<ul class="dropdown-menu" id="budgetSelect">
								  <c:forEach items="${dicts}" var="stage">
								  <li><a href="javascript:void(0);" id="sacleSelect" text="${stage.dictVal}" data="${stage.dictKey}">${stage.dictVal}</a></li>
						          </c:forEach>
								</ul>
							</div>
						</div>
					</label>
				</div>
				
				</div>
				
				<div class="tab1-item">
					<label class="title-style2">公司简介 </label>
				</div>
				<div class="tab1-item">
					<textarea class="form-control" rows="4" placeholder="请输入公司简介"
						name="company.companyIntro" id="auditReasonId">${member.company.companyIntro}</textarea>
				</div>
				</c:if>
				<!--  未注册公司-->
				<c:if test="${member.company.companyed=='false'}">
					<div class="tab1-item">
					<label class="title-style2">你的职位 </label>
				</div>
				<div class="tab1-item">
					<label class="detail-style2"><input class="form-control"
						name="company.companyPosition" type="text"
						value="${member.company.companyPosition}" /></label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">团队人数</label>
				</div>
				<div class="tab1-item">
					<%-- <label class="detail-style2"><input class="form-control"
						name="company.companyScale" type="text"
						value="${member.company.companyScale}" /></label> --%>
							<div class="">
					<label class="title-style2">

						<div class="btn-group" role="group" aria-label="...">

							 <div class="btn-group" role="group">
								<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">
									<div id="bugetShowId">
									<c:if test="${member.company.teamNum==''|| member.company.teamNum==null}">
									     请选择
									</c:if>
									 ${member.company.teamNumVal}
									</div>
									<span class="caret"></span>
								</button>
								<input type="hidden" name="company.teamNum" value="${member.company.teamNum}" id="teamNumId"/>
								<ul class="dropdown-menu" id="budgetSelect">
								  <c:forEach items="${dicts}" var="stage">
								  <li><a href="javascript:void(0);" id="sacleSelect" text="${stage.dictVal}" data="${stage.dictKey}">${stage.dictVal}</a></li>
						          </c:forEach>
								</ul>
							</div>
						</div>
					</label>
				</div>
				
				</div>
				<div class="tab1-item">
					<label class="title-style2">团队介绍 </label>
				</div>
				<div class="tab1-item">
					<textarea class="form-control" rows="4" placeholder="团队介绍"
						name="company.teamIntro" >${member.company.teamIntro}</textarea>
				</div>
				
				
				</c:if>
			</div>
			<div>
				<div class="">
					<label class="title-style3">3.征信信息</label>
				</div>
					<div class="tab1-item">
					<label class="title-style2">
						<div>真实姓名 &nbsp;*</div>
					</label> <label class="title-style2">
						<div
							style="background:url('${_PATH}/res/img/round.png') no-repeat;height:25px">
							&nbsp;<small>隐</small> &nbsp;&nbsp;
						</div>
					</label> <label class="title-style2"> <small>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small>
							<c:if test="${member.credit.isCard==true }">
							验证已经通过，姓名和身份证号码不能修改
							</c:if>
							<c:if test="${member.credit.isCard==false }">
							与身份证上保持一致
							</c:if>
							
							</small>
					</small>
					</label>
					<div class="tab1-item">
						<label class="title-style2">
							<div class="col-xs-30">
								<input type="text" class="form-control" id="dictDescId"
									width="20px" name="credit.realName" placeholder="请输入真实姓名 "
									
									<c:if test="${member.credit.isCard==true }">
							                       readonly
						           	</c:if>
									
									value="${member.credit.realName}">
							</div>
						</label>
					</div>
				</div>
				<div class="tab1-item">
					<label class="title-style2">身份证号 *</label> <label
						class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small>18位二代身份证号
						</small></small></label>
				</div>
				<div class="tab1-item">
					<label class="detail-style2"><input class="form-control"
						name="credit.idCard" type="text" value="${member.credit.idCard}" 
						<c:if test="${member.credit.isCard==true }">
							                       readonly
						           	</c:if>
						 maxlength="18"/></label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">身份证照片 : </label>
					<div>
						<div class="papers">
							<div>
								<span>身份证正面 </span>
							</div>
							<div>
								<img src="${member.credit.cardJustPhoto.getPath()}" id="cardJustPhotoId"  onclick="document.prefectForm.cardJustPhotoFile.click()">
					    <input type="file" name="file"
						id="cardJustPhotoFile" style="display: none;"> 
							<input type="hidden" name="credit.cardJust" value="${member.credit.cardJust}" id="cardJustId">
							</div>
						
	
						</div>
						<div class="papers">
							<div>
								<span>身份证反面</span>
							</div>
							<div>
								<img src="${member.credit.cardReversePhoto.getPath()}" id="cardReversePhotoId"  onclick="document.prefectForm.cardReversePhotoFile.click()">
					    <input type="file" name="file"
						id="cardReversePhotoFile" style="display: none;"> 
							<input type="hidden" name="credit.cardReverse" value="${member.credit.cardReverse}" id="cardReverseId">
							</div>
						</div>
					</div>
					<div style="clear: left;">
						<div class="papers">
							<div>
								<span>工作证</span>
							</div>
							<div>
								<img src="${member.credit.jobCertPhoto.getPath()}" id="jobCertPhotoId"  onclick="document.prefectForm.jobCertPhotoFile.click()">
					    <input type="file" name="file"
						id="jobCertPhotoFile" style="display: none;"> 
							<input type="hidden" name="credit.jobCert" value="${member.credit.jobCert}" id="jobCertId">
							</div>
						</div>
						<div class="papers">
							<div>
								<span>营业执照</span>
							</div>
							<div>
									<img src="${member.credit.businessCertPhoto.getPath()}" id="businessCertPhotoId"  onclick="document.prefectForm.businessCertPhotoFile.click()">
					    <input type="file" name="file"
						id="businessCertPhotoFile" style="display: none;"> 
							<input type="hidden" name="credit.businessCert" value="${member.credit.businessCert}" id="businessCertId">
							</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div style="clear: left;">
			</div>
		
			<div></div>
			<div>
				<input type="text" hidden="true" name="memberId" id="memberId"
					value="${member.id}">
			</div>
			<div class="modal-footer">
						<button type="button" class="btn btn-default"
							data-dismiss="modal">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
						</button>
						<button type="submit" id="btn_submit" class="btn btn-primary">
							<span class="glyphicon glyphicon-floppy-disk"
								aria-hidden="true"></span>提交
						</button>
					</div>
		</div>
	</div>
</form>
