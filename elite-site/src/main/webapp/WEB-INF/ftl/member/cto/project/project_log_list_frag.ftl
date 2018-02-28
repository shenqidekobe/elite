<div class="project-log">
    <#assign stageName=""/>
	<#list list as lpt>
		<div class="log">
		    <#assign stageName="${lpt.stageName},${stageName}"/>
		    <#assign xl="${stageName}"?split(",")/>
		    <#if !xl?seq_contains(lpt.stageName)||lpt_index==0>
		       <div class="period-btn">${lpt.stageName}</div>
		       <span class="log-date">${lpt.createTime?string("yyyy-MM-dd")}</span>
		       <#assign stageName=""/>
		    </#if>
		    <span class="log-time">${lpt.createTime?string("HH:mm")}</span>
		    <div class="log-point"></div>
		    <span class="log-content">${lpt.content}</span>
		</div>
	</#list>
</div>
