 <input type="hidden" id="myIncomeId" value="${myIncome}">
<!--渠道本身收益-->
	<div class="table_head">
		<div class="part" style="width:25%;border-right:1px solid #fff;">人才编号</div>
		<div class="part" style="width:25%;border-right:1px solid #fff;">人才名称</div>
		<div class="part" style="width:25%;border-right:1px solid #fff;">人才收益总金额<span>(元)</span></div>
		<div class="part" style="width:25%">我的收益额<span>(元)</span></div>
	</div>
	<div class="table_body">
		 <#list list as it>
		<div class="group">
			<div class="part" style="width:25%">${it.memberPassport.memberNum}</div>
			<div class="part" style="width:25%">${it.memberPassport.nickName}</div>
			<div class="part" style="width:25%">${it.tasksInAmount}</div>
			<div class="part" style="width:25%">${it.partnerAmount}</div>
		</div>
		</#list>
	</div>
<#include "/init/pager.ftl"/>