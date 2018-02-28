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
			<h4 class="modal-title" id="myModalLabel">渠道审核</h4>
		</div>
		<div class="modal-body">
			<div>
				<div class="tab1-item">
					<label class="title-style2">用户名: </label> <label
						class="detail-style2">${it.nickName}</label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">出生年月: </label> <label
						class="detail-style2"><fmt:formatDate value="${it.basic.birthday}" pattern="yyyy-MM-dd" /></label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">性别: </label> <label
						class="detail-style2"><c:if test="${it.basic.sex =='F'}">女</c:if><c:if test="${it.basic.sex=='M'}">男</c:if></label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">手机号: </label> <label
						class="detail-style2">${it.account}</label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">邮箱: </label> <label
						class="detail-style2">${it.basic.email}</label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">姓名: </label> <label
						class="detail-style2">${it.credit.realName}</label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">身份证号: </label> <label
						class="detail-style2">${it.credit.idCard}</label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">类别: </label> <label
						class="detail-style2">${it.partnerElite.type.label}</label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">从业年限: </label> <label
						class="detail-style2">${it.partnerElite.jobAgeVal}</label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">聚焦行业: </label> <label
						class="detail-style2">${it.partnerElite.attentionIndustryVal}
						</label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">擅长职位: </label> <label
						class="detail-style2">${it.partnerElite.goodatJobVal}
						</label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">所在城市 : </label> <label
						class="detail-style2">${it.basic.areaName}</label>
				</div>
				<div class="tab1-item">
					<label class="title-style2">所在公司 : </label> <label
						class="detail-style2">${it.partnerElite.companyName}</label>
				</div>
				<c:if test="${it.partnerElite.resumeAattaId!=null}">
				<div class="tab1-item">
					<label class="title-style2">个人简历: </label> 
					<label
						class="detail-style2">
                    	 ${it.partnerElite.atta.fileName} 
                    	 
												 <a href="${it.partnerElite.atta.downPath}" target="_blank">
										<span class="glyphicon glyphicon-download custom-icon"></span>
									</a> 

               </label>
				</div>
				</c:if>
				
			</div>
		
			<div>
				<div class="tab1-item">
					<label class="title-style2">证件照片 </label>
					<div>
						<div class="papers">
							<div>
								<span>身份证正面</span>
							</div>
							<div>
								<img src="${it.credit.cardJustPhoto.path}">
							</div>
						</div>
						<div class="papers">
							<div>
								<span>公司营业执照复印件</span>
							</div>
							<div>
								<img src="${it.credit.businessCertPhoto.path}">
							</div>
						</div>
					</div>
					<div style="clear: left;">
						<div class="papers">
							<div>
								<span>公司名片或者工作证</span>
							</div>
							<div>
								<img src="${it.credit.jobCertPhoto.path}">
							</div>
						</div>
						<div class="papers">
							<div>
								<span>人力资源相关行业培训证书</span>
							</div>
							<div>
								<img src="${it.credit.pmpCretPhoto.path}">
							</div>
						</div>
					</div>
				</div>
			</div>

			<div>
				<textarea class="form-control" rows="4" placeholder="请输入原因"
					name="auditReason" id="auditReasonId"></textarea>
			</div>
			<div>
				<input type="text" hidden="true" name="status" id="auditStatusId">
				<input type="text" hidden="true"  id="aduitMemberId"
					value="${it.id}">
				<div>
							<button type="button" id="refuseAduit">不通过</button>
							<button type="button" id="passAduit">通过</button>
				</div>


			</div>
		</div>
	</div>
</form>
