<input type="hidden" id="listallCountId" value="${allCount}">
<input type="hidden" id="listregisterCountId" value="${registerCount}">
<input type="hidden" id="listenterCountId" value="${enterCount}">
<div id="table1">
    <div class="table_title clearfloat">
	    <div class="part1 part">概况</div>
	    <div class="part2 part">状态</div>
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
                       <#if it.memberBasic.areaName??>
                         ${it.memberBasic.areaName}
                       <#else>
                        ${it.areaName}
                       </#if>
                 </span>
	    		<span class="record_time">备案时间：${it.createTime?string("yyyy.MM.dd")}</span>
	    		<span class="register_time">注册时间：
	    		                         <#if it.memberId??>
                                         ${it.memberPassport.createTime?string("yyyy.MM.dd")}
                                         </#if>
                         </span>
	    	</div>
	    	<div class="group_body clearfloat">
	    		<div class="part1 part">
	    			<div class="part1_l">
	    				<span class="name"><#if it.memberCredit.realName??>
                     ${it.memberCredit.realName}
                     <#else>
                      ${it.name}
                     </#if></span>
	    				<span class="nickname">
	    				<#if it.memberPassport.nickName??>
	    				(
                     ${it.memberPassport.nickName}
                         )
                         </#if>
	    				
	    				</span>
	    				<span class="phone">${it.phone}</span>
	    			</div>
	    			<div class="part1_r">
	    				<div class="attribute"><span>人才属性：</span><span>
                      <#if it.memberElite.eliteJobs??>
                         <#list it.memberElite.eliteJobs as jobs>
                         ${jobs.jobRoleVal}
                        </#list>
                       <#else>
                        ${it.defineRoleVal}
                       </#if>
                      </span></div>
	    				<div class="identity"><span>平台身份：</span><span> 
	    				 <#if it.memberId??>
	    				    <#if it.memberElite.ctoed==true>
	    				      CTO
	    				    <#else>
	    				                   精英
	    				    </#if>
				          <#else>
			                                        -----
			              </#if>
	    				
	    				</span></div>
	    				<div class="time"><span>工作年限：</span><span>
                          <#if it.memberElite.jobAge??>
                         ${it.memberElite.jobAgeVal}
                       <#else>
                        ${it.jobAgeVal}
                       </#if>
                             </span></div>
	    			</div>
	    		</div>
			    <div class="part2 part">
			   ${it.status.label}
			  </div>
			    <#if it.memberId??&&it.memberElite.resumeAttaId??>
			     <div class="part3 part"><button type="button" class="downloadBtn"> <a href="${it.elite.resumeAtta}" target="_blank">下载简历</a></button></div>
	            <#elseif it.attaId??>
	             <div class="part3 part"><button type="button" class="downloadBtn"> <a href="${it.atta.downPath}" target="_blank">下载简历</a></button></div>
	             <#else>
	             <div class="part3 part"><button type="button" class="downloadBtn" id="uploadAttaBtnId" data="${it.phone}" >上传简历</button></div>
	            </#if>
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
</dl>