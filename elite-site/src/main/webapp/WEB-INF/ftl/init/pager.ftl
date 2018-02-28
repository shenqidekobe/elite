<ul class="pagination pull-right">
	<#if pagination.pageCount gt 1>
		<!-- 计算上一页按钮样式 -->
		<#if pagination.pageth == 1>
			<li><a href="###" class="disable">«</a></li>
		<#else>
			<li id="prev_pager"><a href="javascript:void(0);">«</a></li>
		</#if>
		<!-- 计算分页样式 -->
		<!-- 只有一页时 -->
		<#if pagination.pageCount lt 2>
			<li class="active" id="jump_pager" data="1"><a href="javascript:void(0);">1</a></li>
		<#else>
			<!-- 包含多页时 -->
			<!-- 首页 -->
			<#if pagination.pageth == 1>
				<li class="active" id="jump_pager" data="1"><a href="javascript:void(0);">1</a></li>
			<#else>
				<li id="jump_pager" data="1"><a href="javascript:void(0);">1</a></li>
			</#if>
			<!-- 是否显示首页分割 -->
			<#if pagination.headView><li><a>...</a></li></#if>
			<!-- 显示中间页 -->
			<#list pagination.pageths as temp>
				<#if temp == pagination.pageth>
					<li class="active" id="jump_pager" data="${temp}"><a href="javascript:void(0);">${temp}</a></li>
				<#else>
					<li id="jump_pager" data="${temp}"><a href="javascript:void(0);">${temp}</a></li>
				</#if>
			</#list>
			<!-- 是否显示尾页分割 -->
			<#if pagination.footView><li><a>...</a></li></#if>
			<!-- 尾页 -->
			<#if pagination.pageth == pagination.pageCount>
				<li class="active" id="jump_pager" data="${pagination.pageCount}"><a href="javascript:void(0);">${pagination.pageCount}</a></li>
			<#else>
				<li id="jump_pager" data="${pagination.pageCount}"><a href="javascript:void(0);">${pagination.pageCount}</a></li>
			</#if>
		    <!-- 计算下一页按钮样式 -->
			<#if pagination.pageth == pagination.pageCount>
				<li><a href="###" class="disable">»</a></li>
			<#else>
				<li id="next_pager"><a href="javascript:void(0);">»</a></li>
		     </#if>
	    </#if>
	</#if>
</ul>
<input type="hidden" id="pager_pageCount" name="pageCount" value="${pagination.pageCount}"/>
<input type="hidden" id="pager_pageth" name="pageth" value="${pagination.pageth}"/>
<input type="hidden" id="pager_rowCount" name="rowCount" value="${pagination.rowCount}"/>
<input type="hidden" id="pager_pageSize" name="pageSize" value="${pagination.pageSize}"/>