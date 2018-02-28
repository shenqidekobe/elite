 <p>${month?string("yyyy年MM月")}周报</p>
 <input type="hidden" id="monthCount" value="${count}">
 <input type="hidden" id="monthUnCount" value="${unCount}">
<div class="report-box">
    <#list weeklyMap.keySet() as wk>
        <#if !wk.exist>
            <div class="report" style="margin-top:0;">
	            <div class="column1">${wk.startTime?string("MM-dd")}-${wk.endTime?string("MM-dd")} &nbsp;&nbsp;
	                <#if wk.currentWeek>
	                     <span style="color:#FEA600;">当前周</span>
	                <#else>
	                                                                           周报
                    </#if>
	            </div>
	            <div class="column2">未收到周报</div>
	            <button type="button" class="opt-btn" id="blag_weekly" pid="${projectId}" month="${monthNum}" week="${wk_index+1}">索要周报</button>
	        </div>
        <#else>
	        <#list weeklyMap.get(wk) as list>
		        <div class="report">
			        <div class="column1">${wk.startTime?string("MM-dd")}-${wk.endTime?string("MM-dd")} &nbsp;
			            <#if wk.currentWeek>
		                     <span style="color:#FEA600;">当前周</span>
		                <#else>
		                                                                           周报
	                    </#if>
			        </div>
			        <div class="column2">
			            <#if list.readed??&&!list.readed>
			                <img src="${_PATH}/res/images/tag_blue_icon.png" class="tag" data="readed">
			            </#if>
		                <#if list.expired??&&list.expired>
			                <img src="${_PATH}/res/images/tag_red_icon.png" class="tag" data="expired">
			            </#if>
			            <img src="${_PATH}/res/images/paper_icon.png" width="18" height="22" style="vertical-align: sub;">
			            <span style="font-size: 14px;">${list.atta.fileName}…</span>
			            &nbsp;<span style="font-size: 12px;color:#9B9B9B;">${list.createTime?string("MM-dd hh:mm")}</span>
			        </div>
			        <div class="report-opt">
			            <img src="${_PATH}/res/images/download_row_icon.png" class="opt-icon" id="download" data="${list.id}" readed="${list.readed}">
			        </div>
			    </div>
	        </#list>
        </#if>
    </#list>
</div>
