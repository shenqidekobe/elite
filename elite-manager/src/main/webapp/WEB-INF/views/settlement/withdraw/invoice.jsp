<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<div class="modal-dialog" role="document">
	<form name="invoiceForm">
		<div class="box box-success">
			<div class="box-header">
				<h3 class="box-title">发票管理</h3>
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
					情况：
					<c:if test="${it.invoiceWay=='platform'}">平台代开发票</c:if>
					<c:if test="${it.invoiceWay=='customer'}">客户自开发票 </c:if>
				</div>
				<div>
					打款额：<input type="text"  id="receiptAmount" value="${it.receiptAmount}"/>元
				</div>
				<div>
					打款渠道：${it.receiptWay.label}
				</div>
				<div>
					打款账号：【${it.bank.bankName}】${it.bank.bankCard}
				</div>
<%-- 				<div>发票图片</div>
				<div class="tab1-item">
					<label class="title-style2">
						<div class="papers">
							<div>
								<img src="${it.atta.path}" id="invoiceAttaImg">
							</div>
							<c:if test="${it.invoiceWay=='customer'}">
								<span><a href="${it.atta.downPath}" target="_blank">点击下载</a></span>
							</c:if>
							<c:if test="${it.invoiceWay=='platform'}">
									<span class="glyphicon glyphicon-plus"></span> &nbsp;<span>上传</span> 
									<input type="file"
										name="file" id="invoiceAttaFile" style="display: none;"> 
								</div>
							</c:if>

						</div>
					</label>
				</div>
				<input type="hidden" name="invoiceAttaId" id="invoiceAttaId" value="${it.invoiceAttaId}"/>
				<div style="margin-right: 15px;">
					<c:if test="${it.invoiceWay=='platform'}">
                                                                                是否确认
							</c:if>
							<c:if test="${it.invoiceWay=='customer'}">
							是否收到
							</c:if>
					      <input type="hidden" id="withdrawId" value="${it.id }"/>
					 &nbsp;<input name="receive" type="radio"  value="YES"/>是 &nbsp;<input name="receive" type="radio" value="NO"/>否
					<button name="btn_sub" type="button" id="submitInvoice" class="btn btn-sm btn-info pull-right">
						<i class="ace-icon fa fa-check"></i> 已上传发票
					</button>
					<div></div>
					<hr>
				</div> --%>
			</div>
		</div>
	</form>
</div>
