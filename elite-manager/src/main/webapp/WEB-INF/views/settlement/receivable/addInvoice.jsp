<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<form id="submitForm" name="submitForm">
	<div class="modal-dialog" role="document">
		<div class="box box-success">
			<div class="box-header">
				<h3 class="box-title">开发票</h3>
				<div class="box-tools pull-right" data-toggle="tooltip" title="Status">
					<div class="btn-group" data-toggle="btn-toggle">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
				</div>
			</div>
			<hr>

			<div style="margin-left: 15px;">
				<c:forEach items="${list}" var="it">
					<input type="hidden" name="projectId" value="${it.projectId}" />
					<input type="hidden" name="memberId" value="${it.memberId}" />
					<input type="hidden" name="stageId" value="${it.stageId}" />
					<div>客户：${it.memberName}</div>
					<div>
						发票抬头：
						<c:if test="${it.invoiceRise!=null}">
							<input type="text" value="${it.invoiceRise}" name="InvoiceRise" id="invoiceResieId" maxlength="25" />
						</c:if>
						<c:if test="${it.invoiceRise==null}">
							<input type="text" value="${it.companyName}" name="InvoiceRise" id="invoiceResieId" maxlength="25" />
						</c:if>
					</div>
					<input type="hidden" id="allpayAmountId" value="${it.stage.amount}">
					<div>
						发票金额：<input type="number" value="${it.stage.amount}" name="amount" id="amountId" maxlength="10" />元
					</div>
					<div class="ID-pic-add" style="margin-left: 10px;" onclick="document.submitForm.invoiceAttaFile.click()">
						<span class="glyphicon glyphicon-plus"></span> &nbsp;<span>上传发票图片</span>
					</div>
					<div>
						<img src="" class="photo-add" id="invoiceAttaImg" class="ID-pic" /> <input type="file" name="file" id="invoiceAttaFile" style="display: none;"> <input type="hidden" name="invoiceAttaId"
							id="invoiceAttaId" />
					</div>
					<br>
					<div>快递</div>
					<div>
						收件人：<input type="text" value="${it.memberName}" name="recipients" id="recipientsId" maxlength="6" />
					</div>
					<div>
						手机号码：<input type="text" value="${it.memberPhone}" name="phone" id="phoneId" maxlength="11" />
					</div>
					<div>
						快递公司：<input type="text" name="expressName" id="expressNameId" maxlength="25" />
					</div>
					<div>
						快递单号：<input type="text" name="expressNum" id="expressNumId" maxlength="20" />
					</div>
					<div class="ID-pic-add" style="margin-left: 10px;" onclick="document.submitForm.expressAttaFile.click()">
						<span class="glyphicon glyphicon-plus"></span> &nbsp;<span>上传快递单图片</span>
					</div>
					<div>
						<img src="" class="photo-add" id="expressAttaImg" class="ID-pic" /> <input type="file" name="file" id="expressAttaFile" style="display: none;"> <input type="hidden" name="expressAttaId"
							id="expressAttaId" />
					</div>
					<br>
				</c:forEach>
				<div style="margin-left: 15px; margin-right: 15px;">
					<span style="margin-left: 5px">
						<button type="button" id="submitReceipteBtn">提交</button>
					</span> <span style="margin-left: 200px;">
						<button type="button" id="cancel" type="margin-right: 15px;">取消</button>
					</span>
				</div>
				<br> <br>
			</div>

		</div>
	</div>
</form>