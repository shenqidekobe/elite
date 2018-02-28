<div class="process">
     <#if obj.status=='wait_recruit'>
        <div class="process-circle active-process">1</div>
        <div class="process-circle" style="left:180px;">2</div>
        <div class="process-circle" style="left:330px;">3</div>
        <div class="process-circle" style="left:480px;">4</div>
        <div class="process-circle" style="left:630px;">
            <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
        </div>
        <p class="process-title active-title">待认领</p>
        <p class="process-title" style="left:176px;">认领中</p>
        <p class="process-title" style="left:328px;">进行中</p>
        <p class="process-title" style="left:484px;">质保</p>
        <p class="process-title" style="left:636px;">完成</p>

        <div class="process-line"></div>
        <div class="process-line" style="left:230px;"></div>
        <div class="process-line" style="left:380px;"></div>
        <div class="process-line" style="left:530px;"></div>
     <#elseif obj.status=='closed'>
        <div class="process-circle active-process">1</div>
        <div class="process-circle active-process" style="left:180px;">2</div>
        
        <p class="process-title active-title">待认领</p>
        <p class="process-title active-title" style="left:176px;">已关闭</p>

        <div class="process-line finish-line"></div>
     <#elseif obj.status=='recruit_in'>
     <div class="process-circle active-process">1</div>
        <div class="process-circle active-process" style="left:180px;">2</div>
        <div class="process-circle" style="left:330px;">3</div>
        <div class="process-circle" style="left:480px;">4</div>
        <div class="process-circle" style="left:630px;">
            <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
        </div>
        <p class="process-title active-title">待认领</p>
        <p class="process-title active-title" style="left:176px;">认领中</p>
        <p class="process-title" style="left:328px;">进行中</p>
        <p class="process-title" style="left:484px;">完成</p>
        <p class="process-title" style="left:636px;">质保</p>

        <div class="process-line finish-line"></div>
        <div class="process-line" style="left:230px;"></div>
        <div class="process-line" style="left:380px;"></div>
        <div class="process-line" style="left:530px;"></div>   
     <#elseif obj.status=='starting'>
     <div class="process-circle active-process">1</div>
        <div class="process-circle active-process" style="left:180px;">2</div>
        <div class="process-circle active-process" style="left:330px;">3</div>
        <div class="process-circle" style="left:480px;">4</div>
        <div class="process-circle" style="left:630px;">
            <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
        </div>
        <p class="process-title active-title">待认领</p>
        <p class="process-title active-title" style="left:176px;">认领中</p>
        <p class="process-title active-title" style="left:328px;">进行中</p>
        <p class="process-title" style="left:484px;">质保</p>
        <p class="process-title" style="left:636px;">完成</p>

        <div class="process-line finish-line"></div>
        <div class="process-line finish-line" style="left:230px;"></div>
        <div class="process-line" style="left:380px;"></div>
        <div class="process-line" style="left:530px;"></div>   
      <#elseif obj.status=='finish'>
      <div class="process-circle active-process">1</div>
        <div class="process-circle active-process" style="left:180px;">2</div>
        <div class="process-circle active-process" style="left:330px;">3</div>
        <div class="process-circle active-process" style="left:480px;">4</div>
        <div class="process-circle active-process" style="left:630px;">
            <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
        </div>
        <p class="process-title active-title">待认领</p>
        <p class="process-title active-title" style="left:176px;">认领中</p>
        <p class="process-title active-title" style="left:328px;">进行中</p>
        <p class="process-title active-title" style="left:484px;">质保</p>
        <p class="process-title active-title" style="left:636px;">完成</p>

        <div class="process-line finish-line"></div>
        <div class="process-line finish-line" style="left:230px;"></div>
        <div class="process-line finish-line" style="left:380px;"></div>
        <div class="process-line finish-line" style="left:530px;"></div>
      <#elseif obj.status=='wait_accept'>
      <div class="process-circle active-process">1</div>
        <div class="process-circle active-process" style="left:180px;">2</div>
        <div class="process-circle active-process" style="left:330px;">3</div>
        <div class="process-circle " style="left:480px;">4</div>
        <div class="process-circle" style="left:630px;">
            <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
        </div>
        <p class="process-title active-title">待认领</p>
        <p class="process-title active-title" style="left:176px;">认领中</p>
        <p class="process-title active-title" style="left:328px;">进行中</p>
        <p class="process-title" style="left:484px;">质保</p>
        <p class="process-title" style="left:636px;">完成</p>

        <div class="process-line finish-line"></div>
        <div class="process-line finish-line" style="left:230px;"></div>
        <div class="process-line" style="left:380px;"></div>
        <div class="process-line " style="left:530px;"></div>   
           
      <#elseif obj.status=='quality'>
      <div class="process-circle active-process">1</div>
        <div class="process-circle active-process" style="left:180px;">2</div>
        <div class="process-circle active-process" style="left:330px;">3</div>
        <div class="process-circle active-process" style="left:480px;">4</div>
        <div class="process-circle" style="left:630px;">
            <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
        </div>
        <p class="process-title active-title">待认领</p>
        <p class="process-title active-title" style="left:176px;">认领中</p>
        <p class="process-title active-title" style="left:328px;">进行中</p>
        <p class="process-title active-title" style="left:484px;">质保</p>
        <p class="process-title" style="left:636px;">完成</p>

        <div class="process-line finish-line"></div>
        <div class="process-line finish-line" style="left:230px;"></div>
        <div class="process-line finish-line" style="left:380px;"></div>
        <div class="process-line" style="left:530px;"></div>   
        
	</#if>
    </div>

    <#--项目详细-->
    <div style="margin-top:40px;">
        <div style="text-align: center;">
            <div class="theme-lab"><strong>任务描述</strong></div>
            <#if obj.atta.downPath>
            	<a href="${obj.atta.downPath}"><img src="${_PATH}/res/images/brooch_icon.png" class="brooch-icon">
           		&nbsp;<span class="document-name">${obj.atta.fileName}</span></a>
            </#if>
        </div>
        <div class="project-index">
            <div style="padding:20px;position: relative;">
                <div class="project-logo" style="background-color:#${obj.project.backgroundColor}">${obj.project.firstName}</div>
                <div class="time-code-status">
                    <span>任务发布时间：${obj.createTime?string("yyyy-MM-dd HH:mm")}</span>
                    <span class="vertical-line"></span>
                    <span style="margin-left:30px;">任务编号：${obj.taskNum}</span>
                </div>
                <!-- 
                <#assign statusLabel=""/>
                <#if obj.status=='wait_recruit'>
                    <#assign statusLabel="待认领"/>
                <#elseif obj.status=='recruit_in'>
                    <#assign statusLabel="招募中"/>
                <#elseif obj.status=='starting'>
                    <#assign statusLabel="进行中"/>
                <#elseif obj.status=='wait_accept'>
                    <#assign statusLabel="待验收"/>
                <#elseif obj.status=='finish'>
                    <#assign statusLabel="已完成"/>
                </#if>
                <span class="project-status">${statusLabel}</span>
				-->
                <div class="main-index">
                    <div>
                        <span>${obj.name}</span>
                        <#--<img src="/res/images/location_icon.png" class="location-icon">-->
                        <#--&nbsp;<span style="font-size: 14px;color:#4A4A4A;">${obj.project.areaName}</span>-->
                        <#--<img src="/res/images/edit_icon.png" class="edit-icon">-->
                    </div>
                    <div style="font-size: 12px;margin-top:14px;position: relative;">
                        <p><span class="index">所需精英：</span>${obj.demandTypeVal}</p>
                        <p><span class="index">任务类型：</span>${obj.taskTypeVal}</p>
                        <p><span class="index">报酬：</span>${obj.amount}元</p>
                        <p class="date_released"><span class="index">期望交付日期：
							<#if obj.startTime??>${obj.startTime?string("yyyy-MM-dd")}</#if>-
							<#if obj.deliveryTime??>${obj.deliveryTime?string("yyyy-MM-dd")}</#if></span> &nbsp;&nbsp;
							共${obj.deliveryDay?string('#')}个工作日
					    </p>
                    </div>
                </div>
                <div class="vertical-line3"></div>
                <#if obj.eliteMemberId?? && recruit.status=='recruit_win'>
                    <#if  obj.status=='starting'>
	                    <div class="deposit"><span style="color:#9B9B9B;">进行中</span></div>
	                    <div class="vertical-line3" style="left:570px;"></div>
	                	<div class="status-to-opt">
	                        <div>
		                        <button style="top:50px;" type="button" class="opt-btn" id="submitMaterial">查看/提交产物</button>
	                        </div>
                        </div>
                   <#elseif  obj.status=='wait_accept'>
	                    <div class="deposit"><span style="color:#9B9B9B;">待验收</span></div>
	                    <div class="vertical-line3" style="left:570px;"></div>
	                	<div class="status-to-opt">
	                        <div>
		                        <button style="top:50px;" type="button" class="opt-btn" id="submitMaterial">查看/提交产物</button>
	                        </div>
                        </div>
                    <#elseif  obj.status=='quality'>
                    	<input type="hidden" id="guaranteeTime" value="${obj.guaranteeTimeVal}">
	                    <div class="deposit"><span style="color:#9B9B9B;">质保期</span></div>
	                    <div class="vertical-line3" style="left:570px;"></div>
	                	<div class="status-to-opt">
	                        <p class="index inde"><span class="sheape"></span>距质保期结束时间：</p>
							<p class="index">还剩<span style="color:#000;"><span id="guaranteeSpan"></span></span></p>
                        </div>
                    <#elseif  obj.status=='完成'>
	                    <div class="deposit"><span style="color:#9B9B9B;">完成</span></div>
	                    <div class="vertical-line3" style="left:570px;"></div>
	                	<div class="status-to-opt">
	                        <p class="end-date">
	                        	<span class="index">已完成</span>
	                    	</p>
                        </div>
                    <#elseif  obj.status=='closed'>
	                    <div class="deposit"><span style="color:#red;">已关闭</span></div>
	                    <div class="vertical-line3" style="left:570px;"></div>
	                	<div class="status-to-opt">
	                        <p class="end-date">
	                        	<span class="index">已关闭</span>
	                    	</p>
                        </div>
                    <#elseif  obj.status=='accept_success'>
	                    <div class="deposit"><span style="color:#red;">已验收</span></div>
	                    <div class="vertical-line3" style="left:570px;"></div>
	                	<div class="status-to-opt">
	                        <p class="end-date">
	                        	<span class="index">待项目方验收</span>
	                    	</p>
                        </div>                       
                   </#if>
                        
               
                <#else>
                	<#if recruit.status=='recruit_in' || recruit.status=='recruit_cancel'>
	                	<div class="deposit">
	                		<span style="color:#9B9B9B;">招募中</span>
	                	</div>
	                	<div class="vertical-line3" style="left:570px;"></div>
	                	<div class="status-to-opt">
	                    	<p class="bid-num">已有<span style="color:#5C5C5C;" id="applyCount">${applyCount}</span>人报名</p>
	                    	<div style="text-align: center;margin-top:20px;">
		                        <div>
			                        <#if applyFlag=='N'>
				                       <button type="button" style="top:50px;" class="opt-btn" id="apply" data="${obj.id}" oper="Y">认领任务</button>
				                    <#else>
				                       <button type="button" style="top:50px;" class="opt-btn" id="apply" data="${obj.id}" oper="N">取消报名</button>
				                    </#if>
		                        </div>
	                    	</div>
	                    </div>
	                 <#elseif recruit.status=='recruit_not'>
	                	<div class="deposit">
	                		<span style="color:red">未被招募</span>
	                	</div>
	                	<div class="vertical-line3" style="left:570px;"></div>
	                	<div class="status-to-opt">
	                    	<div style="text-align: center;margin-top:20px;">
		                        <div>
			                        <span>已指定其它精英</span>
		                        </div>
	                    	</div>
	                    </div>   
	                 </#if>  
               </#if>      
            </div>
        </div>

        <div class="project-intro">
            <div class="project_intro_content">
            	<dl>
            		<dt>一、任务描述</dt>
            		<dd>${obj.intro}</dd>
            	</dl>
            </div>
            <img src="${_PATH}/res/images/paper_foot.png" class="paper-foot">
        </div>
    </div>