 <ul class="clearfloat">
	<#if list?size lt 1>
	   暂无数据
	</#if>
    <#list list as it>
	    <li>
	        <div class="bidContent">
	            <div class="bidContent-a"></div>
	            <div class="bidContent-b">
	                <p class="major">${it.name}</p>
	                <p class="time">交付时间：
		               <span><#if it.deliveryTime??>${it.deliveryTime?string("yyyy.MM.dd")}</#if></span>
		               <span>共${it.deliveryDay}个工作日</span>
	                </p>
	            </div>
	            <div class="bidContent-c">
	                <span class="money">${it.totalAmount?string.currency}<#if pl.isStock>+股权</#if></span>
	                <a class="tobid prepare" href="javascript:void(0);">去竞标</a>
	            </div>
	        </div>
	    </li>
    </#list>
</ul>
<input type="hidden" id="pagingType" value="project"/>
<div class="pickup-page">
	<input type="hidden" id="pager_pageCount" name="pageCount" value="${pagination.pageCount}"/>
	<input type="hidden" id="pager_pageth" name="pageth" value="${pagination.pageth}"/>
	<input type="hidden" id="pager_rowCount" name="rowCount" value="${pagination.rowCount}"/>
	<input type="hidden" id="pager_pageSize" name="pageSize" value="${pagination.pageSize}"/>
    <a class="page-l" href="javascript:void(0);"></a>
    <a class="page-r" href="javascript:void(0);"></a>
</div>
