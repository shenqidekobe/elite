<span style="font-size: 18px;">${obj.name}项目日志</span>
<span class="log-count">共产生<span style="color:#30C3D3;">${count}</span>条项目日志</span>
<hr style="margin-top:10px;">
<div>
     <form id="logListForm" class="logListForm">
	    <div style="position: relative;width:180px;display: inline-block;">
	        <input type="hidden" name="projectId" value="${obj.id}">
	        <input type="hidden" name="stageCode" id="stageId">
	        <input type="text" class="form-control" placeholder="请选择阶段" id="stageBudget" name="stageBudget" readonly style="width:180px;">
	        <div class="triangle" id="log_triangle">
	            <div class="trigger"></div>
	        </div>
	        <@projectStage projectId="${obj.id}"></@projectStage>
	        <div class="budget">
	            <#if stageList??>
		        <ul>
		            <li data="">全部</li>
		            <#list stageList as sl>
	                   <li data="${sl.stageCode}">${sl.title}</li>
	                </#list>
		        </ul>
		        </#if>
	        </div>
	    </div>
        <div style="display: inline-block;margin-left:20px;">
          <span class="date-select" style="width:160px;">
             <img src="${_PATH}/res/images/date_icon.png" class="date-icon" id="startTime_date">
             <input type="text" class="form-control" id="startTime" name="startTime"  readonly  placeholder="开始日期" style="background-color: white;width:160px;"/>
          </span>
          <span>-</span>
          <span class="date-select" style="width:160px;">
             <img src="${_PATH}/res/images/date_icon.png" class="date-icon" id="endTime_date">
             <input type="text" class="form-control" id="endTime" name="endTime"  readonly  placeholder="结束日期" style="background-color: white;width:160px;"/>
          </span>
        </div>

        <div style="margin-top:16px;">
            <input type="text" class="form-control" placeholder="请输入关键字" id="keyword" name="keyword" style="width:200px;display: inline-block;">
            <button type="button" class="search-btn" id="search">
                <img src="${_PATH}/res/images/search_icon.png" class="search-icon">&nbsp;&nbsp;搜索
            </button>
        </div>
        <div id="dataSecondList"></div>
    <form>
</div>
