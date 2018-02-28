<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<div class="modal-dialog" role="document">
	<form name="invoiceForm">
		<div class="box box-success">
			<div class="box-header">
				<h3 class="box-title">提现处理</h3>
				<div class="box-tools pull-right" data-toggle="tooltip"
					title="Status">
					<div class="btn-group" data-toggle="btn-toggle">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
				</div>
			</div>
			<hr>
			<input type="hidden" name="invoiceAttaId" id="invoiceAttaId" value="${it.invoiceAttaId}" />
			<input type="hidden" name="withdrawAttaId" id="withdrawAttaId" value="${it.withdrawAttaId}" />
			<div style="margin-left: 15px;">
				<div>提现金额：${it.amount}元</div>
				<div>提现单号：${it.orderId}</div>
				<div>提现会员帐号：${it.memberPassport.account}</div>
				<div>申请时间：<fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
				<%-- <div>发票</div>
				<div class="tab1-item">
					<label class="title-style2">
						<div class="papers">
							<div>
								<img src="${it.atta.path}" id="invoiceAttaImg">
							</div>
							<span class="glyphicon glyphicon-plus"></span> &nbsp;<span>上传</span>
							<input type="file" name="file" id="invoiceAttaImgFile" style="display: none;" />
						</div>
					</label>
				</div> --%>
				<div>实际打款金额：${it.receiptAmount} &nbsp;元</div>
				<div>提现人户名：${it.bank.holder}</div>
				<div>提现人银行卡号：【${it.bank.bankName}】${it.bank.bankCard}</div>
				<div>打款凭证</div>
				<div class="tab1-item">
					<label class="title-style2">
						<div class="papers">
							<div>
								<img src="${it.withdrawAtta.path}" id="withdrawAttaImg">
							</div>
							<span class="glyphicon glyphicon-plus"></span> &nbsp;<span>上传</span>
							<input type="file" name="file" id="withdrawAttaFile" style="display: none;" />
						</div>
					</label>
				</div>
				<div style="margin-right: 15px;">
					<input type="hidden" id="withdrawId" value="${it.id }" /> &nbsp;
					<button name="btn_sub" type="button" id="submitPay"
						class="btn btn-sm btn-info pull-right">
						<i class="ace-icon fa fa-check"></i>确认打款
					</button>
					<div></div>
					<hr>
				</div>
			</div>


		</div>
	</form>
</div>
