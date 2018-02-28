<ul class="attentionMain_ul">
	<#list list as obj> 
	    <li>
	        <div class="liBox">
	            <div class="liBox_l">
	                <div class="head_img">
	                    <a href="${_PATH}/circle/view/${obj.detailInfo.id}" target="_blank">
		                <#if obj.detailInfo.basic.photoId??>
			                <img src="${obj.detailInfo.basic.memberPhoto.path}" alt="">
			            <#else>
			                <img src="" alt="">
			            </#if>
	                    </a>
	                </div>
	            </div>
	            <div class="liBox_c">
	                <div class="liBox_c_content">
	                    <div class="identityBox clearfloat">
	                        <div class="head_name">${obj.detailInfo.nickName}</div>
	                        <ul>
	                            <#list obj.detailInfo.elite.eliteJobs as li2>
	                            <li><span class="span_text">${li2.getJobRoleVal()}</span><span class="span_point">L${li2.level}</span></li>
	                            </#list>
	                        </ul>
	                        <span class="board">搭伙${obj.partnershipNum}次</span>
	                    </div>
	                    <div class="fieldBox clearfloat">
	                        <div class="field_title">擅长领域：</div>
	                        <ul class="field_ul">
		                        <#if obj.detailInfo.elite.eliteJobs??>
	                       			 <#list obj.detailInfo.elite.eliteJobs as job>
	                        		    <#list job.jobAdeptValList as adept>
	                        		    	<#if adept_index lt 2>
	                        		    		<li>${adept.value}</li>
	                        		    	</#if>
				                   		</#list>
	                   				 </#list>
		               			 <#else>
		               			 	暂无添加
	                            </#if>
	                        </ul>
	                    </div>
	                    <div class="industryBox clearfloat">
	                        <div class="industry_title">关注行业：</div>
	                        <ul class="industry_ul">
		                        <#if obj.detailInfo.elite.getAttentionIndustryListVal()?? && (obj.detailInfo.elite.getAttentionIndustryListVal()?size > 0) >
	                       			 <#list obj.detailInfo.elite.getAttentionIndustryListVal() as li3>
	                            		 <li>${li3}</li>
	                       				</#list>
	                       			 <#else>
	                       			 	暂无添加
	                            </#if>
	                        </ul>
	                    </div>
	                    <div class="digitalBox clearfloat">
	                        <div class="fans">
	                            <img src="${_PATH}/res/images/elite/fans.png">
	                            <span class="text">粉丝数量：</span>
	                            <span class="number">${obj.attenionCount}</span>
	                        </div>
	                        <div class="view">
	                            <img src="${_PATH}/res/images/elite/eyes.png">
	                            <span class="text">查看次数：</span>
	                            <span class="number">${obj.detailInfo.viewCount}</span>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div class="liBox_r">
	                <div class="liBox_r_content">
	                    <div class="taskBox">
	                        <span>正在进行的任务:</span>
	                        <span>${obj.detailInfo.carryProjectCount}</span>
	                    </div>
	                    <div class="timeBox">
	                        <span>每周可共享时长</span>
	                        <span>
	                           <#if obj.detailInfo.elite.getAllotDurationVal()?? && (obj.detailInfo.elite.getAllotDurationVal()?size > 0) >
		                      		${obj.detailInfo.elite.getAllotDurationVal()}
		                       <#else>
		                      		暂无
		                       </#if>
	                        </span>
	                    </div>
	                    <div class="btnBox">
		                    <#if obj.detailInfo.attentioned>
		                        <button type="button" id="remove_${obj_index}" data="${obj.detailInfo.id}" class="noselectBtn">取消关注</button>
			                <#else>
			            		<button type="button" id="add_${obj_index}" data="${obj.detailInfo.id}" class="noselectBtn">互相关注</button>
			                </#if>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </li>
    </#list>
</ul>
<#include "/init/pager.ftl"/>
<#if list?size lt 1>
	<div class="moreBox">
	    <a href="${_PATH}/circle" target="_blank" class="moreBtn">帮手不够？去结识更多的人吧</a>
	</div>
</#if>
