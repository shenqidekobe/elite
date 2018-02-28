<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<div class="modal-dialog" role="document">
	<div class="box box-success">
		<div class="box-header">
			<h3 class="box-title">已收款</h3>
			<div class="box-tools pull-right" data-toggle="tooltip" title="Status">
				<div class="btn-group" data-toggle="btn-toggle">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
			</div>
		</div>
		<hr>
		<c:forEach items="${list}" var="it">
			<div style="margin-left: 15px;">
			    <div>项目编号：${it.project.projectNum}</div>
			 	<div>项目名称：${it.project.name}</div>
				<div>所属阶段：${it.stage.stageCodeVal}</div>
				<div>客户：${it.memberName}</div>
				<div>客户账号：${it.payAccount}</div>
				<div>
					打款时间：
					<fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				</div>
				<div>应收金额：${it.orderAmount}元</div>
				<div>实收金额：${it.receiptAmount}元</div>
				<div></div>
				 <div class="tab1-item">
					<label class="title-style2">
						<div>
							<div class="papers">
								<div>
									<%-- <img src="${it.voucherAtta.path}"> --%>
								</div>
								<div></div>
							</div>

						</div>
					</label>
				</div> 
		</c:forEach>
	</div>

</div>
</div>
