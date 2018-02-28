		<div class="table_head clearfloat">
				<div class="part" style="width:15%;border-right:1px solid #fff;">核算时间</div>
				<div class="part" style="width:15%;border-right:1px solid #fff;">来源类型</div>
				<div class="part" style="width:15%;border-right:1px solid #fff;">来源姓名</div>
				<div class="part" style="width:20%;border-right:1px solid #fff;">项目/任务名称</div>
				<!--<div class="part" style="width:15%;border-right:1px solid #fff;">所属阶段</div>-->
				<div class="part" style="width:10%;border-right:1px solid #fff;">金额<span>(元)</span></div>
				<div class="part" style="width:10%;border-right:1px solid #fff;">比例</div>
				<div class="part" style="width:10%;">收益额<span>(元)</span></div>
			</div>
	      <#list incomelist as it>
			<div class="table_body clearfloat">
				<div class="group">
					<div class="part" style="width:15%;">${it.createTime?string("yyyy.MM.dd")}</div>
					<div class="part" style="width:15%;" title="${it.incomeType.label}">
                     <#if it.incomeType.label?length gt 6>${it.incomeType.label?substring(0,6)}<#else>${it.incomeType.label}</#if>
                    </div>
					<div class="part" style="width:15%;">${it.sourceMemberRealName}</div>
					<#if (it.sourceMemberId??&&it.sourceMemberPassport.currentType=='cto')||!it.taskId??>
					  <div class="part" style="width:20%;" title="${it.project.name}">
                        <#if it.project.name?length gt 7>${it.project.name?substring(0,7)}<#else>${it.project.name}</#if>
                     </div>
                    <#else>
                       <div class="part" style="width:20%;" title="${it.task.name}">
                        <#if it.task.name?length gt 7> ${it.task.name?substring(0,7)}<#else>${it.task.name}</#if>
                       </div>
					</#if>
                    <#if it.sourceMemberId??&&it.sourceMemberPassport.currentType=='cto'||!it.taskId??>
                    <div class="part" style="width:10%;">${it.stage.amount}</div>
                    <#else>
                    <div class="part" style="width:10%;">${it.task.amount}</div>
                    </#if>
					<div class="part" style="width:10%;"><#if it.commissionRatio??>${it.commissionRatio*100}%</#if></div>
					<div class="part" style="width:10%;">${it.amount}</div>
				</div>
			</div>
			</#list>
<#include "/init/pager.ftl"/>