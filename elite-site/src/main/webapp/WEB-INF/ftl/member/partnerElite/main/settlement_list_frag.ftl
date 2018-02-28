<#if list?size lt 1>
暂无数据
</#if>
<#list list as it>
	 <div class="se-line <#if it_index%2==0>bgf5</#if>">
	    <div class="line-content">
	        <div class="line-logo bgee" style="<#if it.projectId??>background-color:#${it.project.backgroundColor}</#if>"><#if it.projectId??>${it.project.firstName}</#if></div>
	        <div class="line-time">${it.createTime?string("yyyy-MM-dd")}<span></span>${it.createTime?string("HH:mm")}</div>
	        <div class="line-title">${it.title}</div>
	        <div class="line-money"><#if it.type=='withdraw'>-<#else>+</#if>${it.amount}</div>
	        <div class="line-state">
				<#if it.status=='wait_pay'>
				<#if it.type=='withdraw'>待处理<#else>待付款</#if>
				<#elseif it.status=='success'>交易成功
				<#elseif it.status=='failure'>交易失败
				<#elseif it.status=='close'>交易关闭
				</#if>
			</div>
	        <#--<a class="line-details" href="javascript:void(0);" id="detail" data="${it.id}">详情</a>-->
	    </div>
	</div>
</#list>
