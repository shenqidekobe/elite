<input type="hidden" id="tenderInCount" value="${tenderInCount}" />
<input type="hidden" id="recommendCount" value="${recommendcount}" />
<input type="hidden" id="tenderCount" value="${tenderCount}" />
<#assign flag = 1 />
<#if result?size lt 1>
<div class="empty_box">
    <div class="icon cto_project"></div>
    <div class="text">这里什么都没有哦~</div>
    <#--<a class="a_btn" href="#">快去接项目吧</a>-->
</div>

</#if>
<#list result as recommend>
<div class="release-list">
    <div style="position: relative;">
        <div class="part1">
            <div class="col-xs-4 col-md-4">
                <span>创建时间：${recommend.project.createTime?string('yyyy-MM-dd HH:mm')}</span>

            </div>
            <div class="col-xs-8 col-md-8">
                <span class="pull-left">项目编号：${recommend.project.projectNum}</span>
            </div>
        </div>
        <div class="vertical-line"></div>
    </div>
    <div class="project-detail">
        <div class="main-info">
            <div style="padding:20px;">
                <div class="project-logo" style="background-color:#${recommend.project.backgroundColor}">${recommend.project.firstName}</div>
                <div class="project-info">
                    <div>
                        <span class="project-name" title="${recommend.project.name}"><#if recommend.project.name?length gt 5>${recommend.project.name?substring(0,5)}...<#else>${recommend.project.name}</#if></span>
                        <#assign n = 1 />
                        <#list recommend.project.industryValList as industry>
	                    	<#if n lt 3>
                    			<div class="class-label" title="${industry}"><#if industry?length gt 2>${industry?substring(0,2)}<#else>${industry}</#if></div>
                    		</#if>
	                    	<#assign n = n+1 />
	                    </#list>
                    </div>
                    <div><span class="index">开发类型:</span>
                    	<span title="${recommend.project.solutionVals}"><#if recommend.project.solutionVals?length gt 10>${recommend.project.solutionVals?substring(0,10)}<#else>${recommend.project.solutionVals}</#if></span>
                    </div>
                    <div><span class="index">我的竞标价格:</span>&nbsp;<span class="value">${recommend.amount}</span></div>
                    <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">${recommend.tender.endTime}</span></div>
                    <div><span class="index">共<span style="color:#000;">${recommend.tender.deliveryDay}</span>个工作日</span></div>
                </div>

            </div>
        </div>
        <div class="status">
        	<div style="padding:20px 18px;">
                <p>标书承诺质保金:<span>${recommend.amount*percent}</span></p>
            </div>
        </div>
        <#if recommend.tender.status =="tender_in">
	        <#if recommend.status =="tender_in">
	         <div class="status">
	            <div style="padding-top:20px;text-align: center;">
	                <p class="current-status">已竞标</p>
	                <a href="javascript:void(0);" id="projectInfo" data="${recommend.project.id}">查看详情</a>
	            </div>
	        </div>
	        
	        <div class="opt">
	            <div style="padding-top:20px;text-align: center;">
	                <div>
	                    <img src="/res/images/clock_icon.png" class="show-icon">
	                    &nbsp;&nbsp;<span style="font-size: 12px;">
		                    <#if recommend.tender.tenderoverVal gt 0>
		                    	<span style="color:#9B9B9B;">招标截止：</span><span id="deadlineSpan_${flag}" data="${recommend.tender.tenderoverVal}"></span>
		                    <#else>
		                    	<span style="color:#9B9B9B;">招标已截止</span>	
		                    </#if>
	                    </span>
	                    <#assign flag = flag+1 />
	                </div>
	                <#--
	                <div style="margin-top:10px;">
	                    <button type="button" class="cancel-bid-btn">取消投标</button>
	                </div>
					
	                <div style="margin-top:10px;">
	                    <button type="button" class="evaluate-btn" style="width:110px;font-size: 14px;">推荐给他人</button>
	                </div>
	                -->
	            </div>
	        </div>
	        <#elseif recommend.status =="tender_not">
	        <div class="status">
	            <div style="padding-top:20px;text-align: center;">
	                <p class="current-status">未中标</p>
	                <a href="javascript:void(0);" id="projectInfo" data="${recommend.project.id}">查看详情</a>
	            </div>
	        </div>
	        <div class="opt">
	            <div style="padding-top:20px;text-align: center;">
	                <div>
	                    <img src="/res/images/sad_face_icon.png" class="show-icon">
	                    &nbsp;<span style="font-size: 12px;">招标截止，再接再厉！</span>
	                </div>
	                <button type="button" class="complete-btn" id="viewReason" data="${recommend.project.id}" style="margin-top:10px;">查看原因</button>
	            </div>
	        </div>
	        
	        <#elseif recommend.status =="tender_abandon">
	        <div class="status">
	            <div style="padding-top:20px;text-align: center;">
	                <p class="current-status">放弃项目</p>
	                <a href="javascript:void(0);" id="projectInfo" data="${recommend.project.id}">查看详情</a>
	            </div>
	        </div>
	        <div class="opt">
	            <div style="padding:20px 10px;">
	                <img src="/res/images/exclamation_mark.png" class="exclamation-mark">
	                &nbsp;<span style="font-size: 12px;">中标取消后会<span style="color:#FEA600;">影响你的诚信度，</span>下次请非常谨慎。</span>
	            </div>
	        </div>		
	        </#if>
	     <#elseif recommend.tender.status =="tender_stop">
	        <div class="status">
	            <div style="padding-top:20px;text-align: center;">
	                <p class="current-status">未中标</p>
	                <a href="javascript:void(0);" id="projectInfo" data="${recommend.project.id}">查看详情</a>
	            </div>
	        </div>
	        <div class="opt">
	            <div style="padding-top:20px;text-align: center;">
	                <div>
	                    <img src="/res/images/sad_face_icon.png" class="show-icon">
	                    &nbsp;<span style="font-size: 12px;">招标已结束</span>
	                </div>
	            </div>
	        </div>
	      <#elseif recommend.tender.status =="tender_cancel">
	        <div class="status">
	            <div style="padding-top:20px;text-align: center;">
	                <p class="current-status">未中标</p>
	                <a href="javascript:void(0);" id="projectInfo" data="${recommend.project.id}">查看详情</a>
	            </div>
	        </div>
	        <div class="opt">
	            <div style="padding-top:20px;text-align: center;">
	                <div>
	                    <img src="/res/images/sad_face_icon.png" class="show-icon">
	                    &nbsp;<span style="font-size: 12px;">招标已取消</span>
	                </div>
	            </div>
	        </div>  
	    </#if>
    </div>
</div>
</#list>
<#include "/init/pager.ftl"/>