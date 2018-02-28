<div class="process project_details_process">
    <#if obj.status=='wait_audit'||obj.status=='audit_in'||obj.status=='pass_wait'||obj.status=='pass_already'>
	    <div class="process-circle active-process">1</div>
	    <div class="process-circle <#if obj.status!='wait_audit'>active-process</#if>" style="left:230px;">2</div>
	    <div class="process-circle <#if obj.status!='audit_in'&&obj.status!='wait_audit'>active-process</#if>" style="left:440px;">3</div>
	    <div class="process-circle <#if obj.status=='pass_already'>active-process</#if>" style="left:650px;">
	        <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
	    </div>
	    <p class="process-title active-title">待审核</p>
	    <p class="process-title <#if obj.status!='wait_audit'>active-title</#if>" style="left:224px;">审核中</p>
	    <p class="process-title <#if obj.status!='audit_in'&&obj.status!='wait_audit'>active-title</#if>" style="left:410px;">审核通过,待立项</p>
	    <p class="process-title <#if obj.status=='pass_already'>active-title</#if>" style="left:636px;">立项成功</p>
	    
	    <div class="process-line <#if obj.status!='wait_audit'>finish-line</#if>"></div>
	    <div class="process-line <#if obj.status!='audit_in'&&obj.status!='wait_audit'>finish-line</#if>" style="left:280px;"></div>
	    <div class="process-line <#if obj.status=='pass_already'>finish-line</#if>" style="left:490px;"></div>
	
	    <p class="process-tip">项目经理对接</p>
	    <p class="process-tip" style="left:310px;">提交项目意向金</p>
	    <p class="process-tip" style="left:524px;">核实立项确认书</p>
	<#elseif obj.status=='invalid'>
	   <div class="process-circle active-process">1</div>
	    <div class="process-circle active-process" style="left:230px;">2</div>
	    <div class="process-circle active-process" style="left:440px;">3</div>
	    <div class="process-circle active-process" style="left:650px;">
	        <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
	    </div>
	    <p class="process-title active-title">待审核</p>
	    <p class="process-title active-title" style="left:224px;">审核中</p>
	    <p class="process-title active-title" style="left:410px;">审核未通过</p>
	    <p class="process-title active-title" style="left:636px;">无效项目</p>
	    
	    <div class="process-line finish-line"></div>
	    <div class="process-line finish-line" style="left:280px;"></div>
	    <div class="process-line finish-line" style="left:490px;"></div>
	
	    <p class="process-tip">项目经理对接</p>
	    <p class="process-tip" style="left:310px;">平台审核</p>
    <#else>
        <#assign increment=110/>
    	<#assign flag=146/>
		<#if stageList?size gt 0>
		<div class="process-circle active-process">1</div>
	    	<#list stageList as pds>
	   	   		<div class="process-circle <#if pds.status!='wait_start'>active-process</#if>" style="left:${flag}px;">${pds_index+2}</div>
	   	   		<#assign flag=flag+increment/>
	        </#list>
        <#else>
            <div class="process-circle <#if obj.status='pass_already'>active-process</#if>">1</div>
	        <#list settingList as pds>      
	        	<div class="process-circle" style="left:${flag}px;">${pds_index+2}</div>
	        	<#assign flag=flag+increment/>
	        </#list>	
        </#if>
        <div class="process-circle <#if obj.status='quality'||obj.status='finish'>active-process</#if>" style="left:${flag}px;">${stageList?size+2}
        </div>
         <div class="process-circle <#if obj.status='finish'>active-process</#if>" style="left:${flag+increment}px;">
            <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
        </div>

        <#assign flagTitle=125/>
        <#if stageList?size gt 0>
        	<p class="process-title active-title">立项</p>
	        <#list stageList as pds>
				<div class="process-title <#if pds.status!='wait_start'>active-title</#if>" style="left:${flagTitle}px;">${pds.title}</div>
				<#assign flagTitle=flagTitle+increment/>
	    	</#list>
	    <#else>
	    	<p class="process-title <#if obj.status='pass_already'>active-title</#if>">立项</p>
	        <#list settingList as pds>      
	        	<div class="process-title" style="left:${flagTitle}px;">${pds.title}</div>
				<#assign flagTitle=flagTitle+increment/>
	        </#list>
	    </#if>
	    
        <div class="process-title <#if obj.status='quality'||obj.status='finish'>active-title</#if>" style="left:${flagTitle+15}px;">质保期</div>
        <div class="process-title <#if obj.status='finish'>active-title</#if>" style="left:${flagTitle+increment}px;">已完成</div>
        
        <#assign flagLine=65/>
        <#assign widthLine=80/>
        <div class="process-line finish-line" style="left:${flagLine}px;width:${widthLine}px;"></div>
        <#if stageList?size gt 0>
	        <#list stageList as pds>
				<#assign flagLine=flagLine+increment/>
				<div class="process-line <#if pds.status!='wait_start'>finish-line</#if>" style="left:${flagLine}px;width:${widthLine}px;"></div>
	    	</#list>
	    <#else>
	        <#list settingList as pds>      
				<#assign flagLine=flagLine+increment/>
	        	<div class="process-line" style="left:${flagLine}px;width:${widthLine}px;"></div>
	        </#list>
	    </#if>
        <div class="process-line <#if obj.status='finish'>finish-line</#if>" style="left:${flagLine+increment}px;width:${widthLine}px;"></div>
     </#if>
