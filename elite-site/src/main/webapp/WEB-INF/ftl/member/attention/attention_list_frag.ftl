<#if list?size lt 1>
暂无数据
</#if>
<#list list as obj> 
  <div class="person-box">
        <div class="column1">
            <div style="padding:20px;">
            <a href="${_PATH}/circle/view/${obj.detailInfo.id}" target="_blank">
            <#if obj.detailInfo.basic.photoId??>
                <div class="person-logo" style="background:url('${obj.detailInfo.basic.memberPhoto.path}') no-repeat;background-size:100px 100px;"></div>
            <#else>
                <div class="person-logo"></div>
            </#if>
            </a>
                <div class="person-index">
                    <div>
                        <span class="person-name" title="${obj.detailInfo.nickName}"><#if obj.detailInfo.nickName?? && obj.detailInfo.nickName?length gt 7>${obj.detailInfo.nickName?substring(0,7)}<#else>${obj.detailInfo.nickName}</#if></span>
                        <#list obj.detailInfo.elite.eliteJobs as li2>
							<div class="work-label">
                           		 <div class="title-label"><span class="title_txt">${li2.getJobRoleVal()}</span> <div class="title-grade">L${li2.level}</div></div>
                       		</div>
						</#list>
                        <div class="coop-box">搭伙${obj.partnershipNum}次</div>
                    </div>
                    <div style="font-size: 12px;margin-top:14px;">
                        <p style="margin-bottom: 6px;">
                            <span class="index">擅长领域：</span>&nbsp;<span class="value">
                            <#if obj.detailInfo.elite.eliteJobs??>
                       			 <#list obj.detailInfo.elite.eliteJobs as job>
                        		    <#list job.jobAdeptValList as adept>
                        		    	<#if adept_index lt 2>
                        		    		<span class="value">${adept.value}</span>
                        		    	</#if>
			                   		</#list>
                   				 </#list>
	               			 <#else>
	               			 	暂无添加
                            </#if>
                           </span>
                        </p>
                        <p style="margin-bottom: 6px;">
                            <span class="index">关注行业： </span>&nbsp;
                            <#if obj.detailInfo.elite.getAttentionIndustryListVal()?? && (obj.detailInfo.elite.getAttentionIndustryListVal()?size > 0) >
                       			 <#list obj.detailInfo.elite.getAttentionIndustryListVal() as li3>
                            		 ${li3}&nbsp;
                       				</#list>
                       			 <#else>
                       			 	暂无添加
                            </#if>
                        </p>
                        <div>
                            <img src="/res/images/fans_icon.png" width="16" height="16" style="vertical-align:sub;">
                            &nbsp;粉丝数量：&nbsp;<span>${obj.detailInfo.fansCount}</span>
                            <img src="/res/images/eye_icon.png" width="18" height="12" style="vertical-align:sub;margin-left:20px;">
                            &nbsp;
                                                                                                   查看次数：&nbsp;<span>${obj.detailInfo.viewCount}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
         <div class="column2">
            <div style="padding:20px 30px 0;">
                <p>
                    <span class="index">正在进行的任务：</span>&nbsp;<span class="value" style="font-size: 14px;">${obj.detailInfo.carryProjectCount}</span>
                </p>
                <p>
                    <span class="index">每周可共享时长：</span>&nbsp;<span class="value" style="font-size: 14px;">
                     <#if obj.detailInfo.elite.getAllotDurationVal()?? && (obj.detailInfo.elite.getAllotDurationVal()?size > 0) >
                      		${obj.detailInfo.elite.getAllotDurationVal()}
                     <#else>
                      		暂无
                     </#if>
                   </span>
                </p>
               	<#if obj.detailInfo.attentioned>
                	<a style="font-size: 14px;margin-right:10px;" id="remove_${obj_index}" data="${obj.detailInfo.id}" href="javascript:void(0)">取消关注</a>
                <#else>
            		<span>
            			<a style="font-size: 14px;margin-right:10px;" id="add_${obj_index}" data="${obj.detailInfo.id}" href="javascript:void(0)">相互关注</a>
            		</span>
                </#if>
               <#-- <button type="button" class="opt-btn">邀请干活</button>-->
            </div>
        </div>
    </div>
  </#list>
  <#include "/init/pager.ftl"/>
  <div style="text-align: center;margin-top:40px;">
      <button type="button" class="find-btn">帮手不够？去结识更多的人吧</button>
  </div>
