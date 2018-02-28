<div style="margin-top:20px;">
	<#if list?size lt 1>
		<div class="empty_box">
		    <div class="icon ceo_message"></div>
		    <div class="text">这里什么都没有哦~</div>
		</div>
	</#if>
    <#list list as mt>
	    <div class="message-frame" data="${mt.id}" style="cursor:pointer">
	        <img src="${_PATH}/res/images/message_frame.png">
	        <p class="message-word">${mt.title}</p>
	        <p class="message-time">${mt.createTime?string("yyyy-MM-dd HH:mm")}</p>
	    </div>
    </#list>
</div>
<#include "/init/pager.ftl"/>
