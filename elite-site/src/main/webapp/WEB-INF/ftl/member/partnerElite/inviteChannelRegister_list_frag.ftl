<input type="hidden" id="listallCountId" value="${allCount}"/>
<input type="hidden" id="listregisterCountId" value="${registerCount}"/>
<input type="hidden" id="listenterCountId" value="${enterCount}"/>
<div class="person-table table" id="table1">
    <div class="table_title clearfloat">
	    <div class="part1 part">概况</div>
	    <div class="part2 part">状态</div>
    </div>
    <div class="table_content">
    <#if list?size lt 1>
	暂无数据
	</#if>
    <#list list as it>
	    <div class="groupBox">
	    	<div class="group_head">
	    		<span class="city_icon"></span>
	    		<span class="city_name">
	    		 <#if it.memberPassport.basic.id??>
               ${it.memberPassport.basic.areaName}
               <#else>
                ${it.areaName}
               </#if>
	    		</span>
	    		<span class="record_time">备案时间：${it.createTime?string("yyyy.MM.dd")}</span>
	    		<span class="register_time">注册时间：<#if it.memberId??>${it.memberPassport.createTime?string("yyyy.MM.dd")}</#if></span>
	    	</div>
	    	<div class="group_body clearfloat">
	    		<div class="part1 part">
	    			<div class="part1_l">
	    				<span class="name">
	    					<#if it.memberPassport.credit.realName??>
                         ${it.memberPassport.credit.realName}
                        <#else>
                        ${it.name}
                        </#if>
	    				</span>
	    				<span class="nickname">
                               <#if it.memberPassport.nickName??>
                               (${it.memberPassport.nickName})
                          </#if></span>
	    				<span class="phone">${it.memberPhone}</span>
	    			</div>
	    			<div class="part1_r">
	    				<div class="attribute"><span>人才属性：</span><span> 
	    				<#if it.memberPassport.partnerElite.type??>
	    				${it.memberPassport.partnerElite.type.label}
	    				<#else>
	    				 ${it.type.label}
	    				</#if>
                       
                        </span></div>
	    				<div class="identity"><span></span><span>
	    				 <#if it.memberPassport.partnerElite.companyName??>
	    				     ${it.memberPassport.partnerElite.companyName}
	    				     <#else>
                              ${it.companyName}
                          </#if> 
				         </span></div>
	    			</div>
	    		</div>
			    <div class="part2 part">${it.status.label}</div>
	    	</div>
	     </div>
    </#list>
    </div>
</div>
<#include "/init/pager.ftl"/>

<dl class="noteBox">
	<dt class="clearfloat"><span class="note_icon"></span>Tips</dt>
	<dd><span class="dd_icon"></span>为了能够能好地服务于您备案的人才，提升精英的和您的收益，备案时请提供尽可能详尽的资料。</dd>
	<dd><span class="dd_icon"></span>备案后，您需要给精英介绍云英汇平台，并督促其注册和接活，如有需要，您可以协助其操作。</dd>
	<dd><span class="dd_icon"></span>对于入住后长期未完善资料或者未接活的用户，您可以使用“提醒”操作，我们会给其发消息提醒。</dd>
	<dd><span class="dd_icon"></span>为避免对用户的打扰，提醒操作每天对每个备案用户只能使用一次。</dd>
</dl>