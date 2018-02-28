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
                        <span class="project-name" title="${recommend.project.name}"><#if recommend.project.name?length gt 5>${recommend.project.name?substring(0,5)}…<#else>${recommend.project.name}</#if></span>
                        <#list recommend.project.industryValList as industry>
	                    	<#if industry_index lt 2>
                    			<div class="class-label"><#if industry?length gt 2>${industry?substring(0,2)}<#else>${industry}</#if></div>
                    		</#if>
	                    </#list>
                    </div>
                    <div><span class="index">开发类型:</span>
                    	<span title="${recommend.project.solutionVals}"><#if recommend.project.solutionVals?length gt 10>${recommend.project.solutionVals?substring(0,10)}<#else>${recommend.project.solutionVals}</#if></span>
                    </div>
                    <#if recommend.status =="tender_normal">
                    	<div><span class="index">我的定标价格:</span>&nbsp;<span class="value">${recommend.projectDefine.totalAmount}元</span></div>
                    <#else>	
                    	<div><span class="index">我的竞标价格:</span>&nbsp;<span class="value">${recommend.amount}元</span></div>
                    </#if>	
                </div>

            </div>
        </div>
       <#if recommend.status =="tender_win">
        <div class="status">
            <div style="padding:20px 18px;">
                <p>标书承诺质保金:<span>${recommend.amount*percent}</span></p>
            </div>
        </div>
        <div class="status">
            <div style="padding-top:20px;text-align: center;">
                <p class="current-status">已中标</p>
                <a href="javascript:void(0);" id="projectInfo" data="${recommend.project.id}">查看详情</a>
            </div>
        </div>
        <div class="opt">
            <div style="padding:20px 10px;">
                <div>
                    <img src="/res/images/smile_face_icon.png" class="show-icon">
                    &nbsp;<span style="font-size: 12px;">恭喜中标!</span>
                </div>
                <#--<div>
                    <img src="/res/images/clock_icon.png" class="show-icon">
                    &nbsp;<span style="font-size: 12px;"><span style="color:#9B9B9B;">还剩</span><span id="deadlineSpan_${flag}" data="${recommend.tender.tenderoverVal}">${recommend.tender.endTime}</span></span>
                </div>
                -->
                <#if recommend.projectDefine?? && recommend.projectDefine.status=='wait'>
                	<button type="button" class="complete-btn" id="confirmatta" data="${recommend.project.id}" style="margin-top:10px;width:130px;">去确认项目定标书</button>
                <#else>
	                <button type="button" class="complete-btn" style="margin-top:10px;width:130px;background:grey">待确认项目定标书</button>
                </#if>
            </div>
        </div>
        <#elseif recommend.status =="tender_normal">
           <div class="status">
                <div style="padding:20px 10px;">
                	<#if recommend.project.status=='quality' || recommend.project.status=='finish'>
                    	<p>累计冻结金额:<br><span>${recommend.projectDefine.totalAmount}</span></p>
                    <#else>
                    	<p>标书冻结质保金:<span>${recommend.qualityAmount}(${percent*100}%)</span></p>
                    	<p>累计冻结金额:<br><span>${recommend.trustedAmount}</span></p>	
                    </#if>
                </div>
            </div>
            <#if recommend.project.status=='quality'>
	            <div class="status">
		            <div style="padding-top:20px;text-align: center;">      
		                <p class="current-status">质保阶段</p>
		                <a href="javascript:void(0);" id="projectInfo" data="${recommend.project.id}">查看详情</a>
		            </div>
		        </div>
		        <div class="opt">
		            <div style="padding:20px 10px;">
		                <p class="yanqi"></p>
		            </div>
		        </div>
		    <#elseif recommend.project.status=='finish'>
	            <div class="status">
	                <div style="padding-top:20px;text-align: center;">      
	                    <p class="current-status">已结束</p>
	                    <a href="javascript:void(0);" id="projectInfo" data="${recommend.project.id}">查看详情</a>
	                </div>
	            </div>
	            <div class="opt">
	                <div style="padding:20px 10px;">
	                    <p class="yanqi"></p>
	                </div>
	            </div> 
	        <#elseif recommend.project.status=='stage_course'>       
	            <#if recommend.trustStage.status=='wait_start'>
	            <div class="status">
	                <div style="padding-top:20px;text-align: center;">      
	                    <p class="current-status">${recommend.trustStage.title}</p>
	                    <a href="javascript:void(0);" id="projectInfo" data="${recommend.project.id}">查看详情</a>
	                </div>
	            </div>
	            <div class="opt">
	                <div style="padding:20px 10px;">
	                    <button type="button" data="${recommend.project.id}" class="complete-btn" id="giveTask" style="margin-top:10px;width:130px;">分配任务</button>
	                    <#--<p class="yanqi">申请延期</p>-->
	                </div>
	            </div>  
	            <#elseif recommend.trustStage.status=='quality'>
		        	<div class="status">
		                <div style="padding-top:20px;text-align: center;">      
		                    <p class="current-status">${recommend.trustStage.title}</p>
		                    <a href="javascript:void(0);" id="projectInfo" data="${recommend.project.id}">查看详情</a>
		                </div>
		            </div>
		            <div class="opt">
		                <div style="padding:20px 10px;">
		                    <p class="yanqi">质保期</p>
		                </div>
		            </div>    
	            <#elseif recommend.trustStage.status=='finish'>
			        	<div class="status">
			                <div style="padding-top:20px;text-align: center;">      
			                    <p class="current-status">${recommend.trustStage.title}(已验收)</p>
			                    <a href="javascript:void(0);" id="projectInfo" data="${recommend.project.id}">查看详情</a>
			                </div>
			            </div>
			            <div class="opt">
			                <div style="padding:20px 10px;">
			                    <p class="yanqi">待项目方托管下一阶段费用</p>
			                </div>
			            </div>    
	            <#elseif recommend.trustStage.status=='starting'>
	            <div class="status">
	                <div style="padding-top:20px;text-align: center;">      
	                    <p class="current-status">${recommend.trustStage.title}</p>
	                    <a href="javascript:void(0);" id="projectInfo" data="${recommend.project.id}">查看详情</a>
	                </div>
	            </div>
	            <div class="opt">
	                <div style="padding:20px 10px;">
	                	<p class="yanqi">收到新文件</p>
	                    <button type="button" class="complete-btn" id="viewMaterial" data="${recommend.project.id}" style="margin-top:10px;width:130px;">查看/提交产出物</button>
	                    <#--<p class="yanqi">申请延期</p>-->
	                </div>
	            </div>
	            <#elseif recommend.trustStage.status=='wait_accept'>
	            <div class="status">
	                <div style="padding-top:20px;text-align: center;">      
	                    <p class="current-status">${recommend.trustStage.title}(待验收)</p>
	                    <a href="javascript:void(0);" id="projectInfo" data="${recommend.project.id}">查看详情</a>
	                </div>
	            </div>
	            <div class="opt">
	                <div style="padding:20px 10px;">
	                	<p class="yanqi">项目方验收中</p>
	                    <button type="button" class="complete-btn" id="viewMaterial" data="${recommend.project.id}" style="margin-top:10px;width:130px;">查看/提交产出物</button>
	                </div>
	            </div>
              </#if>
        	</#if>
        </#if>
    </div>
</div>
</#list>
<#include "/init/pager.ftl"/>