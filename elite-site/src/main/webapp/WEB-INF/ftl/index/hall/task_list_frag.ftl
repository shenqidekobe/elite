 <ul class="clearfloat">
	<#if list?size lt 1>
	暂无数据
	</#if>
    <#list list as it>
	    <li data="${it.id}" status="${it.status}" id="tl_task" style="cursor:pointer;">
	        <div class="bidContent">
	        	<div class="bidContent-a">
	            	<img <#if it.imgName??>src="${_PATH}/res/images/taskLogo/${it.imgName}"<#else>src="${_PATH}/res/images/index/bidContent.png"</#if>>
	            </div>	
	            <div class="bidContent-b">
	                <p class="major">${it.name}</p>
	                <p class="title">
	                    <span>${it.taskTypeVal}</span>
	                    <span>${it.demandTypeVal}</span>
	                    <span>${it.applyCount}人参与</span>
	                </p>
	                <p class="time">交付时间：
		                <span><#if it.deliveryTime??>${it.deliveryTime?string("yyyy.MM.dd")}</#if></span>
		                <span>共${it.deliveryDay}个工作日</span>
	                </p>
	            </div>
	            <div class="bidContent-c">
	                <span class="money">${it.amount?string.currency}</span>
	                <#assign statusLabel=""/>
	                <#if it.status=='wait_recruit'>
	                    <#assign statusLabel="待认领"/>
	                <#elseif it.status=='recruit_in'>
	                    <#assign statusLabel="招募中"/>
	                <#elseif it.status=='starting'||it.status=='wait_accept'||it.status=='accept_success'>
	                    <#assign statusLabel="进行中"/>
	                <#elseif it.status=='quality'>
	                    <#assign statusLabel="质保中"/>
	                <#elseif it.status=='finish'>
	                    <#assign statusLabel="已完成"/>
	                </#if>
	                <a class="tobid prepare" href="javascript:void(0);" id="detail" data="${it.id}" status="${it.status}">${statusLabel}</a>
	            </div>
	        </div>
	    </li>
    </#list>
</ul>
<input type="hidden" id="pagingType" value="task"/>
<#if pagination.pageCount gt 1>
	<div class="pickup-page">
		<input type="hidden" id="pager_pageCount" name="pageCount" value="${pagination.pageCount}"/>
		<input type="hidden" id="pager_pageth" name="pageth" value="${pagination.pageth}"/>
		<input type="hidden" id="pager_rowCount" name="rowCount" value="${pagination.rowCount}"/>
		<input type="hidden" id="pager_pageSize" name="pageSize" value="${pagination.pageSize}"/>
	    <a class="page-l" href="javascript:void(0);"></a>
	    <a class="page-r" href="javascript:void(0);"></a>
	</div>
</#if>