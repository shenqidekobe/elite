<div class="process cto_process">
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
        <p class="process-title" style="left:484px;">质保期</p>
        <p class="process-title" style="left:636px;">完成</p>

        <div class="process-line"></div>
        <div class="process-line" style="left:230px;"></div>
        <div class="process-line" style="left:380px;"></div>
        <div class="process-line" style="left:530px;"></div>
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
        <p class="process-title" style="left:484px;">质保期</p>
        <p class="process-title" style="left:636px;">完成</p>

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
        <p class="process-title" style="left:484px;">质保期</p>
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
        <p class="process-title active-title" style="left:484px;">质保期</p>
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
        <p class="process-title" style="left:484px;">质保期</p>
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
        <div class="process-line finish-line" style="left:530px;"></div>   
       
        <#elseif obj.status=='accept_success'>
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
        <div class="process-line finish-line" style="left:380px;"></div>
        <div class="process-line" style="left:530px;"></div> 
          
      <#elseif obj.status=='closed'>
        <div class="process-circle active-process">1</div>
        <div class="process-circle active-process" style="left:180px;">2</div>
        
        <p class="process-title active-title">待认领</p>
        <p class="process-title active-title" style="left:176px;">已关闭</p>
        
        <div class="process-line finish-line"></div>
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
            		<#if obj.status=='wait_accept' >
            		<div class="deposit">
            			<div class="deposit_img">
                			<#if obj.member.basic.photoId??>
		                		<div class="head-logo2" style="background:url('${obj.member.basic.memberPhoto.path}');background-size: cover;background-position: center;"></div>
		                	<#else>
		                		<div class="head-logo2" style="background:url('${_PATH}/res/images/default.jpg');background-size: cover;background-position: center;"></div>
		                	</#if>
	                	</div>
	                	<span style="color:#9B9B9B;" class="deposit_text">待验收</span>
            		</div>
            		<div class="vertical-line3" style="left:570px;"></div>
            		<div class="status-to-opt">
                    	<div style="text-align: center;margin-top:20px;">
                        <div>
                        	<p style="font-size: 14px;">待验收</p>
                    		<button style="top:60px;right:44px;" type="button" class="opt-btn" id="acceptance" data="${obj.id}" class="opt-btn">去验收</button>
                        </div>
                    </div>
                    <#elseif obj.status=='wait_recruit' >
                    <input type="hidden" id="deadlineTime" value="${deadlineTime}">
            		<div class="deposit">
	                	<span style="color:#9B9B9B;" class="deposit_text">待认领</span>
            		</div>
            		<div class="vertical-line3" style="left:570px;"></div>
            		<div class="status-to-opt" style="top:100px;">
                    	<p style="font-size: 14px;">
                    		<span id="deadlineSpan"></span>
                    	</p>
                     </div> 
                    <#elseif obj.status=='closed'>
                    <div class="deposit"><span style="color:red;">已关闭</span></div>
            		<div class="vertical-line3" style="left:570px;"></div>
                    <div class="status-to-opt">
                    	<div style="text-align: center;margin-top:20px;">
                        <div>
                        	<p style="font-size: 14px;">未指定精英</p>
                        </div>
                    </div>    
                    <#elseif obj.status=='recruit_in' >
                    <input type="hidden" id="deadlineTime" value="${deadlineTime}">
                    <div class="deposit">
                    	<div class="deposit_img">
                		<#list recruitList as recruit>
	                		<#if recruit_index lt 3>
		                		<#if recruit.info.basic.photoId??>
			                		<div class="head-logo2" style="background:url('${recruit.info.basic.memberPhoto.path}');background-size: cover;background-position: center;"></div>
			                	<#else>
			                		<div class="head-logo2" style="background:url('${_PATH}/res/images/default.jpg');background-size: cover;background-position: center;"></div>
			                	</#if>
			                </#if>	
	                	</#list>
	                	</div>
	                	<span style="color:#9B9B9B;" class="deposit_text">认领中</span>
                	</div>
            		<div class="vertical-line3" style="left:570px;"></div>
                    <div class="status-to-opt" style="top:100px;">
                    	<p style="font-size: 14px;">
                    		<img src="${_PATH}/res/images/clock_icon.png" width="16" height="16">
                    		&nbsp;<span class="index">招标截止：</span>&nbsp;<span id="deadlineSpan"></span>
                    	</p>
                     </div>   
                    <#elseif obj.status=='starting' >
                    <input type="hidden" id="deadlineTime" value="${obj.taskoverVal}">
                    <div class="deposit">
                		<div class="deposit_img">
                			<#if obj.member.basic.photoId??>
		                		<div class="head-logo2" style="background:url('${obj.member.basic.memberPhoto.path}');background-size: cover;background-position: center;"></div>
		                	<#else>
		                		<div class="head-logo2" style="background:url('${_PATH}/res/images/default.jpg');background-size: cover;background-position: center;"></div>
		                	</#if>
	                	</div>
	                	<span style="color:#9B9B9B;" class="deposit_text">进行中</span>
                	</div>
                	<div class="vertical-line3" style="left:570px;"></div>
                    <div class="status-to-opt">
                    	<div style="text-align: center;margin-top:20px;">
                        <div>
                        	<p style="font-size: 14px;">
                        		<img src="${_PATH}/res/images/clock_icon.png" width="16" height="16">
                        		&nbsp;<span class="index">据任务交付：</span>&nbsp;<span id="deadlineSpan"></span>
                        	</p>
		                    <#if obj.remind>
		                    	<button type="button" class="opt-btn" style="background-color:grey;top:90px;left:44px">已提醒</button>
		                    <#else>
		                    	<button type="button" style="top:90px;left:44px" class="opt-btn" id="remind" data="${obj.id}">提醒</button>
		                    </#if>
                        </div>
                    </div>        
                    <#elseif obj.status=='quality'>
                    <input type="hidden" id="guaranteeTime" value="${obj.guaranteeTimeVal}">
                    <div class="deposit">
                		<div class="deposit_img">
                			<#if obj.member.basic.photoId??>
		                		<div class="head-logo2" style="background:url('${obj.member.basic.memberPhoto.path}');background-size: cover;background-position: center;"></div>
		                	<#else>
		                		<div class="head-logo2" style="background:url('${_PATH}/res/images/default.jpg');background-size: cover;background-position: center;"></div>
		                	</#if>
	                	</div>
	                	<span style="color:#9B9B9B;" class="deposit_text">质保期</span>
                	</div>
                	<div class="vertical-line3" style="left:570px;"></div>
                    <div class="status-to-opt" style="top:100px;">
                    	<p class="index inde"><span class="sheape"></span>距质保期结束时间：</p>
						<p class="index">还剩<span style="color:#000;"><span id="guaranteeSpan"></span></span></p>
                        <#--<p class="end-date">
                        	<p style="font-size: 14px;">质保期</p>
                    	</p>-->
                    <div style="text-align: center;margin-top:20px;">
                    </div>
                     <#elseif obj.status=='accept_success'>
                    <div class="deposit">
                		<div class="imgBox">
		                	<#if obj.member.basic.photoId??>
		                		<div class="head-logo" style="background:url('${obj.member.basic.memberPhoto.path}')"></div>
		                	<#else>
		                		<div class="head-logo" style="background:url('${_PATH}/res/images/default.jpg')"></div>
		                	</#if>	
		                </div>
                	</div>
                	<div class="vertical-line3" style="left:570px;"></div>
                    <div class="status-to-opt">
                    	<p class="end-date">
                        	<span class="index">已验收</span>
                    	</p>
                    </div>
                    
                    <#elseif obj.status=='finish'>
                    <div class="deposit">
                		<div class="imgBox">
		                	<#if obj.member.basic.photoId??>
		                		<div class="head-logo" style="background:url('${obj.member.basic.memberPhoto.path}')"></div>
		                	<#else>
		                		<div class="head-logo" style="background:url('${_PATH}/res/images/default.jpg')"></div>
		                	</#if>	
		                </div>
                	</div>
                	<div class="vertical-line3" style="left:570px;"></div>
                    <div class="status-to-opt">
                    	<p class="end-date">
                        	<span class="index">已完成</span>
                    	</p>
                    	<#--<button type="button" class="opt-btn">评价</button>-->
                    </div>	
                    </#if>
                   </div>
            </div>
        </div>

        <div class="project-intro">
            <div class="project_intro_content">
				一、任务描述
				${obj.intro}
            </div>
            <img src="${_PATH}/res/images/paper_foot.png" class="paper-foot">
        </div>
    </div>