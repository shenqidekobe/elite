 <div class="groupBox" >
    <div class="groupBox_title clearfloat">
	    <div class="part part1">概况</div>
	    <div class="part part2">统计</div>
	    <div class="part part3">状态</div>
    </div>
    <#if list?size lt 1>
	暂无数据
	</#if>
    <#list list as it>
    <div class="group">
	   	<div class="group_head">
	   		<span class="city_icon"></span>
	   		<span class="city_name"><#if it.memberPassport.memberBasic.areaName??>
                         ${it.memberPassport.memberBasic.areaName}
                       <#else>
                        ${it.areaName}
                       </#if></span>
	   		<span class="time_record">备案时间:${it.createTime?string("yyyy.MM.dd")}</span>
	   		<span class="time_register">注册时间: <#if it.memberId??>
                                         ${it.memberPassport.createTime?string("yyyy.MM.dd")}
                                         </#if></span>
	   		<span class="time_last"></span>
	   	</div>
	   	<div class="group_body clearfloat">
	   		<div class="part part1">
	   			<div class="part1_l">
	   				<span>${it.memberPassport.credit.realName}</span>
	   				<span>(
	   				<#if it.memberId??>
	   				${it.memberPassport.nickName}
	   				<#else>
	   				${it.name}
	   				</#if>
	   				)
	   				</span>
	   				<span>${it.memberPhone}</span>
	   			</div>
	   			<div class="part1_r">
	   				<div class="id_title"><span>身份类型:</span><span>
	   				<#if it.memberPassport.partnerElite.type??>
	   				${it.memberPassport.partnerElite.type.label}
	   				<#else>
	   				${it.type.label}
	   				</#if>
	   				</span></div>
	   				<div class="id_data">
	   				 <#if it.memberPassport.partnerElite.companyName??>
	    				     ${it.memberPassport.partnerElite.companyName}
	    				     <#else>
                              ${it.companyName}
                          </#if> 
	   				</div>
	   			</div>
	   		</div>
		    <div class="part part2">
		    	<div class="part2_l">
		    		<div class="record"><span>备案人才数（入驻数）:</span><span>${it.putCount}（${it.enterCount}）</span></div>
		    		<div class="enter"><span>备案渠道数（入驻数）:</span><span>${it.partnerPutCount}（${it.partnerEnterCount}）</span></div>
		    	</div>
	   			<div class="part2_r">
	   				<div class="earnings_total"><span>收益总额:</span><span>￥
	   				 <#if it.channelIncome??>
                       ${it.channelIncome}
                       <#else>
                       0
                       </#if>
	   				</span></div>
		    		<div class="earnings_mine"><span>我的收益:</span><span>￥
                        <#if it.parentIncome??>
                       ${it.parentIncome}
                       <#else>
                       0
                       </#if>
                      </span></div>
	   			</div>
		    </div>
		    <div class="part part3">${it.status.label}</div>
	   	</div>
	   	</div>
    </#list>
    
</div>
<#include "/init/pager.ftl"/>

<dl class="noteBox">
    <dt class="clearfloat"><span class="dt_icon"></span><span class="dt_text">Tips</span></dt>
    <dd class="clearfloat"><span class="dd_icon"></span><span class="dd_text">备案更多渠道可以帮助您轻松获得更多渠道收益，您的直接渠道和间接渠道都可以给您带来收益。</span></dd>
    <dd class="clearfloat"><span class="dd_icon"></span><span class="dd_text">备案后请协助和指导您的渠道注册和开展工作。</span></dd>
    <dd class="clearfloat"><span class="dd_icon"></span><span class="dd_text">平台不会向任何第三方泄漏您提供的任何渠道资料。</span></dd>
</dl>
