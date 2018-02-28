<div id="channelManage">
 <form id="searchForm">
    <div class="opt-and-search">
        <div class="add">
            <span class="add-title">渠道管理</span>
        </div>
    </div>

    <div class="split-line"></div>

    <div class="opt-and-search">
        <div class="search-box table-search">
            <div class="date-select date_start">
                <img src="/res/images/date_icon.png" class="date-icon" id="startTime-date-icon">
                <input type="text" class="form-control" id="startTimeId" name="startTime" readonly  placeholder="选择开始时间">
            </div>
            <div class="date_line"></div>
            <div class="date-select date_end">
                <img src="/res/images/date_icon.png" class="date-icon" id="endTime-date-icon">
                <input type="text" class="form-control" id="endTimeId"  name="endTime"  placeholder="选择结束时间">
            </div>

            <input class="form-control searchWordId" type="text" id="searchWordId" name="keyword" placeholder="搜索用户名/姓名/手机" maxlength="20">

            <button type="button" class="search-btn searchBtn" id="searchBtn" data="personActive">
                <img src="/res/images/search_icon.png" class="search-icon">&nbsp;&nbsp;搜索
            </button>
        </div>
    </div>

    <#--表格专区-->
    <input type="hidden" id="searchTypeId" name="searchType"/>
    <div class="search-status">
    	<div class="selectTitle">
    		<ul id="selectStatusPane" class="selectUl">
	            <li data="all" index="0" class="active_bg">全部</li>
	            <li data="last_login_over_7days" index="1">近七日未登录</li>
	            <li data="no_task_ever" index="2">注册后从未备案</li>
	            <li data="no_task_over_14days" index="3">近14日无备案操作</li>
	            <li data="no_register" index="4">未注册</li>
	            <div class="ul_bg"></div>
	        </ul>
    	</div>
        <div id="datalist"></div>
    </div>
</form>
</div>

 <!-- 待完善弹窗 -->
    <div class="window"  id="goprefectBoxId" hidden>
    	<div class="window_bg"></div>
    	<form class="windowForm">
    		<div class="close_btn" id="gocloseId"></div>
    		<div class="logoDiv"><span class="logo failure"></span></div>
    		<div class="textDiv failure_text">你的资料完善度还没达到审核要求，先完善哦~</div>
    		<div class="btnDiv"><button type="button" class="saveBtn" id="goPrefectBtn">去完善</button></div>
    	</form>
    </div>
