 <input type="hidden" id="myIncomeId" value="${myIncome}">
 <#if proportion??>
  	<input type="hidden" id="proportionId" value="${proportion*100}%">
 </#if> 	
<!--渠道本身收益-->
	   <div class="table_head">
			<div class="part" style="width:25%;border-right:1px solid #fff;">项目编号</div>
			<div class="part" style="width:25%;border-right:1px solid #fff;">项目名称</div>
			<div class="part" style="width:20%;border-right:1px solid #fff;"><span>项目总额</span><span>(元)</span></div>
			<div class="part" style="width:15%"><span>已验收额</span><span>(元)</span></div>
			<div class="part" style="width:15%"><span>我的收益额</span><span>(元)</span></div>
		</div>
		<div class="table_body">
			 <#list list as it>
			<div class="group">
				<div class="part" style="width:25%">${it.projectNum}</div>
				<div class="part" style="width:25%" title="${it.name}">
                <#if it.name?length gt 7>${it.name?substring(0,7)}<#else>${it.name}</#if>

                </div>
				<div class="part" style="width:20%">${it.totalAmount}</div>
				<div class="part" style="width:15%">${it.allPayAmount}</div>
				<div class="part" style="width:15%">${it.partnerIncome}</div>
			</div>
			</#list>
        </div>
<#include "/init/pager.ftl"/>