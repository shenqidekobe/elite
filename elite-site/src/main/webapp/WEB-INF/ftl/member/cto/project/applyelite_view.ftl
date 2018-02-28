    <div class="window-assigned-box">
        <h1>共有<span class="number">${count}</span>名精英报名</h1>
        <div class="wab-content">
            <div class="wab-title">
                <div class="name">用户</div>
                <div class="experience">工作经验</div>
                <div class="time">每周可共享</div>
                <div class="task-number">进行中的任务</div>
            </div>
            <#list list as obj>
            	<div class="wab-group unactive-wab">
	                <div class="name relative">
	                <#if obj.info.basic.photoId??>
	                    <img class="imgDiv" src="${obj.info.basic.memberPhoto.path}" data="${obj.memberId}">
	                <#else>
	                	<img class="imgDiv" src="${_PATH}/res/images/default.jpg" data="${obj.memberId}">    
	                </#if>    
	                    <p class="name-text">${obj.info.nickName}</p>
	                    <p class="board">搭伙${obj.boardCount}次</p>
	                </div>
	                <div class="experience">${obj.info.elite.jobAgeVal}&nbsp;</div>
	                <div  class="time">${obj.info.elite.allotDurationVal}&nbsp;</div>
	                <div class="task-number" >
	                    <span class="number-total">${obj.taskCount}</span>
	                    <button type="button" class="task-btn" data="${obj.taskId}" member="${obj.memberId}" style="display: none">指派给他/她</button>
	                </div>
	            </div>
            </#list>
        <div class="window-close"></div>
    </div>
