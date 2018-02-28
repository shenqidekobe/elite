<div>
    <span style="font-size: 18px;">${obj.name}项目周报汇总</span>
    <span class="report-count">共收到<span class="keyword" id="allCount">${count}</span>份周报，
	    <#--其中<span class="keyword">2周</span>未收到周报，-->
	    未读周报<span class="keyword" id="unreadCount">${unCount}</span>份</span>
    <#--<span style="float:right;">
        <img src="${_PATH}/res/images/telephone_small_icon.png" width="20" height="21">&nbsp;项目经理:${obj.bmName}
    </span>-->
</div>
<input type="hidden" id="startTime" value="${startTime}">
<input type="hidden" id="endTime" value="${endTime}">
<hr style="margin-top:10px;">
<form id="weeklyListForm">
   <input type="hidden" id="month" name="month" value="${currentMonth}">
   <input type="hidden" id="projectId" name="projectId" value="${obj.id}">
   <div>
      <span class="date-select" style="width:160px;">
         <img src="${_PATH}/res/images/date_icon.png" class="date-icon">
         <input type="text" class="form-control" id="monthVal"  readonly  placeholder="选择月份" style="background-color: white;width:160px;"/>
      </span>
      <button type="button" class="search-btn" id="searchForm">
          <img src="${_PATH}/res/images/search_icon.png" class="search-icon">&nbsp;&nbsp;搜索
      </button>
   </div>
   <div id="dataSecondList"></div>
</form>