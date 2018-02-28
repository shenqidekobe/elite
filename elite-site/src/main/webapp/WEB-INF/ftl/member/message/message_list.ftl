<div id="msgBar">
	<div>
	    <span style="font-size: 18px;" id="titleSpan">系统消息</span>
	    <span class="message-count">共<span id="messageCount">${messageCount}</span>条,未读<span id="head_unread">${unreadCount}</span>条</span>
	    <#if session().currentType=='company'>
		    <div class="project-btn" <#if type!="project">style="display:none;"</#if>><span id="proect_name">全部项目</span>
		        <div class="btn-triangle">
		            <div class="btn-trigger">
		               <ul class="proect" style="display:none;">
		                   <li data=""><a href="javascript:void(0);">全部项目</a></li>
			               <#list projectList as pl>
			                  <li data="${pl.id}"><a href="javascript:void(0);">${pl.name}</a></li>
			               </#list>
		               </ul>
		             </div>
		        </div>
		    </div>
	    </#if>
	</div>
	<hr style="margin-top:10px;">
	<div style="position: relative;">
	    <span class="message-select <#if status=='unread'>current-select</#if>" data="unread">未读消息</span>&nbsp;<span class="current-count" id="unreadCount">${unreadCount}</span>
	    <span class="message-select <#if status=='read'>current-select</#if>" style="margin-left:100px;" data="read">已读消息</span>&nbsp;<span class="current-count" id="readCount">${readCount}</span>
	    <div class="split-line"></div>
	</div>
</div>
<form id="messageForm">
	<input type="hidden" id="status" name="status" value="unread">
	<input type="hidden" id="type" name="type" value="system">
	<input type="hidden" id="pid" name="projectId">
	<div id="dataSecondList"></div>
</form>