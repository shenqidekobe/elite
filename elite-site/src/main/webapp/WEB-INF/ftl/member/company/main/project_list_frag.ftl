<input type="hidden" id="waitCount" value="${waitCount}" />
<input type="hidden" id="auditInCount" value="${auditInCount}" />
<input type="hidden" id="auditedCount" value="${auditedCount}" />
<#if list?size lt 1>
<div class="empty_project_box">
    <div class="icon"></div>
    <div class="text">这里什么都没有哦~</div>
    <a class="a_btn" href="${_PATH}/project/publish">去发布新项目</a>
</div>
</#if>
<#list list as pl>
	<div class="release-list">
	    <div style="position: relative;">
	        <div class="part1">
	            <div class="col-xs-4 col-md-4">
	                <span>创建时间：${pl.createTime?string("yyyy-MM-dd HH:mm")} </span>
	            </div>
	            <div class="col-xs-8 col-md-8">
	                <span class="pull-left">项目编号：${pl.projectNum}</span>
	                <div class="pull-right">
	                    <#if pl.status=='wait_audit' || pl.status=='unpass'>
		                    <img src="${_PATH}/res/images/edit_icon.png" class="opt-icon" id="edit" data="${pl.id}">
		                    <img src="${_PATH}/res/images/delete_icon.png" class="opt-icon" id="delete" data="${pl.id}">
	                    </#if>
	                </div>
	            </div>
	        </div>
	        <div class="vertical-line"></div>
	    </div>
	
	    <div class="project-detail">
	        <div class="main-info">
	            <div style="padding:20px;">
	                <div class="project-logo" style="background-color:#${pl.backgroundColor}">${pl.firstName}</div>
	                <div class="project-info">
	                    <div>
	                        <span class="project-name" title="${pl.name}"><#if pl.name?length gt 10>${pl.name?substring(0,10)}...<#else>${pl.name}</#if></span>
	                    </div>
	                    <div><span class="index">开发类型:</span>&nbsp;<span class="value" title="${pl.solutionVals}"><#if pl.solutionVals?length gt 11>${pl.solutionVals?substring(0,11)}<#else>${pl.solutionVals}</#if></span></div>
	                    <#if pl.status=='wait_audit'||pl.status=='audit_in'||pl.status=='pass_wait'||pl.status=='unpass'>
	                        <div><span class="index">预算:</span>&nbsp;<span class="value">${pl.projectBudget}</span></div>
		                    <div><span class="index">期望交付日期:</span>&nbsp;<span class="value"><#if pl.expectTime??>${pl.expectTime?string("yyyy-MM-dd")}</#if></span></div>		                
                        <#elseif pl.status=='pass_already'||pl.status=='stage_course'||pl.status=='quality'||pl.status=='finish'>
			                <div><span class="index">费用:</span>&nbsp;<span class="value"><#if pl.totalAmount??>${pl.totalAmount?string.currency}</#if></span></div>
		                    <div><span class="index">交付日期:</span>&nbsp;<span class="value"><#if pl.deliveryTime??>${pl.deliveryTime?string("yyyy-MM-dd")}</#if></span></div>
		                </#if>
	                    <div><span class="index">共<span style="color:#000;">${pl.deliveryDay}</span>个工作日</span></div>
	                </div>
	            </div>
	        </div>
	        <div class="status">
	            <div style="padding:20px 10px;">
	                <#if pl.status=='wait_audit'||pl.status=='audit_in'||pl.status=='pass_wait'||pl.status=='pass_already'>
	                   <p>已交意向金：${pl.intentionAmount?string.currency}</p>
	                <#elseif pl.status=='stage_course'>
	                   <p>托管费用合计：${pl.trustedAmount?string.currency}</p>
	                   <p>本阶段已托管：${pl.forStage.amount?string.currency}</p>
	                <#else>
	                   <p>托管费用合计：${pl.trustedAmount?string.currency}</p>
	                </#if>
	                <p>股权托管：<#if pl.isStock>有<#else>无</#if></p>
	            </div>
	        </div>
	        <div class="status">
	            <div style="padding-top:20px;text-align: center;">
	                <#assign statusLabel=""/>
	                <#if pl.status=='wait_audit'>
	                    <#assign statusLabel="待审核"/>
	                <#elseif pl.status=='audit_in'>
	                    <#assign statusLabel="审核中"/>
	                <#elseif pl.status=='pass_wait'>
	                    <#assign statusLabel="审核通过待立项"/>
	                <#elseif pl.status=='pass_already'>
	                    <#assign statusLabel="已立项"/>
	                <#elseif pl.status=='stage_course'>
	                    <#if pl.forStage??&&(pl.forStage.status=='wait_start' || pl.forStage.status=='starting' || pl.forStage.status=='wait_accept')>
	                         <#assign statusLabel="${pl.forStage.title}"/>
	                    <#elseif pl.forStage??&&(pl.forStage.status=='accept_succ'||pl.forStage.status=='quality'||pl.forStage.status=='finish')>
	                         <#assign statusLabel="${pl.forStage.title}(已验收)"/>
	                    </#if>
	                <#elseif pl.status=='quality'>
	                    <#assign statusLabel="质保期"/>
	                <#elseif pl.status=='unpass'>
	                    <#assign statusLabel="审核未通过"/>
	                <#elseif pl.status=='finish'>
	                    <#assign statusLabel="已结束"/>
	                <#elseif pl.status=='invalid'>
	                    <#assign statusLabel="已关闭"/>
	                </#if>
	                <p class="current-status">${statusLabel}</p>
	                <a href="javascript:void(0);" target="_self" id="detail" data="${pl.id}" status="${pl.status}">查看详情</a>
	            </div>
	        </div>
	        <div class="opt">
                <#if pl.status=='wait_audit'>
                    
                    <div style="padding-top:60px;text-align: center;">
                        <button type="button" class="complete-btn" id="submit_intention" data="${pl.id}">提交意向金</button>
                    </div>
                <#elseif pl.status=='audit_in'>
                    <div style="padding-top:20px;text-align: center;">
	                    <p style="font-size: 12px;">项目经理对接中</p>
	                    <button type="button" id="submit_intention" class="complete-btn" data="${pl.id}">提交意向金</button>
                    </div>
                <#elseif pl.status=='pass_wait'>
                    <div style="padding-top:60px;text-align: center;">
	                    <#if pl.sendDefined>
		                    <button type="button" class="complete-btn" id="affirm_projectdefine" style="font-size: 12px;width:126px;" data="${pl.id}">确认项目立项书</button>
		                <#else>
		                                                                        待发立项书
		                </#if>
                    </div>
                <#elseif pl.status=='pass_already'>
                    <div style="padding-top:60px;text-align: center;">
                        <#if !pl.trustStage.finished>
                            <button type="button" class="complete-btn" id="trust_amount" style="margin-top:10px;font-size: 12px;width:126px;" data="${pl.id}">托管${pl.trustStage.title}</button>
                        <#else>
		                                                                        所有阶段已结束
                        </#if>
                    </div>
                <#elseif pl.status=='stage_course'>
                    <#if pl.forStage??&&(pl.forStage.status=='wait_start' || pl.forStage.status=='starting')>
                        <div style="padding-top:20px;text-align: center;">
	                        <#--<div>
			                    <img src="${_PATH}/res/images/write_icon.png" class="show-icon">&nbsp;&nbsp;<span style="font-size: 12px;">需求修改</span>
			                </div>-->
                            <#if pl.materialUnCount gt 0>
				                <div style="margin-top:10px;position: relative;">
				                    <span style="font-size: 12px;color:#9B9B9B;">收到新文件</span>
				                    <button type="button" id="view_material" class="check-btn" data="${pl.id}">去查看</button>
				                    <div class="message-tip">${pl.materialUnCount}</div>
				                </div>
			                </#if>
		                </div>
		            <#elseif pl.forStage??&&pl.forStage.status=='wait_accept'>
		                <div style="padding-top:60px;text-align: center;">
		                    <button type="button" id="go_accept" class="complete-btn" style="margin-top:10px;" data="${pl.id}">验收</button>
		                </div>
                    <#elseif pl.forStage??&&(pl.forStage.status=='quality'||pl.forStage.status=='finish')>
                        <div style="padding-top:20px;text-align: center;">
	                        <#--<div>
			                    <button type="button" id="go_evaluate" class="evaluate-btn" data="${pl.id}">评价</button>
			                </div>-->
	                        <#if !pl.trustStage.finished>
				                <button type="button" class="complete-btn" id="trust_amount" style="margin-top:10px;font-size: 12px;width:126px;" data="${pl.id}" title="去托管${pl.trustStage.title}费用">托管${pl.trustStage.title}</button>
	                        <#else>
			                                                                        所有阶段已结束
	                        </#if>
		                </div>
                    </#if>
                <#elseif pl.status=='quality'>
                    <#--<div style="padding-top:60px;text-align: center;">
                    	<button type="button" class="complete-btn" id="go_evaluate" style="margin-top:10px;font-size: 12px;width:126px;" data="${pl.id}">评价</button>
                    </div>-->
                <#elseif pl.status=='unpass'>
                    <div style="padding-top:60px;text-align: center;">
                   	    <button type="button" class="complete-btn" id="view_cause" style="margin-top:10px;font-size: 12px;width:126px;" data="${pl.id}">查看原因</button>
                    </div>
                <#elseif pl.status=='finish'>
                    <#--<div style="padding-top:60px;text-align: center;">
                        <p style="font-size: 12px;margin-top:10px;">已评价</p>
                    </div>-->
                </#if>
	        </div>
	    </div>
	</div>
</#list>
<#include "/init/pager.ftl"/>