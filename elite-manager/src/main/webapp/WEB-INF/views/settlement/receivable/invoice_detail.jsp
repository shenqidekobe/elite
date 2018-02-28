<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
	<div class="modal-dialog" role="document">
		<div class="box box-success">
			<div class="box-header">
				<h3 class="box-title">已开发票</h3>
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
			 <div>客户：${it.memberPassport.credit.realName}</div>
			 <div>发票抬头：${it.invoiceRise}</div>
			 <div>发票金额：${it.amount}元</div>
			 <img>
			 <div>快递</div>
			 <div>收件人：${it.recipients}</div>
			 <div>手机号码：${it.phone}</div>
			 <div>快递公司：${it.expressName}</div>
			 <div>快递单号：${it.expressNum}</div>
			 <div class="tab1-item">
					<label class="title-style2">
					<div>
						<div class="papers">
							<div>
								<img src="${it.invoiceAtta.path}">
							</div>
							<div>
								<span style="margin-left:30px;">发票</span>
							</div>
						</div>
						<div class="papers">
							
							<div>
								<img src="${it.expressAtta.path}">
							</div>
							<div>
								<span style="margin-left: 30px;">快递</span>
							</div>
						</div>
					</div>
					</label>
			</div>
			 
			</div>
		
		</div>
	</div>
