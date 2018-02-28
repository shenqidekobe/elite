<input type="hidden" id="recomCount" value="${recomCount}" />
<input type="hidden" id="recruitWinCount" value="${recruitWinCount}" />
<input type="hidden" id="recruitInCount" value="${recruitInCount}" />
<#if list?size lt 1>
<div class="empty_box">
    <div class="icon elite_task"></div>
    <div class="text">这里什么都没有哦~</div>
    <a class="a_btn" href="${_PATH}/hall">快去做任务吧</a>
</div>
</#if>
<#list list as recommend>
     <div class="release-list">
        <div style="position: relative;">
            <div class="part1">
                <div class="col-xs-4 col-md-4">
                    <span>创建时间：${recommend.task.createTime?string("yyyy-MM-dd HH:mm")}</span>

                </div>
                <div class="col-xs-8 col-md-8">
                    <span class="pull-left">任务编号：${recommend.task.taskNum}</span>
                    <#--
                    <div class="pull-right">
                        <sapn class="clock"></sapn>
                    </div>
                    -->
                </div>
            </div>
            <div class="vertical-line"></div>
        </div>

        <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                	<#if recommend.task.taskLogo??>
                		<div class="project-logo" style="background:url('${recommend.task.logoAtta.path}')"></div>
                	<#else>
                		<div class="project-logo" style="background-color:#${recommend.task.backgroundColor}">${recommend.task.name?substring(0,1)}</div>
					</#if>
                    <#--<div class="project-logo" style="background-color:#${recommend.project.backgroundColor}">${recommend.project.firstName}</div>-->
                    <div class="project-info">
                        <h1 class="tet" title="${recommend.task.name}"><#if recommend.task.name?length gt 16>${recommend.task.name?substring(0,16)}<#else>${recommend.task.name}</#if></h1>
                        <div><span class="index">所需精英:</span>&nbsp;<span class="value">${recommend.task.demandTypeVal}</span></div>
                        <div><span class="index">任务类型:</span>&nbsp;<span class="value">${recommend.task.taskTypeVal}</span></div>
                         <div><span class="index">报酬:</span>&nbsp;<span class="value">${recommend.task.amount}</span></div>
                        <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">${recommend.task.deliveryTime?string("yyyy-MM-dd")}</span>
                        <span class="index">(<span style="color:#000;">${it.task.deliveryDay}</span>个工作日)</span></div>
                    </div>

                </div>
            </div>
            <#if it.task.deliveryDay lt 0>
            	<div class="status">
	                <div style="padding-top:20px;text-align: center;">
	                    <p ><span class="current-status statu">招募结束</span></p>
	                    <p class="index">${recommend.recommemberName}推荐/任务推荐</p>
	                    <a href="javascript:void(0);" id="taskDetail" data="${recommend.task.id}">查看详情</a>
	                </div>
	            </div>
	            <div class="opt">
	                <div style="padding-top:20px;text-align: center;">
	                	<p class="opt-tai"><span class="tai"></span>您下手太慢啦，下次快点哦！</p>
	                </div>
	            </div>		
            <#else>
            	<div class="status">
	                <div style="padding-top:20px;text-align: center;">
	                    <p ><span class="current-status">招募中</span>&nbsp;<span class="value">${recommend.taskCount}人报名</span></p>
	                    <p class="index">${recommend.recommemberName}推荐/任务推荐</p>
	                    <a href="javascript:void(0);" id="taskDetail" data="${recommend.task.id}">查看详情</a>
	                </div>
	            </div>
	            <div class="opt">
	                <div style="padding-top:20px;text-align: center;">
	                	<p class="index">任务截止:&nbsp;<span style="color:#000;"><#if recommend.task.endTime??>${recommend.task.endTime?string("yyyy-MM-dd")}</#if></span></p>
	                    <button type="button" class="complete-btn" data="${recommend.task.id}" id="signup">我要報名</button>
	                    <p class="rcmd" style="font-size: 12px;margin-top:10px;">推荐给他人</p>
	                </div>
	            </div>
            </#if>
            
        </div>
    </div>
</#list> 
 <#include "/init/pager.ftl"/>