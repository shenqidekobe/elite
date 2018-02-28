<div>
    <span style="font-size: 18px;">${obj.name}项目周报汇总</span>
    <span class="report-count">共发送<span class="keyword" id="allCount">${count}份</span>周报
	    <#--其中<span class="keyword">2周</span>未收到周报，
	    ,未读周报<span class="keyword">${unCount}份</span>--></span>
    <span style="float:right;">
        <#--<img src="${_PATH}/res/images/telephone_small_icon.png" width="20" height="21">&nbsp;CTO:${member.nickName}-->
    </span>
</div>
<hr style="margin-top:10px;">
<form id="weeklyListForm">
   <input type="hidden" id="startTime" value="${startTime}">
   <input type="hidden" id="endTime" value="${endTime}">
   <input type="hidden" id="month" name="month" value="${currentMonth}">
   <input type="hidden" id="taskId" name="taskId" value="${taskId}">
   <div>
      <span class="date-select" style="width:160px;">
        <img src="/res/images/date_icon.png" class="date-icon">
        <input type="text" class="form-control" id="monthVal"  readonly  placeholder="起始日期" style="background-color: white;width:160px;"/>
      </span>

        <button type="button" class="search-btn" id="searchForm">
            <img src="/res/images/search_icon.png" class="search-icon">&nbsp;&nbsp;搜索
        </button>
    </div>
   
   <div id="dataSecondList"></div>
</form>