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
<#list list as it>
 	<div class="release-list">
        <div style="position: relative;">
            <div class="part1">
                <div class="col-xs-4 col-md-4">
                    <span>创建时间：${it.task.createTime?string("yyyy-MM-dd HH:mm")}</span>

                </div>
                <div class="col-xs-8 col-md-8">
                    <span class="pull-left">任务编号：${it.task.taskNum}</span>
                    <div class="pull-right">
                       <#-- <ul class="focusprt">
							<li><span class="focus"></span><a href="#">关注</a></li>
							<li><span class="part"></span><a href="#">暂参加</a></li>
						</ul>-->
                    </div>
                </div>
            </div>
            <div class="vertical-line"></div>
        </div>

       	 <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                	<#if it.task.taskLogo??>
                		<div class="project-logos" style="background:url('${it.task.logoAtta.path}')"></div>
                	<#else>
                		<div class="project-logos" style="background-color:#${it.task.backgroundColor}">${it.task.name?substring(0,1)}</div>
					</#if>
                    <#--<div class="project-logos" style="background-color:#${it.project.backgroundColor}">${it.project.firstName}</div>-->
                    <div class="project-info">
                        <h1 class="tet" title="${it.task.name}"><#if it.task.name?length gt 16>${it.task.name?substring(0,16)}<#else>${it.task.name}</#if></h1>
                        <div><span class="index">所需精英:</span>&nbsp;<span class="value">${it.task.demandTypeVal}</span></div>
                        <div><span class="index">任务类型:</span>&nbsp;<span class="value">${it.task.taskTypeVal}</span></div>
                         <div><span class="index">报酬:</span>&nbsp;<span class="value">${it.task.amount}</span></div>
                        <div><span class="index">期望交付日期:</span>&nbsp;<span class="value"><#if it.task.deliveryTime??>${it.task.deliveryTime?string("yyyy-MM-dd")}</#if></span>
                        <span class="index">(<span style="color:#000;">${it.task.deliveryDay}</span>个工作日)</span></div>
                    </div>

                </div>
            </div>
            
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                    <p>
                        <#assign statusLabel=""/>
		                <#if it.status=='wait_recruit'>
		                    <#assign statusLabel="待认领"/>
		                <#elseif it.status=='recruit_in'>
		                    <#assign statusLabel="招募中"/>
		                <#elseif it.status=='starting'>
		                    <#assign statusLabel="进行中"/>
		                <#elseif it.status=='wait_accept'>
		                    <#assign statusLabel="待验收"/>
		                <#elseif it.status=='finish'>
		                    <#assign statusLabel="已完成"/>
		                </#if>
	                    <span class="current-status">${statusLabel}</span>&nbsp;
	                    <span class="value" id="applyCount">${it.applyCount}人報名</span>
                    </p>
                    <p class="index">自己报名</p>
                    <a href="javascript:void(0);" id="taskDetail" data="${it.taskId}">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
                    <#if it.applyFlag=='N'>
                        <p class="index">未报名</p>
                        <button type="button" class="complete-btns complete-btnss" id="apply" data="${it.taskId}" oper="Y">申请报名</button>
                    <#else>
                        <p class="index">已报名</p>
                        <button type="button" style="background:#fea600" class="complete-btns complete-btnss" id="apply" data="${it.taskId}" oper="N">取消报名</button>
                    </#if>
                    <#--<p class="rcmds" style="font-size: 12px;margin-top:10px;">推荐给他人</p>-->
                </div>
            </div>
        </div>
    </div>
 </#list>
<#include "/init/pager.ftl"/>