</div>
<#--项目详细-->
<div style="margin-top:40px;">
    <div style="text-align: center;">
        <div class="theme-lab"><strong><#if obj.name?length gt 7>${obj.name?substring(0,7)}...<#else>${obj.name}</#if> 项目描述</strong></div>
        <#list obj.attas as ats>
           <a href="${ats.atta.downPath}" target="_blank"><img src="${_PATH}/res/images/brooch_icon.png" class="brooch-icon">
           &nbsp;<span class="document-name">${ats.atta.fileName}</span></a>
       </#list>
    </div>
    <div class="project-index">
        <div style="padding:20px;position: relative;">
            <div class="project-logo" style="background-color:#${obj.backgroundColor}">${obj.firstName}</div>
            <div class="time-code-status">
                <span>创建时间：${obj.createTime?string("yyyy.MM.dd HH:mm")}</span>
                <span class="vertical-line"></span>
                <span style="margin-left:30px;">项目编号：${obj.projectNum}</span>
            </div>
            <input type="hidden" id="status" value="${obj.status}">
            <span class="project-status">
                <#assign statusLabel=""/>
                <#if obj.status=='wait_audit'>
                    <#assign statusLabel="待审核"/>
                <#elseif obj.status=='audit_in'>
                    <#assign statusLabel="审核中"/>
                <#elseif obj.status=='pass_wait'>
                    <#assign statusLabel="审核通过待立项"/>
                <#elseif obj.status=='pass_already'>
                    <#assign statusLabel="已立项"/>
                <#elseif obj.status=='stage_course'>
                    <#if obj.forStage??&&(obj.forStage.status=='wait_start' || obj.forStage.status=='starting' || obj.forStage.status=='wait_accept')>
                         <#assign statusLabel="${obj.forStage.title}"/>
                    <#elseif obj.forStage??&&(obj.forStage.status=='accept_succ'||obj.forStage.status=='quality'||obj.forStage.status=='finish')>
                         <#assign statusLabel="${obj.forStage.title}(已验收)"/>
                    </#if>
                <#elseif obj.status=='quality'>
                    <#assign statusLabel="质保期"/>
                <#elseif obj.status=='unpass'>
                    <#assign statusLabel="审核未通过"/>
                <#elseif obj.status=='finish'>
                    <#assign statusLabel="已结束"/>
                </#if>
                ${statusLabel}
            </span>

            <div class="main-index">
                <div>
                    <span>${obj.name}</span>
                    <#if obj.areaName??><img src="${_PATH}/res/images/location_icon.png" class="location-icon"></#if>
                    &nbsp;<span style="font-size: 14px;color:#4A4A4A;">${obj.areaName}</span>
                    
                    <#if obj.status=='wait_audit' || obj.status=='unpass'>
                        <img src="${_PATH}/res/images/edit_icon.png" class="edit-icon" id="edit" data="${obj.id}">
	                </#if>
                </div>
                <div style="font-size: 12px;margin-top:14px;position: relative;">
                    <p class="development_type"><span class="index">开发类型：</span>${obj.solutionVals}</p>
                    <#if obj.status=='wait_audit'||obj.status=='audit_in'||obj.status=='pass_wait'||obj.status=='unpass'>
                         <p><span class="index">预算：</span>${obj.projectBudget}</p>
                         <p class="date_released"><span class="index">期望交付日期：</span><#if obj.expectTime??>${obj.startTime?string("yyyy.MM.dd")}-${obj.expectTime?string("yyyy.MM.dd")}</#if>
                          &nbsp;&nbsp;共${obj.deliveryDay?string('#')}个工作日</p>                
                    <#elseif obj.status=='pass_already'||obj.status=='stage_course'||obj.status=='quality'||obj.status=='finish'>
		                 <p><span class="index">费用：</span>${obj.totalAmount?string.currency}</p>
                         <p><span class="index">交付日期：</span><#if obj.deliveryTime??>${obj.startTime?string("yyyy.MM.dd")}-${obj.deliveryTime?string("yyyy.MM.dd")}</#if>
                          &nbsp;&nbsp;共${obj.deliveryDay?string('#')}个工作日</p>     
	                </#if>
                </div>

            </div>

            <div class="vertical-line3"></div>
            <div class="deposit">
            		<div class="intentionBox">
	            		<#if obj.status=='wait_audit'||obj.status=='audit_in'||obj.status=='pass_wait'||obj.status=='pass_already'>
		                    <span>已交意向金:</span>
						    <span>${obj.intentionAmount?string.currency}</span>
		                <#elseif obj.status=='stage_course'>
		                   <span>已托管费用合计：${obj.trustedAmount?string.currency}</span><br/>
		                   <span>本阶段已托管费用：${obj.forStage.amount?string.currency}</span>
		                <#else>
		                   <span>已托管费用合计：${obj.trustedAmount?string.currency}</span>
		                </#if>
					</div>
		    </div>
            <div class="vertical-line3" style="left:570px;"></div>
            <#if obj.status=='wait_audit'||obj.status=='audit_in'>
                <button type="button" id="submit_intention" class="opt-btn" data="${obj.id}">提交意向金</button>
            <#elseif obj.status=='pass_wait'>
                <#if obj.sendDefined>
                    <button type="button" id="affirm_define" style="font-size: 12px;width:126px;right: 15px;" class="opt-btn" data="${obj.id}">确认项目立项书</button>
                <#else>
                     <span class="wait_text">待发立项书</span>
                </#if>
            <#elseif obj.status=='pass_already'>
                <div class="describePartRight">
                    <button type="button" class="opt-btn" id="trust_amount" style="position:initial;font-size: 12px;width:132px;right: 5px;" data="${obj.id}" title="去托管${obj.trustStage.title}费用">托管${obj.trustStage.title}</button>
                </div>
            <#elseif obj.status=='stage_course'>
                <#if obj.forStage??&&(obj.forStage.status=='wait_start' || obj.forStage.status=='starting')>
                    <div class="comb describePartRight">
	                    <span>${obj.forStage.title}进行中</span>
	                </div>
	            <#elseif obj.forStage??&&obj.forStage.status=='wait_accept'>
	                <div class="comb describePartRight">
	                    <button type="button" id="go_accept" class="opt-btn" style="margin-top:10px;" data="${obj.id}">验收</button>
	                </div>
                 <#elseif obj.forStage??&&(obj.forStage.status=='accept_succ'||obj.forStage.status=='quality'||obj.forStage.status=='finish')>
                    <div class="comb describePartRight">
		                <button type="button" class="opt-btn" id="trust_amount" style="position:initial;margin-top:10px;font-size: 12px;width:132px;right:10px" data="${obj.id}" title="去托管${obj.trustStage.title}费用">托管${obj.trustStage.title}</button>
	                </div>
                </#if>
            </#if>
           
        </div>
    </div>

    <div class="project-intro">
        <div class="project_intro_content">
	        <dl>
	        	<dt>一、项目描述</dt>
	        	<dd>${obj.intro}</dd>
	        	<dt>二、参考项目</dt>
	        	<dd>${obj.referProject}</dd>
	        	<dd>联系人：${obj.contactName}    ${obj.contactPhone}</dd>
	        </dl>
        </div>
        <img src="${_PATH}/res/images/paper_foot.png" class="paper-foot">
    </div>
</div>