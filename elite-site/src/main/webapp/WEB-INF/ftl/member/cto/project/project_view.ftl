<input type="hidden" id="validStatus" value="${elite.status}" />
<div class="process project_view_process">
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
        <#if stageList?size gt 0>
	        <#list stageList as pds>
				<div class="process-line <#if pds.status!='wait_start'>finish-line</#if>" style="left:${flagLine}px;width:${widthLine}px;"></div>
				<#assign flagLine=flagLine+increment/>
	    	</#list>
	    	<div class="process-line <#if obj.status=='finish'|| obj.status=='quality'>finish-line</#if>" style="left:${flagLine}px;width:${widthLine}px;"></div>
	    <#else>
	    	<div class="process-line" style="left:${flagLine}px;width:${widthLine}px;"></div>
	        <#list settingList as pds>      
				<#assign flagLine=flagLine+increment/>
	        	<div class="process-line" style="left:${flagLine}px;width:${widthLine}px;"></div>
	        </#list>
	    </#if>
        <div class="process-line <#if obj.status=='finish'>finish-line</#if>" style="left:${flagLine+increment}px;width:${widthLine}px;"></div>
</div>
<#--项目详细-->
<div style="margin-top:40px;">
    <div style="text-align: center;">
        <div class="theme-lab"><strong>项目描述</strong></div>
           <#if tenderRecord.tender.atta??>
	           <a href="${tenderRecord.tender.atta.downPath}" target="_blank"><img src="${_PATH}/res/images/brooch_icon.png" class="brooch-icon">
	           &nbsp;<span class="document-name">${tenderRecord.tender.atta.fileName}</span></a>
           </#if>
    </div>
    <div class="project-index">
        <div style="padding:20px;position: relative;">
            <div class="project-logo" style="background-color:#${obj.backgroundColor}">${obj.firstName}</div>
            <div class="time-code-status">
                <span>创建时间：${obj.createTime?string("yyyy-MM-dd HH:mm")}</span>
                <span class="vertical-line"></span>
                <span style="margin-left:30px;">项目编号：${obj.projectNum}</span>
            </div>
            <span class="project-status">
                <#assign statusLabel=""/>
                
                ${statusLabel}
            </span>

            <div class="main-index">
                <div>
                    <span>${obj.name}</span>
                    <img src="${_PATH}/res/images/location_icon.png" class="location-icon">
                    &nbsp;<span style="font-size: 14px;color:#4A4A4A;">${obj.areaName}</span>
                    <#if obj.status=='wait_audit' || obj.status=='unpass'>
                        <img src="${_PATH}/res/images/edit_icon.png" class="edit-icon" id="edit" data="${obj.id}">
	                </#if>
                </div>
                <div style="font-size: 12px;margin-top:14px;position: relative;">
                 	<p><span class="index">行业分类：</span><#list obj.industryValList as industry>${industry}&nbsp;&nbsp;</#list></p>
                    <p><span class="index">开发类型：</span><span  title="${obj.solutionVals}"><#if obj.solutionVals?? && obj.solutionVals?length gt 20>${obj.solutionVals?substring(0,20)}<#else>${obj.solutionVals}</#if></span></p>
                    <#if tenderRecord??>
                    	<#if tenderRecord.status =="tender_normal">
                    		<p><span class="index">我的定标价格：</span>${tenderRecord.projectDefine.totalAmount}</p>
                    	<#else>	
                    		<p><span class="index">我的竞标价格：</span>${tenderRecord.amount}</p>
                    	</#if>	
                    <#else>
                    	<p><span class="index">预算：</span>${obj.projectBudget}</p>	
                    </#if>
                    <p class="date_released"><span class="index">期望交付日期：</span><#if obj.startTime??>${obj.startTime?string("yyyy-MM-dd")}</#if>-<#if obj.expectTime??>${obj.expectTime?string("yyyy-MM-dd")}</#if>
                       &nbsp;&nbsp;共${obj.deliveryDay?string('#')}个工作日</p>
                </div>

            </div>

            <div class="vertical-line3"></div>
                <#if tenderRecord??>
                	<#if tenderRecord.status =="tender_in">
                		<span class="project-status">已竞标</span>
                		<div class="deposit"><div class="moneyBox"><span>标书承诺质保金:</span><span>${tenderRecord.amount*percent}</span></div></div>
		                <div class="vertical-line3" style="left:570px;"></div>
		                <div class="status-to-opt">
		                	<p class="bid-num">已有<span style="color:#5C5C5C;">${tenderRecord.count}</span>人竞标 </p>
		                    <div style="text-align: center;margin-top:30px;">
		                    	<input type="hidden" id="deadlineTime" value="${tenderRecord.tender.tenderoverVal}">
		                        <p class="end-date">
		                        	<#if tenderRecord.tender.status =="tender_stop">
		                        		<span class="index">招标已结束</span>
		                        	<#elseif recommend.tender.status =="tender_in">	
		                        		<#if tenderRecord.tender.tenderoverVal gt 0>
					                    	<img src="/res/images/clock_icon.png" width="16" height="16">&nbsp;<span class="index">招标截止：</span>&nbsp;<span id="deadlineSpan"></span>
					                    <#else>
					                    	<span class="index">招标已截止</span>
					                    </#if>
		                        	</#if>	
		                        </p>
		                    </div>
	                	</div>
	                <#elseif tenderRecord.status =="tender_not">
	                	<span class="project-status" style="color:red">未中标</span>
                		<div class="deposit"><div class="moneyDiv1"><span>标书承诺质保金:</span><span>${tenderRecord.amount*percent}</span></div></div>
		                <div class="vertical-line3" style="left:570px;"></div>
		                <div class="status-to-opt">
		                  <p class="bid-num">已有<span style="color:#5C5C5C;">${tenderRecord.count}</span>人竞标 </p>
		                  <div style="text-align: center;margin-top:50px;">
		                	 <p class="end-date">
	                            <img src="/res/images/sad_face_icon.png" width="16" height="16">
	                            &nbsp;<span class="index">此次未中标,再接再厉!</span>
	                        </p>
	                      </div>  
		                    <#--
		                    <div style="text-align: center;margin-top:20px;">
		                        <div style="margin-top:10px;"><button type="button" class="recommend-btn">查看原因</button></div>
		                    </div>
		                    -->
	                	</div>
	                 <#elseif tenderRecord.status =="tender_abandon">
	                 	<span class="project-status" style="color:red">放弃项目</span>
                		<div class="deposit"><div class="moneyDiv2"><span>标书承诺质保金:</span><span>${tenderRecord.amount*percent}</span></div></div>
		                <div class="vertical-line3" style="left:570px;"></div>
		                <div class="status-to-opt">
		                	<div style="text-align: center;margin-top:50px;">
		                	 <p class="end-date">
	                            <img src="/res/images/exclamation_mark.png" width="16" height="16">
	                             &nbsp;<span style="font-size: 12px;">中标取消后会<span style="color:#FEA600;">影响你的诚信度，</span>下次请非常谨慎。</span>
	                        </p>
	                      </div>
	                	</div>
	                <#elseif tenderRecord.status =="tender_win">
	                	<span class="project-status">已中标</span>
	                	<div class="deposit"><div class="moneyDiv3"><span>标书承诺质保金:</span><span>${tenderRecord.amount*percent}</span></div></div>
		                <div class="vertical-line3" style="left:570px;"></div>
		                <div class="status-to-opt">
		                	<div style="text-align: center;margin-top:50px;">
		                    	<input type="hidden" id="deadlineTime" value="${tenderRecord.tender.tenderoverVal}">
		                        <p class="end-date">
		                            <img src="/res/images/smile_face_icon.png" width="16" height="16">
		                            &nbsp;<span class="index">恭喜中标！</span>
		                        </p>
		                       <#if tenderRecord.projectDefine??>
				                	<div><button type="button" id="confirmatta" data="${tenderRecord.project.id}" style="margin-top:10px;width:130px;" data="${recommend.project.id}" class="opt-btn">去确认项目定标书</button></div>
			                	<#else>
	                				<button type="button" class="opt-btn" style="margin-top:10px;width:130px;background:grey">待确认项目定标书</button>
				                </#if>
		                    </div>
		                	
	                	</div>
	                <#elseif tenderRecord.status =="tender_normal">
		                <#if obj.status='finish'>
		                	<span class="project-status">已结束</span>
	                		<div class="deposit"><div class="cumulativeBox"><span>累计冻结金额:</span><span>${tenderRecord.projectDefine.totalAmount}</span></div></div>
	                		<div class="vertical-line3" style="left:570px;"></div>
			                <div class="status-to-opt">
			                	<div style="text-align: center;margin-top:50px;">
			                        <p class="end-date">
			                           	
			                        </p>
			                    </div>
		                	</div>
		                <#elseif obj.status='quality'>
	                		<span class="project-status">质保期</span>
	                		<div class="deposit"><div class="moneyDiv3"><span style="color:#9B9B9B;">
	                			<p>累计冻结金额:<span>${tenderRecord.projectDefine.totalAmount}</span></p>
	                		</span>
	                		</div>
	                		</div>
	                		<div class="vertical-line3" style="left:570px;"></div>
			                <div class="status-to-opt">
			                	<div style="text-align: center;margin-top:50px;">
			                        <p class="end-date">
			                        	<#if obj.status='quality'|| obj.status='finish'><#else>待项目方托管下一阶段费用</#if>
			                        </p>
			                    </div>    
		                	</div>
		                <#elseif obj.status='stage_course'>
		                	<#if tenderRecord.trustStage.status=='wait_start'>
		                		<span class="project-status">${tenderRecord.trustStage.title}</span>
		                		<div class="deposit">
		                			<div class="tenderBox">
										<p class="tenderBox_p1">标书冻结质保金:<span>${tenderRecord.qualityAmount}</span></p>
		                
		                    			<p class="tenderBox_p2">累计冻结金额:<span>${tenderRecord.trustedAmount}</span></p>
	                    			</div>	
								</div>
				                <div class="vertical-line3" style="left:570px;"></div>
				                <div class="status-to-opt">
				                	<div style="text-align: center;margin-top:50px;">
				                        <p class="end-date">
				                           	 快把项目进行拆分吧
				                        </p>
				                       <div><button type="button" id="signTask" data="${tenderRecord.project.id}" class="opt-btn">分配任务</button></div>
				                    </div>
				                	<#----<span style="color:#FEA600;">申请延期</span>-->
			                	</div>
		                	<#elseif tenderRecord.trustStage.status=='starting'>
		                		<span class="project-status">${tenderRecord.trustStage.title}</span>
		                		<div class="deposit"><span style="color:#9B9B9B;">
									<p>标书冻结质保金:<span>${tenderRecord.qualityAmount}</span></p>
	                
	                    			<p>累计冻结金额:<span>${tenderRecord.trustedAmount}</span></p>
	                    		</span>	
								</div>
		                		<div class="vertical-line3" style="left:570px;"></div>
				                <div class="status-to-opt">
				                	<div style="text-align: center;margin-top:40px;">
				                       <div><button type="button" class="opt-btn" id="viewMaterial" style="margin-top:10px;width:130px;">查看/提交产出物</button></div>
				                    </div>
			                	</div>
			                <#elseif tenderRecord.trustStage.status=='quality'>
			                	<span class="project-status">质保期</span>
		                		<div class="deposit"><span style="color:#9B9B9B;">
	                    			<p>累计冻结金额:<span>${tenderRecord.trustedAmount}</span></p>
	                    		</span>
		                		</div>
		                		<div class="vertical-line3" style="left:570px;"></div>
				                <div class="status-to-opt">
				                	<div style="text-align: center;margin-top:50px;">
				                        <p class="end-date">
				                        	待项目方托管下一阶段费用
				                        </p>
				                    </div>    
			                	</div>	
			                <#elseif tenderRecord.trustStage.status=='finish'>
			                	<span class="project-status">${tenderRecord.trustStage.title}(已验收)</span>
		                		<div class="deposit"><div class="cumulativeBox"><span>累计冻结金额:</span><span>${tenderRecord.trustedAmount}</span></div></div>
		                		<div class="vertical-line3" style="left:570px;"></div>
				                <div class="status-to-opt">
				                	<div style="text-align: center;margin-top:50px;">
				                        <p class="end-date">
				                           	待项目方托管下一阶段费用
				                        </p>
				                    </div>
			                	</div>	
		                	<#elseif tenderRecord.trustStage.status=='wait_accept'>
		                		<span class="project-status">${tenderRecord.trustStage.title}(待验收)</span>
		                		<div class="deposit">
		                			<div class="qualityBox">
										<p class="qualityBox_p1"><span>标书冻结质保金:</span><span>${tenderRecord.qualityAmount}</span></p>
		                
		                    			<p class="qualityBox_p2"><span>累计冻结金额:</span><span>${tenderRecord.trustedAmount}</span></p>
	                    			</div>
								</div>
		                		<div class="vertical-line3" style="left:570px;"></div>
				                <div class="status-to-opt">
					                 <div style="text-align: center;margin-top:50px;">
				                        <p class="end-date">
				                           	 项目方验收中
				                        </p>
				                       <button type="button" class="opt-btn"  id="viewMaterial" style="margin-top:10px;width:130px;">查看/提交产出物</button>
				                    </div>
			                	</div>
		                	</#if>
		                </#if>	
                	</#if>
                	<#else>
            		<#assign statusLabel=""/>
		                <#if recommend.type=='r_project'>
		                    <#assign statusLabel="平台推荐"/>
		                <#elseif recommend.type=='r_task'>
		                    <#assign statusLabel="任务推荐"/>
		                </#if>
		                <#if recommend.tender.status =="tender_in">
		                    <span class="project-status">待竞标</span>
			                <div class="deposit"><span class="platformBox">${statusLabel}</span></div>
			                <div class="vertical-line3" style="left:570px;"></div>	
			                <div class="status-to-opt">
			                    <p class="bid-num">已有<span style="color:#5C5C5C;">${recommend.tenderCount}</span>人竞标</p>
			                    <div style="text-align: center;margin-top:20px;">
			                        <p class="end-date">
			                        	<input type="hidden" id="deadlineTime" value="${recommend.tender.tenderoverVal}">
			                            <img src="/res/images/clock_icon.png" width="16" height="16">
			                            &nbsp;<span class="index">招标截止：</span>&nbsp;<span><span id="deadlineSpan"></span></span>
			                        </p>
			                         <div><button type="button" style="margin-top:20px;margin-right:20px;" class="opt-btn" id="totender" data="${recommend.project.id}">去竞标</button></div>
		                        	 <#--<div style="margin-top:10px;"><button type="button" class="recommend-btn">推荐给他人</button></div>-->
			                        <#--<div><button type="button" class="opt-btn" id="totender" data="${recommend.project.id}">去竞标</button></div>
			                        <div style="margin-top:10px;"><button type="button" class="recommend-btn">推荐给他人</button></div>-->
			                    </div>
		                	</div>	
		              <#else>
		              		<span class="project-status">招标已截止</span>
			                <div class="deposit"><span class="platformBox">${statusLabel}</span></div>
			                <div class="vertical-line3" style="left:570px;"></div>	
		                	<div class="status-to-opt">
			                    <p class="bid-num">已有<span style="color:#5C5C5C;">${recommend.tenderCount}</span>人竞标</p>
			                    <div style="text-align: center;margin-top:30px;">
			                        <p class="end-date">
			                        	招标已结束
			                        </p>
			                    </div>
		                	</div>
		              </#if>  	
            	</#if>
        </div>
    </div>

    <div class="project-intro">
        <div class="project_intro_content">
        <dl>
        <#if tenderRecord?? && tenderRecord.status =="tender_normal">
	        <dt>一、定标书描述</dt>
	        <dd>${define.clause}</dd>
	    <#else>
	    	<dt>一、项目描述</dt>
	        <dd>${obj.intro}</dd>    
        </#if>
        <dt>二、参考项目</dt>
        <dd>${obj.referProject}</dd>
        <dt>联系人：</dt>
        <dd>${obj.contactName}    ${obj.contactPhone}</dd>
        </div>
        <img src="${_PATH}/res/images/paper_foot.png" class="paper-foot">
    </div>
    <#--
    <#if  tenderRecord?? && tenderRecord.status =="tender_not">
	    <div>
	    	失败原因：<span style="color:red">未中标</span>
	    </div>
    </#if>-->
</div>