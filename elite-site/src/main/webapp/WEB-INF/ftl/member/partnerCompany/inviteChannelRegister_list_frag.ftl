<!--渠道查询页面-->
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
                          </#if>
                           </span>
	    				<span class="phone">${it.memberPhone}</span>
	    			</div>
	    			<div class="part1_r">
	    				<div class="attribute"><span>身份类型：</span><span> 
                          <#if it.memberPassport.partnerCompany.type??>
                             ${it.memberPassport.partnerCompany.type.label}
                         <#else>
                              ${it.companyType.label}
                          </#if> 
                         </span></div>
	    				<div class="identity"><span></span><span> 
	    				   <#if it.memberPassport.partnerCompany.companyName??>
	    				     ${it.memberPassport.partnerCompany.companyName}
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

<#--已备案项目渠道-->
    <dl class="noteBox" id="channel_record">
        <dt class="clearfloat"><span class="note_icon"></span>Tips</dt>
        <dd class="clearfloat"><span class="dd_icon"></span><span class="dd_text">备案更多渠道可以帮助您轻松获得更多渠道收益，您的直接渠道和间接渠道都可以给您带来收益。</span></dd>
        <dd class="clearfloat"><span class="dd_icon"></span><span class="dd_text">备案后请协助和指导您的渠道注册和开展工作。</span></dd>
        <dd class="clearfloat"><span class="dd_icon"></span><span class="dd_text">平台不会向任何第三方泄漏您提供的任何渠道资料。</span></dd>
    </dl>

    <#--已注册项目渠道-->
    <dl class="noteBox" id="channel_registered" style="display:none;">
        <dt class="clearfloat"><span class="note_icon"></span>Tips</dt>
        <dd class="clearfloat"><span class="dd_icon"></span><span class="dd_text">备案更多渠道可以帮助您轻松获得更多渠道收益，您的直接渠道和间接渠道都可以给您带来收益。</span></dd>
        <dd class="clearfloat"><span class="dd_icon"></span><span class="dd_text">备案后请协助和指导您的渠道注册和开展工作。</span></dd>
        <dd class="clearfloat"><span class="dd_icon"></span><span class="dd_text">对于备案后的用户您可以使用“提醒”操作，我们会给其发消息提醒其完善信息并进行备案操作。</span></dd>
        <dd class="clearfloat"><span class="dd_icon"></span><span class="dd_text">为避免对用户的打扰，提醒操作每天对每个备案用户只能使用一次。</span></dd>
        <dd class="clearfloat"><span class="dd_icon"></span><span class="dd_text">平台不会向任何第三方泄漏您提供的任何渠道资料。</span></dd>
     </dl>    

