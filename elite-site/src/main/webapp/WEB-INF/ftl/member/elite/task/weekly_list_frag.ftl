 <p>${month?string("yyyy年MM月")}周报</p>
<input type="hidden" id="monthCount" value="${count}">
 <input type="hidden" value="${projectId}" id="projectId" />
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
	            <div class="column2">未提交周报</div>
	            <div class="report-opt">
	            		<img src="/res/images/upload_row_icon.png" class="opt-icon" flag="${wk.endTime?string("dd")}" data="${wk.endTime?string("yyyy-MM-dd")}" id="upload_weekly_${wk_index}">
		            	<input type="file" name="file" id="weeklyFile" style="display:none;">
	            </div>
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
			                <img src="${_PATH}/res/images/tag_blue_icon.png" class="tag">
			            </#if>
		                <#if list.expired??&&list.expired>
			                <img src="${_PATH}/res/images/tag_red_icon.png" class="tag">
			            </#if>
			            <img src="${_PATH}/res/images/paper_icon.png" width="18" height="22" style="vertical-align: sub;">
			            <span style="font-size: 14px;"><#if list.atta.fileName?length gt 7>${list.atta.fileName?substring(0,7)}…<#else>${list.atta.fileName}</#if></span>
			            &nbsp;<span style="font-size: 12px;color:#9B9B9B;">${list.createTime?string("MM-dd hh:mm")}</span>
			        </div>
			        <div class="report-opt">
		            	<img src="/res/images/download_row_icon.png" id="download" data="${list.id}" class="opt-icon">
            			<img src="/res/images/delete_icon.png" class="opt-icon" data="${list.id}" id="delWeekly">
			        </div>
			    </div>
	        </#list>
        </#if>
    </#list>
</div>
