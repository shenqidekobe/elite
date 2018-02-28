<table class="table">
    <tbody>
	     <#if list?size lt 1>
		   暂无数据
		 </#if>
         <#list list as pm>
             <tr style="border-top:none;">
                <td>
                    <div class="period-tag"><#if pm.stageId??>${pm.stage.title}<#else>立项阶段</#if></div>
                </td>
                <td style="color:#9B9B9B;">${pm.createTime?string("yyyy-MM-dd HH:mm")}</td>
                <#if queryType!='send'>
	                <td style="width:100px;">
	                    <img src="${_PATH}/res/images/down_icon.png" width="14" height="16">
	                    &nbsp;收到来自
	                </td>
	                <td style="color:#2CB7C9;width:94px;"><#if pm.uploadId??>${pm.uploadMember.nickName}<#else>平台PM</#if></td>
                </#if>
                <td style="position: relative;width:205px;">
                    <#if queryType!='send'&&pm.read==''>
                        <img src="${_PATH}/res/images/tag_blue_icon.png" class="tag">
                    </#if>
                    <img src="${_PATH}/res/images/zip_icon.png" width="17" height="23">
                    &nbsp;<span style="color:#4A90E2;"><#if pm.atta.fileName?length gt 5>${pm.atta.fileName?substring(0,5)}...<#else>${pm.atta.fileName}</#if></span>
                    &nbsp;<span>${pm.atta.fileSize}</span>
                </td>
                <td style="width:120px;text-align: left;">
                    <a href="javascript:void(0);" id="download" title="下载" data="${pm.id}" readed="${pm.read}"><img src="${_PATH}/res/images/download_row_icon.png" width="20" height="20"></a>
                    <#if queryType=='receive'&&pm.stage??&&pm.stage.status=='wait_accept'>
                        &nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" id="accept" title="去验收" data="${pm.projectId}" ><span>去验收</span></a>
                    </#if>
                    <#if queryType=='send'>
                        &nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" id="del" title="删除" data="${pm.id}" attaId="${pm.attaId}"><img src="${_PATH}/res/images/close_icon.png" width="20" height="20"></a>
                    </#if>
                </td>
            </tr>
        </#list>
    </tbody>
</table>