<input type="hidden" id="recomCount" value="${recomCount}" />
<input type="hidden" id="recruitWinCount" value="${recruitWinCount}" />
<input type="hidden" id="recruitInCount" value="${recruitInCount}" />
<#assign flag = 1 />
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
            <#if it.status=='recruit_not'>
            	<div class="status">
	                <div style="padding-top:20px;text-align: center;">
	                    <p ><span class="current-status">认领失败</span></p>
	                    <a href="javascript:void(0);" id="taskDetail" data="${it.taskId}">查看详情</a>
	                </div>
	            </div>
	            <div class="opt">
	                <div class="opt-rcd" style="padding-top:20px;text-align: center;">
	                	<p class="index inde">已指定其它精英</p>
	                </div>
	            </div>
            </#if>
            <#if it.status=='task_closed'>
            	<div class="status">
	                <div style="padding-top:20px;text-align: center;">
	                    <p ><span class="current-status">已关闭</span></p>
	                    <a href="javascript:void(0);" id="taskDetail" data="${it.taskId}">查看详情</a>
	                </div>
	            </div>
	            <div class="opt">
	                <div class="opt-rcd" style="padding-top:20px;text-align: center;">
	                	<p class="index inde">任务到时已关闭</p>
	                </div>
	            </div>
            </#if>
            <#if it.status=='recruit_win'>
	            <#if it.task.status=='starting'>
	            	<div class="status">
			            <div style="padding-top:20px;text-align: center;">
			                <p ><span class="current-status">进行中</span></p>
			                <a href="javascript:void(0);" id="taskDetail" data="${it.taskId}">查看详情</a>
			            </div>
			        </div>
			        <div class="opt">
			            <div style="padding-top:20px;text-align: center;">
			                <button type="button" id="sendMaterial_${it_index}" data="${it.taskId}" class="complete-btns">提交产出物</button>
			                <#-- <p class="rcmd" style="font-size: 12px;margin-top:10px;">申请延期</p>-->
			            </div>
			        </div>
			        
			    <#elseif it.task.status=='wait_accept'>
		            <div class="status">
		                <div style="padding-top:20px;text-align: center;">
		                    <p ><span class="current-status">待验收</span></p>
		                    <a href="javascript:void(0);" id="taskDetail" data="${it.taskId}">查看详情</a>
		                </div>
		            </div>
		            <div class="opt">
		                <div class="opt-rcd" style="padding-top:20px;text-align: center;">
		                    <#-- <p class="rcmd" style="font-size: 12px;margin-top:10px;">收到新文件</p>-->
		                    <button type="button" id="sendMaterial_${it_index}" data="${it.taskId}" class="complete-btns">查看/提交产出物</button>
		                </div>
		            </div>
	
			    <#elseif it.task.status=='quality'>
		            <div class="status">
		                <div style="padding-top:20px;text-align: center;">
		                    <p ><span class="current-status">质保期</span></p>
		                    <a href="javascript:void(0);" id="taskDetail" data="${it.taskId}">查看详情</a>
		                </div>
		            </div>
		            <div class="opt">
		                <div class="opt-rcd" style="padding-top:20px;text-align: center;">
		                	<p class="index inde"><span class="sheape"></span>距质保期结束时间：</p>
							<p class="index">
								<#if it.task.guaranteeTimeVal gt 0>
							 		还剩<span style="color:#000;"><span id="guaranteeSpan_${it_index}" data="${it.task.guaranteeTimeVal}"></span></span>
							 	<#else>
							 		即将结束完成	
							 	</#if>	
							</p>
							<#assign flag = flag+1 />
		                    <#--<button type="button" class="complete-btns">去评价</button>-->
		                </div>
		            </div>
		            
		        <#elseif it.task.status=='closed'>
		            <div class="status">
		                <div style="padding-top:20px;text-align: center;">
		                    <p ><span class="current-status">已关闭</span></p>
		                    <a href="javascript:void(0);" id="taskDetail" data="${it.taskId}">查看详情</a>
		                </div>
		            </div>
		            <div class="opt">
		                <div class="opt-rcd" style="padding-top:20px;text-align: center;">
		                	<p class="index inde">任务已关闭</p>
		                </div>
		            </div>    
		        <#elseif it.task.status=='accept_success'>
		            <div class="status">
		                <div style="padding-top:20px;text-align: center;">
		                    <p ><span class="current-status">验收成功</span></p>
		                    <a href="javascript:void(0);" id="taskDetail" data="${it.taskId}">查看详情</a>
		                </div>
		            </div>
		            <div class="opt">
		                <div class="opt-rcd" style="padding-top:20px;text-align: center;">
		                	<p class="index inde">待项目方验收</p>
		                </div>
		            </div>    
			    <#elseif it.task.status=='finish'>
				    <div class="status">
		                <div style="padding-top:20px;text-align: center;">
		                    <p ><span class="current-status">已完成</span></p>
		                    <a href="javascript:void(0);" id="taskDetail" data="${it.taskId}">查看详情</a>
		                </div>
		            </div>
		            <div class="opt">
		                <div class="opt-rcd" style="padding-top:20px;text-align: center;">
		                	<p class="index inde">已完成</p>
		                </div>
		            </div>
			        				
	            </#if>
            </#if>
         </div>
    </div>
 </#list>
<#include "/init/pager.ftl"/>