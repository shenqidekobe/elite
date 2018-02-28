<!--备案项目入驻list-->
<input type="hidden" id="listallCountId" value="${allCount}"/>
<input type="hidden" id="listenterCountId" value="${enterCount}"/>
<div id="table1">
    <div class="table_title clearfloat">
	    <div class="part1 part">概况</div>
	    <div class="part2 part">项目状态</div>
	    <div class="part3 part">操作</div>
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
                       <#if it.project.areaName??>
                         ${it.project.areaName}
                       <#else>
                        ${it.partnerProject.areaName}
                       </#if>
                 </span>
	    		<span class="record_time">备案时间：
	    		  <#if it.partnerProject??>
                  ${it.partnerProject.createTime?string("yyyy.MM.dd")}
                   </#if>
                      </span>
	    		<span class="register_time">注册时间：
                                         ${it.createTime?string("yyyy.MM.dd")}
                         </span>
                 <span class="time_last">
                 <#if it.project.contactpartner=='true'>
                                                      可以以我的名义联系
                 </#if>
                 </span>
	    	</div>
	    	<div class="group_body clearfloat">
	    		<div class="part1 part">
	    			<div class="part1_l">
	    				<span class="name">${it.partnerProject.name}|${it.project.name}</span>
	    				<span class="nickname">
	    				(${it.project.memberPassport.nickName})
	    				</span>
	    				<span class="phone">${it.project.memberPassport.accountOffSuffix}</span>
	    			</div>
	    			<div class="part1_r">
	    				<div class="attribute">
	    				<#if it.project.productStageVal??>
	    				<span>项目阶段：</span><span>
	    				 ${it.project.productStageVal}
	    				<#else>
	    				<#if it.partnerProject.productStageVal??>
	    				<span>项目阶段：</span><span>
	    				${it.partnerProject.productStageVal}
                        </#if>
	    				</#if>
                      </span></div>
	    				<div class="identity"><span>所属行业：</span><span> 
	    				 <#list it.project.industryValList as industryVal>
                       <#if industryVal_index!=0>
                                 ,
                         </#if>
                             ${industryVal}
                         </#list>
	    				</span></div>
	    				<div class="time"><span>开发类型：</span><span id="solutionsId" data="${it.project.solutionVals}">
                       <#if it.project.solutionVals?length gt 8>${it.project.solutionVals?substring(0,8)}...<#else>${it.project.solutionVals}</#if>
                             </span></div>
	    			</div>
	    		</div>
			    <div class="part2 part">
			    ${it.project.status.label}
			  </div>
	             <div class="part3 part"><button type="button" class="downloadBtn" id="viewProjectInfoId"  data="${it.project.introStr}">查看项目描述</button></div>
	    	</div>
	     </div>
    </#list>
    </div>
  </div>
<#include "/init/pager.ftl"/>

<#--已备案-->
<dl class="noteBox" id="note_record">
	<dt class="clearfloat"><span class="note_icon"></span>Tips</dt>
	<dd class="clearfloat""><span class="dd_icon"></span><span class="dd_text">为了提高项目的成功率，从而提升您的收益，请提供尽可能详尽的项目资料。</span></dd>
	<dd class="clearfloat"><span class="dd_icon"></span><span class="dd_text">备案后，您需要和客户进行初步沟通，介绍云英汇平台，并督促其注册发布项目，如有需要，您可以协助其操作。</span></dd>
	<dd class="clearfloat"><span class="dd_icon"></span><span class="dd_text">平台不会向任何第三方泄漏您提供的任何项目资料。</span></dd>
</dl>

<#--已入驻-->
<dl class="noteBox" id="note_housed" style="display:none;">
	<dt class="clearfloat"><span class="note_icon"></span>Tips</dt>
	<dd class="clearfloat"><span class="dd_icon"></span><span class="dd_text">为了提高项目的成功率，从而提升您的收益，请提供尽可能详尽的项目资料。</span></dd>
	<dd class="clearfloat"><span class="dd_icon"></span><span class="dd_text">备案后，您需要和客户进行初步沟通，介绍云英汇平台，并督促其注册发布项目，如有需要，您可以协助其操作。</span></dd>
	<dd class="clearfloat"><span class="dd_icon"></span><span class="dd_text">对于入驻后长期发项目的用户您可以使用“提醒”操作，我们会给其发消息提醒发布项目。</span></dd>
	<dd class="clearfloat"><span class="dd_icon"></span><span class="dd_text">为避免对用户的打扰，提醒操作每天对每个备案用户只能使用一次。平台不会向任何第三方泄漏您提供的任何项目资料。</span></dd>
</dl>

