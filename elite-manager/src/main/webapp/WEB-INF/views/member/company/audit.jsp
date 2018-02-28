<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<form id="auditForm" method="post">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">审核项目方资料</h4>
		</div>
		<div class="modal-body">
			<div>
				<div class="">
					<label class="title-style3">1. 基本资料</label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">真实姓名 : </label> <label
						class="detail-style2">${member.credit.realName}</label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">生日年月 : </label> <label
						class="detail-style2"><fmt:formatDate
							value="${member.basic.birthday}" pattern="yyyy-MM-dd" /></label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">所在城市 :</label> <label
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
					<label class="title-style2">头像 : </label>
					<div>
						<img src="${member.basic.memberPhoto.path}" class="head-icon">
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
					<label class="title-style3">3.征信信息</label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">身份证号 : </label> <label
						class="detail-style2">${member.credit.idCard}</label>
				</div>
				<div class="tab1-item">
					<label class="title-style2"></label>
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
								<span>工作证</span>
							</div>
							<div>
								<img src="${member.credit.jobCertPhoto.getPath()}">
							</div>
						</div>
						<div class="papers">
							<div>
								<span>名片</span>
							</div>
							<div>
								<img src="${member.credit.visitingCertPhoto.getPath()}">
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

					</div>
				</div>
			</div>
			<div>
				<textarea class="form-control" rows="4" placeholder="请输入原因"
					name="auditReason" id="auditReasonId">${member.company.auditReason}</textarea>
			</div>
			<div>
				<input type="text" hidden="true" name="status" id="auditStatusId">
				<input type="text" hidden="true" name="memberId" id="auditId"
					value="${member.id}">
				<div>
							<button type="button" id="refuseAudit">不通过</button>
							<button type="button" id="passAudit">通过</button>
				</div>


			</div>
		</div>
	</div>
</form>
