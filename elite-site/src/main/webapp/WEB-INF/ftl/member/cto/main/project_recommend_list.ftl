<input type="hidden" id="tenderInCount" value="${tenderInCount}" />
<input type="hidden" id="recommendCount" value="${recommendcount}" />
<input type="hidden" id="tenderCount" value="${tenderCount}" />
<#if result?size lt 1>
<div class="empty_box">
    <div class="icon cto_project"></div>
    <div class="text">这里什么都没有哦~</div>
    <#--<a class="a_btn" href="#">快去接项目吧</a>-->
</div>

</#if>
<input type="hidden" id="validStatus" value="${elite.status}" />
<#list result as recommend>
<div class="release-list">
    <div style="position: relative;">
        <div class="part1">
            <div class="col-xs-4 col-md-4">
                <span>推荐时间：${recommend.createTime?string('yyyy-MM-dd HH:mm')}</span>

            </div>
            <div class="col-xs-8 col-md-8">
                <span class="pull-left">项目编号：${recommend.project.projectNum}</span>
                <div class="pull-right">
                    <ul class="focusprt">
						<#--<li><span class="part"></span><a href="#">暂不参加</a></li>-->
					</ul>
                </div>
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
                        <span class="project-name" title="${recommend.project.name}"><#if recommend.project.name?length gt 5>${recommend.project.name?substring(0,5)}…<#else>${recommend.project.name}</#if></span>
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
                    <div><span class="index">预算:</span>&nbsp;<span class="value">${recommend.project.projectBudget}</span></div>
                    <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">${recommend.project.expectTime}</span></div>
                    <div><span class="index">共<span style="color:#000;">${recommend.project.deliveryDay}</span>个工作日</span></div>
                </div>

            </div>
        </div>
        <div class="status">
            <div style="padding:20px 21px;">
                <#if recommend.type=='r_project'>
                    <p>平台推荐</p>
                <#else>
                   <p>${recommend.recommemberName}推荐</p>
                </#if>
            </div>
        </div>
        <#if recommend.tender.status =="tender_in">
	        <div class="status">
	            <div style="padding-top:20px;text-align: center;">
	                <p class="current-status">待竞标</p>
	                <p>已有<span>${recommend.tenderCount}</span>人竞标</p>
	                <a href="javascript:void(0);" id="projectInfo" data="${recommend.project.id}">查看详情</a>
	            </div>
	        </div>
	        <div class="opt">
	            <div style="padding-top:20px;text-align: center;">
	                <div>
	                    <img src="/res/images/clock_icon.png" class="show-icon">
	                    &nbsp;&nbsp;<span style="font-size: 12px;"><span style="color:#9B9B9B;">招标截止：</span><span id="deadlineSpan_${recommend_index}" data="${recommend.tender.tenderoverVal}"></span></span>
	                </div>
	                <div style="margin-top:10px;">
	                    <button type="button" class="complete-btn" id="totender" data="${recommend.project.id}">去竞标</button>
	                </div>
	
	                <div style="margin-top:10px;">
	                    <#--<button type="button" class="evaluate-btn" style="width:110px;font-size: 14px;">推荐给他人</button>-->
	                </div>
	            </div>
	        </div>
	    <#else>
	        <div class="status">
	            <div style="padding-top:20px;text-align: center;">
	                <p class="current-status">招标已截止</p>
	                <p>已有<span>${recommend.tenderCount}</span>人竞标</p>
	                <a href="javascript:void(0);" id="projectInfo" data="${recommend.project.id}">查看详情</a>
	            </div>
	        </div>
	        <div class="opt">
	            <div style="padding-top:20px;text-align: center;">
	                <div>
	                    <span style="font-size: 12px;"><span style="color:#9B9B9B;">招标已结束</span></span>
	                </div>
	            </div>
	        </div>
        </#if>
    </div>
</div>
</#list>
<#include "/init/pager.ftl"/>