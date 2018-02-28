<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<form id="submitForm">
	<div class="modal-dialog" role="document">
		<div class="box box-success">
			<div class="box-header">
				<h3 class="box-title">确认收款</h3>
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
			 <div>项目编号：${it.project.projectNum}</div>
			 <div>项目名称：${it.project.name}</div>
			 <div>所属阶段：${it.stageCodeVal}</div>
			 <div>客户：<input type="text" placeholder="请输入客户名称" name="memberName" id="memberNameId" maxlength="20"></div>
			 <div>客户银行卡号：<input type="text" placeholder="请输入客户银行卡号" name="payAccount" id="payAccountId" maxlength="19"/></div>
			 <div>打款时间:<input type="text" id="receiptTimeId" name="payTime"/></div>
			 <!-- <div>打款方式:<input type="text" placeholder="请输入客户打款方式" name="payType"/></div> -->
			 <div>此阶段应收金额：
			  <c:if test="${it.firstStage==true }">
			 	 ${it.firstAmount} 元 (第一阶段，减去意向金后所需支付费用)
			 	 <input type="hidden" name="orderAmount" value="${it.firstAmount}" >
			 </c:if>
			 <c:if test="${it.firstStage==false }">
			 	 ${it.amount} 元
			 	 <input type="hidden" name="orderAmount" value="${it.amount}" >
			 </c:if> 
		
			</div>
			 <div>实收金额：<input type="number" placeholder="请输入实收金额" name="receiptAmount" id="receiptAmountId" maxlength="10">元</div>
			 <input type="hidden" name="stageId"  value="${it.id}" />
			 <input type="hidden" name="type"  value="prostage" />
			 <input type="hidden" name="memberId"  value="${it.project.companyId}" />
			 <div style="margin-left: 15px; margin-right: 15px;">
			<span style="margin-left: 5px">
				<button  type="button"  id="submitReceipteBtn">
				             已确认
				</button>
			</span>
			<span style="margin-left: 300px;">
				<button type="button"  id="receiptCancelBtn" type="margin-right: 15px;">
				      取消
				</button>
				</span>
			</div>
			<br>
			</div>
		
		</div>
	</div>
</form>