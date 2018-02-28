<#list result as team>
		<div class="person-box">
        <div class="column1">
            <div style="padding:20px;">
            	<#if team.info.basic.photoId??>
            		<div class="person-logo" style="background:url(${team.info.basic.memberPhoto.path}) no-repeat scroll 0 0 / 100px 100px"></div>
            	<#else>
            		<div class="person-logo" style="background:url(${_PATH}/res/images/default.jpg) no-repeat scroll 0 0 / 100px 100px"></div>
            	</#if>
                
                <div class="person-index">
                    <div>
                        <span class="person-name">${team.info.nickName}</span>
                        <#list team.info.elite.eliteJobs as job>
	                        <div class="work-label">
	                        	<div class="title_div"><div class="title-text">${job.jobRoleVal}</div><div class="title-grade">L${job.level}</div></div>
	                        </div>	
			             </#list>
                        <div class="coop-box">搭伙${team.partnershipNum}次</div>
                    </div>
                    <div style="font-size: 12px;margin-top:14px;">
                        <p style="margin-bottom: 6px;">
                            <span class="index">擅长领域：</span>&nbsp;<span class="value">
                            	<#list team.info.elite.eliteJobs as job>
			                   		<#list job.jobAdeptValList as adept>
			                   		  <#if adept_index lt 5> ${adept.value}</#if>
			                   		</#list>
			                   </#list>
                            </span>
                        </p>
                        <p style="margin-bottom: 6px;">
                            <span class="index">关注行业：</span>&nbsp;<span class="value">
                            	<#list team.info.elite.attentionIndustryListVal as indus>
                            		<span class="value">${indus}</span>
                            	</#list>
                            </span>
                        </p>
                        <#--
                        <div>
                            <img src="/res/images/fans_icon.png" width="16" height="16" style="vertical-align:sub;">
                            &nbsp;粉丝数量：&nbsp;<span>136</span>
                            <img src="/res/images/eye_icon.png" width="18" height="12" style="vertical-align:sub;margin-left:20px;">
                            &nbsp;查看次数：&nbsp;<span>136</span>
                        </div>
                        -->
                    </div>
                </div>
            </div>
        </div>
        <div class="column2">
            <div style="padding:20px 30px 0;">
                <p>
                    <span class="index">正在进行的任务：</span>&nbsp;<span class="value" style="font-size: 14px;">${team.info.taskCount}</span>
                </p>
                <p>
                    <span class="index">每周可共享时长：</span>&nbsp;<span class="value" style="font-size: 14px;">${team.info.elite.allotDurationVal}</span>
                </p>
                <#if team.attentioned><span style="font-size: 14px;margin-right:10px;">已互相关注</span><#else><button type="button" data="${team.teamMemberId}" class="opt-btn">关注</button></#if>
                <#--<button type="button" class="opt-btn">邀请干活</button>-->
            </div>
        </div>
    </div>
</#list>
<#include "/init/pager.ftl"/>
<div style="text-align: center;margin-top:40px;">
    <button type="button" class="find-btn">帮手不够？我要找精英</button>
</div>
