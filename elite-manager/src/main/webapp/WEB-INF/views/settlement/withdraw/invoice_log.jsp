<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<div class="modal-dialog" role="document">
	<form name="invoiceForm">
		<div class="box box-success">
			<div class="box-header">
				<h3 class="box-title">提现详情</h3>
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
				<div>
					<fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
					&nbsp;申请提现${it.amount}元
				</div>
				<div>当前状态：${it.status.label}</div>
				<div>提现会员帐号：${it.memberPassport.account}</div>
				<div>提现单号：${it.orderId}</div>
				<div>申请时间：<fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
				<div>提现金额：${it.amount}元</div>
				<div>实际打款额度：${it.receiptAmount}元（扣税：${it.taxAmount}元）</div>
				<div>财务确认时间：${it.affirmTime}</div>
				<div>打款时间：${it.processTime}</div>
				<div>第三方流水ID：${it.thirdSerial}</div>
				<div>打款凭证</div>
				<div class="tab1-item">
					<label class="title-style2">
						<div class="papers">
							<div>
								<img src="${it.withdrawAtta.path}"> <span> <c:if test="${it.withdrawAttaId!=null}">
										<a href="${it.withdrawAtta.downPath}" target="_blank">点击下载</a>
									</c:if>
								</span>
							</div>
						</div>
					</label>
				</div>
			</div>
		</div>
	</form>
</div>
