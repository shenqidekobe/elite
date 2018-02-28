<div id="personActive">
 <form id="searchForm">
    <div class="opt-and-search">
        <div class="add">
            <span class="add-title">我推荐的精英</span>
        </div>
    </div>

    <div class="split-line"></div>

    <div class="opt-and-search" style="margin-top:24px;">
        <div class="search-box table-search">
            <div class="date-select date_start">
                <img src="/res/images/date_icon.png" class="date-icon" id="startTime-date-icon">
                <input type="text" class="form-control" id="startTimeId" name="startTime" readonly  placeholder="选择开始时间" style="background-color: white;"/>
            </div>
            <div class="date_line"></div>
            <div class="date-select date_end">
                <img src="/res/images/date_icon.png" class="date-icon" id="endTime-date-icon">
                <input type="text" class="form-control" id="endTimeId"  name="endTime"  placeholder="选择结束时间" style="background-color: white;"/>
            </div>

            <input class="form-control" type="text" id="searchWordId" name="keyword" placeholder="搜索用户名/姓名/手机" maxlength="20">

            <button type="button" class="search-btn" id="searchBtn" data="personActive">
                <img src="/res/images/search_icon.png" class="search-icon">&nbsp;&nbsp;搜索
            </button>
        </div>
        <div class="active-order">
         	 <ul>
         	 <!--	<li style="text-align:left;" class="active_color">活跃度排名倒序</li>
         	 	<li style="text-align:center;border-left:1px solid #d9d9d9;border-right:1px solid #d9d9d9;">活跃度排正序</li>
         	 	<li style="text-align:right;">按注册日期排名</li>-->
         	 </ul>
        </div>
    </div>

    <#--表格专区-->
    <input type="hidden" id="searchTypeId" name="searchType"/>
    <div class="search-status">
    	<div class="searchTitle">
    		<ul id="selectStatusPane" class="clearfloat">
	            <li data="all" index="0" class="active_bg">全部</li>
	            <li data="last_login_over_7days" index="1" style="border-left:1px solid #e6e6e6;border-right:1px solid #e6e6e6;">近七日未登录</li>
	            <li data="no_task_ever" index="2" style="border-left:1px solid #e6e6e6;border-right:1px solid #e6e6e6;">注册后从未接单</li>
	            <li data="no_recevieandcurrenttask_in_14days" index="3" style="line-height:25px;border-left:1px solid #e6e6e6;border-right:1px solid #e6e6e6;" >近14日未接单操作<br/>且无进行中的项目</li>
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
